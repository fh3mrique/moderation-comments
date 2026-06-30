package com.projeto.comment_service.api.client;

import com.projeto.comment_service.api.model.ModerationRequest;
import com.projeto.comment_service.api.model.ModerationResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface ModerationClient {

    @PostExchange("/api/moderate")
     ModerationResponse moderationComment(@RequestBody ModerationRequest request);
}
