package com.interview.ExpenseManagerAPI.config;

import com.interview.ExpenseManagerAPI.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration

@RequiredArgsConstructor
public class WebSecurityFilter {

       public final PasswordEncoder passwordEncoder;
       public final JwtFilter jwtFilter;


//    @Bean
    public UserDetailsService userDetails(){
        UserDetails user_admin = User.withUsername("admin")
                .password(passwordEncoder.encode("password"))
                .roles("ADMIN")
                .build();

        UserDetails user_normal = User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();


        return new InMemoryUserDetailsManager(user_admin, user_normal);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers("/Expencse/getExpenseById/**" , "/h2-console/**", "/Auth/login", "/Auth/auth").permitAll()
//                .requestMatchers("/Expencse/getExpenseByMonthWithYear").hasRole("ADMIN")
//                .requestMatchers( "/Expencse/add").hasRole("USER") applicabel Without JWT & unable the
                .anyRequest().authenticated()) // All other routes must be logged in
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/Expencse/add", "/Auth/login", "/Auth/auth")) // allow h2 and POSTs
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // required for H2 to work
                .formLogin(Customizer.withDefaults()) // <-- enables default login form
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtFilter , UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }






}
