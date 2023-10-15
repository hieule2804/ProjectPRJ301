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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Book;
import model.PageControl;

/**
 *
 * @author ADMIN
 */
public class AdminCRUDBook extends HttpServlet {
    AccountDAO accdao = new AccountDAO();
    bookDao bookdao = new bookDao();
    categoriDAO catedao = new categoriDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //get action
        List<Book> listBookPerPage = new ArrayList<>();
        //pageControl
        PageControl pageControl = new PageControl();
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
                int page;
                try {
                    page = Integer.parseInt(pageRaw);
                } catch (Exception e) {
                    page = 1;
                }
                // set vao page control
                pageControl.setPage(page);
                //tim kiem xem tongr bao nhieu page va book   
                int totalBook;

                totalBook = bookdao.findTotalBook();

                int totalPage = (totalBook % 9) == 0 ? (totalBook / 9) : (totalBook / 9) + 1;
                pageControl.setTotalBook(totalBook);
                pageControl.setTotalPage(totalPage);
                //get ra 1 khoi du lieu
                listBookPerPage = bookdao.getBookOnPage(page);
                break;
            default:
                int pageDE = 1;
                // set vao page control
                pageControl.setPage(pageDE);
                //tim kiem xem tongr bao nhieu page va book   
                int totalBookDE = bookdao.findTotalBook();
                int totalPageDE = (totalBookDE % 9) == 0 ? (totalBookDE / 9) : (totalBookDE / 9) + 1;
                pageControl.setTotalBook(totalBookDE);
                pageControl.setTotalPage(totalPageDE);
                listBookPerPage = bookdao.getBookOnPage(1);
                break;
        }
        session.setAttribute("PageControl", pageControl);
        session.setAttribute("listBook", listBookPerPage);
        session.setAttribute("listcate", catedao.getListCate());
        request.getRequestDispatcher("ForAdmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //get action
        List<Book> listBook = new ArrayList<>();
        //pageControl
        PageControl pageControl = new PageControl();
        String action = request.getParameter("action");
        switch (action) {
            case "Add":
                String NameBook = request.getParameter("BookName");
                String image = request.getParameter("image");
                String author = request.getParameter("author");
                String priceRe = request.getParameter("price");
                String date_of_publicRe = request.getParameter("date_of_public");
                String describle = request.getParameter("describle");
                String cate = request.getParameter("cate");
                try {
                    int price = Integer.parseInt(priceRe);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf.parse(date_of_publicRe);
                    int cateid = Integer.parseInt(cate);
                    bookdao.addBookFromAdmin(NameBook, image, author, price, new java.sql.Date(date.getTime()), describle, cateid);
                    listBook = bookdao.getListBook();
                } catch (Exception e) {
                    request.setAttribute("mess", "Can not Add Book!");
                }
                session.setAttribute("listBook", listBook);
                session.setAttribute("listcate", catedao.getListCate());
                request.getRequestDispatcher("ForAdmin.jsp").forward(request, response);
                break;
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                bookdao.deleteById(id);
                listBook = bookdao.getListBook();
                session.setAttribute("listBook", listBook);
                session.setAttribute("listcate", catedao.getListCate());
                request.getRequestDispatcher("ForAdmin.jsp").forward(request, response);
                break;
            case "update":
                int idU = Integer.parseInt(request.getParameter("ID"));
                String nameU = request.getParameter("BookName");
                String imageURE = request.getParameter("image");
                String authorU = request.getParameter("author");
                String priceReU = request.getParameter("price");
                String date_of_publicReURE = request.getParameter("date_of_public");
                String describleU = request.getParameter("describle");
                String cateU = request.getParameter("cate");
                try {
                    int priceU = Integer.parseInt(priceReU);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf.parse(date_of_publicReURE);
                    int cateid = Integer.parseInt(cateU);
                    bookdao.updateBook(idU, nameU, imageURE, authorU, priceU, new java.sql.Date(date.getTime()), describleU, cateid);
                } catch (Exception e) {
                    request.setAttribute("mess", "Can not Update Book!");
                }
                session.setAttribute("listBook", listBook);
                session.setAttribute("listcate", catedao.getListCate());
                request.getRequestDispatcher("ForAdmin.jsp").forward(request, response);
                break;
            case "searchBook":
                String nameSearch = request.getParameter("search");
                String typeSearch = request.getParameter("type");
                if (typeSearch.equalsIgnoreCase("searchbook")) {
                    listBook = bookdao.searchByName(nameSearch);
                    session.setAttribute("listBook", listBook);
                    session.setAttribute("listcate", catedao.getListCate());
                    request.getRequestDispatcher("ForAdmin.jsp").forward(request, response);
                }else{
                        session.setAttribute("listAccount", accdao.getListAccountByName(nameSearch));
                        session.setAttribute("listcate", catedao.getListCate());
                        response.sendRedirect("ForAdminAccount.jsp");
                }
                break;
            case "searchByCate":
                int idS = Integer.parseInt(request.getParameter("id"));
                listBook = bookdao.searchByCate(idS);
                //set id de dung cho pagination  cua list book cung id
                session.setAttribute("listBook", listBook);
                session.setAttribute("listcate", catedao.getListCate());
                request.getRequestDispatcher("ForAdmin.jsp").forward(request, response);
                break;
            case "getAllBook":
                listBook = bookdao.getListBook();
                session.setAttribute("listBook", listBook);
                session.setAttribute("listcate", catedao.getListCate());
                response.sendRedirect("admin");
                break;

        }

    }

}
