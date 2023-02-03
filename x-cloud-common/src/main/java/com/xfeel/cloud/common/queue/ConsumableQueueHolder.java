package com.xfeel.cloud.common.queue;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConsumableQueueHolder {

    public static final ConcurrentHashMap<String, ConcurrentLinkedQueue> MAPPED_QUEUE = new ConcurrentHashMap<>();
}
