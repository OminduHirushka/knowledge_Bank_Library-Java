package service.custom.impl;

import dao.DaoFactory;
import dao.custom.BorrowingDao;
import dto.BorrowingDto;
import entity.BorrowingEntity;
import service.custom.BorrowingService;

public class BorrowingServiceImpl implements BorrowingService {

    private BorrowingDao borrowingDao = (BorrowingDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BORROWING);

    private BorrowingEntity getBorrowingEntity(BorrowingDto borrowingDto) {
        return new BorrowingEntity(
                borrowingDto.getBrID(),
                borrowingDto.getBookID(),
                borrowingDto.getMemberID(),
                borrowingDto.getBrDate(),
                borrowingDto.getDueDate(),
                borrowingDto.getReturnDate(),
                borrowingDto.getStatus());
    }

    @Override
    public String save(BorrowingDto borrowingDto) throws Exception {
        BorrowingEntity entity = getBorrowingEntity(borrowingDto);
        return borrowingDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(BorrowingDto borrowingDto) throws Exception {
        BorrowingEntity entity = getBorrowingEntity(borrowingDto);
        return borrowingDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String brID) throws Exception {
        return borrowingDao.delete(brID) ? "Success" : "Fail";
    }

}
