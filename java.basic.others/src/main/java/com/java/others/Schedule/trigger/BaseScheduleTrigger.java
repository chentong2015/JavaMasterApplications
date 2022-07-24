package com.java.others.Schedule.trigger;

import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class BaseScheduleTrigger {

    public void testJobScheduler() {
        // 创建一个日志的触发器: 使用第三分的依赖
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("name")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .build();
    }
}
