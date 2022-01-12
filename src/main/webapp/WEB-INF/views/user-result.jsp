<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>


    <h3>
        # 아이디: ${u.id}  <br>
        # 비번: ${u.pw} <br>
        # 이름: ${u.userName} <br>
        # 메일 수신여부:
        <c:if test="${u.mail == true}">
          예  <br>
        </c:if>
        <c:if test="${u.mail == false}">
          아니오  <br>
        </c:if>
        # 취미: ${u.hobby} <br>
    </h3>

</body>

</html>