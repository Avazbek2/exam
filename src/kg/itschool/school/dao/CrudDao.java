package kg.itschool.school.dao;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDao<Model> {
    Optional<Model> save(Model model);
    Optional<Model> findById(Long id);
    List<Model> findAll() throws SQLException;
    List<Model> saveAll(List<Model> models);

    default Connection getConnection() throws SQLException {
        final String URL = "jdbc:postgresql://localhost:5432/postgres";
        final String USERNAME = "postgres";
        final String PASSWORD = "$_admin_$";

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}
