package io.github.rothschil.domain.entity;

import io.github.rothschil.base.persistence.jpa.entity.BaseJpaPo;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2021/10/15 - 17:02
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "t_user")
public class User extends BaseJpaPo<Long> {

    @Id
    @Column(name = "u_id", nullable = false)
    private Long id;

    @Column(name = "u_name")
    private String name;

    @Column(name = "pass_word")
    private String passWord;

    public User() {
    }

    public User(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }
}
