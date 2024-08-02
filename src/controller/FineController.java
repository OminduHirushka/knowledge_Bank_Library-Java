package controller;

import dto.FineDto;
import service.ServiceFactory;
import service.custom.FineService;

public class FineController {

    private FineService fineService = (FineService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.FINE);

    public String save(FineDto fineDto) throws Exception {
        return fineService.save(fineDto);
    }

    public String update(FineDto fineDto) throws Exception {
        return fineService.update(fineDto);
    }

    public String delete(String brID) throws Exception {
        return fineService.delete(brID);
    }

}
