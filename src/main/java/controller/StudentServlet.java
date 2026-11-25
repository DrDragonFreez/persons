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

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    ConnectionProperty prop;

    public StudentServlet() throws IOException {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDbDAO daoStudent = new StudentDbDAO();
        GroupDbDAO daoGroup = new GroupDbDAO();
        try {
            List<Student> students = daoStudent.findAll();
            List<Group> groups = daoGroup.findAll();

            // Привязываем объект группы к каждому студенту
            for (Student s : students) {
                s.setGroup(daoGroup.FindById(s.getIdGroup(), groups));
            }

            request.setAttribute("students", students);
            request.setAttribute("groups", groups); // Для выпадающего списка в форме
        } catch (DAOException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/views/student.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        StudentDbDAO dao = new StudentDbDAO();

        // Получаем данные
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String birthDate = request.getParameter("birthDate");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        Long idGroup = Long.parseLong(request.getParameter("group_id"));

        // Создаем объект (используем конструктор)
        Student newStudent = new Student(lastName, firstName, middleName, birthDate, phone, email, null);
        newStudent.setIdGroup(idGroup);

        try {
            Long index = dao.insert(newStudent);
            System.out.println("Добавлен студент с ID: " + index);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        doGet(request, response);
    }
}