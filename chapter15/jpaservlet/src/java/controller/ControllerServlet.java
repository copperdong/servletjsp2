package controller;

import action.DeleteBookAction;
import action.EditBookAction;
import action.ListBooksAction;
import action.SaveBookAction;
import action.UpdateBookAction;
import entity.Book;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControllerServlet", urlPatterns = {
        "/list-books", "/add-book", "/save-book",
        "/edit-book", "/update-book", "/delete-book"})
public class ControllerServlet extends HttpServlet {
    EntityManagerFactory emf;
    
    @Override
    public void init(ServletConfig servletConfig) {
        Map<String, String> properties = new HashMap<>();
        // change jdbc.url, otherwise the db will be created related to
        // Tomcat's bin directory.
        String property = "javax.persistence.jdbc.url";
        String realPath = servletConfig.getServletContext().getRealPath("/");
        String dbLocation = "jdbc:derby:" + realPath + "WEB-INF/db/jpaservletdb;create=true";
        System.out.println("dbLocation:"  + dbLocation);
        properties.put(property, dbLocation);
        emf = Persistence.createEntityManagerFactory("jpaservletPU", properties);
    } 

    @Override
    public void destroy() {
        if (emf.isOpen()) {
            emf.close();
        }
    } 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/css/")) {
            return;
        }
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        String dispatchUrl = null;
        String bookId = null;
        BigDecimal price = null;
        Book book = null;
        boolean redirect = false;
        switch (action) {
            case "add-book":
                dispatchUrl = "/WEB-INF/jsp/AddBookForm.jsp";
                break;
            case "save-book":
                book = new Book();
                book.setIsbn(request.getParameter("isbn"));
                book.setAuthor(request.getParameter("author"));
                book.setTitle(request.getParameter("title"));
                price = new BigDecimal(request.getParameter("price"));
                book.setPrice(price);
                SaveBookAction saveBookAction = new SaveBookAction(emf);
                saveBookAction.saveBook(book);
                redirect = true;
                break;
            case "edit-book":
                bookId = request.getParameter("id");
                EditBookAction editBookAction = new EditBookAction(emf);
                book = editBookAction.getBook(Long.parseLong(bookId));
                request.setAttribute("book", book);
                dispatchUrl = "/WEB-INF/jsp/EditBookForm.jsp";
                break;
            case "update-book":
                book = new Book();
                book.setId(Long.parseLong(request.getParameter("id")));
                book.setIsbn(request.getParameter("isbn"));
                book.setAuthor(request.getParameter("author"));
                book.setTitle(request.getParameter("title"));
                price = new BigDecimal(request.getParameter("price"));
                book.setPrice(price);
                UpdateBookAction updateBookAction = new UpdateBookAction(emf);
                updateBookAction.updateBook(book);
                redirect = true;
                break;
            case "delete-book":
                bookId = request.getParameter("id");
                DeleteBookAction deleteBookAction = new DeleteBookAction(emf);
                deleteBookAction.deleteBook(Long.parseLong(bookId));
                redirect = true;
                break;
            default:
                dispatchUrl = "/WEB-INF/jsp/BookList.jsp";
                ListBooksAction listBookAction = new ListBooksAction(emf);
                List<Book> books = listBookAction.getBooks();
                request.setAttribute("books", books);
                break;
        }
        if (redirect) {
            response.sendRedirect("list-books");
        } else {
            RequestDispatcher rd =
                    request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Controller Servlet";
    }

}
