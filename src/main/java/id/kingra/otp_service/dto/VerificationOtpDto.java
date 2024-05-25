package id.kingra.otp_service.dto;

import lombok.Data;

@Data
public class VerificationOtpDto {

    private String email;
    private String otp;
}
