package com.ThreadCount.demo.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class WrapperRequest extends HttpServletRequestWrapper {
    private byte[] requestBody = null;
    private boolean printBody = true;

    public WrapperRequest(HttpServletRequest request) {
        super(request);

        try {
            InputStream in = request.getInputStream();
            if (in == null) {
                this.requestBody = new byte[0];
            } else {
                ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
                byte[] buffer = new byte[1024];

                int bytesRead;
                while((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                out.flush();
                this.requestBody = out.toByteArray();
            }
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public WrapperRequest(HttpServletRequest request, boolean printBody) {
        super(request);
        this.printBody = printBody;
        if (printBody) {
            try {
                InputStream in = request.getInputStream();
                if (in == null) {
                    this.requestBody = new byte[0];
                } else {
                    ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
                    byte[] buffer = new byte[1024];

                    int bytesRead;
                    while((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }

                    out.flush();
                    this.requestBody = out.toByteArray();
                }
            } catch (IOException var7) {
                var7.printStackTrace();
            }
        }

    }

    public ServletInputStream getInputStream() throws IOException {
        return (ServletInputStream)(this.printBody ? new SmartInputStream(new ByteArrayInputStream(this.requestBody)) : super.getInputStream());
    }

    public byte[] getInput() throws IOException {
        return this.requestBody;
    }

    public boolean isPrintBody() {
        return this.printBody;
    }

    public void setRequestBody(byte[] requestBody) {
        this.printBody = true;
        this.requestBody = requestBody;
    }

    public static class SmartInputStream extends ServletInputStream {
        ByteArrayInputStream bas;

        SmartInputStream(ByteArrayInputStream bas) {
            this.bas = bas;
        }

        public boolean isFinished() {
            return false;
        }

        public boolean isReady() {
            return true;
        }

        public void setReadListener(ReadListener listener) {
        }

        public int read() throws IOException {
            return this.bas.read();
        }
    }
}
