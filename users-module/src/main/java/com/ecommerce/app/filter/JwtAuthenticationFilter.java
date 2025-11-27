package com.ecommerce.app.filter;

import com.ecommerce.app.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {

        //Get Token from header of HttpServletRequest request

        final String authHeader = req.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer")){
            // return user request with authenticating
            filterChain.doFilter(req,res);
            return;
        }

        final String jwtToken = authHeader.substring(7).trim();
        final String username = jwtService.extractUsername(jwtToken);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(username != null && authentication==null){

            //Authentication Begins
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //check if the Token is valid
            if (jwtService.isTokenValid(jwtToken,userDetails)){

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

        }

        filterChain.doFilter(req,res);
    }
}
