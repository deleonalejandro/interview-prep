package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        aVeryBigSum();
    }

    /**
     * A Very Big Sum <br/>
     * <a href="https://www.hackerrank.com/challenges/a-very-big-sum/problem">HackerRank Link</a>
     */
    private static void aVeryBigSum() {
        List<Long> bigNumbers = List.of(12323234234L, 2133000034L);
        Long bigSum = bigNumbers.stream().mapToLong(l -> l).sum();
        log.info("Sum: {}", bigSum);
    }
}