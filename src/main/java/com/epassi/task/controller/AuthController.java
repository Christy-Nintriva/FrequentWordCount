package com.epassi.task.controller;

import com.epassi.task.service.AuthService;
import com.epassi.task.vo.AuthenticationResponseVo;
import com.epassi.task.vo.LoginVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/auth")
@Log4j2
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseVo> login(@RequestBody LoginVo loginReq){
        return ResponseEntity.ok(authService.login(loginReq));
    }
}
