package com.youtube.patrickzinner.outbox.peristence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TaskJpaRepo : JpaRepository<TaskEntity, UUID>