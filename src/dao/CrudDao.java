package dao;

public interface CrudDao<T, ID> extends SuperDao {
    
    boolean create(T t) throws Exception;

    boolean update(T t) throws Exception;

    boolean delete(ID id) throws Exception;

}
