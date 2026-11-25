package dao;

import domain.Group;
import exception.DAOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GroupDbDAO implements RepositoryDAO<Group> {
    private static final String select_all = "SELECT * FROM groupss ORDER BY name ASC";
    private static final String select_by_id = "SELECT * FROM groupss WHERE id = ?";
    private static final String insert_group = "INSERT INTO groupss(name, faculty, course, education_type) VALUES(?,?,?,?)";
    private static final String update_group = "UPDATE groupss SET name=?, faculty=?, course=?, education_type=? WHERE id=?";
    private static final String delete_group = "DELETE FROM groupss WHERE id=?";

    private ConnectionBuilder builder = new DbConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long insert(Group group) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(insert_group, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, group.getName());
            pst.setString(2, group.getFaculty());
            pst.setInt(3, group.getCourse());
            pst.setString(4, group.getEducationType());
            pst.executeUpdate();
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) return gk.getLong(1);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return -1L;
    }

    @Override
    public void update(Group group) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(update_group)) {
            pst.setString(1, group.getName());
            pst.setString(2, group.getFaculty());
            pst.setInt(3, group.getCourse());
            pst.setString(4, group.getEducationType());
            pst.setLong(5, group.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(delete_group)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Group findById(Long id) throws DAOException {
        Group group = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(select_by_id)) {
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                group = new Group();
                group.setId(rs.getLong("id"));
                group.setName(rs.getString("name"));
                group.setFaculty(rs.getString("faculty"));
                group.setCourse(rs.getInt("course"));
                group.setEducationType(rs.getString("education_type"));
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return group;
    }

    @Override
    public List<Group> findAll() throws DAOException {
        List<Group> list = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(select_all);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Group g = new Group();
                g.setId(rs.getLong("id"));
                g.setName(rs.getString("name"));
                g.setFaculty(rs.getString("faculty"));
                g.setCourse(rs.getInt("course"));
                g.setEducationType(rs.getString("education_type"));
                list.add(g);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }

    public Group FindById(Long id, List<Group> groups) {
        if (groups != null) {
            for (Group g : groups) {
                if (g.getId().equals(id)) return g;
            }
        }
        return null;
    }
}