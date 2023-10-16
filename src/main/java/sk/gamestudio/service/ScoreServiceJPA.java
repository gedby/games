package sk.gamestudio.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sk.gamestudio.entity.Score;

@Component
@Transactional
public class ScoreServiceJPA implements ScoreService {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addScore(Score score) {
		entityManager.persist(score);
	}

	@Override
	public List<Score> getTopScores(String game) {
		return entityManager.createQuery("SELECT s FROM Score s WHERE s.game = :game ORDER BY s.value DESC").setParameter("game", game).setMaxResults(10)
				.getResultList();
	}

}
