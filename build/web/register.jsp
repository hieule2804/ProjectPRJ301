<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap"
            rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
            integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <style>
            .icon {
                margin-top: 10px;
            }
        </style>
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
                            <div class="top-cart-content" id="LoginLogout"
                                style="display: none;     margin-left: -30px;">
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
                                                   height: 50px;" type="submit" value="${cate.categori}">
                                    </form>
                                </li>
                            </c:forEach>

                        </ul>

                    </div>
                </nav>
            </div>
        </div>
        <!--form Register-->
        <div class="formRegister">


            <section class="vh-100" style="background-color: #eee;">
                <div class="container h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-lg-12 col-xl-11">
                            <div class="card text-black" style="border-radius: 25px;">
                                <div class="card-body p-md-5">
                                    <div class="row justify-content-center">
                                        <div class="order-2 order-lg-1 " style="text-align: center;">

                                            <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

                                            <form action="login?action=register" method="post" class="mx-1 mx-md-4">

                                                <div class="row d-flex flex-row align-items-center mb-4">
                                                    <div class="col-xs-6 icon glyphicon glyphicon-user"></div>
                                                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                                                    <div class="form-outline col-xs-6 flex-fill mb-0">
                                                        <input type="text" name="username" id="form3Example1c" style="width: 600px;
                                                        /* text-align: center; */
                                                        
                                                        margin-bottom: 10px;margin-left: -300px; 
                                                        
    " class="form-control" placeholder="Your Name" />

                                                    </div>
                                                </div>

                                                <div class=" row d-flex flex-row align-items-center mb-4">
                                                    <div class="col-xs-6 glyphicon glyphicon-calendar"></div>
                                                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                                    <div class="form-outline col-xs-6 flex-fill mb-0">
                                                        <input type="text" name="date" id="form3Example3c" style="width: 600px;
                                                        /* text-align: center; */
                                                           margin-bottom: 10px;margin-left: -300px;
    " class="form-control" placeholder="YYYY-MM-DD" />

                                                    </div>
                                                </div>

                                                <div class="row d-flex flex-row align-items-center mb-4">
                                                    <div class="col-xs-6 icon glyphicon glyphicon-lock"></div>
                                                    <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                                                    <div class="form-outline col-xs-6 flex-fill mb-0"
                                                        style="text-align: left;">
                                                        <select name="gender" id="" style=" height: 36px;
                                                        width: 100px;
                                                        margin-left: -300px;">

                                                            <option value="true">Male</option>
                                                            <option value="false">Female</option>

                                                        </select>

                                                    </div>
                                                </div>

                                                <div class="row d-flex flex-row align-items-center mb-4"
                                                    style="margin-top: 10px;">
                                                    <div class="col-xs-6 icon glyphicon glyphicon-lock"></div>
                                                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                                                    <div class="form-outline col-xs-6 flex-fill mb-0">
                                                        <input type="password" name="password" id="form3Example4cd"
                                                            style="width: 600px;
                                                        /* text-align: center; */
                                                      margin-left:-300px;  margin-bottom: 10px;
    " class="form-control" placeholder="password" />


                                                    </div>
                                                </div>



                                                <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                                    <button type="submit"
                                                        class="btn btn-primary btn-lg">Register</button>
                                                </div>

                                            </form>

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <h2 style="color: red">${mess}</h2>
        <h2 style="color: red">${messDate}</h2>
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