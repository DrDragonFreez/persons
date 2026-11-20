<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Group"%>
<%
    Group g1 = new Group(1L, "ИС-21", "Информатика", 2, "Бюджет");
    Group g2 = new Group(2L, "ПИ-22", "Программирование", 2, "Контракт");
    Group g3 = new Group(3L, "ЭК-20", "Экономика", 4, "Бюджет");
    Group[] groups = new Group[]{g1, g2, g3};
    pageContext.setAttribute("groups", groups);
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Студенческие группы</title>
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
                        <th scope="col">Ред.</th>
                        <th scope="col">Удалить</th>
                    </thead>
                    <tbody>
                        <c:forEach var="group" items="${groups}">
                            <tr>
                                <td>${group.getId()}</td>
                                <td>${group.getName()}</td>
                                <td>${group.getFaculty()}</td>
                                <td>${group.getCourse()}</td>
                                <td>${group.getEducationType()}</td>
                                <td width="20"><a href="#" role="button" class="btn btn-outline-primary"><img alt="Ред." src="images/icon-edit.png" width="20"></a></td>
                                <td width="20"><a href="#" role="button" class="btn btn-outline-primary"><img alt="Удалить" src="images/icon-delete.png" width="20"></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4">
                <form method="POST" action="">
                    <h3>Новая группа</h3>
                    <div class="mb-3">
                        <label for="inputName" class="form-label">Наименование</label>
                        <input type="text" name="inputName" class="form-control" id="inputName" required>
                    </div>
                    <div class="mb-3">
                        <label for="inputFaculty" class="form-label">Факультет</label>
                        <input type="text" name="inputFaculty" class="form-control" id="inputFaculty" required>
                    </div>
                    <div class="mb-3">
                        <label for="inputCourse" class="form-label">Курс</label>
                        <input type="number" name="inputCourse" class="form-control" id="inputCourse" required>
                    </div>
                    <div class="mb-3">
                        <label for="inputEduType" class="form-label">Вид обучения</label>
                        <input type="text" name="inputEduType" class="form-control" id="inputEduType" required>
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