package kg.itschool.school;

import kg.itschool.school.dao.Impl.StudentDaoImpl;
import kg.itschool.school.dao.StudentDao;
import kg.itschool.school.dao.daoutil.DaoContext;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        StudentDao studentDao = (StudentDao) DaoContext.autowired("StudentDao" , "singleton");

        System.out.println(studentDao.findAll());
    }
}
