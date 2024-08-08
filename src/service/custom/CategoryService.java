package service.custom;

import dto.CategoryDto;
import service.SuperService;

public interface CategoryService extends SuperService {

    String save(CategoryDto categoryDto) throws Exception;
    String update(CategoryDto categoryDto) throws Exception;
    String delete(String catID) throws Exception;

}
