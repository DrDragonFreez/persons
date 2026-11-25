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

@WebServlet("/editgroup")
public class EditGroupServlet extends HttpServlet {
    ConnectionProperty prop;

    public EditGroupServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDbDAO dao = new GroupDbDAO();
        try {
            List<Group> groups = dao.findAll();
            request.setAttribute("groups", groups);

            String strId = request.getParameter("id");
            if (strId != null) {
                Long id = Long.parseLong(strId);
                Group editGroup = dao.findById(id);
                request.setAttribute("groupEdit", editGroup);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/editgroup.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        GroupDbDAO dao = new GroupDbDAO();

        Long id = Long.parseLong(request.getParameter("id")); // Берем ID из скрытого поля (или из URL)
        String name = request.getParameter("name");
        String faculty = request.getParameter("faculty");
        int course = Integer.parseInt(request.getParameter("course"));
        String eduType = request.getParameter("eduType");

        Group updateGroup = new Group(id, name, faculty, course, eduType);
        try {
            dao.update(updateGroup);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/persons/group");
    }
}