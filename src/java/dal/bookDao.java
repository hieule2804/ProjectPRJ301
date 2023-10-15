/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Book;

/**
 *
 * @author ADMIN
 */
public class bookDao extends DBContext {

    //get list book
    public List<Book> getListBook() {
        List<Book> list = new ArrayList<>();
        //connet db
        connection = getConnection();
        String sql = "Select * from Book";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameBook = resultSet.getString("bookName");
                String image = resultSet.getString("image");
                String author = resultSet.getString("author");
                int price = resultSet.getInt("price");
                Date date_of_public = resultSet.getDate("date_of_public");
                String describle = resultSet.getString("describle");
                int cateid = resultSet.getInt("cateid");
                Book book = new Book();
                book.setId(id);
                book.setBookname(nameBook);
                book.setImage(image);
                book.setAuthor(author);
                book.setPrice(price);
                book.setDate_of_public(date_of_public);
                book.setDescrible(describle);
                book.setCateid(cateid);
                list.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(bookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
//Search Book by name

    public List<Book> searchByName(String namebook) {
        List<Book> list = new ArrayList<>();
        //connect db
        connection = getConnection();
        String sql = "SELECT *\n"
                + "  FROM Book\n"
                + "   where bookname like ?;";
        try {
            //prepare command
            statement = connection.prepareStatement(sql);
            statement.setString(1,  namebook + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameBook = resultSet.getString("bookName");
                String image = resultSet.getString("image");
                String author = resultSet.getString("author");
                int price = resultSet.getInt("price");
                Date date_of_public = resultSet.getDate("date_of_public");
                String describle = resultSet.getString("describle");
                int cateid = resultSet.getInt("cateid");
                Book book = new Book();
                book.setId(id);
                book.setBookname(nameBook);
                book.setImage(image);
                book.setAuthor(author);
                book.setPrice(price);
                book.setDate_of_public(date_of_public);
                book.setDescrible(describle);
                book.setCateid(cateid);
                list.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(bookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
//    public static void main(String[] args) {
//        bookDao dao = new bookDao();
//        for (Book book : dao.searchByCate(2)) {
//            System.out.println(book);
//        }
//    }

    //search book by cate id
    public List<Book> searchByCate(int id) {
        List<Book> list = new ArrayList<>();
        //connect db
        connection = getConnection();
        String sql = "SELECT *\n"
                + "  FROM Book\n"
                + "   where cateid = ?;";
        try {
            //prepare command
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idbook = resultSet.getInt("id");
                String nameBook = resultSet.getString("bookName");
                String image = resultSet.getString("image");
                String author = resultSet.getString("author");
                int price = resultSet.getInt("price");
                Date date_of_public = resultSet.getDate("date_of_public");
                String describle = resultSet.getString("describle");
                int cateid = resultSet.getInt("cateid");
                Book book = new Book();
                book.setId(idbook);
                book.setBookname(nameBook);
                book.setImage(image);
                book.setAuthor(author);
                book.setPrice(price);
                book.setDate_of_public(date_of_public);
                book.setDescrible(describle);
                book.setCateid(cateid);
                list.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(bookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public List<Book> getBookOnPage(int page) {
        List<Book> list = new ArrayList<>();
        connection = getConnection();
        String sql = "select * from Book\n"
                + "order by id \n"
                + "OFFSET ? row\n"
                + "fetch next 9 row only";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, (page - 1) * 9);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idbook = resultSet.getInt("id");
                String nameBook = resultSet.getString("bookName");
                String image = resultSet.getString("image");
                String author = resultSet.getString("author");
                int price = resultSet.getInt("price");
                Date date_of_public = resultSet.getDate("date_of_public");
                String describle = resultSet.getString("describle");
                int cateid = resultSet.getInt("cateid");
                Book book = new Book();
                book.setId(idbook);
                book.setBookname(nameBook);
                book.setImage(image);
                book.setAuthor(author);
                book.setPrice(price);
                book.setDate_of_public(date_of_public);
                book.setDescrible(describle);
                book.setCateid(cateid);
                list.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(bookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int findTotalBook() {
        connection = getConnection();
        int count = 0;
        String sql = "select count(*) from book";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(bookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int addBookFromAdmin(String NameBook, String image, String author, int price, Date date, String describle, int cateid) {
        connection = getConnection();
        String sql = "INSERT INTO [dbo].[Book]\n"
                + "           ([bookName]\n"
                + "           ,[image]\n"
                + "           ,[author]\n"
                + "           ,[price]\n"
                + "           ,[date_of_public]\n"
                + "           ,[describle]\n"
                + "           ,[cateid])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, NameBook);
            statement.setString(2, image);
            statement.setString(3, author);
            statement.setInt(4, price);
            statement.setDate(5, date);
            statement.setString(6, describle);
            statement.setInt(7, cateid);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(bookDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public static void main(String[] args) {
        bookDao dao = new bookDao();
        System.out.println((dao.findTotalBook() % 9) == 0 ? (dao.findTotalBook() / 9) : (dao.findTotalBook() / 9) + 1);
    }

    public void deleteById(int id) {
        connection = getConnection();
        String sql = "DELETE FROM [dbo].[Book]\n"
                + " WHERE id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(bookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateBook(int idU, String nameU, String imageURE, String authorU, int priceU, Date date, String describleU, int cateU) {
        connection = getConnection();
        String sql = "UPDATE [dbo].[Book]\n"
                + "   SET [bookName] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[author] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[date_of_public] = ?\n"
                + "      ,[describle] = ?\n"
                + "      ,[cateid] = ?\n"
                + " WHERE id = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, nameU);
            statement.setString(2, imageURE);
            statement.setString(3, authorU);

            statement.setInt(4, priceU);
            statement.setDate(5, date);
            statement.setString(6, describleU);
            statement.setInt(7, cateU);
            statement.setInt(8, idU);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(bookDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int findTotalBookFromId(int id) {
          connection = getConnection();
        int count = 0;
        String sql = "select count(*) from book where id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(bookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
        
    }

}
