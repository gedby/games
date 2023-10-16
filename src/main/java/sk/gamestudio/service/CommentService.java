package sk.gamestudio.service;

import java.util.List;

import sk.gamestudio.entity.Comment;

public interface CommentService {
	void addComment(Comment comment);
	
	List<Comment> getComments(String game);
}
