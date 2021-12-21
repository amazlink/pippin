package io.github.rothschil.war.domain.repository;

import io.github.rothschil.base.persistence.jpa.repository.BaseRepository;
import io.github.rothschil.war.domain.entity.User;

/**
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2021/10/15 - 16:41
 * @since 1.0.0
 */

public interface UserRepository extends BaseRepository<User, Long> {

    User getUserById(Long id);

}
