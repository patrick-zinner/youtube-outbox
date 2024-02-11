package com.youtube.patrickzinner.outbox.service

import com.youtube.patrickzinner.outbox.peristence.TaskOutboxRepo
import jakarta.transaction.Transactional
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class TaskOutboxService(
        val taskOutboxRepo: TaskOutboxRepo,
        val eventBusService: EventBusService
) {

    @Transactional
    fun publishNextBatchToEventBus(batchSize: Int) {
        val batch = taskOutboxRepo.findAllNotSent(batchSize)
        log.debug { "Found ${batch.size} items to publish to event bus" }
        batch.forEach{
            eventBusService.publishTask(it.task)
            taskOutboxRepo.save(it.markAsSent())
        }
    }

    companion object {

        private val log = KotlinLogging.logger {}
    }
}