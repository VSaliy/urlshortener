package com.vsaliy.urlshortener.generator;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Base62TimestampUrlIdentifierGenerator implements UrlIdentifierGenerator {

    protected AtomicInteger counter = new AtomicInteger();

    public String generate() {
        final int counterValue = counter.getAndUpdate((operand) -> (operand + 1) % 1000);
        final long base10Id = Long.valueOf("" + counterValue + System.currentTimeMillis());
        return Base62.fromBase10(base10Id);
    }

}
