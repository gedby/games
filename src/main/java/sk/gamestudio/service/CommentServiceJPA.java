package sk.gamestudio.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sk.gamestudio.entity.Comment;

@Component
@Transactional
public class CommentServiceJPA implements CommentService {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addComment(Comment comment) {
		entityManager.persist(comment);
	}

	@Override
	public List<Comment> getComments(String game) {
		return entityManager.createQuery("SELECT c FROM Comment c")
				.getResultList();
	}
}
