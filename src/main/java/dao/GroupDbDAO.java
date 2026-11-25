package dao;

import domain.Group;
import exception.DAOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GroupDbDAO implements RepositoryDAO<Group> {
    // SQL-запрос (обрати внимание на обратные кавычки вокруг `groups`)
    private static final String select_all = "SELECT * FROM groupss ORDER BY name ASC";
    private static final String insert_group = "INSERT INTO groupss(name, faculty, course, education_type) VALUES(?,?,?,?)";

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

    // Заглушки для неиспользуемых пока методов
    @Override public void update(Group group) throws DAOException {}
    @Override public void delete(Long id) throws DAOException {}
    @Override public Group findById(Long id) throws DAOException { return null; }

    // Вспомогательный метод для поиска группы в списке (нужен для студентов)
    public Group FindById(Long id, List<Group> groups) {
        if (groups != null) {
            for (Group g : groups) {
                if (g.getId().equals(id)) return g;
            }
        }
        return null;
    }
}