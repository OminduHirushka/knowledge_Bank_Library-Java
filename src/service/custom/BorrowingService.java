package service.custom;

import java.util.ArrayList;

import dto.BorrowingDto;
import service.SuperService;

public interface BorrowingService extends SuperService {

    String save(BorrowingDto borrowingDto) throws Exception;
    String update(BorrowingDto borrowingDto) throws Exception;
    String delete(String brID) throws Exception;
    BorrowingDto get(String brID) throws Exception;
    ArrayList<BorrowingDto> getAll() throws Exception;

}