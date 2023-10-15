/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.bookDao;
import dal.categoriDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import model.Book;
import model.PageControl;

/**
 *
 * @author ADMIN
 */
public class HomeServlet extends HttpServlet {

    bookDao bookdao =new bookDao();
    categoriDAO cateDAO=new categoriDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Book> listBook = new ArrayList<>();
        //pageControl
        PageControl pageControl = new PageControl();
        //pagination
        String action;
        try {
            action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
        } catch (Exception e) {
            action = "";
        }
        switch (action) {
            case "pagination":
                //get page 
               String pageRaw = request.getParameter("page");
                //validate page
               int page ;
                try {
                   page =Integer.parseInt(pageRaw);
                } catch (Exception e) {
                    page =1;
                }
                // set vao page control
                pageControl.setPage(page);
                //tim kiem xem tongr bao nhieu page va book   
                int totalBook = bookdao.findTotalBook();
                int totalPage = (totalBook % 9)==0?(totalBook/9):(totalBook/9)+1;
                pageControl.setTotalBook(totalBook);
                pageControl.setTotalPage(totalPage);
                //get ra 1 khoi du lieu
                listBook = bookdao.getBookOnPage(page);
                break;
            default:
                int pageDE =1 ;
                 // set vao page control
                pageControl.setPage(pageDE);
                //tim kiem xem tongr bao nhieu page va book   
                int totalBookDE = bookdao.findTotalBook();
                int totalPageDE = (totalBookDE % 9)==0?(totalBookDE/9):(totalBookDE/9)+1;
                pageControl.setTotalBook(totalBookDE);
                pageControl.setTotalPage(totalPageDE);
                listBook =bookdao.getBookOnPage(1);
               break;
        }
        
        session.setAttribute("PageControl", pageControl);
        session.setAttribute("listBook", listBook);
        session.setAttribute("listcate", cateDAO.getListCate());
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get action
        String action = request.getParameter("action");
                HttpSession session = request.getSession();
        switch (action) {
            case "searchByName":
                String namebook = request.getParameter("bookNameSerach");
                //set session list book search by name
                session.setAttribute("listBook", bookdao.searchByName(namebook));
                break;
            case "searchByCate" :
                int id = Integer.parseInt(request.getParameter("id"));
                session.setAttribute("listBook", bookdao.searchByCate(id));
                break;
            default:
                throw new AssertionError();
        }
           request.getRequestDispatcher("home.jsp").forward(request, response);
    }



}
