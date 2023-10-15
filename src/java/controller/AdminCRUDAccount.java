/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.bookDao;
import dal.categoriDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class AdminCRUDAccount extends HttpServlet {

    AccountDAO accdao = new AccountDAO();
    bookDao bookdao = new bookDao();
    categoriDAO catedao = new categoriDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        switch (action) {
            case "getaccount":
                session.setAttribute("listAccount", accdao.getListAccount());
                session.setAttribute("listcate", catedao.getListCate());
                request.getRequestDispatcher("ForAdminAccount.jsp").forward(request, response);
                break;
            case "Add":
                String usernameRe = request.getParameter("username");
                String dateRe = request.getParameter("date");
                String genderRe = request.getParameter("gender");
                String passwordRe = request.getParameter("password");
                int role =Integer.parseInt(request.getParameter("role"));
                //chuyen gender sang kieu boolean
                boolean gender = true;
                if (genderRe.equalsIgnoreCase("false")) {
                    gender = false;
                }
                //check dupliacte username , true : duplicate
                if (accdao.checkDuplicateUsername(usernameRe) == true) {
                    request.setAttribute("mess", "username is duplicate!");
                    session.setAttribute("listAccount", accdao.getListAccount());
                    session.setAttribute("listcate", catedao.getListCate());
                    request.getRequestDispatcher("ForAdminAccount.jsp").forward(request, response);
                } else {
                    try {
                        //chuyen date tu string sang date
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        // parsedDate dang la java.util.date
                        Date parsedDate = (Date) dateFormat.parse(dateRe);
                        // add vao db
                        // chuyen parsedDate thanh java.sql.date
                        accdao.addToDB(usernameRe, passwordRe, new java.sql.Date(parsedDate.getTime()), gender ,role);
                        session.setAttribute("listAccount", accdao.getListAccount());
                        session.setAttribute("listcate", catedao.getListCate());
                       response.sendRedirect("ForAdminAccount.jsp");
                    } catch (Exception e) {
                        session.setAttribute("messDate", "it not date");
                        session.setAttribute("listAccount", accdao.getListAccount());
                        session.setAttribute("listcate", catedao.getListCate());
                        request.getRequestDispatcher("ForAdminAccount.jsp").forward(request, response);
                    }

                }

                break;
            case "delete":
                String userDE = request.getParameter("id");
                accdao.deleAcc(userDE);
                        session.setAttribute("listAccount", accdao.getListAccount());
                        session.setAttribute("listcate", catedao.getListCate());
                       request.getRequestDispatcher("ForAdminAccount.jsp").forward(request, response);
                break;
        }
    }

}
