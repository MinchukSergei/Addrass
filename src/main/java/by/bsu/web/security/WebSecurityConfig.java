//package by.bsu.web.security;
//
//import by.bsu.web.service.impl.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Bean
//    public ShaPasswordEncoder getShaPasswordEncoder(){
//        return new ShaPasswordEncoder();
//    }
//
//    @Autowired
//    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(getShaPasswordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/resources/**", "/**").permitAll()
//                .anyRequest().permitAll()
//                .and();
//
//        http.formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/j_spring_security_check")
//                .failureUrl("/login?error")
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
//                .permitAll();
//
//        http.logout()
//                // разрешаем делать логаут всем
//                .permitAll()
//                // указываем URL логаута
//                .logoutUrl("/logout")
//                // указываем URL при удачном логауте
//                .logoutSuccessUrl("/login?logout")
//                // делаем не валидной текущую сессию
//                .invalidateHttpSession(true);
//    }
//}