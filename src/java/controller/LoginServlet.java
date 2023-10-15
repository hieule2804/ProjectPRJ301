/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.bookDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

    AccountDAO accdao = new AccountDAO();
    bookDao bookdao = new bookDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("account");
        session.getAttribute("PageControl");
        session.getAttribute("listBook");
        session.getAttribute("listcate");
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //get action
        String action = request.getParameter("action");
        switch (action) {
            case "loginAcc":
                //get username,password
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                //check account
                if (accdao.checkAccount(username, password) == null) {
                    request.setAttribute("mess", "username or password not valid");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    if(accdao.checkAccount(username, password).getRole()==3)
                    {
                    session.setAttribute("account", accdao.checkAccount(username, password));
                    response.sendRedirect("home.jsp");

                    }else if(accdao.checkAccount(username, password).getRole()==2)
                    {
                    session.setAttribute("account", accdao.checkAccount(username, password));
                    session.setAttribute("listBook",bookdao.getListBook() );
                    response.sendRedirect("admin");
                    }
                }
                break;

            case "register":

                String usernameRe = request.getParameter("username");
                String dateRe = request.getParameter("date");
                String genderRe = request.getParameter("gender");
                String passwordRe = request.getParameter("password");

                //chuyen gender sang kieu boolean
                boolean gender = true;
                if (genderRe.equalsIgnoreCase("false")) {
                    gender = false;
                }
                //check dupliacte username , true : duplicate
                if (accdao.checkDuplicateUsername(usernameRe) == true) {
                    request.setAttribute("mess", "username is duplicate!");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                } else {
                    try {
                        //chuyen date tu string sang date
                      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                      // parsedDate dang la java.util.date
                      Date parsedDate = (Date) dateFormat.parse(dateRe);
                     
                        // add vao db
                        // chuyen parsedDate thanh java.sql.date
                        accdao.addToDB(usernameRe, passwordRe, new java.sql.Date(parsedDate.getTime()) , gender);
                        session.setAttribute("account", accdao.checkAccount(usernameRe, passwordRe));
                        request.getRequestDispatcher("home.jsp").forward(request, response);
                    } catch (Exception e) {
                        session.setAttribute("messDate", "it not date");
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }

                }
                break;
            default:
                throw new AssertionError();
        }

    }

}
