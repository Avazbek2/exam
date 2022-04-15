package kg.itschool.school.dao.Impl;

import kg.itschool.school.dao.StudentDao;
import kg.itschool.school.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {

    public StudentDaoImpl() throws SQLException {
        Connection connection = null;
        Statement statement = null;

        try {  // api:driver://host:port/database_name
            System.out.println("Connecting to database...");
            connection = getConnection();
            System.out.println("Connection succeeded.");

            String ddlQuery = "CREATE TABLE IF NOT EXISTS tb_students(" +
                    "id           BIGSERIAL, " +
                    "first_name   VARCHAR(50)  NOT NULL, " +
                    "last_name     VARCHAR(50)  NOT NULL, " +
                    "email        VARCHAR(100) NOT NULL UNIQUE, " +
                    "phone_number CHAR(13)     NOT NULL, " +
                    "dob          DATE         NOT NULL CHECK(dob < NOW()), " +
                    "date_created TIMESTAMP    NOT NULL DEFAULT NOW() " +
                    ");";

            System.out.println("Creating statement...");
            statement = connection.createStatement();
            System.out.println("Executing create table statement...");
            statement.execute(ddlQuery);

        } catch (SQLException e) {
            System.out.println("Some error occurred");
            e.printStackTrace();
        } finally {
            statement.close();
            connection.close();
        }
    }


    @Override
    public Optional<Student> save(Student student) {
        return Optional.empty();
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List <Student> students = new ArrayList<>();

        try {

            System.out.println("Connecting");
            connection = getConnection();
            System.out.println("Connection succeful");

            String readQuery = "SELECT * FROM tb_students;";

            preparedStatement = connection.prepareStatement(readQuery);

            resultSet = preparedStatement.executeQuery();

            for (int i = 0; i <= students.size() && resultSet.next(); i++) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setEmail(resultSet.getString("email"));
                student.setPhoneNumber(resultSet.getString("phone_number"));
                student.setDob(resultSet.getDate("dob").toLocalDate());
                student.setDateCreated(resultSet.getTimestamp("date_created").toLocalDateTime());
                students.add(student);
            }
            return students;
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return null;

    }


    @Override
    public List<Student> saveAll(List<Student> students) {
        return null;
    }
}
