package com.youtube.patrickzinner.outbox.service

import com.youtube.patrickzinner.outbox.peristence.TaskOutboxRepo
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TaskOutboxService(
        val taskOutboxRepo: TaskOutboxRepo,
        val eventBusService: EventBusService
) {

    @Transactional
    fun publishNextBatchToEventBus(batchSize: Int) {
        val batch = taskOutboxRepo.findAllNotSent(batchSize)
        batch.forEach{
            eventBusService.publishTask(it.task)
            taskOutboxRepo.save(it.markAsSent())
        }
    }

}