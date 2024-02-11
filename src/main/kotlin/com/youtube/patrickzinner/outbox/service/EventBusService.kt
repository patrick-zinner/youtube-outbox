package com.youtube.patrickzinner.outbox.service

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class EventBusService(
        private val kafkaTemplate: KafkaTemplate<UUID, Task>
) {

    fun publishTask(task: Task) {
        kafkaTemplate.send("tasks", task.id, task)
    }

}