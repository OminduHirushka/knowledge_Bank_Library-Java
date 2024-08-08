package service.custom.impl;

import dao.DaoFactory;
import dao.custom.CategoryDao;
import dto.CategoryDto;
import entity.CategoryEntity;
import service.custom.CategoryService;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = (CategoryDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.CATEGORY);

    private CategoryEntity getCategoryEntity(CategoryDto categoryDto) {
        return new CategoryEntity(
                categoryDto.getCatID(),
                categoryDto.getCatName());
    }

    @Override
    public String save(CategoryDto categoryDto) throws Exception {
        CategoryEntity entity = getCategoryEntity(categoryDto);
        return categoryDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(CategoryDto categoryDto) throws Exception {
        CategoryEntity entity = getCategoryEntity(categoryDto);
        return categoryDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String catID) throws Exception {
        return categoryDao.delete(catID) ? "Success" : "Fail";
    }

}
