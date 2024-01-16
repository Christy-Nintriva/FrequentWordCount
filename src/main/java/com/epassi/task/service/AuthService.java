package com.epassi.task.service;

import com.epassi.task.entity.User;
import com.epassi.task.repository.UserRepository;
import com.epassi.task.vo.AuthenticationResponseVo;
import com.epassi.task.vo.LoginVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationResponseVo login(LoginVo userReq) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userReq.getEmail(),
                            userReq.getPassword()                    )
            );
        User user = userRepository.findByEmail(userReq.getEmail())
                .orElseThrow();
        String accessToken = jwtService.generateToken(user);
        return AuthenticationResponseVo.builder()
                .accessToken(accessToken)
                .build();
    }
}
