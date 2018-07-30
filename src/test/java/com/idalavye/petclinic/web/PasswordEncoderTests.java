package com.idalavye.petclinic.web;


import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTests {

    @Test
    public void generateEncodedPassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("{bcrypt}" + passwordEncoder.encode("secret"));
        System.out.println("{bcrypt}" + passwordEncoder.encode("secret"));
        System.out.println("{bcrypt}" + passwordEncoder.encode("secret"));
        System.out.println(passwordEncoder.encode("mustafa"));
        System.out.println(passwordEncoder.encode("mu stafa iyibirins andeg ildirco kyavsa ktiredsfa sdfasdf sdfsdfs daf asdf sdf sadfasd fsadf sdfsdf asfd"));
    }
}
