package com.factsdemo.guineaPigFacts.filters;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * A class that allows the servlet output stream to write out data even after the
 * servlet output stream has been closed / the data has been committed
 */
public class FilterServletOutputStream extends ServletOutputStream {

    DataOutputStream output;

    /**
     * Constructor that initializes the DataOutputStream variable
     * @param output
     */
    public FilterServletOutputStream(OutputStream output) {
        this.output = new DataOutputStream(output);
    }

    /**
     * Method that writes the byte to the output stream
     * @param arg0
     * @throws IOException
     */
    @Override
    public void write(int arg0) throws IOException {
        output.write(arg0);
    }

    /**
     * Method that writes byte array from starting offset (arg1) until the specified length has been reached
     * (arg2)
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws IOException
     */
    @Override
    public void write(byte[] arg0, int arg1, int arg2) throws IOException {
        output.write(arg0, arg1, arg2);
    }

    /**
     * Method that writes the byte array to the output stream
     * @param arg0
     * @throws IOException
     */
    @Override
    public void write(byte[] arg0) throws IOException {
        output.write(arg0);
    }

    /**
     * Checks the nonblocking write will succeed
     * @return boolean
     */
    @Override
    public boolean isReady() {
        return false;
    }

    /**
     * Sets writeListener and allows non-blocking IO to servlet
     * @param writeListener
     */
    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
