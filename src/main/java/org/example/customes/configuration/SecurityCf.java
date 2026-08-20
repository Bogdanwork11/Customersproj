package org.example.customes.configuration;

import org.example.customes.filter.JwtFt;
import org.example.customes.handler.Oauth2hd;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityCf {

    private final Oauth2hd oauth2hd;

    public SecurityCf(Oauth2hd oauth2hd) {
        this.oauth2hd = oauth2hd;
    }

    //-----цепочка фильтров доступа-----
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFt jwtFilter) throws Exception {
        http

                .csrf(csrf -> csrf.disable()
                )
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )
                .authorizeHttpRequests(auth -> auth
                        //*****create*****
                        .requestMatchers(HttpMethod.POST,"/customers/create")
                        .hasAnyRole("DEVELOPER", "DEVOPS")
                        //*****get*****
                        .requestMatchers(HttpMethod.GET, "/customers/**")
                        .hasAnyRole("DEVELOPER", "DEVOPS", "HR", "USER", "RECRUITER", "TEAMLEAD")



                        .requestMatchers("/info").permitAll() //разраешает доступ без аунтефикации осталбное с аутентификацией
                        .requestMatchers("/photo/**").permitAll()
                        .requestMatchers("/oauth2/**").permitAll()
                        .anyRequest().authenticated()


                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth -> oauth
                        .successHandler(oauth2hd)
                );


        return http.build();
    }

}

