package ru.golovanov.controllers

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.golovanov.service.WebhookService

@RestController
@RequestMapping(path = ["/webhook"])
class WebhookController(
    private val webhookService: WebhookService,
) {

    private val logger = LoggerFactory.getLogger(WebhookController::class.java)

    @PostMapping("/github")
    fun handleWebhook(
        @RequestHeader("X-GitHub-Event") event: String,
        @RequestBody payload: String
    ) = webhookService.sendActionMessage(payload, event)

}