package controller;

import dao.ConnectionProperty;
import dao.GroupDbDAO;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deletegroup")
public class DeleteGroupServlet extends HttpServlet {
    ConnectionProperty prop;

    public DeleteGroupServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupDbDAO dao = new GroupDbDAO();
        String strId = request.getParameter("id");
        if (strId != null) {
            Long id = Long.parseLong(strId);
            try {
                dao.delete(id);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("/persons/group");
    }
}