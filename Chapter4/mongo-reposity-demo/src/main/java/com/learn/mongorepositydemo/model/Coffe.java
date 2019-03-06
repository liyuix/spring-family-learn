package com.learn.mongorepositydemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by 咯噔 on 2019/3/4
 * NoArgsConstructor:生成一个无参数的构造方法
 * AllArgsConstructor：生成一个包含所有参数的构造方法
 * @Data : 注解在类上, 为类提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
 *  @Builder声明实体，表示可以进行Builder方式初始化
 **/
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffe {
    @Id
    private String id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
