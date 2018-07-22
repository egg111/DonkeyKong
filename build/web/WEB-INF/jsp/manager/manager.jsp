<%-- 
    Document   : manager
    Created on : 2018-5-23, 23:07:42
    Author     : hasee
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Admin Page</title>
        <base href="${pageContext.request.contextPath}/"/>
        <link rel="stylesheet" href="resource/css/css/template.css">
        <style>
            .body .content{
                display: none;
                width: 100%;
                height: inherit;
                overflow: hidden;
            }
            iframe{
                width: 100%;
                min-height: 1000px;
                overflow: hidden;
            }
        </style>
    </head>

    <body>
        <div class="header">
            <div class="title">
                <img src="resource/imags/logo.png" width="100px" height="100px"/>
                <span id="">
                    DonkeyKong
                </span>
            </div>
            <span class="admin">Hello Edward!</span>
            <button type="button" class="logout">Log Out</button>
            <button type="button" class="to-main">Main Page</button>
        </div>
        <div class="banner">
            <nav>
                <ul id="tabs">
                    <li>
                        <a href="javascript:void(0)" onclick="selectTab('content0', this)">
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />修改密码</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" onclick="selectTab('content1', this)">
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />用户管理</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" onclick="selectTab('content2', this)">
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />景点管理</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" onclick="selectTab('content3', this)">
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />酒店管理</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" onclick="selectTab('content4', this)">
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />美食管理</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" onclick="selectTab('content5', this)">
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />团游申请</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" onclick="selectTab('content6', this)">
                            <img src="resource/imags/icon/menu.png" class="icon-menu" alt="" />游记管理</a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="body">
            <div id="content0" class="content">
                <iframe src="toChangePass" frameborder="0"></iframe>
            </div>
<!--            <div id="content1" class="content" style="display: block;">
                <iframe src="toUserManage" frameborder="0"></iframe>
            </div>-->

            
            <div id="content2" class="content">
                <iframe src="toScenicManage" frameborder="0"></iframe>
            </div>
            <div id="content3" class="content">
                <iframe src="toHotelManage" frameborder="0"></iframe>
            </div>
            <div id="content4" class="content">
                <iframe src="toFoodManage" frameborder="0"></iframe>
            </div>
            <div id="content5" class="content">
                <iframe src="toTravelManage" frameborder="0"></iframe>
            </div>
            <div id="content6" class="content">
                <iframe src="toNoteManage" frameborder="0"></iframe>
            </div>
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
        })
        function selectTab(thisContent, thisObj) {
            //      document.getElementById('welcome').style.display = "none";
            thisObj.blur();
            var tab = document.getElementById('tabs').getElementsByTagName('li');
            var tabLen = tab.length;
            for (i = 0; i < tabLen; i++) {
                tab[i].className = "";
            }
            thisObj.parentNode.className = 'selectTab';
            for (i = 0; j = document.getElementById('content' + i); i++) {
                j.style.display = "none";
            }
            document.getElementById(thisContent).style.display = "block";
        }
    </script>
</html>
