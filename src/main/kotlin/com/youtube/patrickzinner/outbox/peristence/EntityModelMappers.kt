package com.youtube.patrickzinner.outbox.peristence

import com.youtube.patrickzinner.outbox.service.Task
import com.youtube.patrickzinner.outbox.service.TaskOutbox

fun modelToEntity(taskOutbox: TaskOutbox) =
        TaskOutboxEntity(
                id = taskOutbox.id,
                task = modelToEntity(taskOutbox.task),
                createdAt = taskOutbox.createdAt,
                sentToBus = taskOutbox.sentToBus
        )

fun entityToModel(entity: TaskOutboxEntity) =
        TaskOutbox(
                id = entity.id,
                task = entityToModel(entity.task),
                createdAt = entity.createdAt,
                sentToBus = entity.sentToBus
        )

fun modelToEntity(task: Task) = TaskEntity(
        id = task.id,
        name = task.name
)

fun entityToModel(entity: TaskEntity) = Task(
        id = entity.id,
        name = entity.name
)