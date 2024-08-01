package controller;

import java.util.ArrayList;

import dto.CategoryDto;
import service.ServiceFactory;
import service.custom.CategoryService;

public class CategoryContoller {

    private CategoryService categoryService = (CategoryService) ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CATEGORY);

    public String save(CategoryDto categoryDto) throws Exception {
        return categoryService.save(categoryDto);
    }

    public String update(CategoryDto categoryDto) throws Exception {
        return categoryService.update(categoryDto);
    }

    public String delete(String catID) throws Exception {
        return categoryService.delete(catID);
    }

    public CategoryDto get(String catID) throws Exception {
        return categoryService.get(catID);
    }

    public ArrayList<CategoryDto> getAll() throws Exception {
        return categoryService.getAll();
    }

}
