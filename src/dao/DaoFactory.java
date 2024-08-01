package dao;

import dao.custom.impl.BookDaoImpl;
import dao.custom.impl.CategoryDaoImpl;

public class DaoFactory {

    private static DaoFactory daoFactory;

    public DaoFactory() {
    }

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public SuperDao getDao(DaoTypes type) {
        switch (type) {
            case CATEGORY:
                return new CategoryDaoImpl();
            case BOOK:
                return new BookDaoImpl();
            default:
                return null;
        }
    }

    public enum DaoTypes {
        CATEGORY, BOOK
    }

}
