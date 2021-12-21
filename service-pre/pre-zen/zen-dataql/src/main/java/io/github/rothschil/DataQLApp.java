package io.github.rothschil;

import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2021/11/6 - 21:27
 * @since 1.0.0
 */
@EnableHasor()
@EnableHasorWeb()
@SpringBootApplication
public class DataQLApp {

    public static void main(String[] args) {
        SpringApplication.run(DataQLApp.class, args);
    }
}
