<!-- WEB-INF 자원은 외부에서 url로 호출해도 안나옴,
오로지 컨트롤러로 호출해야함. -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<!-- action에 상대경로가 사용되었음.
[현재 URL이 속한 계층 경로 + /save]
action = "/servlet-mvc/members/save" 과 동일함.-->

<form action="save" method="post">
  username :  <input type="text" name = "username" />
  age :       <input type="text" name = "age" />
  <button type = "submit">전송</button>
</form>

</body>
</html>
