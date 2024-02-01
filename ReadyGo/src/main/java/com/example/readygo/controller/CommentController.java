package com.example.readygo.controller;

import com.example.readygo.model.Comment;
import com.example.readygo.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    final private ICommentService iCommentService;

    @CrossOrigin(
            origins = {
                    "http://localhost:3000"
            },
            methods = {
                    RequestMethod.OPTIONS,
                    RequestMethod.GET,
                    RequestMethod.PUT,
                    RequestMethod.DELETE,
                    RequestMethod.POST
            })

    @GetMapping("/all_comments")
    public ResponseEntity<List<Comment>> showAllComments(){
        try{
            List<Comment> comments = iCommentService.showAllComments();
            return ResponseEntity.ok(comments);
        }catch (Exception e){
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping("/add_comment")
    public ResponseEntity<String> addComment(@RequestParam Long destination_id,
                                             @RequestParam Long user_id,
                                             @RequestParam String comment_content
    ){
        try{
            if (iCommentService.addComment(comment_content, destination_id, user_id)){
                return ResponseEntity.ok("Successful");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
