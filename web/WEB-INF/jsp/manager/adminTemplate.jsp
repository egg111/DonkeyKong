<%-- 
    Document   : adminHeader
    Created on : 2018-6-4, 10:28:21
    Author     : hasee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <base href="${pageContext.request.contextPath}/"/>
        <link rel="stylesheet" href="resource/css/css/template.css">
    </head>
    <body>
        <div class="header">
            <div class="title">
                <img src="resource/imags/logo.png" width="100px" height="100px"/>
                <span id="">
                    DonkeyKong
                </span>
            </div>
            <span class="admin">Hello Admin!</span>
            <button type="button" class="logout">Log Out</button>
            <button type="button" class="to-main">Main Page</button>
        </div>
                <div class="banner">
            <nav>
                <ul id="tabs">
                    <li>
                        <a href="toChangePass" >
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />Change Password</a>
                    </li>
                    <li>
                        <a href="toUserManage" >
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />User Manage</a>
                    </li>
                    <li>
                        <a href="toScenicManage">
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />Scenic Manage</a>
                    </li>
                    <li>
                        <a href="toHotelManage">
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />Hotel Manage</a>
                    </li>
                    <li>
                        <a href="toFoodManage">
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />FoodStore Manage</a>
                    </li>
                    <li>
                        <a href="toTravelManage">
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />Tour Manage</a>
                    </li>
                    <li>
                        <a href="toReportManage">
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />Report Manage</a>
                    </li>
                </ul>
            </nav>
        </div>
       
    </body>
    <script type="text/javascript" src="resource/js/jquery-2.1.0.js" ></script>
    <script>
        $(function () {
            $('.to-main').click(function () {
                window.location.href = 'redirect/main';
            });
            $('.logout').click(function(){
                window.location.href = 'redirect/login';
            })
        });
    </script>
</html>
