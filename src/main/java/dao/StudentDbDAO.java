package dao;

import domain.Student;
import exception.DAOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StudentDbDAO implements RepositoryDAO<Student> {
    private static final String select_all = "SELECT * FROM students ORDER BY last_name ASC";
    private static final String select_by_id = "SELECT * FROM students WHERE id = ?";
    private static final String insert_student = "INSERT INTO students(group_id, last_name, first_name, middle_name, birth_date, phone, email) VALUES(?,?,?,?,?,?,?)";
    private static final String update_student = "UPDATE students SET group_id=?, last_name=?, first_name=?, middle_name=?, birth_date=?, phone=?, email=? WHERE id=?";
    private static final String delete_student = "DELETE FROM students WHERE id=?";

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
    public void update(Student student) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(update_student)) {
            pst.setLong(1, student.getIdGroup());
            pst.setString(2, student.getLastName());
            pst.setString(3, student.getFirstName());
            pst.setString(4, student.getMiddleName());
            pst.setString(5, student.getBirthDate());
            pst.setString(6, student.getPhone());
            pst.setString(7, student.getEmail());
            pst.setLong(8, student.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(delete_student)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Student findById(Long id) throws DAOException {
        Student student = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(select_by_id)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getLong("id"));
                student.setIdGroup(rs.getLong("group_id"));
                student.setLastName(rs.getString("last_name"));
                student.setFirstName(rs.getString("first_name"));
                student.setMiddleName(rs.getString("middle_name"));
                student.setBirthDate(rs.getString("birth_date"));
                student.setPhone(rs.getString("phone"));
                student.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return student;
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
}