package id.kingra.otp_service.service;

import id.kingra.otp_service.dto.OtpReqDto;
import org.springframework.http.ResponseEntity;

public interface OTPService {

    void requestOTP(OtpReqDto request);
}
