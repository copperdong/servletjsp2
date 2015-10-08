package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE HTML>\n");
      out.write("<html>\n");
      out.write("   <head>\n");
      out.write("       <title>Important page</title>\n");
      out.write("   </head>\n");
      out.write("   <body>\n");
      out.write("       <p>This page contains confidential information that must be erased when\n");
      out.write("           the user session on the server expires</p>\n");
      out.write("   \n");
      out.write("       <div style=\"height:50px;background:yellow\" id=\"debug\"></div>\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            var debug = function(s) {\n");
      out.write("                document.getElementById(\"debug\").innerHTML += \"<br>\" + s;\n");
      out.write("            }\n");
      out.write("            if (\"WebSocket\" in window) {\n");
      out.write("                var uri = \"ws://\" + document.location.host \n");
      out.write("                        + document.location.host + \"config\";\n");
      out.write("                debug(\"uri:\"  + uri)\n");
      out.write("                var ws = new WebSocket(uri);\n");
      out.write("                ws.onmessage = function (event) {\n");
      out.write("                    if (event.data == \"expired\") {\n");
      out.write("                        document.body.innerHTML = \"Your HttpSession has expired\";\n");
      out.write("                    }\n");
      out.write("                };\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("\n");
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
