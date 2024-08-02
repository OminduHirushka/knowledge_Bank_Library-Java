package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.BorrowingDao;
import entity.BorrowingEntity;

public class BorrowingDaoImpl implements BorrowingDao {

    @Override
    public boolean create(BorrowingEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO borrowing VALUES(?,?,?,?,?,?,?)", t.getBrID(), t.getBookID(),
                t.getMemberID(), t.getBrDate(), t.getDueDate(), t.getReturnDate(), t.getStatus());
    }

    @Override
    public boolean update(BorrowingEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE borrowing SET bookID = ?, memberID = ?, brDate = ?, dueDate = ?, returnDate = ?, status = ? WHERE brID = ?",
                t.getBookID(), t.getMemberID(), t.getBrDate(), t.getDueDate(), t.getReturnDate(), t.getStatus(),
                t.getBrID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM borrowing WHERE brID = ?", id);
    }

    @Override
    public BorrowingEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM borrowing WHERE brID = ?", id);

        if (rst.next()) {
            BorrowingEntity entity = new BorrowingEntity(rst.getString("brID"),
                    rst.getString("BookID"),
                    rst.getString("memberID"),
                    rst.getString("brDate"),
                    rst.getString("dueDate"),
                    rst.getString("returnDate"),
                    rst.getString("status"));
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<BorrowingEntity> getAll() throws Exception {
        ArrayList<BorrowingEntity> borrowingEntities = new ArrayList<>();

        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM borrowing");

        while (rst.next()) {
            BorrowingEntity entity = new BorrowingEntity(rst.getString("brID"),
                    rst.getString("BookID"),
                    rst.getString("memberID"),
                    rst.getString("brDate"),
                    rst.getString("dueDate"),
                    rst.getString("returnDate"),
                    rst.getString("status"));

            borrowingEntities.add(entity);
        }
        return borrowingEntities;
    }

}
