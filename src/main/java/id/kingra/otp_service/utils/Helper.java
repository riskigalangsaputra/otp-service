package id.kingra.otp_service.utils;

import java.text.DecimalFormat;
import java.util.Random;

public class Helper {

    public static String generateOTP() {
        return new DecimalFormat("0000").format(new Random().nextInt(9999));
    }
}
