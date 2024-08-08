package service.custom.impl;

import dao.DaoFactory;
import dao.custom.MemberDao;
import dto.MemberDto;
import entity.MemberEntity;
import service.custom.MemberService;

public class MemberServiceImpl implements MemberService {

    private MemberDao memberDao = (MemberDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.MEMBER);

    private MemberEntity getMemberEntity(MemberDto memberDto) {
        return new MemberEntity(
                memberDto.getMemberID(),
                memberDto.getName(),
                memberDto.getAddress(),
                memberDto.getMobile());
    }

    @Override
    public String save(MemberDto memberDto) throws Exception {
        MemberEntity entity = getMemberEntity(memberDto);
        return memberDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(MemberDto memberDto) throws Exception {
        MemberEntity entity = getMemberEntity(memberDto);
        return memberDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String memberID) throws Exception {
        return memberDao.delete(memberID) ? "Success" : "Fail";
    }

}
