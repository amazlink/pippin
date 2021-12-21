package io.github.rothschil.war.web;

import io.github.rothschil.base.persistence.jpa.repository.BaseRepository;
import io.github.rothschil.war.domain.entity.Location;
import io.github.rothschil.war.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {


    private List<BaseRepository> repositories;

    @Autowired
    public void setRepositories(List<BaseRepository> repositories) {
        this.repositories = repositories;
    }

    @GetMapping(value = "/test")
    public Object getEntry(@RequestParam(value="type", required = true) String type,
                           @RequestParam(value="id", required=true) Integer id){
        if(type.equals("area")){
            type = Location.class.getName();
        }else if (type.equals("user")) {
            type = User.class.getName();
        }
        for (BaseRepository baseRepository : repositories) {
            if(baseRepository.support(type)){
                return baseRepository.getById(new Long((long)id));
            }
        }
        return null;
    }
}
