package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.FineDao;
import entity.FineEntity;

public class FineDaoImpl implements FineDao {

    @Override
    public boolean create(FineEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO fine VALUES(?,?,?,?,?,?)", t.getFineID(), t.getBrID(),
                t.getAmount(), t.getIssuedDate(), t.getPaidDate(), t.getStatus());
    }

    @Override
    public boolean update(FineEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE fine SET brID = ?, amount = ?, issuedDate = ?, paidDate = ?, status = ? WHERE fineID = ?",
                t.getBrID(), t.getAmount(), t.getIssuedDate(), t.getPaidDate(), t.getStatus(), t.getFineID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM fine WHERE fineID = ?", id);
    }

}
