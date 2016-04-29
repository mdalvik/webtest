package com.wzb.sevlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Administrator on 2016/4/28 0028.
 */
public class TestServlet extends HttpServlet {

    /**
     * Servlet 实例化之后,Servlet容器调用该方法来完成初始化工作
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
        /**
         * <init-param>
         *<param-name>x1</param-name>
         *<param-value>111</param-value>
         *</init-param>
         */
        String x1 = config.getInitParameter("x1");//获取 param-name 为 x1的值
        Enumeration<String> initParameterNames = config.getInitParameterNames();//获取初始化名称的参数集合
        while (initParameterNames.hasMoreElements())
        {
            String value = initParameterNames.nextElement();
             System.out.println("value="+value); //value =x1
        }
     /**
      *<servlet-mapping>
      *<servlet-name>demo</servlet-name>
      *<url-pattern>/test</url-pattern>
    *</servlet-mapping>
    */
        String servletName = config.getServletName();//获取servlet实例名称
        System.out.println("servletName="+servletName); //servletName=demo



    }

    /**
     * 处理客户端请求
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        testResponse(resp);

    }

    private void testResponse(HttpServletResponse resp) throws IOException {
        //Response sent 10 bytes of Cookie data:
        //  Set-Cookie: name=value
        resp.addCookie(new Cookie("name","value"));//客户端写入cooike  可以拿fiddler查看
        // resp.sendError(405);//发送一个错误状态码 到客户端
        /**
         * HTTP Status 405 - 这是一个错误信息
         *type Status report
         *message 这是一个错误信息
         *description The specified HTTP method is not allowed for the requested resource.
         *Apache Tomcat/7.0.54
         */
        // resp.sendError(405,"这是一个错误信息");//发送一个错误状态码 到客户端 以及错误信息
        //重定向到百度
        resp.sendRedirect("http://www.baidu.com");
    }

    private void testRequest(HttpServletRequest req) {
        String contextPath = req.getContextPath();//返回请求的上下文路径
        /**
         *    <url-pattern>/test</url-pattern>
         */
        System.out.println("contextPath="+contextPath);//contextPath=/test
        Cookie[] cookies = req.getCookies();//获取客户端请求中所有的cookies 返回一个cookies 数组
        for(int i=0;i<cookies.length;i++)
        {
            System.out.println("cookie="+ cookies[i].getName());

        }
        String method = req.getMethod();//获取请求方式 post  get  等
        System.out.println("请求类型="+method);

        //浏览器输入http://localhost:8080/test/test?que=11
        String queryString = req.getQueryString();//返回请求参数字符串形式 如Myservlet?aaa=111 则返回 aaa=111
        System.out.println("queryString="+queryString);//que=11
        String requestURI = req.getRequestURI();//返回主机名到请求参数之间的字符串形式
        System.out.println("requestURI="+requestURI);//requestURI=/test/test
        //http://localhost:8080/test/test?parament=1
        StringBuffer requestURL = req.getRequestURL();//获取请求的url 不包含请求参数
        System.out.println("requestURL="+requestURL);//http://localhost:8080/test/test
        String servletPath = req.getServletPath();//返回请求中uri的servlet路径 不包括请求参数
        System.out.println("servletPath="+servletPath);//servletPath=/test
        HttpSession session = req.getSession();//返回请求关联的session对象
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     * Servlet对象从Servlet容器移除时调用该方法 释放资源
     */
    @Override
    public void destroy() {
        super.destroy();
    }

    /**
     * 获取Servlet对象配置信息 返回ServletConfig对象
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return super.getServletConfig();
    }

    /**
     * 返回Servlet信息 存文本信息 如作者 版本
     * @return
     */
    @Override
    public String getServletInfo() {
        return super.getServletInfo();
    }
}
