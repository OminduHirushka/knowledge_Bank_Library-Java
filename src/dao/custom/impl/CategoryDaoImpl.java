package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.CategoryDao;
import entity.CategoryEntity;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public boolean create(CategoryEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO category VALUES(?,?)", t.getCatID(), t.getCatName());
    }

    @Override
    public boolean update(CategoryEntity t) throws Exception {
        return CrudUtil.executeUpdate("UPDATE category SET catName = ? WHERE catID = ?", t.getCatName(), t.getCatID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM category WHERE catID = ?", id);
    }

    @Override
    public CategoryEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM category WHERE catID = ?", id);

        if (rst.next()) {
            CategoryEntity entity = new CategoryEntity(rst.getString("catID"),
                    rst.getString("catName"));
            return entity;
        }
        return null;

    }

    @Override
    public ArrayList<CategoryEntity> getAll() throws Exception {
        ArrayList<CategoryEntity> categoryEntities = new ArrayList<>();

        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM category");

        while (rst.next()) {
            CategoryEntity entity = new CategoryEntity(rst.getString("catID"),
                    rst.getString("catName"));

            categoryEntities.add(entity);
        }
        return categoryEntities;
    }

}
