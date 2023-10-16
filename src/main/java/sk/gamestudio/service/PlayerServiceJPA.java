package sk.gamestudio.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sk.gamestudio.entity.Player;

@Component
@Transactional
public class PlayerServiceJPA implements PlayerService {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Player login(String name, String password) {
		try {
		return (Player)entityManager.createQuery("SELECT p FROM Player p WHERE p.name = :name AND p.passwd = :password").
			setParameter("name", name).setParameter("password", password).getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void register(Player player) {
		entityManager.persist(player);
	}
}
