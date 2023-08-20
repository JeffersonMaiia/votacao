package com.sicred.votacao.fixture;

import org.jeasy.random.EasyRandom;

import java.util.List;
import java.util.stream.Collectors;

public class Fixture {

    private static final EasyRandom GENERATOR = new EasyRandom();

    public static <T> T random(final Class<T> clazz) {
        return GENERATOR.nextObject(clazz);
    }

    public static <T> List<T> randomList(final Class<T> clazz, final Integer size) {
        return GENERATOR.objects(clazz, size).collect(Collectors.toList());
    }
}
