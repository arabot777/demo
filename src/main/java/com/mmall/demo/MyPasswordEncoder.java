package com.mmall.demo;

import org.springframework.security.crypto.password.PasswordEncoder;
public class MyPasswordEncoder implements PasswordEncoder{
    @Override
    public String encode(CharSequence charSequence) {
        Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
        return null;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
