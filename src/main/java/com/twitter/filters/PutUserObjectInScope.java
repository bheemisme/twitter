/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.twitter.filters;


import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author chin
 */
@WebFilter(filterName = "PutUserObjectInSessionScopeFilter", urlPatterns = "/*")
public class PutUserObjectInScope implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {

            Logger.getLogger("Filter").log(Level.INFO, request.getParameter("j_username"));
        }

        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
    
}
