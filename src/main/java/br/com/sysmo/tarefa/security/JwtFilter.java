package br.com.sysmo.tarefa.security;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@WebFilter("/*") 
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
    private JwtUtil jwtUtil;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
	
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {

        String token = extractToken(request);

        if (token != null && jwtUtil.validateToken(token)) {
            String username = jwtUtil.extractUsername(token);
            logger.debug("Usuário extraído do token: {}", username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }else {
        	logger.warn("Token inválido ou ausente");
        }

        logger.debug("Token JWT: {}", token);
        filterChain.doFilter(request, response);
    }

	private String extractToken(HttpServletRequest request) {
	    String header = request.getHeader("Authorization");
	    if (header == null) {
	    	// Adiciona verificação para o cabeçalho em minúsculas
	        header = request.getHeader("authorization");
	    }
	    
	    // Log para verificar o cabeçalho
	    logger.debug("Header Authorization: {}", header); 
	    if (header != null && header.startsWith("Bearer ")) {
	    	// Verifique o token extraído
	        System.out.println("Token recebido: " + header.substring(7)); 
	        // Retorna o token sem o "Bearer "
	        return header.substring(7);
	    }
	    return null;
	}

}
