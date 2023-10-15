

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LOGIN</title>
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
                <div class="seachHeader col-md-8">

                    <form action="#" method="post" role="search">
                        <input style="    margin-left: 20px; 
                    width: 500px;
                    height: 35px;" type="search" name="query" value="" placeholder="Tìm kiếm sản phẩm... "
                            class="input-group-field st-default-search-input search-text" autocomplete="off">

                        <button style="    width: 35px; border: 0px;
                    height: 33px;" tyle="submit " class="glyphicon glyphicon-search">
                            <i class="fa fa-search"></i>
                        </button>
                    </form>
                </div>
                <div class="itemHearder col-md-4" style="    padding-top: 7px;
                margin-left: -33px;">
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
    <!--Form Login-->
    <form action="login?action=loginAcc" method="post" style="width: 600px;
    
    margin: auto;">
        <!-- username input -->
        <div class="form-outline mb-4" style="padding-bottom: 20px;">
            <input type="text" name="username" id="form2Example1" class="form-control" placeholder="username" />
        </div>

        <!-- Password input -->
        <div class="form-outline mb-4" style="padding-bottom: 20px;">
            <input type="password" name="password" id="form2Example2" class="form-control" placeholder="password" />
        </div>

        <!-- 2 column grid layout for inline styling -->
        <div class="row mb-4" style="padding-bottom: 20px;">
         

            <div class="col-md-6">
                <!-- Simple link -->
                <a href="#!">Forgot password?</a>
            </div>
        </div>

        <!-- Submit button -->
        <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button>


    </form>
    <h2 style="color: red">${mess}</h2>
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
