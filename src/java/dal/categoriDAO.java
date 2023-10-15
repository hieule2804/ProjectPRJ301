/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categori;

/**
 *
 * @author ADMIN
 */
public class categoriDAO extends DBContext {

    //get list catgori
    public List<Categori> getListCate() {
        List<Categori> list = new ArrayList<>();
        //connet db
        connection = getConnection();
        String sql = "Select * from categori";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameBook = resultSet.getString("categories");

                Categori cate = new Categori();
                cate.setId(id);
                cate.setCategori(nameBook);
                list.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(bookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
