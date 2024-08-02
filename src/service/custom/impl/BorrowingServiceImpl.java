package service.custom.impl;

import java.util.ArrayList;

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

    private BorrowingDto getBorrowingDto(BorrowingEntity entity) {
        return new BorrowingDto(
                entity.getBrID(),
                entity.getBookID(),
                entity.getMemberID(),
                entity.getBrDate(),
                entity.getDueDate(),
                entity.getReturnDate(),
                entity.getStatus());
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

    @Override
    public BorrowingDto get(String brID) throws Exception {
        BorrowingEntity entity = borrowingDao.get(brID);

        if (entity != null) {
            return getBorrowingDto(entity);
        }
        return null;
    }

    @Override
    public ArrayList<BorrowingDto> getAll() throws Exception {
        ArrayList<BorrowingEntity> borrowingEntities = borrowingDao.getAll();

        if (borrowingEntities != null && !borrowingEntities.isEmpty()) {
            ArrayList<BorrowingDto> borrowingDtos = new ArrayList<>();

            for (BorrowingEntity borrowingEntity : borrowingEntities) {
                borrowingDtos.add(getBorrowingDto(borrowingEntity));
            }
            return borrowingDtos;
        }

        return null;
    }

}
