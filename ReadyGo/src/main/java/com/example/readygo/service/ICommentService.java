package com.example.readygo.service;

import com.example.readygo.model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> showAllComments();


    boolean addComment(String comment, Long destinationId, Long userId);
}
