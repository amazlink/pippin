package io.github.rothschil;

import io.github.rothschil.base.persistence.jpa.repository.factory.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2021/11/6 - 21:27
 * @since 1.0.0
 */
@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"io.github"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class
)
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
