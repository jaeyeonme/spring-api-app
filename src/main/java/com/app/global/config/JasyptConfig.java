package com.app.global.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEncryptableProperties
public class JasyptConfig {

    @Value("${jasypt.password}")
    private String password;

    @Bean
    public PooledPBEStringEncryptor jasyptStringEncryptor() {
        // PooledPBEStringEncryptor: 멀티코어 시스템에서 해독을 벙력처리하는 인크립터
        // 보통 멀티코어를 사용하므로 해당 클래스를 사용하면 StandardPBEString Encryptor를 사용할때마다 성능이 좋다.
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        encryptor.setPassword(password);
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
        return encryptor;
    }
}
