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
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

class time2 extends TimerTask {

    public int ider;
    String a = "jdbc:mysql://localhost:3306/oopproject";

    time2(String a) {
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

            String query = "SELECT * FROM texshare ";

            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                SwingP.shareable.codearea.setText(r.getString(2));
//            System.out.println(r.getString(2));
                int lol = r.getInt(3);
                if (lol == 0) {
                    SwingP.shareable.codearea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_C);
                    SwingP.shareable.codearea.setCodeFoldingEnabled(true);
                }

                if (lol == 1) {
                    SwingP.shareable.codearea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
                    SwingP.shareable.codearea.setCodeFoldingEnabled(true);

                }
                if (lol == 2) {
                    SwingP.shareable.codearea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
                    SwingP.shareable.codearea.setCodeFoldingEnabled(true);

                }
                if (lol == 3) {
                    SwingP.shareable.codearea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
                    SwingP.shareable.codearea.setCodeFoldingEnabled(true);
                }
                if (lol == 4) {
                    SwingP.shareable.codearea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
                    SwingP.shareable.codearea.setCodeFoldingEnabled(false);
                }
            }
        } catch (SQLException sq) {
            System.out.println(sq.getMessage());

        }

    }
}

public class REA {

    public Timer timer;
    public TimerTask task;

    public void dispose() {
        timer.cancel();
        task.cancel();
    }

    REA(String A) {

        timer = new Timer();
        task = new time2(A);

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
