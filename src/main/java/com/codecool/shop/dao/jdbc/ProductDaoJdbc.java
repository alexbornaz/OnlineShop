package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private DataSource dataSource;
    private ProductCategoryDao categoryDao;
    private SupplierDao supplierDao;
    public ProductDaoJdbc(DataSource dataSource, ProductCategoryDao categoryDao, SupplierDao supplierDao) {
        this.dataSource = dataSource;
        this.categoryDao = categoryDao;
        this.supplierDao = supplierDao;
    }
    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, default_price, currency_string, description, category_id, supplier_id FROM products WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) return null;
            ProductCategory category = categoryDao.find(resultSet.getInt(5));
            Supplier supplier = supplierDao.find(resultSet.getInt(6));
            Product product = new Product(resultSet.getString(1), resultSet.getBigDecimal(2), resultSet.getString(3), resultSet.getString(4), category, supplier);
            product.setId(id);
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, default_price, currency_string, description, category_id, supplier_id FROM products";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            List<Product> productList = new ArrayList<>();
            while(resultSet.next()) {
                ProductCategory category = categoryDao.find(resultSet.getInt(6));
                Supplier supplier = supplierDao.find(resultSet.getInt(7));
                Product product = new Product(resultSet.getString(2), resultSet.getBigDecimal(3), resultSet.getString(4), resultSet.getString(5), category, supplier);
                product.setId(resultSet.getInt(1));
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

}
