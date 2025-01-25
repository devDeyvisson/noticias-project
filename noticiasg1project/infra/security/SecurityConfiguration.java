package br.com.ifs.noticiasg1project.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Indica que EU IREI CONFIGURAR a segurança a partir dessa classe.
                   // Não terá mais a config padrão do spring security!
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "usuarios/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "usuarios/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "usuarios/listar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "usuarios/listar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "usuarios/inativar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "usuarios/reativar/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "usuarios/atualizar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "usuarios/remover/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
                //o securityFilter chama o método doFilterInternal, ou seja, é ele que será feito antes de qualquer outro filtro.
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}