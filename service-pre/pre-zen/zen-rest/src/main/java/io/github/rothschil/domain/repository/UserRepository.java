package io.github.rothschil.domain.repository;

import io.github.rothschil.base.persistence.jpa.repository.BaseRepository;
import io.github.rothschil.domain.entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2021/10/15 - 16:41
 * @since 1.0.0
 */
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends BaseRepository<User, Long> {

    User getUserById(Long id);

    List<User> findUserByNameIsLike(@Param("name") String name);
}
