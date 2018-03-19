package com.mmall.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SrpingSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private MyUserService myUserService;

    /**
     * 基于内存的验证
     * 如果对权限进行控制，
     * 请在对应入口处添加 PreAuthorize("hasRole(ROLE_admin)")注解,需要EnableGlobalMethodSecurity(prePostEnabled = true)启动
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new MyPasswordEncoder())//在此处应用自定义PasswordEncoder
//                .withUser("admin").password("123456").roles("admin");
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new MyPasswordEncoder())//在此处应用自定义PasswordEncoder
//                .withUser("zhangsan").password("zhangsan").roles("admin");
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new MyPasswordEncoder())//在此处应用自定义PasswordEncoder
//                .withUser("demo").password("demo").roles("user");
        auth.userDetailsService(myUserService);
    }
    public class MyPasswordEncoder implements PasswordEncoder {

        @Override
        public String encode(CharSequence arg0) {
            return arg0.toString();
        }

        @Override
        public boolean matches(CharSequence arg0, String arg1) {
            return arg1.equals(arg0.toString());
        }

    }
    /**
     * 拦截请求
     * 允许 /  访问
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .formLogin();
        http.csrf().disable();
    }

    /**
     * 不对这些文件进行拦截
     */
    @Override
    public void configure(WebSecurity web) throws  Exception{
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }

}
