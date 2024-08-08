package dao.custom.impl;

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

}
