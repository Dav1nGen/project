package cn.edu.nbut.chatroom.filter;

import cn.edu.nbut.chatroom.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class WebSocketFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("WebSocketFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("WebSocketFilter doFilter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = servletRequest.getParameter("token");
        String url = request.getRequestURI();

        // 登录页面，登录请求和注册请求不拦截
        if (url.contains("/login") || url.contains("/user/signUp") || url.contains("/user/signIn") || url.contains("/user/forgetPassword")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 聊天页面，需要验证token
        if (url.contains("/chat")) {
            String[] urlArray = url.split("/");
            String userId = urlArray[urlArray.length - 1];

            if (!compareTokenWithId(token, userId)) {
                response.sendRedirect("/login");
                return;
            }

            log.info("chat connect");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 修改和忘记密码页面，需要验证token
        if (url.contains("/changePassword")) {
            String id = servletRequest.getParameter("id");

            if (!compareTokenWithId(token, id)) {
                response.sendRedirect("/login");
                return;
            }

            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 其他情况直接重定向
        response.sendRedirect("/login");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("WebSocketFilter destroy");
    }

    private boolean compareTokenWithId(String token, String id) {
        if (token == null) {
            return false;
        }

        Claims claims = JwtUtil.parseToken(token);
        if (claims == null) {
            return false;
        }

        if (!claims.get("id").equals(id)) {
            return false;
        }

        return true;
    }
}
