package com.youtube.patrickzinner.outbox.service

import com.youtube.patrickzinner.outbox.peristence.TaskJpaRepo
import com.youtube.patrickzinner.outbox.peristence.TaskOutboxJpaRepo
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.shaded.org.awaitility.Awaitility
import java.util.*

@Testcontainers
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
@EmbeddedKafka(
        partitions = 1,
        ports = [9092]
)
class TaskServiceTest {

    @Autowired
    lateinit var taskService: TaskService

    @Autowired
    lateinit var taskJpaRepo: TaskJpaRepo

    @Autowired
    lateinit var taskOutboxJpaRepo: TaskOutboxJpaRepo

    @Test
    fun `create stores task in database`() {
        // given
        val task = aTask()

        //when
        taskService.create(task)

        // then
        val entity = taskJpaRepo.findById(task.id).orElseThrow()
        assertThat(entity.id).isEqualTo(task.id)
        assertThat(entity.name).isEqualTo(task.name)
    }

    @Test
    fun `create stores a task outbox entry in database`() {
        // given
        val task = aTask()

        //when
        taskService.create(task)

        //then
        val taskOutbox = taskOutboxJpaRepo.findByTaskId(task.id)
        assertThat(taskOutbox.task.id).isEqualTo(task.id)
        assertThat(taskOutbox.sentToBus).isFalse()
    }

    @Test
    fun `a created task ends up being delivered to the eventbus`() {
        // given
        val task = aTask()

        //when
        taskService.create(task)

        //then
        Awaitility.await().untilAsserted {
            val taskOutbox = taskOutboxJpaRepo.findByTaskId(task.id)
            assertThat(taskOutbox.task.id).isEqualTo(task.id)
            assertThat(taskOutbox.sentToBus).isTrue()
        }
    }

    fun aTask(
            id: UUID = UUID.randomUUID(),
            name: String = "fix bugs"
    ) = Task(id, name)

}