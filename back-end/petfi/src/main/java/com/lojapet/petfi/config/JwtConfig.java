package com.lojapet.petfi.config;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtConfig {
	
	//Parâmetros para geração do token
	
	public static final String SECRET_KEY = "MYSECRETKEYTORANDOMIZEAPIMYSECRETKEYTORANDOMIZEAPIMYSECRETKEYTORANDOMIZEAPI";
	public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
	public static final int HOURS_EXPIRATION_TOKEN = 3;

}
