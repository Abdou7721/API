package com.api.ticket.Apiticket.Securité;


import com.api.ticket.Apiticket.service.ServiceUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private ServiceUtilisateur serviceUtilisateur;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registre -> {
                    registre.requestMatchers("/admin/").hasRole("ADMIN");
                    registre.requestMatchers("/formateur/**").hasRole("FORMATEUR");
                    registre.requestMatchers("/apprenant/**").hasRole("APPRENANT");
                    registre.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return serviceUtilisateur;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider Provider = new DaoAuthenticationProvider();
        Provider.setUserDetailsService(userDetailsService());
        Provider.setPasswordEncoder(passwordEncoder());
        return Provider;

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/webjars/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
