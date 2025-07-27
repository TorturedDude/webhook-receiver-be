package ru.golovanov.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.golovanov.dto.ActionMessage
import ru.golovanov.utils.parseGithubEvent

@Service
class WebhookService(
    private val kafkaProducerService: KafkaProducerService,
) {

    private val logger = LoggerFactory.getLogger(KafkaProducerService::class.java)

    fun sendActionMessage(body: String, eventType: String) {
        logger.info("body from webhook {}", body)
        val action = parseGithubEvent(body, eventType)
        kafkaProducerService.sendActionMessage(action)
    }
}