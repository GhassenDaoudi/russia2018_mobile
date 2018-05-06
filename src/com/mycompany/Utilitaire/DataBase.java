/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Utilitaire;

import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.mycompany.Entite.User;
import java.io.IOException;

/**
 *
 * @author Ghassen
 */
public class DataBase {

    private Database db;
    private boolean created = false;

    public DataBase() {
        created = Database.exists("russia2018");
        try {
            db = Database.openOrCreate("russia2018");
            if (created == false) {
                String sql = "create table user(id INTEGER);";
                db.execute(sql);
            }
        } catch (IOException ex) {
        }
    }

    public void persist(User u) {
        try {
            String sql = "insert into user (id) values('" + u.getId() + "');";
            db.execute(sql);
        } catch (IOException ex) {
        }
    }

    public int findUser() {
        int a = -1;
        try {
            String rq = "select * from user";
            Cursor cursor = db.executeQuery(rq);
            if (cursor.next()) {
                Row row = cursor.getRow();
                return row.getInteger(0);
            } else {
                return -1;
            }

        } catch (IOException ex) {
        }
        return a;

    }

    public void clearUser() {
        try {
            Database.delete("russia2018");
            System.out.println(created);

            //String sql = "create table user(id INTEGER);";
            //  db.execute(sql);
            //String sql = "delete * from user;";
            //db.execute("delete * from user;");
        } catch (IOException ex) {
        }

    }
}
