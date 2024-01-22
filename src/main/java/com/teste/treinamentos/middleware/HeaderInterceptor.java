package com.teste.treinamentos.middleware;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer ".length());

            if (token.equals("token_exercicio_rest")) {
                return true;
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized");
                return false;
            }

        }

        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized");
        return false;
    }
}
