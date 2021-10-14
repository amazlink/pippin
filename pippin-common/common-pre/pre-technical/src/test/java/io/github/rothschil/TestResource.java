package io.github.rothschil;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

public class TestResource {

    public static void main(String[] args) throws Exception {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("Y6M9fAJQdU7jNp5MW");
//        config.setAlgorithm(algorithm);
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
//
//
//        String key = "x";
//        String encrypt =encryptor.encrypt(key);
//        System.out.println("[加密1]"+encrypt);
//        System.out.println("[解密1]"+encryptor.decrypt(encrypt));
//
//        String key2 = "x";
//        String encrypt2 =encryptor.encrypt(key2);
//        System.out.println("[加密2]"+encrypt2);
//        System.out.println("[解密2]"+encryptor.decrypt(encrypt2));
//        String key3 = "abram";
//        String encrypt3 =encryptor.encrypt(key3);
//        System.out.println("[加密3]"+encrypt3);
//        System.out.println("[解密1]"+encryptor.decrypt(encrypt3));

//        String key = "";
//        String encrypt = encryptor.encrypt(key);
//        System.out.println("[加密1]" + encrypt);
//        System.out.println("[解密1]" + encryptor.decrypt(encrypt));
//
//        String key2 = "";
//        String encrypt2 = encryptor.encrypt(key2);
//        System.out.println("[加密2]" + encrypt2);
//        System.out.println("[解密3]" + encryptor.decrypt(encrypt2));
//
//        String key3 = "";
//        String encrypt3 = encryptor.encrypt(key3);
//        System.out.println("[加密1]" + encrypt3);
//        System.out.println("[解密1]" + encryptor.decrypt(encrypt3));

        String key4 = "QIUFEAORHRRGRPCR";
        String encrypt4 = encryptor.encrypt(key4);
        System.out.println("[加密1]" + encrypt4);
        System.out.println("[解密1]" + encryptor.decrypt(encrypt4));
    }
}