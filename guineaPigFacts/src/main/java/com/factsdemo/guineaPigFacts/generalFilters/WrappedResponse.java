package com.factsdemo.guineaPigFacts.generalFilters;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * A wrapper class that uses the FilterServletOutputStream. The response created here will be sent back to the
 * client.
 */
public class WrappedResponse extends HttpServletResponseWrapper {

        ByteArrayOutputStream output;
        FilterServletOutputStream filterOutput;

        /**
         * Constructor that initializes ByteArrayOutputStream and FilterServletOutputStream
         * @param response
         */
        public WrappedResponse(HttpServletResponse response) {
                super(response);
                output = new ByteArrayOutputStream();
        }

        /**
         * Returns the new stream data using the filtered output response
         * @return ServletOutputStream
         * @throws IOException
         */
        @Override
        public ServletOutputStream getOutputStream() throws IOException {
                if (filterOutput == null) {
                        filterOutput = new FilterServletOutputStream(output);
                }
                return filterOutput;
        }

        /**
         * Returns the ByteArrayOutputStream as a Byte Array
         * @return byte array
         */
        public byte[] getDataStream() {
                return output.toByteArray();
        }
}
