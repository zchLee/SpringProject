<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>注册学生</p>
    <form action="reg" method="post">
        <table>
            <tr>
                <td>id</td>
                <td><input type="text" name="id"></td>

                <td>姓名：</td>
                <td><input type="text" name="name"></td>

                <td>email：</td>
                <td><input type="text" name="email"></td>

                <td>年龄：</td>
                <td><input type="text" name="age"></td>

                <td></td>
                <td><input type="submit" value="注册"></td>
            </tr>
        </table>
    </form>
</body>
</html>
