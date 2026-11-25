package controller;

import dao.ConnectionProperty;
import dao.GroupDbDAO;
import dao.StudentDbDAO;
import domain.Group;
import domain.Student;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/editstudent")
public class EditStudentServlet extends HttpServlet {
    ConnectionProperty prop;

    public EditStudentServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDbDAO daoStudent = new StudentDbDAO();
        GroupDbDAO daoGroup = new GroupDbDAO();
        try {
            List<Student> students = daoStudent.findAll();
            List<Group> groups = daoGroup.findAll();

            for (Student s : students) {
                s.setGroup(daoGroup.FindById(s.getIdGroup(), groups));
            }

            request.setAttribute("students", students);
            request.setAttribute("groups", groups);

            String strId = request.getParameter("id");
            if (strId != null) {
                Long id = Long.parseLong(strId);
                Student editStudent = daoStudent.findById(id);
                request.setAttribute("studentEdit", editStudent);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/editstudent.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        StudentDbDAO dao = new StudentDbDAO();

        Long id = Long.parseLong(request.getParameter("id"));
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String birthDate = request.getParameter("birthDate");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        Long idGroup = Long.parseLong(request.getParameter("group_id"));

        Student updateStudent = new Student(id, lastName, firstName, middleName, birthDate, phone, email, idGroup, null);

        try {
            dao.update(updateStudent);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/persons/student");
    }
}