package org.springframework.cloud.stream.app.log.sink.kafka.controller;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by anbabans on 11/27/2017.
 */
public class MaxSizeHashMap <K, V> extends LinkedHashMap<K, V> {
    public static MaxSizeHashMap<String, String> instance = new MaxSizeHashMap<String, String>(50);

    private final int maxSize;

    private MaxSizeHashMap(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > maxSize;
    }
}