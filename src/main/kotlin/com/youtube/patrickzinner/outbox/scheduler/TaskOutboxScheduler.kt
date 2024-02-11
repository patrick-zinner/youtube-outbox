package com.youtube.patrickzinner.outbox.scheduler

import com.youtube.patrickzinner.outbox.service.TaskOutboxService
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TaskOutboxScheduler(
        private val taskOutboxService: TaskOutboxService
) {

    @Scheduled(fixedDelayString = "1000")
    @SchedulerLock(name = "task-outbox")
    fun publishBatch() {

        taskOutboxService.publishNextBatchToEventBus(10);

    }

}