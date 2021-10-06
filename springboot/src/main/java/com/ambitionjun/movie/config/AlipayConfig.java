package com.ambitionjun.movie.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created  on 2019-9-09
 */
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000118618684"; //测试

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCUwUInE3sBt/aEYJfPAyT7rQHiGfncnPSVGC//iadXiGYIcwBwvndr9CoEwXbRCM5k+dVv8O9suyZMf12Bwr5kRfG9+QE10kYJy85mN/TQcjyQXZyKnItGlRyj+SpOe9TUs8Xjf+Qp+wrXMTB4dSmFJ2IV4q4LgloEvSuPCN1zAZI3sD4WtjtnWFpBXaY29KRrm5nzVHn9iaz4zoOgxX/OBkcnhjUplHf3JHnJry5+PIEIWe92l7tqVjTQ23BTda5Pft2QktKXFfWPkLFc1rzr2yIB3BCPAQR+QqPuqSUlAN4/wecjwmlzCXO6RG0722yl5k9igzUpByyDP9Soj6HlAgMBAAECggEAIszjkQi5qjlDbsy6Ny/hIw/idObm0XnWrlquP/YQNCYw2KEu5TFp9tN0URCBn32T64OXL00KdkGCQDs7wPOyXvsOBweOBO1gfUI00y0cFPfku1FrIfJlkumnfaT5BMtriydTkFmHI1sbuiRD1qpyQDhqdfxGA2NHc/xdiGTmidTh8BblTQKZyj/sJc1q0/NIG66oVnjOSkuVdS3X+tTxmW557q8KBNG/WRdJyP7tEG/vH/CmkXB6pChbvZ9ss0JESzR4Z3PHUPPRs/8AU0bTwZ8W3jSsx7dlscNNGIBbYws6CMCd4+4y4mpqDs9maJnU0sFu2wBnDzoHT0GpIfsZAQKBgQD8N5PvQVN2DB2wU0rCgdPpBAk3QZEqxw5eAeCgdvRFgjqseVhfUEEsd/TB0cqQGHVXSew1OePtTCdcgHIswvrUOD6FSdGjJtemttfkZS1JZ8lCAujZrmh8grqyVynGHy1/4AYtDYFxlAFgohznqPGPPErp/mHj3CFriU/7cqsB1QKBgQCW/GxdMdKYQo4+K+TLgd2s7cU2D5Tg9cyYFcwStJaUjeojHBi37hm4crhAvwtSVhDnoBxFQXXS+5pxXC4M9BQjiJQaocjAwgHA24PmG88AoeBxgZiIWkgHD8BVCwRqYwTGKduKdSicOi0RRUdtIRohDzRhpMsxYbegoReUwiUX0QKBgQC0Ya9cotTlDV9CVx+VorKC6U8KBMuk035T/3W+bbTf7Xa3GYX5jPU/r38OC1Oa4NMzHXxgyMlZUmioQHjnyH8JvjwAqb3TuJjs88HN6AqOgDqxClGsNY8m1XxexfXrayroVLk2CX21VEvz551SlUJF1bIiTbuG8k24khjw1Z62DQKBgH7uh0tx71IB4gNyc06DRxFyczEXuFnHStEwwfeXyl/pTsVlgYJfsU/XYf9MnQADSwLvH9U5SwysaRKUJELPj36v+5LIIDWs5Dx2T1TMXCJz9HR4NvuqqpMFMvRL5tJpnEHcPq0QAawVXH4AeCPOiGDyn17XY9uIAxAPXfu5m+WBAoGBAOPA3p4QTK/zoNroJ5ZYmj96m4g32ULgTJPQZwUvDhfPQyG0yO+r9U0prX68beCtBd3Qo1ZQGJNShw4E5uJt74j4u9ddkCxRYFr8wRJN7+6F4absJNeCLRCd5HRImlJm0u0TR51O3VBpwPuCD9a2q/UtAvyXnQDGsHEq0zJXr3JW";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtKPupE+w6qthNktgDh2fTwZm3SsqzB0Jd4VZP2YMjBi1TN7hW2t1JaeIZSnjMhFycDk0eaz1OX5b3u3uKptvk+fBLT+GnvZ674RY/9sg3ZX856gnbrCKuNfHkynp+NpOVHXxa5jGBqrp/PVN8PqimVChNMeLZPVWaNs53JqZFcRO8wADbn3TAt8wGvsP/igSw4H2xGr7ZQOrSrS0B8Ge8gbn0ll3xiu0AHe8gKhnQFKc7qJ6qnF6/LNwezA1STgv6EnVvCJm05IjLNsFC4drJKms6Fa5fXJErWupJOvXx4auz4g94BefkmE7ACTC/6s5FFafE+lESFXy2yTYEI8TpQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://101.200.193.211:8088/Alipay/notifyUrl";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://101.200.193.211:8088/Alipay/returnUrl";
    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "UTF-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do"; //测试

    // 支付宝网关
    public static String log_path = "E:\\";

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}