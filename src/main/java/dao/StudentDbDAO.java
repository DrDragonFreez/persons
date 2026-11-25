package dao;

import domain.Student;
import exception.DAOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StudentDbDAO implements RepositoryDAO<Student> {
    private static final String select_all = "SELECT * FROM students ORDER BY last_name ASC";
    private static final String insert_student = "INSERT INTO students(group_id, last_name, first_name, middle_name, birth_date, phone, email) VALUES(?,?,?,?,?,?,?)";

    private ConnectionBuilder builder = new DbConnectionBuilder();
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Student student) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(insert_student, Statement.RETURN_GENERATED_KEYS)) {
            pst.setLong(1, student.getIdGroup());
            pst.setString(2, student.getLastName());
            pst.setString(3, student.getFirstName());
            pst.setString(4, student.getMiddleName());
            pst.setString(5, student.getBirthDate());
            pst.setString(6, student.getPhone());
            pst.setString(7, student.getEmail());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) return gk.getLong(1);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return -1L;
    }

    @Override
    public List<Student> findAll() throws DAOException {
        List<Student> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getLong("id"));
                s.setIdGroup(rs.getLong("group_id"));
                s.setLastName(rs.getString("last_name"));
                s.setFirstName(rs.getString("first_name"));
                s.setMiddleName(rs.getString("middle_name"));
                s.setBirthDate(rs.getString("birth_date"));
                s.setPhone(rs.getString("phone"));
                s.setEmail(rs.getString("email"));
                list.add(s);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    @Override public void update(Student student) throws DAOException {}
    @Override public void delete(Long id) throws DAOException {}
    @Override public Student findById(Long id) throws DAOException { return null; }
}