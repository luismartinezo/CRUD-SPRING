/**
 * copyright 2022 All right reserved
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */
package com.covinoc.crud.Security.Jwt;

import java.util.Date;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.covinoc.crud.Security.Entity.UserMain;

import io.jsonwebtoken.*;

/**
 * @author LUIS MARTINEZ
 * @date 27 abr 2022
 */

@Component
public class JwtProvider {

	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private int expiration;

	public String generateToken(Authentication authentication) {
		UserMain usuarioPrincipal = (UserMain) authentication.getPrincipal();
		return Jwts.builder().setSubject(usuarioPrincipal.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String getUserNameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("malformed token");
		} catch (UnsupportedJwtException e) {
			logger.error("token not supported");
		} catch (ExpiredJwtException e) {
			logger.error("Expired token");
		} catch (IllegalArgumentException e) {
			logger.error("empty token ");
		} catch (SignatureException e) {
			logger.error("signature failure");
		}
		return false;
	}
}
