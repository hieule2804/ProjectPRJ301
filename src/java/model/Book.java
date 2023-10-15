/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class Book {
    private int id;
    private  String bookname,image,author;
    private int price;
    private Date date_of_public;
    private  String describle;
    private int cateid;

    public Book() {
    }

    public Book(int id, String bookname, String image, String author, int price, Date date_of_public, String describle, int cateid) {
        this.id = id;
        this.bookname = bookname;
        this.image = image;
        this.author = author;
        this.price = price;
        this.date_of_public = date_of_public;
        this.describle = describle;
        this.cateid = cateid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate_of_public() {
        return date_of_public;
    }

    public void setDate_of_public(Date date_of_public) {
        this.date_of_public = date_of_public;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", bookname=" + bookname + ", image=" + image + ", author=" + author + ", price=" + price + ", date_of_public=" + date_of_public + ", describle=" + describle + ", cateid=" + cateid + '}';
    }
    
}
