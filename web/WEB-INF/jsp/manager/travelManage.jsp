<%-- 
    Document   : travelManage
    Created on : 2018-5-23, 23:17:47
    Author     : hasee
    组团游管理
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <base href="${pageContext.request.contextPath}/"/>
        <link rel="stylesheet" type="text/css" href="resource/css/css/manage.css"/>
        <link rel="stylesheet" href="resource/css/edbox.css"/>
        <title>Travel Manage</title>
    </head>
    <body>
        <%@include file="adminTemplate.jsp" %>
        <div class="contentManage">
            <div class="t-title">Travel Manage</div>
            <div class="table-head">
            </div>
            <div class="table-content">
                <table border="1" cellpadding="0" cellspacing="0">
                    <tr>
                        <th>Group Travelling ID</th>
                        <th>Destination</th>
                        <th>Price</th>
                        <th>Days</th>
                        <th>Operate</th>
                    </tr>
                    <c:forEach items="${travelList}" var="t">
                        <tr id="${t.tourId}">                   
                            <td>${t.tourId}</td>
                            <td>${t.scenicName}</td>
                            <td>${t.tourPrice}</td>
                            <td>${t.tourEndTime}-${t.tourBeginTime}</td>
                            <td>
                                <button type="button" class="submit" onclick="approve('${t.tourId}')">Approve</button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <script type="text/javascript" src="resource/js/jquery-2.1.0.js" ></script>
        <script src="resource/js/jquery.edbox.js"></script>
        <script type="text/javascript">
            function approve(id) {
                $.ajax({
                    url: 'travel/approve',
                    type: 'POST',
                    data: {
                        "id": id
                    },
                    dataType: "json",
                    success: function () {
                        console.log("success!");
                        $('#' + id).remove();
                        window.location.href = "toTravelManage";
                    },
                    error: function () {
                        console.log('error!!!!!');
                        $('#' + id).remove();
                        window.location.href = "toTravelManage";
                    }
                });
            }
        </script>
    </body>

</html>
