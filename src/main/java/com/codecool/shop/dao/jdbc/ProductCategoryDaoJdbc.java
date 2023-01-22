package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, department,description FROM categories WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) return null;
            ProductCategory category = new ProductCategory(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3));
            category.setId(id);
            return category;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, department FROM categories";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            List<ProductCategory> categoryList = new ArrayList<>();
            while(resultSet.next()) {
                ProductCategory category = new ProductCategory(resultSet.getString(2), resultSet.getString(3));
                category.setId(resultSet.getInt(1));
                categoryList.add(category);
            }
            return categoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
