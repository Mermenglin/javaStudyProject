package com.ThreadCount.demo.logfilter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

public class WrapperResponse extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer = null;
    private ServletOutputStream out = null;
    private PrintWriter writer = null;
    private boolean isPrintWriter = true;

    public WrapperResponse(HttpServletResponse resp) throws IOException {
        super(resp);
        this.buffer = new ByteArrayOutputStream();
        this.writer = new PrintWriter(new OutputStreamWriter(this.buffer, this.getCharacterEncoding()));
    }

    public ServletOutputStream getOutputStream() throws IOException {
        if (this.out == null) {
            this.out = new WapperedOutputStream(super.getOutputStream());
        }

        return this.out;
    }

    public PrintWriter getWriter() throws UnsupportedEncodingException {
        return this.writer;
    }

    public boolean isPrintWriter() {
        return this.isPrintWriter;
    }

    public void reset() {
        this.buffer.reset();
    }

    public byte[] getResponseData() throws IOException {
        return ((WapperedOutputStream)this.getOutputStream()).getOutPut();
    }

    class WapperedOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream bas = null;
        private final ServletOutputStream delegated;

        public WapperedOutputStream(ServletOutputStream sos) throws IOException {
            this.bas = new ByteArrayOutputStream();
            this.delegated = sos;
        }

        public void write(int b) throws IOException {
            WrapperResponse.this.isPrintWriter = false;
            this.bas.write(b);
            this.delegated.write(b);
        }

        public void write(byte[] b) throws IOException {
            WrapperResponse.this.isPrintWriter = false;
            this.bas.write(b, 0, b.length);
            this.delegated.write(b);
        }

        public boolean isReady() {
            return false;
        }

        public void setWriteListener(WriteListener listener) {
        }

        public byte[] getOutPut() {
            return this.bas.toByteArray();
        }
    }
}
