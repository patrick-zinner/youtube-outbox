package com.youtube.patrickzinner.outbox.service

import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class EventBusService(
        private val kafkaTemplate: KafkaTemplate<String, Task>
) {

    fun publishTask(task: Task) {
        log.debug { "Publishing task $task.name to Kafka" }
        kafkaTemplate.send("tasks", task.id.toString(), task)
    }

    companion object {

        private val log = KotlinLogging.logger {}
    }

}