package com.agelgil.agelgil.lib.controllers.auth.handlers;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.agelgil.agelgil.lib.data.models.auth.User.Role;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
	
public class AgelgilAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private final Map<String, String> destinationsMap = new HashMap<String, String>() {{
		put("ROLE_"+Role.ADMIN.name(), "/admin/dashboard/hotels");
		put("ROLE_"+Role.CLIENT.name(), "/");
		put("ROLE_"+Role.HOTEL.name(), "/hotel/dashboard/services");
	}};

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
			String targetUrl = determineTargetUrl(authentication);
			if(!response.isCommitted())
				redirectStrategy.sendRedirect(request, response, targetUrl);
			
			clearAuthenticationAttributes(request);
		
	}

	private String determineTargetUrl(final Authentication authentication){
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		if(authorities.size() != 1)
			throw new IllegalStateException();
		
		GrantedAuthority authority = authorities.iterator().next();
		String targetUrl = destinationsMap.get(authority.getAuthority());
		if(targetUrl == null)
			throw new IllegalStateException();
		return targetUrl;
		
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}


	
	
}
