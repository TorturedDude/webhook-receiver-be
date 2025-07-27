package ru.golovanov.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.golovanov.dto.ActionMessage

@Service
class KafkaProducerService(
    private val kafkaTemplate: KafkaTemplate<String, ActionMessage>,
    @Value("\${kafka.producer.topic}")
    private val topic: String,
) {

    private val logger = LoggerFactory.getLogger(KafkaProducerService::class.java)

    fun sendActionMessage(message: ActionMessage) {
        kafkaTemplate.send(topic, message)
        logger.info("Sending message {}", message)
    }

}