package io.github.rothschil.unit;

import io.github.rothschil.BaseTest;
import io.github.rothschil.war.domain.entity.Location;
import io.github.rothschil.war.domain.service.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

public class LocationCase extends BaseTest {

    @Resource
    private LocationService locationService;

    @Test
    public void findByPoAttr() {
        Location location = new Location();
        location.setLocalName("安徽省");
        List<Location> lists = locationService.findByEntity(location);
        print(lists);
    }

    @Test
    public void findBySql() {
        String sql = "SELECT t.* FROM tb_locations t WHERE t.id=2288662";
        List<Location> lists = locationService.listBySql(sql);
        for (Object list : lists) {
            Object[] obj = (Object[]) list;
        }
    }

    @Test
    public void findByHql() {
        String hql = "FROM Location t WHERE t.id=2288662";
        List<Location> lists = locationService.listByHql(hql);
        print(lists);
    }

    public void print(List<?> list){
        Assert.notNull(list,"List must not null");
        list.forEach(o ->
                System.out.println(o.toString()));
    }
}
