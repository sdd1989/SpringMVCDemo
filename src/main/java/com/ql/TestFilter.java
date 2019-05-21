package com.ql;


import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TestFilter implements Filter {


    private static final String CONTENT_TYPE_JSON = "application/json";

    private static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";

    private static Logger logger = LoggerFactory.getLogger(TestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequestWrapper requestWrapper = new MultiReadHttpServletRequest((HttpServletRequest)servletRequest);
        if(CONTENT_TYPE_FORM.equals(requestWrapper.getContentType())){
            int contentLen = requestWrapper.getContentLength();
            logger.info("=====contentLen:{}",contentLen);
            if(contentLen>0) {
                //TODO:解决后面读getparamter问题，读取流放到getparamter
                String body = IOUtils.toString(requestWrapper.getReader());
                logger.info("====filter=====request body:{}=====", body);
            }else{
                //后面不可能读流，不需要解决读流的问题
                String id = requestWrapper.getParameter("id");
                String name = requestWrapper.getParameter("name");
                logger.info("===filter======id:{},name:{}",id,name);
            }
        }else if(CONTENT_TYPE_JSON.equals(requestWrapper.getContentType())){
            String body = IOUtils.toString(requestWrapper.getReader());
            logger.info("====filter=====request body:{}=====", body);
        }
        filterChain.doFilter(requestWrapper,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
