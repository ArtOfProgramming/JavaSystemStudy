<%--
  Created by IntelliJ IDEA.
  User: daiwei
  Date: 2020/10/18
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.9.1.min.js"></script>
</head>
<%
    pageContext.setAttribute("path", request.getContextPath());
%>
<body>
        <a id="json" href="#" >获取用户信息</a>
        <div></div>

        <img src="${path}/images/test.png">
${hello}
<script>
    $("#json").click(function () {
      $.ajax({
        url:"${path}/json",
        type: "GET",
        success: function (data) {
          console.log(data);
          $.each(data,function() {
            var user = this.id+"--"+this.name+"--"+this.age+"--"+this.gender+"--"+this.birth+"--"+this.email;
            $("div").append(user+'<br/>');
          })
        }
      })
    })
</script>
</body>
</html>
