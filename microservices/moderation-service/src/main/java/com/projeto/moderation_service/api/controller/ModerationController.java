package com.projeto.moderation_service.api.controller;

import com.projeto.moderation_service.api.model.ModerationInput;
import com.projeto.moderation_service.api.model.ModerationOutput;
import com.projeto.moderation_service.domain.service.ModerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moderate")
@RequiredArgsConstructor
public class ModerationController {

    private final ModerationService moderationService;


    @PostMapping
    ModerationOutput moderate(@RequestBody ModerationInput request){
        return moderationService.moderate(request);
    }
}
