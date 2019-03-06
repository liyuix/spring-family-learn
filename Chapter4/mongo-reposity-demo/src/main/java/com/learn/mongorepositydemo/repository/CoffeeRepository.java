package com.learn.mongorepositydemo.repository;

import com.learn.mongorepositydemo.model.Coffe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by 咯噔 on 2019/3/6
 **/
public interface CoffeeRepository extends MongoRepository<Coffe,String> {
    List<Coffe> findByName(String name);
}
