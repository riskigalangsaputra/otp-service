package id.kingra.otp_service.controller;

import id.kingra.otp_service.dto.OtpReqDto;
import id.kingra.otp_service.service.OTPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController @RequiredArgsConstructor
public class OTPController {

    private final OTPService otpService;
    private final Environment environment;

    @PostMapping("/otp/request")
    public ResponseEntity<?> requestOTP(@RequestBody OtpReqDto request) {
        log.debug("Request OTP : {}", request);
        otpService.requestOTP(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test-load-balancer")
    public String testLoadBalancer() {
        String port = environment.getProperty("local.server.port");
        log.debug("Execute Port : {}", port);
        return port;
    }
}

