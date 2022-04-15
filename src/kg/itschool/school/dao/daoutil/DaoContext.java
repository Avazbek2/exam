package kg.itschool.school.dao.daoutil;

import kg.itschool.school.dao.CrudDao;
import kg.itschool.school.dao.Impl.StudentDaoImpl;
import kg.itschool.school.dao.StudentDao;

import java.sql.SQLException;

public abstract class DaoContext {
    static {
        try {
            System.out.println("Loading driver...");
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded.");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver loading failed");
            e.printStackTrace();
        }
    }

    private static  StudentDao studentDao;

    public static CrudDao<?> autowired(String qualifier, String scope) throws SQLException {
        if (!scope.equals("singleton") && !scope.equals("prototype")) {
            throw new RuntimeException("Invalid scope of bean " + scope);
        }
        switch (qualifier) {
            case "StudentDao":
                return studentDaoSql(scope);
            default:
                throw new RuntimeException("Can not find bean for autowiring: " + qualifier);
        }
    }

    private static StudentDao studentDaoSql(String scope) throws SQLException {
        if (scope.equals("prototype")) {
            return new StudentDaoImpl();
        }
        if (studentDao == null) {
            studentDao = new StudentDaoImpl();
        }
        return studentDao;
    }




}
