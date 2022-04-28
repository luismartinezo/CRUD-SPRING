/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Security.Jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{

	private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("fail in the Method commence");
		 ((HttpServletResponse) request).sendError(HttpServletResponse.SC_UNAUTHORIZED, "not authorized");
		
	}

}
