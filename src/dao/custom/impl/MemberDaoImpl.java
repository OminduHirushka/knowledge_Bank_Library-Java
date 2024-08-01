package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

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

    @Override
    public MemberEntity get(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM member WHERE memberID = ?", id);

        if (rst.next()) {
            MemberEntity entity = new MemberEntity(rst.getString("memberID"),
                    rst.getString("name"),
                    rst.getString("address"),
                    rst.getString("mobile"));
            return entity;
        }
        return null;
    }

    @Override
    public ArrayList<MemberEntity> getAll() throws Exception {
        ArrayList<MemberEntity> memberEntities = new ArrayList<>();

        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM member");

        while (rst.next()) {
            MemberEntity entity = new MemberEntity(rst.getString("memberID"),
            rst.getString("name"),
            rst.getString("address"),
            rst.getString("mobile"));

            memberEntities.add(entity);
        }
        return memberEntities;
    }
  
}
