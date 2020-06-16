package com.sistema.application.funciones;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Passgenerator {
	public static void main(String ...args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
		
		//El String que mandamos al metodo encode es el password que queremos encriptar.
		System.out.println(bCryptPasswordEncoder.encode("40000001"));
	}
}
