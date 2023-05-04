package com.trouvaille.aladdin.utils;

import java.util.Random;

/**
 * @param null:
 * @author willi
 * @return null
 * @description 手机验证码工具类
 * @date 2023/04/06 22:02
 */
public class ValidateCodeUtils {
    private static final int FourLength = 4;
    private static final int SixLength = 6;
    
    /**
     * @param length:
     * @return Integer
     * @author William_Trouvaille
     * @description 随机生成验证码
     * @date 2022/07/22 16:32
     */
    public static Integer generateValidateCode (int length) {
        Integer code = null;
        if (length == FourLength) {
//            生成随机数，最大为9999
            code = new Random().nextInt(9999);
            if (code < 1000) {
                //保证随机数为4位数字
                code = code + 1000;
            }
        } else if (length == SixLength) {
            //生成随机数，最大为999999
            code = new Random().nextInt(999999);
            if (code < 100000) {
                //保证随机数为6位数字
                code = code + 100000;
            }
        } else {
            throw new RuntimeException("只能生成4位或6位数字验证码");
        }
        return code;
    }
    
    /**
     * @param length:
     * @return String
     * @author William_Trouvaille
     * @description 随机生成指定长度字符串验证码
     * @date 2022/07/22 16:32
     */
    public static String generateValidateCode4String (int length) {
        Random rdm = new Random();
        String hash1 = Integer.toHexString(rdm.nextInt());
        return hash1.substring(0 , length);
    }
}
