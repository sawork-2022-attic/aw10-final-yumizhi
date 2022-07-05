package com.micropos.products.repository;

import com.micropos.products.model.Product;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Repository;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class JDRepository implements ProductRepository {
//    private List<Product> products = null;
//
//    @Cacheable(value = "pos-products", key = "'allProducts'")
//    @Override
//    public List<Product> allProducts() {
//        try {
//            if (products == null)
//                products = parseJD("Java");
//        } catch (IOException e) {
//            products = new ArrayList<>();
//        }
//        return products;
//    }
//
//    @Cacheable(value="pos-products", key="#productId")
//    @Override
//    public Product findProduct(String productId) {
//        for (Product p : allProducts()) {
//            if (p.getId().equals(productId)) {
//                return p;
//            }
//        }
//        return null;
//    }
//
//    public static List<Product> parseJD(String keyword) throws IOException {
//        String url = "https://search.jd.com/Search?keyword=" + keyword;
//        Document document = Jsoup.parse(new URL(url), 10000);
//        Element element = document.getElementById("J_goodsList");
//        Elements elements = element.getElementsByTag("li");
//        List<Product> list = new ArrayList<>();
//
//        for (Element el : elements) {
//            String id = el.attr("data-spu");
//            String img = "https:".concat(el.getElementsByTag("img").eq(0).attr("data-lazy-img"));
//            String price = el.getElementsByAttribute("data-price").text();
//            String title = el.getElementsByClass("p-name").eq(0).text();
//            if (title.indexOf("，") >= 0)
//                title = title.substring(0, title.indexOf("，"));
//
//            Product product = new Product(id, title, Double.parseDouble(price), img);
//            list.add(product);
//        }
//        return list;
//    }
//}

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class amazon implements ProductRepository {

    private List<Product> products = null;
    private List<Product> products1 = null;

    @Override
    public List<Product> allProducts() {
        try {
            if (products == null) {
                products = parseAmazon("software", 30);
            }
        } catch (IOException e) {
            products = new ArrayList<>();
        }
        return products;
    }

    @Override
    public Product findProduct(String productId) {
        for (Product p : allProducts()) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> parseAmazon(String keyword, int limit) throws IOException {
        List<Product> list = new ArrayList<>();


        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/amazondata", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return list;
        }

        if (connection == null)
            System.out.print("connection is null!\n");

        try {
            Statement stmt = connection.createStatement();
            if (!stmt.execute(String.format("SELECT * FROM amazondata" + ".%s LIMIT %d, %d;", keyword, 0, limit))) {
                return list;
            }
            ResultSet resultSet = stmt.getResultSet();
            while (resultSet.next()) {
                String id = resultSet.getString("asin");
                String img = resultSet.getString("imageURLHighRes");
                String price = resultSet.getString("price");
                String title = resultSet.getString("title");

                Product product = new Product(id, title, Double.parseDouble(price.substring(1)), img);

                list.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
