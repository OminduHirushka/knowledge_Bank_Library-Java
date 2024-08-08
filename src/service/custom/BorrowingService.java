package service.custom;

import dto.BorrowingDto;
import service.SuperService;

public interface BorrowingService extends SuperService {

    String save(BorrowingDto borrowingDto) throws Exception;
    String update(BorrowingDto borrowingDto) throws Exception;
    String delete(String brID) throws Exception;

}