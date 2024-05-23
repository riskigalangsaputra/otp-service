package id.kingra.otp_service.controller;

import id.kingra.otp_service.dto.OtpReqDto;
import id.kingra.otp_service.service.OTPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController @RequiredArgsConstructor
public class OTPController {

    private final OTPService otpService;

    @PostMapping("/otp/request")
    public ResponseEntity<?> requestOTP(@RequestBody OtpReqDto request) {
        log.debug("Request OTP : {}", request);
        otpService.requestOTP(request);
        return ResponseEntity.ok().build();
    }
}

