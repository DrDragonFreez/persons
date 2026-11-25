package controller;

import dao.ConnectionProperty;
import dao.GroupDbDAO;
import domain.Group;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

// Аннотация теперь /group (чтобы было красиво)
@WebServlet("/group")
public class GroupServlet extends HttpServlet {
    ConnectionProperty prop;

    public GroupServlet() throws IOException {
        super();
        prop = new ConnectionProperty(); // Загружаем конфиг при старте
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDbDAO dao = new GroupDbDAO();
        try {
            List<Group> groups = dao.findAll();
            request.setAttribute("groups", groups); // Передаем в JSP
        } catch (DAOException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/group.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}