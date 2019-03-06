package com.learn.mongorepositydemo;

import com.learn.mongorepositydemo.converter.MoneyReadConverter;
import com.learn.mongorepositydemo.model.Coffe;
import com.learn.mongorepositydemo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.Date;

/*EnableMongoRepositories:加上这个注释就可以使用Repository*/
@Slf4j
@SpringBootApplication
@EnableMongoRepositories
public class MongoReposityDemoApplication implements CommandLineRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoReposityDemoApplication.class, args);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
    }

    @Override
    public void run(String... args) throws Exception {
        Coffe espresso=Coffe.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"),20.0))
                .createTime(new Date())
                .updateTime(new Date()).build();

        Coffe latte=Coffe.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"),30.0))
                .createTime(new Date())
                .updateTime(new Date()).build();

        coffeeRepository.insert(Arrays.asList(espresso,latte));
        coffeeRepository.findAll(Sort.by("name")).forEach(c->log.info("saved Coffe{}",c));


        Thread.sleep(1000);
        latte.setPrice(Money.of(CurrencyUnit.of("CNY"),35.0));
        latte.setUpdateTime(new Date());
        coffeeRepository.save(latte);
        coffeeRepository.findByName("latte").forEach(c->log.info("Coffe {}",c));
      /*  coffeeRepository.deleteAll();*/
    }
}
