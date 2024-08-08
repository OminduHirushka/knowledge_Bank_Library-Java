package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.MemberDao;
import entity.MemberEntity;

public class MemberDaoImpl implements MemberDao {

    @Override
    public boolean create(MemberEntity t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO member VALUES(?,?,?,?)", t.getMemberID(), t.getName(),
                t.getAddress(), t.getMobile());
    }

    @Override
    public boolean update(MemberEntity t) throws Exception {
        return CrudUtil.executeUpdate(
                "UPDATE member SET name = ?, address = ?, mobile = ? WHERE memberID = ?",
                t.getName(), t.getAddress(), t.getMobile(), t.getMemberID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM member WHERE memberID = ?", id);
    }
  
}
