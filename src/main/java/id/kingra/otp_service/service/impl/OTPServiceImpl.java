package id.kingra.otp_service.service.impl;

import id.kingra.otp_service.dto.EmailDto;
import id.kingra.otp_service.dto.OtpReqDto;
import id.kingra.otp_service.entity.TempOtp;
import id.kingra.otp_service.repository.TempOtpRepository;
import id.kingra.otp_service.service.OTPService;
import id.kingra.otp_service.utils.Helper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OTPServiceImpl implements OTPService {

    private final TempOtpRepository tempOtpRepository;
    private final RedisTemplate redisTemplate;
    private final ChannelTopic channelTopic;

    @Override
    public void requestOTP(OtpReqDto request) {
        final String email = request.getEmail();
        Optional<TempOtp> opTempOtp = tempOtpRepository.getFirstByEmail(email);
        opTempOtp.ifPresent(tempOtpRepository::delete);

        String randomOtp = Helper.generateOTP();
        saveToRedis(email);
        sendingEmail(email, "This is your verification code : " + randomOtp);
    }

    private void saveToRedis(String email) {
        String randomOTP = Helper.generateOTP();
        log.info("Random-OTP : {}", randomOTP);
        TempOtp tempOtp = new TempOtp();
        tempOtp.setOtp(randomOTP);
        tempOtp.setEmail(email);
        tempOtpRepository.save(tempOtp);
    }

    private void sendingEmail(String to, String body) {
        log.debug("To : {}, Body : {}", to, body);
        EmailDto emailDto = new EmailDto();
        emailDto.setTo(to);
        emailDto.setSubject("Verification Code");
        emailDto.setBody(body);
        redisTemplate.convertAndSend(channelTopic.getTopic(), emailDto);
    }
}
