package service.custom.impl;

import java.util.ArrayList;

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

    private MemberDto getMemberDto(MemberEntity entity) {
        return new MemberDto(
            entity.getMemberID(),
            entity.getName(),
            entity.getAddress(),
            entity.getMobile());
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

    @Override
    public MemberDto get(String memberID) throws Exception {
        MemberEntity entity = memberDao.get(memberID);

        if (entity != null) {
            return getMemberDto(entity);
        }
        return null;
    }

    @Override
    public ArrayList<MemberDto> getAll() throws Exception {
        ArrayList<MemberEntity> memberEntities = memberDao.getAll();

        if (memberEntities != null && !memberEntities.isEmpty()) {
            ArrayList<MemberDto> memberDtos = new ArrayList<>();

            for (MemberEntity memberEntity : memberEntities) {
                memberDtos.add(getMemberDto(memberEntity));
            }
            return memberDtos;
        }

        return null;
    }
    
}
