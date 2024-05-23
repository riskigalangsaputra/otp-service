package id.kingra.otp_service.repository;

import id.kingra.otp_service.entity.TempOtp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TempOtpRepository extends CrudRepository<TempOtp, String> {

    Optional<TempOtp> getFirstByEmail(String email);
}
