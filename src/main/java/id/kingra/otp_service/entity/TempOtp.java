package id.kingra.otp_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@RedisHash(value = "otp", timeToLive = 300)
public class TempOtp {

    @Id
    private String id;
    private String otp;

    @Indexed
    private String email;
}
