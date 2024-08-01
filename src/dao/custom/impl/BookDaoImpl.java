package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

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

    @Override
    public BookEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM book WHERE bookID = ?", id);

        if (rst.next()) {
            BookEntity entity = new BookEntity(rst.getString("bookID"),
                    rst.getString("bookName"),
                    rst.getString("description"),
                    rst.getString("catID"),
                    rst.getString("author"),
                    rst.getInt("nob"));
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<BookEntity> getAll() throws Exception {
        ArrayList<BookEntity> bookEntities = new ArrayList<>();

        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM category");

        while (rst.next()) {
            BookEntity entity = new BookEntity(rst.getString("bookID"),
                    rst.getString("bookName"),
                    rst.getString("description"),
                    rst.getString("catID"),
                    rst.getString("author"),
                    rst.getInt("nob"));

            bookEntities.add(entity);
        }
        return bookEntities;
    }

}
