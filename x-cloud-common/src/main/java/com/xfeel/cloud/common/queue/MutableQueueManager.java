package com.xfeel.cloud.common.queue;

import java.util.concurrent.ConcurrentLinkedQueue;

public class MutableQueueManager {

    public <T> void addQueue(String group,T t){
        if (ConsumableQueueHolder.MAPPED_QUEUE.contains(group)){
            ConcurrentLinkedQueue queue = ConsumableQueueHolder.MAPPED_QUEUE.get(group);
            if (queue == null){
                queue = new ConcurrentLinkedQueue();
            }
            queue.add(t);
            //调整消费频率
        }else {
            ConcurrentLinkedQueue<T> queue = new ConcurrentLinkedQueue<>();
            queue.add(t);
            ConsumableQueueHolder.MAPPED_QUEUE.put(group,queue);
            //启动单线程消费
        }
    }
}
