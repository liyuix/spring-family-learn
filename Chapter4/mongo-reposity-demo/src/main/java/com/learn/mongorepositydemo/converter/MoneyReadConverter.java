package com.learn.mongorepositydemo.converter;

import org.bson.Document;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by 咯噔 on 2019/3/4
 **/
public class MoneyReadConverter implements Converter<Document, Money> {
    @Override
    public Money convert(Document document) {
        Document money=(Document)document.get("money");
        double amount=Double.parseDouble(money.getString("amount"));
        String currency=((Document)money.get("currency")).getString("code");
        /*Money.of:传入钱的类型*/
        return Money.of(CurrencyUnit.of(currency),amount);
    }
}
