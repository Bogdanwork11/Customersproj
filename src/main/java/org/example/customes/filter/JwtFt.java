package org.example.customes.filter;
//работает и перехватывает каждый http запрос и превращает jwt в authentification для SpringSecurity
import io.jsonwebtoken.Claims;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.customes.entity.Users;
import org.example.customes.repository.UsersRp;
import org.example.customes.service.JwtSvc;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.example.customes.role.Role;
import org.example.customes.entity.Users;


import java.util.List;

@Slf4j
@Component
public class JwtFt extends OncePerRequestFilter {
    private final JwtSvc jwtservice;
    private final UsersRp usersRepository;

    public JwtFt(JwtSvc jwtservice, UsersRp usersRepository){
        this.jwtservice = jwtservice;
        this.usersRepository = usersRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info(request.getMethod());
        log.info(request.getRequestURI());
        log.info(request.getHeader("Authorization"));

        System.out.println("Authorization = " + request.getHeader("Authorization"));
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            System.out.println("Jwt был найден в jwtft файле: ");
            String token = authHeader.substring(7);

            System.out.println("Token валидный" + jwtservice.isTokenValid(token));

            if (jwtservice.isTokenValid(token)) {
                Claims claims = jwtservice.extractClaims(token);

                String email = claims.getSubject();

                Users user = usersRepository.findByEmail(email);

                Role role = jwtservice.extractRole(token);
                System.out.println("Role = " + role);



                var authorities = List.of(new SimpleGrantedAuthority("ROLE" + role));
                var authToken = new UsernamePasswordAuthenticationToken(email, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
        filterChain.doFilter(request, response);
    }


}
