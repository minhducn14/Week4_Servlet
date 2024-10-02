package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dao.ProductDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import modal.Product;

public class ProductDaoImpl implements ProductDAO {

	private EntityManager em;

	public ProductDaoImpl(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public List<Product> findAll() {
		try {
			return em.createQuery("from Product").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Product getById(int id) {
		try {
			Product product = em.find(Product.class, id);
			return product;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Product addProduct(Product p) {
		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();

			if (!transaction.isActive()) {
				transaction.begin();
			}

			em.persist(p);
			transaction.commit();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}
		return null;

	}

}
