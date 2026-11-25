<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Group"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Редактирование группы</title>
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
                <h3>Список групп</h3>
                <table class="table">
                    <thead>
                        <th scope="col">Код</th>
                        <th scope="col">Наименование</th>
                        <th scope="col">Факультет</th>
                        <th scope="col">Курс</th>
                        <th scope="col">Вид обучения</th>
                    </thead>
                    <tbody>
                        <c:forEach var="group" items="${groups}">
                            <tr>
                                <td>${group.getId()}</td>
                                <td>${group.getName()}</td>
                                <td>${group.getFaculty()}</td>
                                <td>${group.getCourse()}</td>
                                <td>${group.getEducationType()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4">
                <form method="POST" action="">
                    <h3>Редактировать группу</h3>
                    <div class="mb-3">
                        <label for="idgroup" class="form-label">Код группы</label>
                        <input type="text" class="form-control" readonly value="${groupEdit.getId()}">
                    </div>
                    <div class="mb-3">
                        <label for="name" class="form-label">Наименование</label>
                        <input type="text" name="name" class="form-control" value="${groupEdit.getName()}" required>
                    </div>
                    <div class="mb-3">
                        <label for="faculty" class="form-label">Факультет</label>
                        <input type="text" name="faculty" class="form-control" value="${groupEdit.getFaculty()}" required>
                    </div>
                    <div class="mb-3">
                        <label for="course" class="form-label">Курс</label>
                        <input type="number" name="course" class="form-control" value="${groupEdit.getCourse()}" required>
                    </div>
                    <div class="mb-3">
                        <label for="eduType" class="form-label">Вид обучения</label>
                        <input type="text" name="eduType" class="form-control" value="${groupEdit.getEducationType()}" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Редактировать</button>
                    <a href='<c:url value="/group" />' role="button" class="btn btn-secondary">Отменить</a>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>