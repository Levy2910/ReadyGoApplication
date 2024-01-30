package com.example.readygo.service;

import com.example.readygo.model.Comment;
import com.example.readygo.model.Destination;
import com.example.readygo.model.User;
import com.example.readygo.repository.CommentRepository;
import com.example.readygo.repository.DestinationRepository;
import com.example.readygo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final DestinationRepository destinationRepository;
    @Override
    public List<Comment> showAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public boolean addComment(String comment_content, Long destination_id, Long user_id) {
        Comment comment = new Comment();
        if(user_id == null){
            return false;
        }
        Optional<User> user = userRepository.findById(user_id);
        Optional<Destination> destination = destinationRepository.findById(destination_id);
        if (!user.isPresent() || !destination.isPresent()){
            System.out.println("user or destination must be there");
            return false;
        }
        comment.setComment_content(comment_content);
        comment.setUser(user.get());
        comment.setDestination(destination.get());
        try{
            commentRepository.save(comment);
            return true;
        }catch( Exception e){
            return false;
        }
    }
}
