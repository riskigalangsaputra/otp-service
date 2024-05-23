package id.kingra.otp_service.service.impl;

import id.kingra.otp_service.dto.OtpReqDto;
import id.kingra.otp_service.entity.TempOtp;
import id.kingra.otp_service.repository.TempOtpRepository;
import id.kingra.otp_service.service.OTPService;
import id.kingra.otp_service.utils.Helper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OTPServiceImpl implements OTPService {

    private final TempOtpRepository tempOtpRepository;

    @Override
    public void requestOTP(OtpReqDto request) {
        String email = request.getEmail();
        Optional<TempOtp> opTempOtp = tempOtpRepository.getFirstByEmail(email);
        opTempOtp.ifPresent(tempOtpRepository::delete);

        String randomOTP = Helper.generateOTP();
        log.info("Random-OTP : {}", randomOTP);
        TempOtp tempOtp = new TempOtp();
        tempOtp.setOtp(randomOTP);
        tempOtp.setEmail(email);
        tempOtpRepository.save(tempOtp);
    }
}
