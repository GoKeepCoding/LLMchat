package com.jnu.myllm.interceptor;

import com.jnu.myllm.annotation.JwtAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final String SECRET_KEY = "your-secret-key";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        log.info("开始token验证:");

        if (method.isAnnotationPresent(JwtAuth.class)) {
            String token = request.getHeader("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {
                log.info("token为空或者不合法------------------------------------------");
                //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"code\": 401, \"msg\": \"UNAUTHORIZED\"}");
                return false;
            }

            token = token.substring(7);
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();
                request.setAttribute("claims", claims);
            } catch (Exception e) {
                log.info("token被篡改------------------------------------------");
                //response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"code\": 401, \"msg\": \"UNAUTHORIZED\"}");
                return false;
            }
        }
        log.info("token验证通过------------------------------------------");
        return true;
    }
}
