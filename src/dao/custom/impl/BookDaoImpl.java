package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.BookDao;
import entity.BookEntity;

public class BookDaoImpl implements BookDao {

    @Override
    public boolean create(BookEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO book VALUES(?,?,?,?,?,?)", t.getBookID(), t.getTitle(),
                t.getDescription(), t.getCatID(), t.getAuthor(), t.getNob());

    }

    @Override
    public boolean update(BookEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE book SET title = ?, description = ?, catID = ?, author = ?, nob = ? WHERE bookID = ?",
                t.getTitle(), t.getDescription(), t.getCatID(), t.getAuthor(), t.getNob(), t.getBookID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM book WHERE bookID = ?", id);

    }

}
