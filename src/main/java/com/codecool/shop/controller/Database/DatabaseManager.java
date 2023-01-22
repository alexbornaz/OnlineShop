package com.codecool.shop.controller.Database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.jdbc.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.jdbc.ProductDaoJdbc;
import com.codecool.shop.dao.jdbc.SupplierDaoJdbc;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DatabaseManager {
    private static DatabaseManager dbManager = null;
    private ProductDao productDao;
    private SupplierDao supplierDao;
    private ProductCategoryDao categoryDao;
    private Properties properties;

    private DatabaseManager(){

    }
    public static DatabaseManager getInstance(){
        if(dbManager == null) {
            dbManager = new DatabaseManager();
        }
        return dbManager;
    }

    public void setup() throws SQLException, IOException {
        properties = initProperties();
        DataSource dataSource = connect(properties);
        supplierDao = new SupplierDaoJdbc(dataSource);
        categoryDao = new ProductCategoryDaoJdbc(dataSource);
        productDao = new ProductDaoJdbc(dataSource, categoryDao, supplierDao);
    }
    private Properties initProperties() throws IOException {
        String rootPath = "src/main/resources/";
        String appConfigPath = rootPath + "connection.properties";

        Properties properties = new Properties();
        properties.load(new FileInputStream(appConfigPath));
        return properties;
    }
    private DataSource connect(Properties properties) throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = properties.getProperty("database");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
    public ProductDao getProductDao() {
        return productDao;
    }

    public SupplierDao getSupplierDao() {
        return supplierDao;
    }

    public ProductCategoryDao getCategoryDao() {
        return categoryDao;
    }
    public Properties getProperties() {
        return properties;
    }
}
