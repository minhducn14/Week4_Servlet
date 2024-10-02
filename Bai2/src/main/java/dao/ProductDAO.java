package dao;

import java.util.List;

import modal.Product;

public interface ProductDAO {
	public List<Product> findAll();

	public Product getById(int id);

	public Product addProduct(Product p);
}
