package com.learn.mongodemo;

import com.learn.mongodemo.converter.MoneyReadConverter;
import com.learn.mongodemo.demo.Coffe;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
public class MongoDemoApplication implements ApplicationRunner {
    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MongoDemoApplication.class, args);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions(){
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffe espresso=Coffe.builder().name("espresso").price(Money.of(CurrencyUnit.of("CNY"),20.0))
                .createTime(new Date())
                .updateTime(new Date()).build();
        Coffe saved=mongoTemplate.save(espresso);
        log.info("Coffe{}",saved);
        List<Coffe> list=mongoTemplate.find(Query.query(Criteria.where("name").is("espresso")),Coffe.class);
        log.info("Find {} Coffe",list.size());

        Thread.sleep(1000);
        UpdateResult result=mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("espresso")),new Update()
                .set("price",Money.ofMajor(CurrencyUnit.of("CNY"),30))
                .currentDate("updateTime"),Coffe.class);
        log.info("update Result:{}",result.getModifiedCount());
        Coffe updateOne=mongoTemplate.findById(saved.getId(),Coffe.class);
        log.info("Update Result:{}",updateOne);
        mongoTemplate.remove(updateOne);



    }
}
