package controller;

import dao.ConnectionProperty;
import dao.StudentDbDAO;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deletestudent")
public class DeleteStudentServlet extends HttpServlet {
    ConnectionProperty prop;

    public DeleteStudentServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDbDAO dao = new StudentDbDAO();
        String strId = request.getParameter("id");
        if (strId != null) {
            Long id = Long.parseLong(strId);
            try {
                dao.delete(id);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("/persons/student");
    }
}