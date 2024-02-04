package com.youtube.patrickzinner.outbox.service

import com.youtube.patrickzinner.outbox.peristence.TaskRepo
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TaskService(
        private val taskRepo: TaskRepo
) {

    @Transactional
    fun create(task: Task) : Task {
        return taskRepo.create(task)
    }

}