package xyz.wongs.drunkard.alipay.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.wongs.drunkard.alipay.pojo.OssSdk;

/**
 * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @github <a>https://github.com/rothschil</a>
 * @date 2021/9/23 - 15:18
 * @version 1.0.0
 */
@Configuration
public class OssPropertiesClassLoader {

    private static final Logger LOG = LoggerFactory.getLogger(OssPropertiesClassLoader.class);

    @Autowired
    private OssSdk ossSdk;

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(ossSdk.getStorehouse());
        config.setKeyObtentionIterations(ossSdk.getIterations());
        config.setPoolSize(ossSdk.getSize());
        config.setProviderName(ossSdk.getProviderName());
        config.setSaltGeneratorClassName(ossSdk.getSaltGeneratorClassName());
        config.setIvGeneratorClassName(ossSdk.getLvGeneratorClassName());
        config.setStringOutputType(ossSdk.getStringOutputType());
        encryptor.setConfig(config);
        return encryptor;
    }

}
