package ru.golovanov.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "kafka")
data class KafkaConfigurationPropertires(
    val bootstrapServers: String
)
