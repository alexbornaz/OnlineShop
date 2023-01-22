package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    private DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name,description FROM suppliers WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) return null;
            Supplier supplier = new Supplier(resultSet.getString(1),resultSet.getString(2));
            supplier.setId(id);
            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name,description FROM suppliers";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);
            List<Supplier> supplierList = new ArrayList<>();
            while(resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getString(2),resultSet.getString(3));
                supplier.setId(resultSet.getInt(1));
                supplierList.add(supplier);
            }
            return supplierList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
