package org.example.customes.handler;
//handler обрабатывает успещный вход через гугл oauth2 и после него выдает jwt
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.example.customes.entity.Users;
import org.example.customes.repository.UsersRp;
import org.example.customes.service.JwtSvc;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.example.customes.entity.Users;
import org.springframework.stereotype.Service;


@Component
public class Oauth2hd  implements AuthenticationSuccessHandler {

    private final UsersRp userRepository;
    private final JwtSvc jwrService;

    public Oauth2hd(
            UsersRp userRepository,
            JwtSvc jwrService
    ){
        this.userRepository = userRepository;
        this.jwrService = jwrService;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        OAuth2AuthenticationToken oauthtoken =
                (OAuth2AuthenticationToken) authentication;

        OAuth2User oAuth2User = oauthtoken.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        Users user = userRepository.findByEmail(email);

        if (user == null) {
            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED, //типа ошибки 401
                        "Пользователя нет в локальной бдшке"

            );
            return;
        }
        String jwt = jwrService.generationToken(
                user.getEmail(),
                user.getRole()
        );
        response.setContentType("application/json");
        response.getWriter().write(
                """
                        {
                            "message": "Авторизация успешна",
                            "email": "%s",
                            "role": "%s",
                            "token": "%s"
                        }
                        """.formatted(
                        user.getEmail(),
                        user.getRole(),
                        jwt
                )
        );
    }
}
