package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.UserDAO;
import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UserDAOImpl implements UserDAO {

	private EntityManager entityManager;

	public UserDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public List<User> getAllUsers() {
		try {
			return entityManager.createQuery("from User").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public User getById(int id) {
		try {
			User user = entityManager.find(User.class, id);
			return user;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User addUser(User p) {
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();

			if (!transaction.isActive()) { 
				transaction.begin();
			}

			entityManager.persist(p);
			transaction.commit();
			return p;
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback(); 
			}
			e.printStackTrace();
		}
		return null;
	}

}
