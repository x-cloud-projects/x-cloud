package com.xfeel.cloud.common.queue;

import org.springframework.scheduling.annotation.Scheduled;

public class MutableQueueConsumer {

    @Scheduled(cron = "${schedule.queue.cron.express:*/3 * * * * ?}")
    public void execute(){}
}
