package com.jeronima.helpdesk.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    private final Environment env;

    public WebSecurityConfig(Environment env, JwtAuthenticationFilter jwtAuthenticationFilter,
                             AuthenticationProvider authenticationProvider){
        //Environment é para verificar se está no perfil de teste
        this.env = env;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()     //para protejer contra ataque de armazenamento de sessões de usuário,
                .disable() //mas essa aplicação não armazena sessão, então é desnecessário.
                .authorizeHttpRequests()
                    .antMatchers("/h2-console/**").permitAll()
                    .antMatchers("/autenticacao/**").permitAll()
                .anyRequest().authenticated()
                .and().cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //não armazenar estado/sessão
                .and().authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        if(Arrays.asList(env.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable();
            //se estiver no perfil de teste irá permitir acessar o conteúdo da página do h2-console
            //desabilitando frameOptions, q é uma medida de segurança q serve p/ indicar  ao browser se
            //seu site pode aparecer dentro de um <iframe> de outro site, se pode renderizar uma página
        }

        return http.build();
    }

}
