package com.ufrn.imd.ponto_certo.filter;

import com.ufrn.imd.ponto_certo.service.JwtService;
import com.ufrn.imd.ponto_certo.util.EndpointChecker;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService IJwtService;
    private final UserDetailsService userDetailsService;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final EndpointChecker endpointChecker;

    public JwtAuthenticationFilter(JwtService IJwtService,
                                   UserDetailsService userDetailsService,
                                   HandlerExceptionResolver handlerExceptionResolver,
                                   EndpointChecker endpointChecker) {
        this.IJwtService = IJwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.endpointChecker = endpointChecker;
    }


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        if (!endpointChecker.isEndpointExist(request)) {
            handlerExceptionResolver.resolveException(
                    request,
                    response,
                    null,
                    new NoResourceFoundException(HttpMethod.valueOf(request.getMethod()), request.getRequestURI())
            );
            return;
        }

        String token = recoverToken(request);

        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }

        try {

            String username = IJwtService.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (IJwtService.isValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );

                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (ExpiredJwtException exception) {
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    public String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

}
