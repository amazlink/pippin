package io.github.rothschil.oauth2.event.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author WCNGS@QQ.COM
 * @date 20/11/27 17:10
 * @since 1.0.0
 */
@Slf4j
public class UserAuthenticationEventPublisher extends DefaultAuthenticationEventPublisher {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception,
                                             Authentication authentication) {
        //this.applicationEventPublisher.publishEvent(authentication);
        log.error("---自定-------publishAuthenticationFailure---------");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
