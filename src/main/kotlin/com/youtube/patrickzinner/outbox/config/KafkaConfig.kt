package com.youtube.patrickzinner.outbox.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.support.serializer.JsonSerializer

@EnableKafka
@Configuration
class KafkaConfig {

    @Bean
    fun defaultKafkaProducerCustomizer(): DefaultKafkaProducerFactoryCustomizer {
        return DefaultKafkaProducerFactoryCustomizer {
            it.updateConfigs(
                    mapOf(
                            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.qualifiedName,
                            JsonSerializer.ADD_TYPE_INFO_HEADERS to true
                    )
            )
        }
    }
}