package com.trouvaille.aladdin.filter;


import com.alibaba.fastjson.JSON;
import com.trouvaille.aladdin.common.BaseContext;
import com.trouvaille.aladdin.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {


    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


//    过滤器具体地处理逻辑如下：
//            1、获取本次请求的URI
//            2、判断本次请求是否需要处理

    public static boolean check(final String[] urls, final String requestURL) {
        for (final String url : urls) {
            final boolean match = LoginCheckFilter.PATH_MATCHER.match(url, requestURL);
            if (match) {
                return true;
            }
        }
        return false;
    }

    //            4、判断登录状态，如果已登录，则直接放行
//            5、如果未登录则返回未登录结果
    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse, final FilterChain filterChain)
    throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final Long employeeId = (Long) request.getSession().getAttribute("employee");
        final Long userId = (Long) request.getSession().getAttribute("user");

        //            1、获取本次请求的URI
        final String requestURL = request.getRequestURI();
        LoginCheckFilter.log.info("拦截到请求链接为" + requestURL);

        //            2、判断本次请求是否需要处理
//        不需要处理的资源路径
        final String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login"
        };

        //            3、如果不需要处理，则直接放行
        final boolean check = LoginCheckFilter.check(urls, requestURL);
        if (check) {
            LoginCheckFilter.log.info("本次{}请求不需要拦截", requestURL);
            filterChain.doFilter(request, response);
            return;
        }

        if (employeeId != null) {
            LoginCheckFilter.log.info("{}已登录", requestURL);

            BaseContext.setCurrentId(employeeId);

            filterChain.doFilter(request, response);
            return;
        }

        if (userId != null) {
            LoginCheckFilter.log.info("{}已登录", requestURL);

            BaseContext.setCurrentId(userId);

            filterChain.doFilter(request, response);
            return;
        }

        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }
}
