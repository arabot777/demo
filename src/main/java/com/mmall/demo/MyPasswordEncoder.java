package com.mmall.demo;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
public class MyPasswordEncoder implements PasswordEncoder{
    private  final  static  String SALT = "123456";
    @Override
    public String encode(CharSequence rawPassword) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(rawPassword.toString(),SALT);
    }
@Override
    public boolean matches(CharSequence rawPassword, String encoderPassword) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        //加密之后密码，原来密码
        return encoder.isPasswordValid(encoderPassword,rawPassword.toString(),SALT);
    }
}
