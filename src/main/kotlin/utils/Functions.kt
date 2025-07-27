package ru.golovanov.utils

import com.fasterxml.jackson.databind.ObjectMapper
import ru.golovanov.dto.ActionMessage
import ru.golovanov.enums.ActionType

fun parseGithubEvent(body: String, eventType: String): ActionMessage {
    val json = ObjectMapper().readTree(body)
    return ActionMessage(
        actionType = mapEventTypeToActionMessageType(eventType),
        repository = json["repository"]["html_url"].asText(),
        author = json["head_commit"]["author"]["name"].asText(),
        branch = json["ref"].asText().replace("refs/heads/", ""),
        message = json["head_commit"]["message"].asText(),
        time = json["head_commit"]["timestamp"].asText(),
        link = json["head_commit"]["url"].asText()
    )
}

fun mapEventTypeToActionMessageType(eventType: String): ActionType =
    when (eventType) {
        "push" -> ActionType.COMMIT
        "pull_request" -> ActionType.PULL_REQUEST
        "issues" -> ActionType.ISSUE
        "release" -> ActionType.RELEASE
        else -> ActionType.DEFAULT
    }