package com.example.demo.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyServletOutputStream extends ServletOutputStream{

    private final Logger LOG = LogManager.getLogger(getClass());

    private OutputStream outputStream;
    private ByteArrayOutputStream copy;

    public MyServletOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        this.copy = new ByteArrayOutputStream(1024);
    }

    @Override
    public void write(int b) throws IOException {
        LOG.info("write int");

        outputStream.write(b);
        copy.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        LOG.info("write byte[]");

        outputStream.write(b);
        copy.write(b);
    }

    public byte[] getCopy() {
        return copy.toByteArray();
    }

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWriteListener(WriteListener arg0) {
		// TODO Auto-generated method stub
		
	}
}
