package com.langjialing.helloworld.service;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 郎家岭伯爵
 */
@Component
public class FilterService implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String ip = request.getRemoteHost();
        List<String> ipList = new ArrayList<>();
        ipList.add("0:0:0:0:0:0:0:1");
        if (!ipList.contains(ip)){
            System.out.println("非法IP");
            return;
        }

        System.out.println("Remote Host: " + request.getRemoteHost());
        System.out.println("Remote Address: " + request.getRemoteAddr());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
