package controller;

import dto.MemberDto;
import service.ServiceFactory;
import service.custom.MemberService;

public class MemberController {

    private MemberService memberService = (MemberService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.MEMBER);

    public String save(MemberDto memberDto) throws Exception {
        return memberService.save(memberDto);
    }

    public String update(MemberDto memberDto) throws Exception {
        return memberService.update(memberDto);
    }

    public String delete(String bookID) throws Exception {
        return memberService.delete(bookID);
    }

}
