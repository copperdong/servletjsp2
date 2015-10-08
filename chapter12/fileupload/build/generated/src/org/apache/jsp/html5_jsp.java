package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class html5_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<script>\n");
      out.write("    var totalFileLength, totalUploaded, fileCount, filesUploaded;\n");
      out.write("\n");
      out.write("    function debug(s) {\n");
      out.write("        var debug = document.getElementById('debug');\n");
      out.write("        if (debug) {\n");
      out.write("            debug.innerHTML = debug.innerHTML + '<br/>' + s;\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function onUploadComplete(e) {\n");
      out.write("        totalUploaded += document.getElementById('files').\n");
      out.write("                files[filesUploaded].size;\n");
      out.write("        filesUploaded++;\n");
      out.write("        debug('complete ' + filesUploaded + \" of \" + fileCount);\n");
      out.write("        debug('totalUploaded: ' + totalUploaded);        \n");
      out.write("        if (filesUploaded < fileCount) {\n");
      out.write("            uploadNext();\n");
      out.write("        } else {\n");
      out.write("            alert('Finished uploading file(s)');\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("    \n");
      out.write("    function onFileSelect(e) {\n");
      out.write("        var files = e.target.files; // FileList object\n");
      out.write("        var output = [];\n");
      out.write("        fileCount = files.length;\n");
      out.write("        totalFileLength = 0;\n");
      out.write("        for (var i=0; i<fileCount; i++) {\n");
      out.write("            var file = files[i];\n");
      out.write("            output.push(file.name, ' (',\n");
      out.write("                  file.size, ' bytes, ',\n");
      out.write("                  file.lastModifiedDate.toLocaleDateString(), ')'\n");
      out.write("            );\n");
      out.write("            output.push('<br/>');\n");
      out.write("            debug('add ' + file.size);\n");
      out.write("            totalFileLength += file.size;\n");
      out.write("        }\n");
      out.write("        document.getElementById('selectedFiles').innerHTML = \n");
      out.write("            output.join('');\n");
      out.write("        debug('totalFileLength:' + totalFileLength);\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function onUploadProgress(e) {\n");
      out.write("        if (e.lengthComputable) {\n");
      out.write("            var percentComplete = parseInt(\n");
      out.write("                    (e.loaded + totalUploaded) * 100 \n");
      out.write("                    / totalFileLength);\n");
      out.write("            var bar = document.getElementById('bar');\n");
      out.write("            bar.style.width = percentComplete + '%';\n");
      out.write("            bar.innerHTML = percentComplete + ' % complete';\n");
      out.write("        } else {\n");
      out.write("            debug('unable to compute');\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function onUploadFailed(e) {\n");
      out.write("        alert(\"Error uploading file\");\n");
      out.write("    }\n");
      out.write("    \n");
      out.write("    function uploadNext() {\n");
      out.write("        var xhr = new XMLHttpRequest();\n");
      out.write("        var fd = new FormData();\n");
      out.write("        var file = document.getElementById('files').\n");
      out.write("                files[filesUploaded];\n");
      out.write("        fd.append(\"fileToUpload\", file);\n");
      out.write("        xhr.upload.addEventListener(\n");
      out.write("                \"progress\", onUploadProgress, false);\n");
      out.write("        xhr.addEventListener(\"load\", onUploadComplete, false);\n");
      out.write("        xhr.addEventListener(\"error\", onUploadFailed, false);\n");
      out.write("        xhr.open(\"POST\", \"multipleUploads\");\n");
      out.write("        debug('uploading ' + file.name);\n");
      out.write("        xhr.send(fd);\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    function startUpload() {\n");
      out.write("        totalUploaded = filesUploaded = 0;\n");
      out.write("        uploadNext();\n");
      out.write("    }\n");
      out.write("    window.onload = function() {\n");
      out.write("        document.getElementById('files').addEventListener(\n");
      out.write("                'change', onFileSelect, false);\n");
      out.write("        document.getElementById('uploadButton').\n");
      out.write("                addEventListener('click', startUpload, false);\n");
      out.write("    }\n");
      out.write("</script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<h1>Multiple file uploads with progress bar</h1>\n");
      out.write("<div id='progressBar' style='height:20px;border:2px solid green'>\n");
      out.write("    <div id='bar' \n");
      out.write("            style='height:100%;background:#33dd33;width:0%'>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("<form id='form1' action=\"multipleUploads\" \n");
      out.write("        enctype=\"multipart/form-data\" method=\"post\">\n");
      out.write("    <input type=\"file\" id=\"files\" multiple/>\n");
      out.write("    <br/>\n");
      out.write("    <output id=\"selectedFiles\"></output>\n");
      out.write("    <input id=\"uploadButton\" type=\"button\" value=\"Upload\"/>\n");
      out.write("</form>\n");
      out.write("<div id='debug' \n");
      out.write("    style='height:100px;border:2px solid green;overflow:auto'>\n");
      out.write("</div>\n");
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
}
