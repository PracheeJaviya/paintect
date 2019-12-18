/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PRACHEE JAVIYA
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;

import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

class time extends TimerTask {

    public int ider;
    String a = "jdbc:mysql://localhost:3306/oopproject";

    time(String a) {
        this.a = a;
        ider = SwingP.mainwindow.id;
    }

    public static int i = 0;

    @Override
    public void run() {
        Connection c = SwingP.canvas1.conn;

        //return con;
        try {
            Statement s = c.createStatement();

            String query = "SELECT * FROM javap where id >" + ider;
            ResultSet r = s.executeQuery(query);

            while (r.next()) {
                ider = r.getInt(1);

                System.out.println(r.getString(2) + ", " + r.getInt(3) + ", "
                        + r.getInt(4) + "," + r.getInt(5) + "," + r.getInt(6) + "," + r.getInt(7) + "," + r.getInt(8));

                if (r.getInt(1) == 1) {
                    if (JOptionPane.showConfirmDialog(null, "Clear?") == 0) {
                        SwingP.canvas1.g2.setStroke(new BasicStroke(r.getInt(7)));
                        try {
                            SwingP.canvas1.clear();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(time.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        SwingP.canvas1.repaint();

                    } else {
                        continue;
                    }
                }
                if (r.getString(2).equals("LINE")) {
                    Color m =SwingP.canvas1.g2.getColor();
                    Stroke a=SwingP.canvas1.g2.getStroke();
                    SwingP.canvas1.g2.setStroke(new BasicStroke(r.getInt(7)));
                    SwingP.canvas1.g2.setColor(new Color(r.getInt(8)));
                    SwingP.canvas1.g2.drawLine(r.getInt(3), r.getInt(4), r.getInt(5), r.getInt(6));
                    SwingP.canvas1.repaint();
                    SwingP.canvas1.g2.setColor(m);
                    SwingP.canvas1.g2.setStroke(a);
                } else if (r.getString(2).equals("ERASE")) {
                    Color m =SwingP.canvas1.g2.getColor();
                    SwingP.canvas1.g2.setStroke(new BasicStroke(r.getInt(7)));
                    SwingP.canvas1.g2.setColor(Color.white);
                    SwingP.canvas1.g2.drawLine(r.getInt(3), r.getInt(4), r.getInt(5), r.getInt(6));
                    SwingP.canvas1.repaint();
                    SwingP.canvas1.g2.setColor(m);
                } else if (r.getString(2).equals("CLEAR")) {
                    SwingP.canvas1.g2.setStroke(new BasicStroke(r.getInt(7)));
                    try {
                        SwingP.canvas1.clear();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(time.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    SwingP.canvas1.repaint();
                    
                } else if (r.getString(2).equals("RECT")) {
//          SwingP.canvas1.g2.setStroke(new BasicStroke(r.getInt(7)));
                    //SwingP.canvas1.clear();
                    Color m =SwingP.canvas1.g2.getColor();
                    SwingP.canvas1.g2.setStroke(new BasicStroke(r.getInt(7)));
                    SwingP.canvas1.g2.setColor(new Color(r.getInt(8)));
                    SwingP.canvas1.g2.drawRect(r.getInt(3), r.getInt(4), r.getInt(5), r.getInt(6));
                    SwingP.canvas1.repaint();
                    SwingP.canvas1.g2.setColor(m);
                } else if (r.getString(2).equals("FILLRECT")) {
//          SwingP.canvas1.g2.setStroke(new BasicStroke(r.getInt(7)));
                    //SwingP.canvas1.clear();
                    Color m =SwingP.canvas1.g2.getColor();
                    SwingP.canvas1.g2.setStroke(new BasicStroke(r.getInt(7)));
                    SwingP.canvas1.g2.setColor(new Color(r.getInt(8)));
                    SwingP.canvas1.g2.fillRect(r.getInt(3), r.getInt(4), r.getInt(5), r.getInt(6));
                    SwingP.canvas1.repaint();
                    SwingP.canvas1.g2.setColor(m);
                } else if (r.getString(2).equals("FILLOVAL")) {
//          SwingP.canvas1.g2.setStroke(new BasicStroke(r.getInt(7)));
                    //SwingP.canvas1.clear();
                    Color m =SwingP.canvas1.g2.getColor();
                    SwingP.canvas1.g2.setStroke(new BasicStroke(r.getInt(7)));
                    SwingP.canvas1.g2.setColor(new Color(r.getInt(8)));
                    SwingP.canvas1.g2.fillOval(r.getInt(3), r.getInt(4), r.getInt(5), r.getInt(6));
                    SwingP.canvas1.repaint();
                    SwingP.canvas1.g2.setColor(m);
                } else if (r.getString(2).equals("OVAL")) {
//          SwingP.canvas1.g2.setStroke(new BasicStroke(r.getInt(7)));
                    //SwingP.canvas1.clear();
                    Color m =SwingP.canvas1.g2.getColor();
                    SwingP.canvas1.g2.setStroke(new BasicStroke(r.getInt(7)));
                    SwingP.canvas1.g2.setColor(new Color(r.getInt(8)));
                    SwingP.canvas1.g2.drawOval(r.getInt(3), r.getInt(4), r.getInt(5), r.getInt(6));
                    SwingP.canvas1.repaint();
                    SwingP.canvas1.g2.setColor(m);
                }
            }

        } catch (SQLException sq) {
            System.out.println("ERROR123");
        }
    }
}

public class RED {

    public Timer timer;
    public TimerTask task;
    public void dispose()
    {
      timer.cancel();
      task.cancel();
    }
    RED(String A) {

       timer = new Timer();
        task = new time(A);

        timer.schedule(task, 10, 20);

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
