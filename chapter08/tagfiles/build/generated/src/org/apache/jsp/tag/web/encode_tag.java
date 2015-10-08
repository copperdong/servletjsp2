package org.apache.jsp.tag.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class encode_tag
    extends javax.servlet.jsp.tagext.SimpleTagSupport
    implements org.apache.jasper.runtime.JspSourceDependent {



    private String encodeHtmlTag(String tag) {
        if (tag==null) {
            return null;
        }
        int length = tag.length();
        StringBuilder encodedTag = new StringBuilder(2 * length);
        for (int i=0; i<length; i++) {
            char c = tag.charAt(i);
            if (c=='<') {
                encodedTag.append("&lt;");
            } else if (c=='>') {
                encodedTag.append("&gt;");
            } else if (c=='&') {
                encodedTag.append("&amp;");
            } else if (c=='"') {
                encodedTag.append("&quot;");  
            } else if (c==' ') {
                encodedTag.append("&nbsp;");
            } else {
                encodedTag.append(c);
            }
        }
        return encodedTag.toString();
    }

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private JspContext jspContext;
  private java.io.Writer _jspx_sout;
  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public void setJspContext(JspContext ctx) {
    super.setJspContext(ctx);
    java.util.ArrayList<String> _jspx_nested = null;
    java.util.ArrayList<String> _jspx_at_begin = null;
    java.util.ArrayList<String> _jspx_at_end = null;
    this.jspContext = new org.apache.jasper.runtime.JspContextWrapper(ctx, _jspx_nested, _jspx_at_begin, _jspx_at_end, null);
  }

  public JspContext getJspContext() {
    return this.jspContext;
  }
  private java.lang.String input;

  public java.lang.String getInput() {
    return this.input;
  }

  public void setInput(java.lang.String input) {
    this.input = input;
  }

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void doTag() throws JspException, java.io.IOException {
    PageContext _jspx_page_context = (PageContext)jspContext;
    HttpServletRequest request = (HttpServletRequest) _jspx_page_context.getRequest();
    HttpServletResponse response = (HttpServletResponse) _jspx_page_context.getResponse();
    HttpSession session = _jspx_page_context.getSession();
    ServletContext application = _jspx_page_context.getServletContext();
    ServletConfig config = _jspx_page_context.getServletConfig();
    JspWriter out = jspContext.getOut();
    if( getInput() != null ) {
      _jspx_page_context.setAttribute("input", getInput());
}

    try {
      out.write('\n');
      out.write('\n');
      out.print(encodeHtmlTag(input));
    } catch( Throwable t ) {
      if( t instanceof SkipPageException )
          throw (SkipPageException) t;
      if( t instanceof java.io.IOException )
          throw (java.io.IOException) t;
      if( t instanceof IllegalStateException )
          throw (IllegalStateException) t;
      if( t instanceof JspException )
          throw (JspException) t;
      throw new JspException(t);
    } finally {
      ((org.apache.jasper.runtime.JspContextWrapper) jspContext).syncEndTagFile();
    }
  }
}
