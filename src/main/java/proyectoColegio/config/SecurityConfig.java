package proyectoColegio.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  @EnableMethodSecurity
@EnableWebSecurity  @RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(c -> c.disable())
                .authorizeHttpRequests(auth -> {

                    /*
                    auth.requestMatchers(HttpMethod.POST, "/api/reporte/add").permitAll();
                    auth.requestMatchers(HttpMethod.PUT, "/api/estudiante/updateContactos").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/reporte/add").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/profesor/add").permitAll();
                    auth.anyRequest().authenticated();
                     */
                    auth.anyRequest().permitAll();
                });

        return httpSecurity.build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
