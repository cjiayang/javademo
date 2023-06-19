package net.jyou.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * @author Joey Chen
 * @created 2023/3/28 16:41
 */
public class EncryptUtil {
    public static void main(String[] args) {
        String spk = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkpvPAdktYcIt/bpM3DMdY5VZCjV5+cJJHxfuNVhGRyqlLe0mlh8PwebmfLc+NEI4TOthSIVMNRIs1Dcz1cjvWYNv6tJLwzhlj1v+Gc196oN5jZUU2TzLbMRTVAVgsqVLlhs82D3LBlEPL80MFKvUpIJ++OAuEVmIYLDfKqSJEHIEzteq9Y0ZKPo5jLSZlXUyVmGzB7gzP+0F92JFcKyJkFRjHZPCWmL2q6pp+GLK+vWTYTvoEsBhQ4ChYyjOCmyayvqwHrj3j88dEhCjO0lTusZnFddphZBaifJYqNALhakCT9PGf43YV9xvUv2ntQ7nCN0UQojUKclNwxxoPHR3mwIDAQAB";
        RSA rsa = new RSA(null,spk);
        String encryptSecret = rsa.encryptBase64(
                "1",
                CharsetUtil.CHARSET_UTF_8,
                KeyType.PublicKey
        );
        System.out.println("encryptSecret: " + encryptSecret);
    }
}
