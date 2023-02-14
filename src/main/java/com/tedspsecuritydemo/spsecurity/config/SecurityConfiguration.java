//package com.tedspsecuritydemo.spsecurity.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class SecurityConfiguration  {
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
////        http
////                .authorizeHttpRequests(auth -> auth
////                        .antMatchers("/h2/**").permitAll()
////                        .antMatchers("/admin").hasRole("ADMIN")
////                        .antMatchers("/user").hasAnyRole("ADMIN", "USER")
////                        .antMatchers("/login").permitAll()
////                        .antMatchers("/logout").permitAll()
////                        .antMatchers("/").permitAll()
////                )
////                .headers(headers -> headers.frameOptions().disable())
////                .csrf(csrf -> csrf
////                        .ignoringAntMatchers("/h2/**"));
////        return http.build();
//
////    }
//
//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//                .build();
//    }
//
//    @Bean
//    public UserDetailsManager users(DataSource dataSource){
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("joe")
//                .password("joa")
//                .roles("USER")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        return users;
//    }
//}
