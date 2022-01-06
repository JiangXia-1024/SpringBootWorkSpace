package com.springboot.springbootdemo.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;

@Document(indexName="jiang",createIndex = false)
/** 设置分片和副本 */
//@Setting(shards = 3, replicas = 1)
public class Book implements Serializable {
    /** 必须有 ID，这里的 ID 是全局唯一的标识，等同于 es 中的 "_id" */
    @Id
    @Field(name = "id", type = FieldType.Long)
    private Integer id;
    // 书籍名称
    private String bookname;
    // 作者
    private String author;

    public Book(Integer id, String bookname, String author) {
        this.id = id;
        this.bookname = bookname;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookname='" + bookname + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public Book() {
    }
}

