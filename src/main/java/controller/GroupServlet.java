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
        // Устанавливаем кодировку, чтобы русские буквы сохранялись корректно
        request.setCharacterEncoding("UTF-8");

        GroupDbDAO dao = new GroupDbDAO();

        // Получаем данные из формы
        String name = request.getParameter("inputName");
        String faculty = request.getParameter("inputFaculty");
        int course = Integer.parseInt(request.getParameter("inputCourse"));
        String educationType = request.getParameter("inputEduType");

        // Создаем объект и сохраняем
        Group newGroup = new Group(name, faculty, course, educationType);
        try {
            Long index = dao.insert(newGroup);
            System.out.println("Добавлена группа с ID: " + index);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        // Обновляем страницу (вызываем doGet, чтобы список перезагрузился)
        doGet(request, response);
    }
}