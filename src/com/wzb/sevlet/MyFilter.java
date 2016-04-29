package com.wzb.sevlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public class MyFilter implements Filter {
    /**
     * 过滤器初始化方法 该过滤器初始化的时候调用
     * @param filterConfig
     * @throws ServletException
     */
    private  int count;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 比TestServlet init方法调用的时机还早啊
        System.out.println("过滤器 init被调用");
        //获取过滤器的名称
        String filterName = filterConfig.getFilterName();//filterName=MyFilter
        System.out.println("filterName="+filterName);//filtername=filtervalue
        filterConfig.getServletContext();//获取servlet的上下文
        //获取初始化参数
        String initname = filterConfig.getInitParameter("initname");
        System.out.println("initname="+initname);//initname=initname
        filterConfig.getInitParameterNames();//获取过滤器所有的初始化参数名称



    }

    /**
     * 对请求资源进行过滤
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        //在这里处理编码
        //过滤数据请求
        HttpServletResponse res=   (HttpServletResponse)servletResponse;
        HttpServletRequest request =(HttpServletRequest)servletRequest;
        ServletContext servletContext = request.getSession().getServletContext();
        count++;
        servletContext.setAttribute("count",count);
        //  res.sendRedirect("http://www.baidu.com");
      filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * 销毁方法 释放资源
     */
    @Override
    public void destroy() {

    }
}
