<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <style>

        .reg {
            border: 1px solid gray;
            margin : 20px auto;
            padding: 15px;
            width: 40%;
        }

    </style>
</head>
<body>

    <div class="reg">
        <form action="/emp/register" method="POST">
            # 이름: <input type="text" name="empName"> <br>
            # 직급:
            <select name="position">
                <option value="사원">사원</option>
                <option value="대리">대리</option>
                <option value="과장">과장</option>
                <option value="차장">차장</option>
                <option value="부장">부장</option>
            </select>

            <button type="submit">등록하기</button>
        </form>

        <ul>
            <c:forEach var="emp" items="${empList}">
                <li>
                # 사번: ${emp.empNum}, 이름: ${emp.empName}, 직급: ${emp.position}
                 <a href="/emp/delete?empNum=${emp.empNum}">삭제</a>
                </li>
            </c:forEach>
        </ul>

    </div>

</body>
</html>