<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Group"%>
<%@ page import="domain.Student"%>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Студенты</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script defer src="js/jquery-3.6.4.js"></script>
    <script defer src="js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid mt-3">
        <div class="row justify-content-start">
            <div class="col-8 border bg-light px-4">
                <h3>Список студентов</h3>
                <table class="table table-sm">
                    <thead>
                        <th scope="col">Код</th>
                        <th scope="col">Фамилия</th>
                        <th scope="col">Имя</th>
                        <th scope="col">Группа</th>
                        <th scope="col">Телефон</th>
                        <th scope="col">Ред.</th>
                        <th scope="col">Удалить</th>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.getId()}</td>
                                <td>${student.getLastName()}</td>
                                <td>${student.getFirstName()}</td>
                                <td>${student.getGroupName()}</td>
                                <td>${student.getPhone()}</td>
                                <td width="20">
                                    <a href='<c:url value="/editstudent?id=${student.getId()}" />' role="button" class="btn btn-outline-primary">
                                        <img alt="Ред." src="images/icon-edit.png" width="20">
                                    </a>
                                </td>
                                <td width="20">
                                    <a href='<c:url value="/deletestudent?id=${student.getId()}" />' role="button" class="btn btn-outline-primary" onclick="return confirm('Удалить студента ${student.getLastName()} ${student.getFirstName()}?')">
                                        <img alt="Удалить" src="images/icon-delete.png" width="20">
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4">
                <form method="POST" action="">
                    <h3>Новый студент</h3>
                    <div class="mb-3">
                        <label for="lastName" class="form-label">Фамилия</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" required>
                    </div>
                    <div class="mb-3">
                        <label for="firstName" class="form-label">Имя</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" required>
                    </div>
                    <div class="mb-3">
                        <label for="middleName" class="form-label">Отчество</label>
                        <input type="text" class="form-control" id="middleName" name="middleName" required>
                    </div>
                    <div class="mb-3">
                        <label for="birthDate" class="form-label">Дата рождения</label>
                        <input type="date" class="form-control" id="birthDate" name="birthDate" required>
                    </div>
                    <div class="mb-3">
                        <label for="group" class="form-label">Группа</label>
                        <select name="group_id" class="form-control" required>
                            <option value="">Выберите группу</option>
                            <c:forEach var="g" items="${groups}">
                                <option value="${g.getId()}">${g.getName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">Телефон</label>
                        <input type="text" class="form-control" id="phone" name="phone" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Эл.почта</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>