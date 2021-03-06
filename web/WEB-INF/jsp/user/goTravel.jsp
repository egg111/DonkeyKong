<%-- 
    Document   : goTravel
    Created on : 2018-5-23, 23:05:18
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
        <title>Travelling</title>
        <base href="${pageContext.request.contextPath}/"/>
        <link rel="stylesheet" type="text/css" href="resource/css/css/goTravel.css" />
        <link rel="stylesheet" type="text/css" href="resource/js/jquery-ui-1.12.1.custom/jquery-ui.min.css" />
        <link rel="stylesheet" href="resource/css/edbox.css"/>
        <style>
            #num-table{
                width: 100%;
                height: auto;
                margin: 0 auto;
                border:1px solid #cccccc;
                border-radius: 3px;
                text-align: center;
            }
            #num-table .user-delete{
                border-radius: 6px;
                outline: 0;
                padding: 3px;
                height: 30px;
                width: auto;
                background-color: #f56c6c;
                color: #fff;
                border: none;
            }
            #num-table .user-delete:hover{
                background-color: #f78989;
                border-color: #f78989;
                transition-duration: 0.2s;
            }
        </style>
    </head>

    <body>
        <%@include file="userTemplate.jsp" %>
        <div id="tab">
            <div class="travel-head">
                <span id="">
                    Go Travelling!
                </span>
                <ul>
                    <li>
                        <input type="button"  value="Go Travel"/>
                    </li>
                    <li>
                        <input type="button" value="Travel List"/>
                    </li>
                    <li>
                        <input type="button" value="My Tour"/>
                    </li>
                </ul>
            </div>
            
            
            <div class="detail">
                <div id="tab1" class="travel-content">
                    <form action="travel/add" method="post">
                        <table border="0" cellspacing="2" cellpadding="2">
                            <input type="hidden" name="isApprove" value="0"/>
                            <tr>
                                <th>Title:</th>
                                <td><input type="text" name="tourTitle" class="t-title"  /></td>
                            </tr>
                            <tr>
                                <th>Scenic Name:</th>
                                <td><input type="text" name="scenicName" class="t-place" /></td>
                            </tr>
                            <tr>
                                <th>Time:</th>
                                <td>From&nbsp;<input type="text" name="tourBeginTime" id="from" class="t-from"/></td>
                                <td>To&nbsp;<input type="text" name="tourEndTime" id="to" class="t-to"/></td>
                            </tr>
                            <tr>
                                <th>Cost:</th>
                                <td><input type="text" id="" name="tourPrice" class="t-cost"  /></td>
                            </tr>
                            <tr>
                                <th>Person Limit:</th>
                                <td><input type="text" name="limitNum" id="" class="t-num" /></td>
                            </tr>
                            <tr>
                                <th>Trip Mode:</th>
                                <td><input type="text" name="tripMode" id="" class="t-mode"/></td>
                            </tr>
                            <tr>
                                <th>Hotel:</th>
                                <td><input type="text" name="hotelName" id="" class="t-hotel"  /></td>
                            </tr>
                            <tr>
                                <th>Apply Deadline:</th>
                                <td><input type="text" name="applyEndTime" id="" class="t-final-time" /></td>
                            </tr>
                        </table>
                        <div id="t-submit">
                            <button type="submit" class="travel-sub">Submit</button>
                        </div>
                    </form>
                </div>
                <div id="tab2" class="travel-list">
                    <div class="list">
                        <dl class="toggle">
                        <c:forEach items="${travelList}" var="t">                            
                            <dt class="item-content" id="${t.tourId}">
                                    <span class="item-title">${t.tourTitle}</span>
                                    <span class="start-time">BeginTime : ${t.tourBeginTime}</span>
                                </dt>
                                <dd>
                                    <div class="person">
                                        <form action="travel/edit" method="post">
                                            <table border="0" cellspacing="2" cellpadding="2">
                                                <input type="hidden" value="${t.tourId}" name="tourId"/>
                                                <input type="hidden" value="${t.isApprove}" name="isApprove"/>
                                                <tr>
                                                    <th>title:</th>
                                                    <td class="finish">${t.tourTitle}</td>
                                                    <td class="unfinish"><input type="text" name="tourTitle" class="t-title" value="${t.tourTitle}" /></td>
                                                </tr>
                                                <tr>
                                                    <th>place:</th>
                                                    <td class="finish">${t.scenicName}</td>
                                                    <td class="unfinish"><input type="text"  name="scenicName" class="t-place" value="${t.scenicName}" /></td>
                                                </tr>
                                                <tr>
                                                    <th>time:</th>
                                                    <td class="finish">from&nbsp; ${t.tourBeginTime}</td>
                                                    <td class="unfinish">from&nbsp;		
                                                        <input type="text" name="tourBeginTime" id="from" class="t-from" value="${t.tourBeginTime}" />
                                                    </td>
                                                    <td class="finish">to&nbsp; ${t.tourEndTime}</td>
                                                    <td class="unfinish">to&nbsp;
                                                        <input type="text" name="tourEndTime" id="to" class="t-to" value="${t.tourEndTime}" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <th>cost:</th>
                                                    <td class="finish">${t.tourPrice}</td>
                                                    <td class="unfinish"><input type="text"  name="tourPrice" class="t-cost" value="${t.tourPrice}" /></td>
                                                </tr>
                                                <tr>
                                                    <th>person limit:</th>
                                                    <td class="finish">${t.limitNum}</td>
                                                    <td class="unfinish"><input type="text"  name="limitNum" class="t-num" value="${t.limitNum}" /></td>
                                                </tr>
                                                <tr>
                                                    <th>trip mode:</th>
                                                    <td class="finish">${t.tripMode}</td>
                                                    <td class="unfinish"><input type="text"  name="tripMode" class="t-mode" value="${t.tripMode}" /></td>
                                                </tr>
                                                <tr>
                                                    <th>hotel:</th>
                                                    <td class="finish">${t.hotelName}</td>
                                                    <td class="unfinish"><input type="text"  name="hotelName" class="t-hotel" value="${t.hotelName}" /></td>
                                                </tr>
                                                <tr>
                                                    <th>final time:</th>
                                                    <td class="finish">${t.applyEndTime}</td>
                                                    <td class="unfinish"><input type="text" name="applyEndTime" class="t-final-time" value="${t.applyEndTime}" /></td>
                                                </tr>

                                            </table>
                                            <div id="t-submit">
                                                <button type="button" class="edit editInfo">
                                                    Edit
                                                </button>
                                                <button type="button" class="cancel cancelInfo">
                                                    Cancel
                                                </button>
                                                <button type="submit" class="submit saveInfo">
                                                    Submit
                                                </button>										
                                                <button type="button" class="delete deleteInfo" onclick="delTravel('${t.tourId}')">
                                                    Delete
                                                </button>
                                                <button type="button" name="checkNumber" class="person personInfo" onclick="getTourId('${t.tourId}')">
                                                    Check Person
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </dd>
                        </c:forEach>
                            </dl>
                    </div>
                </div>
                <div id="tab3" class="my-tour">
                    <table class="tour-table" border="0">
                        <thead>
                        <tr>
                            <th>Tour Name</th>
                            <th>Tour Time</th>
                            <th>Tour Price</th>
                            <th>Operate</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${travelJoinList}" var="t">
                        <tr>
                            <td>${t.tour.tourTitle}</td>
                            <td>${t.tour.tourBeginTime} — ${t.tour.tourEndTime}</td>
                            <td>${t.tour.tourPrice}</td>
                            <td>
                                <button type="button" class="delete" onclick="delTravelJoin('${t.tour.tourId}')">Cancel</button>
                            </td>
                        </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div id="num-dialog" title="Person List">
            <table id="num-table">
                <tr>
                    <th>User Name</th>
                    <th>Delete</th>
                </tr>
                <c:forEach items="${applyList}" var="a">
                    <tr id="">
                        <td>${a}</td>
                        <td><button type="button" class="user-delete" onclick="delNum('${a}')">Delete</button></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
    <script type="text/javascript" src="resource/js/jquery-2.1.0.js"></script>
    <script type="text/javascript" src="resource/js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
    <script src="resource/js/jquery.edbox.js"></script>
    <script type="text/javascript">
                            $(function () {
                                $('.cancelInfo').hide();
                                $('.saveInfo').hide();
                                $('td.unfinish').hide();
                                $('.editInfo').click(function () {
                                    $('.deleteInfo').hide();
                                    $('.personInfo').hide();
                                    $('td.finish').hide();
                                    $('td.unfinish').show();
                                    $(this).hide();
                                    $('.cancelInfo').show();
                                    $('.saveInfo').show();
                                });
                                $('.cancelInfo').click(function () {
                                    $(this).hide();
                                    $('.deleteInfo').show();
                                    $('.personInfo').show();
                                    $('.editInfo').show();
                                    $('td.finish').show();
                                    $('td.unfinish').hide();
                                    $('.saveInfo').hide();
                                });
                            });
                            $(document).ready(function () {
                                $('#tab2').hide();
                                $('#tab3').hide();
                                $('.travel-head li').bind("click", function () {
                                    var index = $(this).index();
                                    var divs = $(".detail > div");
                                    $(this).parent().children("li").attr("class", "tab-nav");
                                    divs.hide();
                                    divs.eq(index).show();
                                });
                            });
                            $(function () {
                                $('#from').datepicker({
                                    defaultDate: '+1w',
                                    changeMonth: true,
                                    dateFormat: "yy-mm-dd",
                                    numberOfMonth: 1,
                                    onClose: function (selectDate) {
                                        $('#to').datepicker('option', 'minDate', selectDate);
                                        $('.t-final-time').datepicker('option','maxDate',selectDate);
                                    }
                                });
                                $('#to').datepicker({
                                    defaultDate: '+1w',
                                    changeMonth: true,
                                    dateFormat: "yy-mm-dd",
                                    numberOfMonth: 1,
                                    onClose: function (selectDate) {
                                        $('#from').datepicker('option', 'maxDate', selectDate);
                                    }
                                });
                                $('.t-final-time').datepicker({
                                    defaultDate: '+1w',
                                    changeMonth: true,
                                    dateFormat: "yy-mm-dd",
                                    numberOfMonth: 1
                                    
                                });
                                $('.list dl dd').hide();
                                $('.list dl dt').click(function () {
                                    $('.list dl dd').not($(this).next()).hide();
                                    $(this).next().slideToggle(300);

                                });
                                $("button[name='checkNumber']").click(function () {
                                    $('#num-dialog').dialog('open');
                                    
                                });
                                $('#num-dialog').dialog({
                                    width: 300,
                                    height: 300,
                                    modal: true,
                                    autoOpen: false
                                });
                            });

                            //组团游删除
                            function delTravel(tid) {
                                $.ajax({
                                    url: 'travel/delete',
                                    type: 'POST',
                                    data: {
                                        "tid": tid
                                    },
                                    dataType: "json",
                                    success: function () {
                                        console.log("success! delete");
                                        alert('okok');
                                        $('#' + tid).remove();
                                        window.location.href = "toGoTravel";
                                    },
                                    error: function () {
                                        alert('wrong');
                                        console.log('error!!!!!');
                                        window.location.href = "toGoTravel";
                                    }
                                });
                            }
                            //退出加入的组团游
                            function delTravelJoin(tid) {
                                $.ajax({
                                    url: 'travel/delTravelJoin',
                                    type: 'POST',
                                    data: {
                                        "tid": tid
                                    },
                                    dataType: "json",
                                    success: function () {
                                        $('#' + tid).remove();
                                        window.location.href = "toGoTravel";
                                    },
                                    error: function () {
                                        window.location.href = "toGoTravel";
                                    }
                                });
                            }
                            //获取组团游id
                            function getTourId(tid){
                                $.ajax({
                                    url: 'travel/getTourId',
                                    type: 'POST',
                                    data: {
                                        "tid":tid
                                    },
                                    success: function () {
                                        console.log('success getTourId');
                                    },
                                    error: function () {
                                        console.log('error getTourId!');
                                    }
                                })
                            }
                            //删除申请人
                            function delNum(uid) {
                                $.ajax({
                                    url: 'travel/delMember',
                                    type: 'POST',
                                    data: {
                                        "uid":uid
                                    },
                                    success: function () {
                                        alert(uid);
                                        console.log('success delete');
                                        location.reload();
                                    },
                                    error: function () {
                                        console.log('error delete member!');
                                    }
                                })
                            }
                            var wordRegex = /^.+?$/;
                            var numRegex = /^[1-9][0-9]{1,6}$/;
                            
                            $('.travel-sub').click(function(check){
                                var title = $('.t-title').val();
                                var scenic = $('.t-place').val();
                                var from = $('.t-from').val();
                                var to = $('.t-to').val();
                                var cost = $('.t-cost').val();
                                var limit = $('.t-num').val();
                                var mode = $('.t-mode').val();
                                var hotel = $('.t-hotel').val();
                                var deadline = $('.t-final-time').val(); 
                                if(title == ""){
                                    $.edbox({warning:'<b>Warning</b>,input should not be blank!'});
                                    $('.t-title').focus();
                                    check.preventDefault();
                                }
                                if(title != null){
                                    if(!wordRegex.test(title)){
                                        $.edbox({warning:'<b>Warning</b>Invalid Title!'});
                                        $('.t-title').focus();
                                        check.preventDefault();
                                    }
                                }
                                if(scenic == ""){
                                    $.edbox({warning:'<b>Warning</b>,input should not be blank!'});
                                    $('.t-place').focus();
                                    check.preventDefault();
                                }
                                if(scenic != null){
                                    if(!wordRegex.test(scenic)){
                                        $.edbox({warning:'<b>Warning</b>Invalid Scenic Name!'});
                                        $('.t-place').focus();
                                        check.preventDefault();
                                    }
                                }
                                if(from == ""){
                                    $.edbox({warning:'<b>Warning</b>,input should not be blank!'});
                                    $('.t-from').focus();
                                    check.preventDefault();
                                }
                                if(to == ""){
                                    $.edbox({warning:'<b>Warning</b>,input should not be blank!'});
                                    $('.t-to').focus();
                                    check.preventDefault();
                                }
                                if(cost == ""){
                                    $.edbox({warning:'<b>Warning</b>,input should not be blank!'});
                                    $('.t-cost').focus();
                                    check.preventDefault();
                                }
                                if(cost != null){
                                    if(!numRegex.test(cost)){
                                        $.edbox({warning:'<b>Warning</b>Invalid Cost!'});
                                        $('.t-cost').focus();
                                        check.preventDefault();
                                    }
                                }
                                if(limit == ""){
                                    $.edbox({warning:'<b>Warning</b>,input should not be blank!'});
                                    $('.t-num').focus();
                                    check.preventDefault();
                                }
                                if(limit != null){
                                    if(!numRegex.test(limit)){
                                        $.edbox({warning:'<b>Warning</b>Invalid Limit!'});
                                        $('.t-num').focus();
                                        check.preventDefault();
                                    }
                                }
                                if(mode == ""){
                                    $.edbox({warning:'<b>Warning</b>,input should not be blank!'});
                                    $('.t-mode').focus();
                                    check.preventDefault();
                                }
                                if(mode != null){
                                    if(!wordRegex.test(mode)){
                                        $.edbox({warning:'<b>Warning</b>Invalid Trip Mode!'});
                                        $('.t-mode').focus();
                                        check.preventDefault();
                                    }
                                }
                                if(hotel == ""){
                                    $.edbox({warning:'<b>Warning</b>,input should not be blank!'});
                                    $('.t-hotel').focus();
                                    check.preventDefault();
                                }
                                if(hotel != null){
                                    if(!wordRegex.test(hotel)){
                                        $.edbox({warning:'<b>Warning</b>Invalid Hotel!'});
                                        $('.t-hotel').focus();
                                        check.preventDefault();
                                    }
                                }
                                if(deadline == ""){
                                    $.edbox({warning:'<b>Warning</b>,input should not be blank!'});
                                    $('.t-final-time').focus();
                                    check.preventDefault();
                                }
                                
                            });
                            
    </script>


</html>
