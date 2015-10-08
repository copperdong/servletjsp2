package controller;

import action.FetchEmailAction;
import action.GetAttachmentAction;
import action.GetMessageAction;
import action.SendEmailAction;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.FetchResponse;
import util.Constants;

/**
 *
 * @author budi2020
 */
@WebServlet(name = "ControllerServlet",
        urlPatterns = {"/login", "/main", "/message",
            "/compose", "/send", "/attachment"})
public class ControllerServlet extends HttpServlet {

    private String smtpHost;
    private int smtpPort;
    private String mailStoreProtocol;
    private String mailStoreHost;

    @Override
    public void init(ServletConfig servletConfig)
            throws ServletException {
        ServletContext servletContext
                = servletConfig.getServletContext();
        String configFilePath = servletContext
                .getRealPath("/WEB-INF/config.properties");
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(configFilePath)) {
            props.load(in);
        } catch (IOException e) {

        }
        smtpHost = props.getProperty("smtp.host");
        try {
            smtpPort = Integer.parseInt(
                    props.getProperty("smtp.port"));
        } catch (NumberFormatException e) {
        }
        mailStoreHost = props.getProperty(
                "mail.store.host");
        mailStoreProtocol = props.getProperty(
                "mail.store.protocol");
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/css/")) {
            return;
        }
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        String dispatchUrl = null;
        boolean redirect = false;
        String folder = request.getParameter("folder");
        if (folder == null || folder.isEmpty()) {
            folder = "INBOX";
        }
        HttpSession session = request.getSession();
        switch (action) {
            case "":
                dispatchUrl = "/WEB-INF/jsp/Login.jsp";
                break;
            case "login":
                String userName = request.getParameter("userName");
                String password = request.getParameter("password");
                session.setAttribute("userName", userName);
                session.setAttribute("password", password);
            // no break here so "main" will be called
            case "main":
                request.setAttribute("folder", folder);
                userName = (String) session.getAttribute("userName");
                password = (String) session.getAttribute("password");
                FetchEmailAction fetchMailAction = new FetchEmailAction();
                String pageString = request.getParameter("page");
                int page = 1;
                if (pageString != null && !pageString.isEmpty()) {
                    try {
                        page = Integer.parseInt(pageString);
                    } catch (Exception e) {
                    }
                }
                try {
                    FetchResponse fetchResponse = fetchMailAction
                            .fetchEmails(mailStoreProtocol,
                                    mailStoreHost, userName,
                                    password, folder, page);
                    request.setAttribute("page", page);
                    long messageCount = fetchResponse.getMessageCount();
                    int maxPage = (int) Math.ceil(0.5 + messageCount
                            / Constants.ROWS_PER_PAGE);
                    request.setAttribute("rowsPerPage",
                            Constants.ROWS_PER_PAGE);
                    request.setAttribute("maxPage", maxPage);

                    request.setAttribute("messageCount", messageCount);
                    request.setAttribute("messages", fetchResponse.
                            getMessageBeans());
                    request.setAttribute("folders", fetchResponse.
                            getFolders());
                    dispatchUrl = "/WEB-INF/jsp/Main.jsp";
                } catch (Exception e) {
                    System.out.println("error:" + e.getMessage());
                    dispatchUrl = "/WEB-INF/jsp/Login.jsp";
                }
                break;
            case "message":
                userName = (String) session.getAttribute("userName");
                password = (String) session.getAttribute("password");
                request.setAttribute("folder", folder);
                String idString = request.getParameter("id");
                int id = 0;
                try {
                    id = Integer.parseInt(idString);
                } catch (NumberFormatException e) {

                }
                GetMessageAction getMessageAction = new GetMessageAction();
                try {
                    FetchResponse fetchResponse = getMessageAction.
                            getMessage(
                                    mailStoreProtocol, mailStoreHost,
                                    userName, password, folder, id);
                    request.setAttribute("messageCount", fetchResponse.
                            getMessageCount());
                    request.setAttribute("message", fetchResponse.
                            getMessageBean());
                    request.setAttribute("folders", fetchResponse.
                            getFolders());
                    dispatchUrl = "/WEB-INF/jsp/Message.jsp";
                } catch (Exception e) {
                    dispatchUrl = "/WEB-INF/jsp/Login.jsp";
                }
                break;
            case "compose":
                dispatchUrl = "/WEB-INF/jsp/Compose.jsp";
                break;
            case "send":
                SendEmailAction sendEmailAction = new SendEmailAction();
                String toEmail = request.getParameter("to");
                String subject = request.getParameter("subject");
                String text = request.getParameter("text");
                userName = (String) session.getAttribute("userName");
                password = (String) session.getAttribute("password");
                try {
                    sendEmailAction.sendEmail(smtpHost, smtpPort,
                            userName, password, toEmail, subject, text);
                    redirect = true;
                } catch (Exception e) {
                    request.setAttribute("error", e.getMessage());
                    dispatchUrl = "/WEB-INF/jsp/Error.jsp";
                }
                break;
            case "attachment":
                userName = (String) session.getAttribute("userName");
                password = (String) session.getAttribute("password");
                String messageIdString = request.getParameter("mid");
                int messageId = -1;
                try {
                    messageId = Integer.parseInt(messageIdString);
                } catch (Exception e) {
                }
                String attachmentFileName = request.getParameter("file");
                GetAttachmentAction getAttachmentAction = new GetAttachmentAction();
                try (InputStream inputStream = getAttachmentAction
                        .getAttachment(mailStoreProtocol,
                                mailStoreHost, userName,
                                password, folder, messageId,
                                attachmentFileName);
                        BufferedInputStream bis = new BufferedInputStream(
                                inputStream);
                        OutputStream os = response.getOutputStream()) {
                    response.setContentType("APPLICATION/OCTET-STREAM");
                    response.addHeader("Content-Disposition",
                            "attachment; filename=" + attachmentFileName);
                    byte[] buffer = new byte[1024];
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
//                break;
            default:
                dispatchUrl = "/WEB-INF/jsp/Error.jsp";
                break;
        }
        if (redirect) {
            response.sendRedirect("main");
        } else {
            RequestDispatcher rd
                    = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
