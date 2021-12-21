package io.github.rothschil.war.domain.service;

import io.github.rothschil.base.persistence.jpa.service.BaseService;
import io.github.rothschil.war.domain.entity.User;
import io.github.rothschil.war.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2021/10/15 - 16:41
 * @since 1.0.0
 */
@Transactional
@Service
public class UserService extends BaseService<User, Long> {


    private UserRepository userRepository;

    @Autowired
    @Qualifier("userRepository")
    @Override
    public void setJpaRepository(JpaRepository<User, Long> jpaRepository) {
        this.jpaRepository = jpaRepository;
        this.userRepository = (UserRepository) jpaRepository;
    }

    @Autowired
    public void setUserMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User insert(User user){
        return userRepository.save(user);
    }

    public User getByKey(Long id){
        return userRepository.getUserById(id);
    }

    public List<?> listBySql(String sql) {
        return userRepository.listBySql(sql);
    }

}
