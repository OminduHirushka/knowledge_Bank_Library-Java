package controller;

import dto.BorrowingDto;
import service.ServiceFactory;
import service.custom.BorrowingService;

public class BorrowingController {

    private BorrowingService borrowingService = (BorrowingService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.BORROWING);

    public String save(BorrowingDto borrowingDto) throws Exception {
        return borrowingService.save(borrowingDto);
    }

    public String update(BorrowingDto borrowingDto) throws Exception {
        return borrowingService.update(borrowingDto);
    }

    public String delete(String brID) throws Exception {
        return borrowingService.delete(brID);
    }

}
