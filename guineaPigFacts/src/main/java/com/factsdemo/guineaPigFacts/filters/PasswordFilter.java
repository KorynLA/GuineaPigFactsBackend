package com.factsdemo.guineaPigFacts.filters;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public final class PasswordFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        WrappedResponse wrappedResponse = new WrappedResponse((HttpServletResponse)response);

        chain.doFilter(request, wrappedResponse);

        String responseContent = new String(wrappedResponse.getDataStream());
        String filteredResponse = filterPassword(responseContent);
        byte[] responseToSend = restResponseBytes(filteredResponse);

        response.getOutputStream().write(responseToSend);

    }

    @Override
    public void destroy() {
    }

    private String filterPassword(String responseContent) {
        StringBuilder filteredContent = new StringBuilder();
        String [] parseContent = responseContent.split(",");
        for(int i=0; i < parseContent.length; i++) {
            if(!parseContent[i].contains("\"password\"")) {
                if(i < parseContent.length-1) {
                    filteredContent=filteredContent.append(parseContent[i]+",");
                }
                else {
                    filteredContent=filteredContent.append(parseContent[i]);
                }
            }
        }
        return filteredContent.toString();
    }

    private byte[] restResponseBytes(String response) throws IOException {
        return response.getBytes();
    }
}