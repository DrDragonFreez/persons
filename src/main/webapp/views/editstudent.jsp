<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Student"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактирование студента</title>
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
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${students}">
                            <tr>
                                <td>${student.getId()}</td>
                                <td>${student.getLastName()}</td>
                                <td>${student.getFirstName()}</td>
                                <td>${student.getGroupName()}</td>
                                <td>${student.getPhone()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4">
                <form method="POST" action="">
                    <h3>Редактирование данных</h3>
                    <input type="hidden" name="id" value="${studentEdit.getId()}">

                    <div class="mb-3">
                        <label for="idstudent" class="form-label">Код студента</label>
                        <input type="text" class="form-control" readonly value="${studentEdit.getId()}">
                    </div>
                    <div class="mb-3">
                        <label for="lastName" class="form-label">Фамилия</label>
                        <input type="text" name="lastName" class="form-control" value="${studentEdit.getLastName()}" required>
                    </div>
                    <div class="mb-3">
                        <label for="firstName" class="form-label">Имя</label>
                        <input type="text" name="firstName" class="form-control" value="${studentEdit.getFirstName()}" required>
                    </div>
                    <div class="mb-3">
                        <label for="middleName" class="form-label">Отчество</label>
                        <input type="text" name="middleName" class="form-control" value="${studentEdit.getMiddleName()}" required>
                    </div>
                    <div class="mb-3">
                        <label for="birthDate" class="form-label">Дата рождения</label>
                        <input type="date" name="birthDate" class="form-control" value="${studentEdit.getBirthDate()}" required>
                    </div>

                    <!-- Выпадающий список групп -->
                    <div class="mb-3">
                        <label for="group_id" class="form-label">Группа</label>
                        <select name="group_id" class="form-control" required>
                            <c:forEach var="g" items="${groups}">
                                <option value="${g.getId()}" ${g.getId() == studentEdit.getIdGroup() ? 'selected' : ''}>
                                    ${g.getName()}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="phone" class="form-label">Телефон</label>
                        <input type="text" name="phone" class="form-control" value="${studentEdit.getPhone()}" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Эл.почта</label>
                        <input type="email" name="email" class="form-control" value="${studentEdit.getEmail()}" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Редактировать</button>
                    <a href='<c:url value="/student" />' role="button" class="btn btn-secondary">Отменить</a>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>