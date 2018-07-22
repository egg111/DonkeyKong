<%-- 
    Document   : foodPage
    Created on : 2018-5-23, 23:02:01
    Author     : hasee
    美食主页
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <base href="${pageContext.request.contextPath}/"/>
        <link rel="stylesheet" href="resource/css/css/foodPage.css">
        <link rel="stylesheet" type="text/css" href="resource/css/swiper-4.1.6.min.css" />
        <title>Food Page</title>
        <style>
            a{
                color: black;
                text-decoration: none;
            }
            a:link{
                color: black;
            }
            a:visited{
                color: black;
            }
        </style>
    </head>

    <body>
        <!--导航模块-->
        <%@include file="header.jsp" %>
        <div class="swiper-container swiper-header">
            <div class="swiper-wrapper">
                <c:forEach items="${foodListByQD}" var="f">
                <div class="swiper-slide hot-img">
                    
                        <a href="food/getAFood?fid=${f.foodStoreId}">
                            <span hidden="true">${f.foodStoreId}</span>
                            <span class="img-info">${f.foodStoreName}!</span>
                            <img src="resource/imags/food/${f.storeImg == null ? 'Akz00I_GGjU.jpg' : f.storeImg}" />
                        </a>
                    
                </div>
                            </c:forEach>
            </div>
            <div class="swiper-pagination swiper-btn"></div>
            <div class="swiper-button-prev swiper-btn"></div>
            <div class="swiper-button-next swiper-btn"></div>

        </div>

        <div class="food-remomend">
            <span id="r-title">
                Food Remomend
            </span>
            <div class="r-item swiper-container swiper-remomend">
                <div class="swiper-wrapper">
                    <c:forEach items="${topFoodList}" var="f">
                        <div class="swiper-slide r-img">

                            <a href="food/getAFood?fid=${f.foodStoreId}">
                                <span hidden="true">${f.foodStoreId}</span>
                                <img src="resource/imags/food/${f.storeImg == null ? 'Akz00I_GGjU.jpg' : f.storeImg}" />
                                <span class="r-info">${f.foodStoreName}!</span>
                            </a>

                        </div>
                    </c:forEach>
                </div>
                <div class="swiper-pagination swiper-btn"></div>
            </div>
        </div>

        <div class="food-content">
<!--            <div class="sousuo">
                <img src="resource/imags/icon/search.png" class="icon-search" width="20px" height="20px" alt="">
                <input class="in-find" type="text" placeholder="Search the food" />
                <button type="button" class="find">Search</button>
            </div>-->
            <div class="food-list">
                <c:forEach items="${foodList}" var="f">
                    <a href="food/getAFood?fid=${f.foodStoreId}">
                        <div class="food-item">
                            <span hidden="true">${f.foodStoreId}</span>
                            <div class="s-img">
                                <img src="resource/imags/food/${f.storeImg == null ? 'Akz00I_GGjU.jpg' : f.storeImg}" width="300px" height="200px" />
                            </div>
                            <div class="s-info">
                                <span id="s-name">
                                    ${f.foodStoreName}
                                </span>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>

    </body>
    <script src="resource/js/jquery-2.1.0.js"></script>
    <script type="text/javascript" src="resource/js/swiper-4.1.6.min.js" ></script>
    <script>
        var mySwiper = new Swiper('.swiper-header', {
            loop: true,
            speed: 500,
            pagination: {
                el: '.swiper-pagination'
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            autoplay: true,
            effect: 'coverflow'
        });

        var rSwiper = new Swiper('.swiper-remomend', {
            loop: true,
            speed: 400,
            autoplay: true,
            pagination: {
                el: '.swiper-pagination'
            }
        });
    </script>


</html>
