package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class dataFormatterTagTest_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/WEB-INF/mytags.tld");
  }

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

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Testing DataFormatterTag</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      if (_jspx_meth_easy_dataFormatter_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("<br/>\n");
      if (_jspx_meth_easy_dataFormatter_1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("</body>\n");
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

  private boolean _jspx_meth_easy_dataFormatter_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  easy:dataFormatter
    customtagsdemo.DataFormatterTag _jspx_th_easy_dataFormatter_0 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(customtagsdemo.DataFormatterTag.class) : new customtagsdemo.DataFormatterTag();
    _jspx_th_easy_dataFormatter_0.setJspContext(_jspx_page_context);
    _jspx_th_easy_dataFormatter_0.setHeader("States");
    _jspx_th_easy_dataFormatter_0.setItems("Alabama,Alaska,Georgia,Florida");
    _jspx_th_easy_dataFormatter_0.doTag();
    return false;
  }

  private boolean _jspx_meth_easy_dataFormatter_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  easy:dataFormatter
    customtagsdemo.DataFormatterTag _jspx_th_easy_dataFormatter_1 = (_jspx_resourceInjector != null) ? _jspx_resourceInjector.createTagHandlerInstance(customtagsdemo.DataFormatterTag.class) : new customtagsdemo.DataFormatterTag();
    _jspx_th_easy_dataFormatter_1.setJspContext(_jspx_page_context);
    _jspx_th_easy_dataFormatter_1.setHeader("Countries");
    String _jspx_temp0 = "US,UK,Canada,France";
    _jspx_th_easy_dataFormatter_1.setItems(_jspx_temp0);
    _jspx_th_easy_dataFormatter_1.doTag();
    return false;
  }
}
