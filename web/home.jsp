

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>

    <head>
        <title>Home Book</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
              integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">

    </head>

    <body>
        <!--header-->
        <div class="header container ">
            <div class="firtHeader row" style="    margin-top: -20px;">
                <div class="logoHearder col-md-2">
                    <img style="height: 200px;"
                         src="https://png.pngtree.com/template/20191125/ourlarge/pngtree-book-store-logo-template-sale-learning-logo-designs-vector-image_335046.jpg"
                         alt="">
                </div>
                <div class="rightHeader col-md-10" style="margin-top: 79px;">
                    <!--search book-->
                    <div class="seachHeader col-md-8">

                        <form action="home?action=searchByName" method="post" role="search">
                            <input style="    margin-left: 20px;
                                   width: 500px;
                                   height: 35px;" type="text" name="bookNameSerach"  placeholder="Tìm kiếm sản phẩm... "
                                   class="input-group-field st-default-search-input search-text" autocomplete="off">

                            <button style="    width: 35px; border: 0px;
                                    height: 33px;" tyle="submit " class="glyphicon glyphicon-search">
                                <i class="fa fa-search"></i>
                            </button>
                        </form>
                    </div>
                    <div class="itemHearder col-md-4" style="    padding-top: 7px;
                         margin-left: -33px;">
                        <!--Dang ky ,Dang Nhap-->
                        <!--tai khoan truoc khi dang nhap-->
                        <%
                        if( session.getAttribute("account") ==null)
                        {
                        %>
                        <div class="taikhoan col-md-6">
                            <div onclick="LoginLogout()" class="login">

                                <a href="#" class="cart-label header-icon">
                                    <div class="cart-info" style="padding-left: 20px;">
                                        <span class="glyphicon glyphicon-user header-icon"></span>
                                    </div>
                                </a>
                                <span class="block">Tài khoản</span>
                            </div>
                            <div class="top-cart-content" id="LoginLogout" style="display: none;     margin-left: -30px;">
                                <ul>

                                    <a href="login.jsp"><i class=""></i> Đăng nhập
                                    </a></li>
                                    <a href="register.jsp"><i class="fa fa-shopping-cart"></i> Đăng ký
                                    </a></li>

                                </ul>
                            </div>
                        </div>
                        <%  
                            }else{
                        %>

                        <!--Tai khoan sau khi dang nhap-->
                        <div class="taikhoan col-md-6">
                            <div onclick="LoginLogout()" class="login">

                                <a href="#" class="cart-label header-icon">
                                    <div class="cart-info" style="padding-left: 20px;">
                                        <span class="glyphicon glyphicon-user header-icon"></span>
                                    </div>
                                </a>
                                <span class="block">${account.username}</span>
                            </div>
                            <div class="top-cart-content" id="LoginLogout" style="display: none;     margin-left: -30px;">
                                <ul>

                                    <a href="login"><i class=""></i> LOG OUT
                                    </a></li>
                                </ul>
                            </div>
                        </div>
                        <%   }
                        %>
                        <!-- gior hangf -->
                        <div class="giohang col-md-6" style=" padding-left: 0px;">

                            <a href="#" class="cart-label header-icon">
                                <div class="cart-info" style="padding-left: 20px;">
                                    <span class="glyphicon glyphicon-shopping-cart" id="cart-total"></span>
                                </div>
                            </a>
                            <span class="block">Giỏ hàng</span>

                        </div>
                    </div>
                </div>
            </div>
            <!-- list the loai sach -->
            <div class="ThanhSelect">
                <nav class="navbar navbar-default " style="margin-top: -20px;">
                    <div class="container">

                        <ul class="nav nav-pills">
                            <!--sẽ dùng c:foreach trong jsp và sử dụng list thể lại sách để cho vào tên và link-->
                            <c:forEach items="${listcate}" var="cate">
                                <li style="margin-right: 15px;" role="presentation">
                                    <form action="home?action=searchByCate&id=${cate.id}" method="post">
                                        <input style="border: 0px;
                                               height: 50px;" type="submit" value="${cate.categori}" >
                                    </form>
                                </li>
                            </c:forEach>

                        </ul>

                    </div>
                </nav>
            </div>
        </div>
        <!--body-->

        <div class="row ">
            <!--sản phẩm -->
            <!--dùng c:foreach để hiển thị tất cả các sản phẩm , chỉ cần sử dụng 1 khối div-->
            <c:forEach items="${listBook}" var="o">
                <div class="col-sm-6 col-md-4">
                    <div class="thumbnail" style=" text-align: center; height: 464px; ">
                        <img style="    width: 200px;
                             height: 250px;" src="${o.image}" alt="...">
                        <div class="caption">
                            <h3>${o.bookname}</h3>
                            <p><a>Author : ${o.author}</a> </p>
                            <p>  <a>Price : ${o.price} VND</a></p>
                            <!--session su dung khi ma an vao nut buy or add to card de chuyen sang trang chi tiet san pham -->
                            <%
//if(  session.getAttribute("account")==null)
//                            {
//                           request.getRequestDispatcher("login.jsp").forward(request, response);
//                            }
                            %>
                            <p><a href="#" class="btn btn-primary" role="button">BUY</a>
                                <a href="#" class="btn btn-default" role="button">ADD TO CARD</a>        
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!-- pagination -->
        <nav aria-label="Page navigation " style="text-align: center">
            <ul class="pagination">
                <c:if test="${PageControl.page==1}">
                    <li class="Disabled">
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${PageControl.page>1}">
                    <li>
                        <a href="home?action=pagination&page=${PageControl.page-1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <li><a href="home?action=pagination&page=${PageControl.page}">${PageControl.page}</a></li>
                        </a>
                    </li>
                </c:if>
                <c:if test="${PageControl.page+1<PageControl.totalPage }">
                    <li><a href="home?action=pagination&page=${PageControl.page}">${PageControl.page}</a></li>
                    <li><a href="home?action=pagination&page=${PageControl.page+1}">${PageControl.page+1}</a></li>
                    </c:if>
                    <c:if test="${PageControl.page+1==PageControl.totalPage}">
                    <li><a href="home?action=pagination&page=${PageControl.page+1}">${PageControl.page+1}</a></li>

                </c:if>   

            </ul>
        </nav>
    </body>
    <script>
        function LoginLogout() {
            let x = document.getElementById('LoginLogout');
            if (x.style.display == 'none') {
                x.style.display = 'block';
            } else {
                x.style.display = 'none';
            }
        }
    </script>

</html>
