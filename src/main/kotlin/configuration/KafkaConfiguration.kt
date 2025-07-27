package ru.golovanov.configuration

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import ru.golovanov.dto.ActionMessage

@EnableKafka
@Configuration
@EnableConfigurationProperties(KafkaConfigurationPropertires::class)
class KafkaConfiguration(
    private val properties: KafkaConfigurationPropertires
) {

    @Bean
    fun producerConfigs(): Map<String, Any> =
        hashMapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to properties.bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java,
            JsonSerializer.ADD_TYPE_INFO_HEADERS to false
        )

    @Bean
    fun producerFactory(): ProducerFactory<String, ActionMessage> =
        DefaultKafkaProducerFactory(producerConfigs())

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, ActionMessage> =
        KafkaTemplate(producerFactory())

}