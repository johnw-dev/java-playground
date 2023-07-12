package jmwdev.search;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BadCodeReview {

    @GetMapping("/bookmark")
    public List<Map<String, ?>> get(@RequestParam(value = "id", defaultValue = "0") int id) {
        try {
            String myurl = "jdbc:mysql://localhost:3306/db";
            Connection conn = DriverManager.getConnection(myurl, "root", "******");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * from bookmarks where id = " + id);
            List<Map<String, ?>> results = new ArrayList<>();
            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                results.add(row);
            }
            return results;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    @PostMapping("/bookmark")
    public void update(@RequestParam(value = "id", defaultValue = "0") int id, @RequestParam(value = "url", defaultValue = "") String url) {
        try {
            String myurl = "jdbc:mysql://localhost:3306/db";
            Connection conn = DriverManager.getConnection(myurl, "root", "******");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            if (id == 0) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                stmt.executeUpdate("INSERT INTO bookmarks (url, date) VALUES ('" + url + "', '" + formatter.format(date) + "')");
            } else {
                stmt.executeUpdate("UPDATE bookmarks set url = '" + url + "' where id = " + id);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
