<%-- 
    Document   : hotelBook
    Created on : 2018-6-15, 20:14:24
    Author     : hasee
--%>

<%@page import="pojo.Users"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" import="java.util.*"%> 
<%
    java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    java.util.Date currentTime = new java.util.Date();//得到当前系统时间  

    String time = formatter.format(currentTime); //将日期时间格式化  

    Users user = (Users) session.getAttribute("CURRENT_USER");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <base href="${pageContext.request.contextPath}/"/>
        <link rel="stylesheet" href="resource/js/jquery-ui-1.12.1.custom/jquery-ui.min.css">
        <title>Document</title>
        <link rel="stylesheet" type="text/css" href="resource/css/css/myHotelBooking.css" />
        <link rel="stylesheet" href="resource/css/edbox.css"/>
    </head>
    <body>
        <%@include file="userTemplate.jsp" %>
        <%            request.setCharacterEncoding("utf8");
            response.setCharacterEncoding("utf8");
        %>
        <div class="content">
            <div class="head"><span>My Hotel Booking</span></div>
            <div class="body">
                <table class="hotel-table">
                    <thead>
                        <tr>
                            <th>Hotel Name</th>
                            <th>Hotel Address</th>
                            <th>Room Number</th>
                            <th>Room Type</th>
                            <th>Room Price</th>
                            <th>Check-in Time</th>
                            <th>Check-out Time</th>
                            <th>Operate</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${bookList}" var="b">
                            <tr id="${b.bookId}">
                                <td>${b.hotel.getHotelName()}</td>
                                <td>${b.hotel.hotelAddress}</td>
                                <td>${b.hotelRoom.roomNum}</td>
                                <td>${b.hotelRoom.roomType}</td>
                                <td>${b.hotelRoom.roomPrice}</td>
                                <td>${b.checkInTime}</td>
                                <td>${b.checkOutTime}</td>
                                <td><button type="type" class="delete" onclick="delBook('${b.bookId}')">Cancel</button></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
    <script src="resource/js/jquery-2.1.0.js"></script>
    <script src="resource/js/jquery.edbox.js"></script>
    <script>
                                    $(document).ready(function () {
                                        delHotel(hid);
                                    });
                                    function delBook(bid) {
                                        $.ajax({
                                            url: 'hotel/delBook',
                                            type: 'POST',
                                            data: {
                                                "bid": bid
                                            },
                                            success: function () {
                                                $.edbox({success:'delete sucess'});
                                                $('#' + bid).remove();
                                            },
                                            error: function () {
                                                $.edbox({success:'delete sucess'})
                                                $('#' + bid).remove();
                                            }
                                        });
                                    }
    </script>
</html>