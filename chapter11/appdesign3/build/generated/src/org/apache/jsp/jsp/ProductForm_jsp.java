package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ProductForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<title>Add Product Form</title>\r\n");
      out.write("<style type=\"text/css\">@import url(css/main.css);</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<form method=\"post\" action=\"save-product\">\r\n");
      out.write("    <h1>Add Product \r\n");
      out.write("        <span>Please use this form to enter product details</span>\r\n");
      out.write("    </h1>\r\n");
      out.write("    <p style=\"color:red\">\r\n");
      out.write("    Error(s)!\r\n");
      out.write("    <ul>\r\n");
      out.write("        <!--");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.errors.stream().map(\r\n              x -> \"--><li>\"+=x+=\"</li><!--\").toList()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("-->\r\n");
      out.write("    </ul>\r\n");
      out.write("    </p>\r\n");
      out.write("    <label>\r\n");
      out.write("        <span>Product Name :</span>\r\n");
      out.write("        <input id=\"name\" type=\"text\" name=\"name\" \r\n");
      out.write("            placeholder=\"The complete product name\"/>\r\n");
      out.write("    </label>\r\n");
      out.write("    <label>\r\n");
      out.write("        <span>Description :</span>\r\n");
      out.write("        <input id=\"description\" type=\"text\" name=\"description\" \r\n");
      out.write("            placeholder=\"Product description\"/>\r\n");
      out.write("    </label>\r\n");
      out.write("    <label>\r\n");
      out.write("        <span>Price :</span>\r\n");
      out.write("        <input id=\"price\" name=\"price\" type=\"number\" step=\"any\"\r\n");
      out.write("            placeholder=\"Product price in #.## format\"/>\r\n");
      out.write("    </label> \r\n");
      out.write("    <label>\r\n");
      out.write("        <span>&nbsp;</span> \r\n");
      out.write("        <input type=\"submit\"/> \r\n");
      out.write("    </label> \r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
