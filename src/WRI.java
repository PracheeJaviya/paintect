/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PRACHEE JAVIYA
 */


import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;


class time1 extends TimerTask {

    public int ider;
    String a = "jdbc:mysql://localhost:3306/oopproject";
    String b;
    time1(String a) {
        this.a = a;
        ider = SwingP.mainwindow.id;
        b=null;
    }

    public static int i = 0;

    @Override
    public void run() {
        Connection c = SwingP.canvas1.conn;

        //return con;
        try {
            Statement s = c.createStatement();
            String delete="delete from texshare where id=1";
            String insert="INSERT INTO `texshare`( `id`,`text`, `type`) VALUES ('"+1+"', '" +SwingP.shareable.codearea.getText()+"', '" +SwingP.shareable.jList1.getSelectedIndex()+"')";
            if(!SwingP.shareable.codearea.getText().equals(b))
            {
                s.executeUpdate(delete);
                s.executeUpdate(insert);
            }b=SwingP.shareable.codearea.getText();
            
            

        } catch (SQLException sq) {
            System.out.println(sq.getMessage());
            
        }
    }
}

public class WRI {

    public Timer timer;
    public TimerTask task;
    public void dispose()
    {
      timer.cancel();
      task.cancel();
    }
    WRI(String A) {

       timer = new Timer();
        task = new time1(A);

        timer.schedule(task, 100, 200);

//        Class.forName("com.mysql.jdbc.Driver");
//        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopproject", "root","");
//    
//        Statement s = c.createStatement();
//        String query = "SELECT * FROM javap";
//
//       ResultSet r = s.executeQuery(query);
//
//      while (r.next()) {
//      System.out.println(r.getString(1) + ", " + r.getInt(2) + ", "
//          + r.getInt(3)+","+r.getInt(4)+","+r.getInt(5)+","+r.getInt(6)+","+r.getInt(7));
    }
}
