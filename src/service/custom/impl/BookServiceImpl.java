package service.custom.impl;

import dao.DaoFactory;
import dao.custom.BookDao;
import dto.BookDto;
import entity.BookEntity;
import service.custom.BookService;

public class BookServiceImpl implements BookService {

    private BookDao bookDao = (BookDao) DaoFactory.getInstance().getDao(DaoFactory.DaoTypes.BOOK);

    private BookEntity getBookEntity(BookDto bookDto) {
        return new BookEntity(
                bookDto.getBookID(),
                bookDto.getTitle(),
                bookDto.getDescription(),
                bookDto.getCatID(),
                bookDto.getAuthor(),
                bookDto.getNob());
    }

    @Override
    public String save(BookDto bookDto) throws Exception {
        BookEntity entity = getBookEntity(bookDto);
        return bookDao.create(entity) ? "Success" : "Fail";
    }

    @Override
    public String update(BookDto bookDto) throws Exception {
        BookEntity entity = getBookEntity(bookDto);
        return bookDao.update(entity) ? "Success" : "Fail";
    }

    @Override
    public String delete(String bookID) throws Exception {
        return bookDao.delete(bookID) ? "Success" : "Fail";
    }

}
