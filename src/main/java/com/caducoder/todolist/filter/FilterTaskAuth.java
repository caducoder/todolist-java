package com.caducoder.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.caducoder.todolist.user.IUserRepository;
import com.caducoder.todolist.user.UserModel;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

	@Autowired
	private IUserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var servletPath = request.getServletPath();
		
		if(servletPath.equals("/tasks")) {
			var authorization = request.getHeader("Authorization");
			
			var authEncoded = authorization.substring("Basic".length()).trim();
			
			byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
			String auth = new String(authDecoded);
		
			String[] credentials = auth.split(":");
			String username = credentials[0];
			String passw = credentials[1];
			
			var user = userRepository.findByUsername(username);
			
			if (user.isEmpty()) {
				response.sendError(401);
			} else {
				UserModel usuario = user.get();
				
				var passwVerify = BCrypt.verifyer().verify(passw.toCharArray(), usuario.getPassword());
				
				if(passwVerify.verified) {
					request.setAttribute("idUser", usuario.getId());
					filterChain.doFilter(request, response);				
				} else {
					response.sendError(401);
				}
			}
		} else {
			filterChain.doFilter(request, response);
		}
		
		
		
	}

}
