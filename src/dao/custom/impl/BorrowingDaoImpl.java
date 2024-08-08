package dao.custom.impl;

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

}
