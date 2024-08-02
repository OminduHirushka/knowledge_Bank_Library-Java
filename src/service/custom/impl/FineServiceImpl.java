package service.custom.impl;

import dao.DaoFactory;
import dao.custom.FineDao;
import dto.FineDto;
import entity.FineEntity;
import service.custom.FineService;

public class FineServiceImpl implements FineService {

    private FineDao fineDao = (FineDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.FINE);

    private FineEntity getFineEntity(FineDto fineDto) {
        return new FineEntity(
            fineDto.getFineID(),
            fineDto.getBrID(),
            fineDto.getAmount(),
            fineDto.getIssuedDate(),
            fineDto.getPaidDate(),
            fineDto.getStatus());
    }

    @Override
    public String save(FineDto fineDto) throws Exception {
        FineEntity entity = getFineEntity(fineDto);
        return fineDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(FineDto fineDto) throws Exception {
        FineEntity entity = getFineEntity(fineDto);
        return fineDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String fineID) throws Exception {
        return fineDao.delete(fineID) ? "Success" : "Fail";
    }
    
}
