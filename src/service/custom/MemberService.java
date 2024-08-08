package service.custom;

import dto.MemberDto;
import service.SuperService;

public interface MemberService extends SuperService {

    String save(MemberDto memberDto) throws Exception;
    String update(MemberDto memberDto) throws Exception;
    String delete(String memberID) throws Exception;

}
