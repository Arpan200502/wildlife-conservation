package com.wildlife.conservation.controller;

import com.wildlife.conservation.dto.AssistantChatRequest;
import com.wildlife.conservation.dto.AssistantChatResponse;
import com.wildlife.conservation.service.AssistantChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/assistant")
public class AssistantChatController {

    private final AssistantChatService assistantChatService;

    public AssistantChatController(AssistantChatService assistantChatService) {
        this.assistantChatService = assistantChatService;
    }

    @PostMapping("/chat")
    public ResponseEntity<AssistantChatResponse> chat(@RequestBody AssistantChatRequest request) {
        if (request == null || request.getMessage() == null || request.getMessage().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message is required.");
        }

        AssistantChatResponse response = assistantChatService.answer(request.getMessage());
        return ResponseEntity.ok(response);
    }
}
