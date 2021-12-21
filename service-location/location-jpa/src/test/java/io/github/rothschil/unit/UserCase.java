package io.github.rothschil.unit;

import io.github.rothschil.BaseTest;
import io.github.rothschil.war.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

public class UserCase extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void listBySql() {
        String sql ="select * from tb_user t where t.id=2";
        List<?> lists = userService.listBySql(sql);
        print(lists);
    }


    public void print(List<?> list){
        Assert.notNull(list,"List must not null");
        list.forEach(o ->
                System.out.println(o.toString()));
    }
}
