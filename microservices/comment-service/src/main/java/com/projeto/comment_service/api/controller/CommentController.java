package com.projeto.comment_service.api.controller;

import com.projeto.comment_service.api.model.CommentInput;
import com.projeto.comment_service.api.model.CommentOutput;
import com.projeto.comment_service.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping()
    public CommentOutput insert (@RequestBody CommentInput input){
        return  commentService.createComment(input);
    }


    @GetMapping("{id}")
    public CommentOutput getCommentById(@PathVariable String id){
        return commentService.getCommentById(UUID.fromString(id));
    }

    @GetMapping
    public Page<CommentOutput> getAllComments(Pageable pageable) {
        return commentService.getAllComments(pageable);
    }


}
