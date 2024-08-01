package service.custom.impl;

import java.util.ArrayList;

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

    private CategoryDto getCategoryDto(CategoryEntity entity) {
        return new CategoryDto(
                entity.getCatID(),
                entity.getCatName());
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

    @Override
    public CategoryDto get(String catID) throws Exception {
        CategoryEntity entity = categoryDao.get(catID);

        if (entity != null) {
            return getCategoryDto(entity);
        }
        return null;
    }

    @Override
    public ArrayList<CategoryDto> getAll() throws Exception {
        ArrayList<CategoryEntity> categoryEntities = categoryDao.getAll();

        if (categoryEntities != null && !categoryEntities.isEmpty()) {
            ArrayList<CategoryDto> categoryDtos = new ArrayList<>();

            for (CategoryEntity categoryEntity : categoryEntities) {
                categoryDtos.add(getCategoryDto(categoryEntity));
            }
            return categoryDtos;
        }

        return null;
    }

}
