package service.custom;

import dto.FineDto;
import service.SuperService;

public interface FineService extends SuperService {

    String save(FineDto fineDto) throws Exception;
    String update(FineDto fineDto) throws Exception;
    String delete(String fineID) throws Exception;

}
