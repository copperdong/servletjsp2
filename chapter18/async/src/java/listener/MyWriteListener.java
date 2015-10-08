package listener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class MyWriteListener implements WriteListener {
    ServletOutputStream servletOutputStream;
    AsyncContext asyncContext;
    FileInputStream fileInputStream;
    BufferedInputStream bufferedInputStream;
    
    public MyWriteListener(File file, 
            ServletOutputStream servletOutputStream,
            AsyncContext asyncContext) {
        this.servletOutputStream = servletOutputStream;
        this.asyncContext = asyncContext;
        try {
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = 
                    new BufferedInputStream(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void onWritePossible() throws IOException {
        System.out.println("onWritePossible");
        while (servletOutputStream.isReady()) {
            byte[] buffer = new byte[1024];
            int bytesRead = bufferedInputStream.read(buffer);
            if (bytesRead != -1) {
                servletOutputStream.write(buffer, 0, bytesRead);
            } else {
                // finished reading file
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                asyncContext.complete();
                break;
            }
        }
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("onError");
    }
}
