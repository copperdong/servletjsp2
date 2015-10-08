package listener;

import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

public class MyReadListener implements ReadListener {

    private ServletInputStream servletInputStream;
    private AsyncContext asyncContext;
    StringBuilder sb = new StringBuilder(1000000);

    public MyReadListener(ServletInputStream servletInputStream, 
            AsyncContext asyncContext) {
        this.servletInputStream = servletInputStream;
        this.asyncContext = asyncContext;
    }

    public void onDataAvailable() throws IOException {
        System.out.println("onDataAvailable");
        byte buffer[] = new byte[1024];
        int bytesRead;
        while (servletInputStream.isReady() 
                && (bytesRead = servletInputStream.read(buffer)) != -1) {
            String data = new String(buffer, 0, bytesRead);
            sb.append(data);
        }
    }

    public void onAllDataRead() throws IOException {
        System.out.println("onAllDataRead");
        asyncContext.getRequest().setAttribute("result", sb.toString());
        asyncContext.complete();
    }

    public void onError(Throwable throwable) {
        System.out.println("onError");
        asyncContext.getRequest().setAttribute("error", 
                throwable.getMessage());
        asyncContext.complete();
    }
}