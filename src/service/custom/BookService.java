package service.custom;

import dto.BookDto;
import service.SuperService;

public interface BookService extends SuperService {

    String save(BookDto bookDto) throws Exception;
    String update(BookDto bookDto) throws Exception;
    String delete(String bookID) throws Exception;

}
