package com.factsdemo.guineaPigFacts.generalFilters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter class that removes passwords from responses being sent back to the client
 */
@Component
public final class PasswordFilter implements Filter {
    /**
     * Called before Filter is used & sets the configuration object
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Called with every request/response pair sent due to a client request for a resource
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
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

    /**
     * Called after the filter is done
     */
    @Override
    public void destroy() {
    }

    /**
     * Searches the response that will be sent to client and removes the password if it was found
     * @param responseContent
     * @return String filtered response
     */
    private String filterPassword(String responseContent) throws IOException {
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            JsonNode filterNode = jsonMapper.readTree(responseContent);
            if (filterNode.has("password")) {
                ((ObjectNode) filterNode).remove("password");
            }
            return filterNode.toString();
        }
        catch (Exception e) {
            return responseContent;
        }
    }

    /**
     * Converts String to byte array
     * @param response
     * @return byte array
     * @throws IOException
     */
    private byte[] restResponseBytes(String response) throws IOException {
        return response.getBytes();
    }
}