
import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.RenderingHints;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Canvas extends JComponent {

    Image image;
    Graphics2D g2;
    private int currentX, currentY, oldX, oldY, inix, iniy;
//    static BufferedImage bi = new BufferedImage(720, 720, BufferedImage.TYPE_INT_ARGB);
    int prevx;
    int prevy;
    static int stroke = 2;
    MouseListener a;
    MouseMotionListener b;
    static int shape = 1;
    static Connection conn = null;
    static int fill = 0;
    static int write = 0;
    static int connectflag = 0;
    public String connstring;

    public Canvas() {
        this.setDoubleBuffered(false);
        connstring = "jdbc:mysql://localhost:3306/oopproject";
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }

        // MouseListener a=new MouseAdapter() {
        //     public void mousePressed(MouseEvent e) {
        //       // save coord x,y when mouse is pressed
        //       oldX = e.getX();
        //       oldY = e.getY();
        //       inix=e.getX();
        //       iniy=e.getY();
        //     }
        //   };
        //   this.addMouseListener(a);
        //   MouseMotionListener b= new MouseMotionAdapter() {
        //     public void mouseDragged(MouseEvent e) {
        //       // coord x,y when drag mouse
        //       currentX = e.getX();
        //       currentY = e.getY();
        //       if (g2 != null) {
        //         // draw line if g2 context not null
        //         g2.drawLine(oldX, oldY, currentX, currentY);
        //         // refresh draw area to repaint
        //        repaint();
        //         // store current coords x,y as olds x,y
        //         oldX = currentX;
        //         oldY = currentY;
        //       }
        //     }
        //   };
        //   this.addMouseMotionListener(b);
    }
//    public Canvas(Image a)
//    {
//      setDoubleBuffered(false);
//      image=a;
////      try {
////            // The newInstance() call is a work around for some
////            // broken Java implementations
////
////            Class.forName("com.mysql.jdbc.Driver").newInstance();
////        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
////            // handle the error
////        }
//    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image == null) {

            image = createImage(this.getHeight(),this.getWidth());
            g2 = (Graphics2D) image.getGraphics();

            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2.setRenderingHint(RenderingHints.KEY_RESOLUTION_VARIANT, RenderingHints.VALUE_RESOLUTION_VARIANT_BASE);

            try {
                // clear draw area
                clear();
            } catch (InterruptedException ex) {
                Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        g.drawImage(image, 0, 0, null);
    }

    public void clear() throws InterruptedException {
        g2.setPaint(Color.white);
        // draw white on entire draw area to clear
        g2.fillRect(0, 0, getSize().width, getSize().height);
        String l = "CLEAR";
        int nunux = getSize().width;
        int nunuy = getSize().height;
        repaint();
        if (write == 1) {
            Color bb = g2.getColor();
            try {
                String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + "CLEAR" + "', '" + 0 + "', '" + 0 + "', '" + nunux + "', '" + nunuy + "', '" + stroke + "', '" + bb.getRGB() + "')";

                PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
            } catch (SQLException ee) {
                Component rootPane = null;
                JOptionPane.showMessageDialog(rootPane, ee);
            }
            
            synchronized(this){
//    while(!condition){
        this.wait(500);
//    }
}
//            this.wait(500);
            try {
                String sql = "delete from javap where 1";

                PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
            } catch (SQLException ee) {
                Component rootPane = null;
                JOptionPane.showMessageDialog(rootPane, ee);
            }
            try {
                String sql = "INSERT INTO `javap`(`id`,`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + 1 + "', '" + "CLEAR" + "', '" + 0 + "', '" + 0 + "', '" + nunux + "', '" + nunuy + "', '" + stroke + "', '" + bb.getRGB() + "')";

                PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
            } catch (SQLException ee) {
                Component rootPane = null;
                JOptionPane.showMessageDialog(rootPane, ee);
            }

        }
        g2.setPaint(Color.black);
        repaint();
    }

    public void red() {
        // apply red color on g2 context
        g2.setPaint(Color.red);
    }

    public void black() {
        g2.setPaint(Color.black);
    }

    public void magenta() {
        g2.setPaint(Color.magenta);
    }

    public void green() {
        g2.setPaint(Color.green);
        //shape=0;
    }

    public void blue() {
        g2.setPaint(Color.blue);
        //shape=0;
        repaint();
    }

    public void gray() {
        g2.setPaint(Color.gray);
    }

    public void dark_gray() {
        g2.setPaint(Color.darkGray);
    }

    public void orange() {
        g2.setPaint(Color.orange);
    }

    public void cyan() {
        g2.setPaint(Color.cyan);
    }

    public void pink() {
        g2.setPaint(Color.pink);
    }

    public void white() {
        g2.setPaint(Color.white);
    }

    public void yellow() {
        g2.setPaint(Color.yellow);
    }

    public void stroke(int a) {
        g2.setStroke(new BasicStroke(a));
        stroke = a;
    }

    public void rect() {
        // removeMouseMotionListener(this);
        // removeMouseListener(this);
        removeMouseMotionListener(b);
        this.removeMouseListener(a);

        b = new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Paint curr=g2.getPaint();
                // int currx=e.getX();
                // int curry=e.getY();

                // if(currx>inix&&curry>iniy)
                // { g2.drawRect(inix,iniy,Math.abs(currx-inix),Math.abs(curry-iniy));
                //   g2.setPaint(Color.WHITE);
                //   g2.drawRect(inix,iniy,Math.abs(prevx-inix),Math.abs(prevy-iniy));
                //   g2.setPaint(curr);
                // }
                // if(curry>iniy&&currx<inix)
                // { g2.drawRect(currx,iniy,Math.abs(currx-inix),Math.abs(curry-iniy));
                //   g2.setPaint(Color.WHITE);
                //   g2.drawRect(prevx,iniy,Math.abs(prevx-inix),Math.abs(prevy-iniy));
                //   g2.setPaint(curr);
                // }                  
                // if(currx<inix && curry<iniy)
                // { g2.drawRect(currx,curry,Math.abs(currx-inix),Math.abs(curry-iniy));
                //   g2.setPaint(Color.WHITE);
                //   g2.drawRect(prevx,prevy,Math.abs(prevx-inix),Math.abs(prevy-iniy));
                //   g2.setPaint(curr);
                // }
                // if(currx>inix && curry<iniy)
                // { g2.drawRect(inix,curry,Math.abs(currx-inix),Math.abs(curry-iniy));
                //   g2.setPaint(Color.WHITE);
                //   g2.drawRect(inix,prevy,Math.abs(prevx-inix),Math.abs(prevy-iniy));
                //   g2.setPaint(curr);
                // }
                // repaint();
                // prevx=currx;
                // prevy=curry;
            }
        };
        a = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();
                inix = e.getX();
                iniy = e.getY();
                prevx = inix;
                prevy = iniy;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                if (currentX > inix && currentY > iniy) {
                    if (fill == 0) {
                        g2.drawRect(inix, iniy, Math.abs(currentX - inix), Math.abs(currentY - iniy));

                        String l = "RECT";
                        int nunux = Math.abs(currentX - inix);
                        int nunuy =  Math.abs(currentY - iniy);
                        if (write == 1) {
                            Color bb = g2.getColor();
                            try {
                                String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l + "', '" + inix + "', '" + iniy + "', '" + nunux + "', '" + nunuy + "', '" + stroke + "', '" + bb.getRGB() + "')";

                                PreparedStatement pst = conn.prepareStatement(sql);
                                pst.execute();
                            } catch (SQLException ee) {
                                Component rootPane = null;
                                JOptionPane.showMessageDialog(rootPane, ee);
                            }
                        }
                    }
                    if (fill == 1) {
                        g2.fillRect(inix, iniy, Math.abs(currentX - inix), Math.abs(currentY - iniy));

                        String l1 = "FILLRECT";
                        int nunux1 = Math.abs(currentX - inix);
                        int nunuy1 = Math.abs(currentY - iniy);
                        if (write == 1) {
                            Color bb = g2.getColor();
                            try {
                                String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l1 + "', '" + inix + "', '" + iniy + "', '" + nunux1 + "', '" + nunuy1 + "', '" + stroke + "', '" + bb.getRGB() + "')";

                                PreparedStatement pst = conn.prepareStatement(sql);
                                pst.execute();
                            } catch (SQLException ee) {
                                Component rootPane = null;
                                JOptionPane.showMessageDialog(rootPane, ee);
                            }
                        }
                    }
                }
                if (currentY > iniy && currentX < inix) {
                    if (fill == 0) {
                        g2.drawRect(currentX, iniy, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                        String l = "RECT";
                        int nunux = Math.abs(currentX - inix);
                        int nunuy = Math.abs(currentY - iniy);
                        if (write == 1) {
                            Color bb = g2.getColor();
                            try {
                                String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l + "', '" + currentX + "', '" + iniy + "', '" + nunux + "', '" + nunuy + "', '" + stroke + "', '" + bb.getRGB() + "')";

                                PreparedStatement pst = conn.prepareStatement(sql);
                                pst.execute();
                            } catch (SQLException ee) {
                                Component rootPane = null;
                                JOptionPane.showMessageDialog(rootPane, ee);
                            }
                        }
                    }
                    if (fill == 1) {
                        g2.fillRect(currentX, iniy, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                        String l1 = "FILLRECT";
                        int nunux1 = Math.abs(currentX - inix);
                        int nunuy1 = Math.abs(currentY - iniy);
                        if (write == 1) {
                            Color bb = g2.getColor();
                            try {
                                String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l1 + "', '" + currentX + "', '" + iniy + "', '" + nunux1 + "', '" + nunuy1 + "', '" + stroke + "', '" + bb.getRGB() + "')";

                                PreparedStatement pst = conn.prepareStatement(sql);
                                pst.execute();
                            } catch (SQLException ee) {
                                Component rootPane = null;
                                JOptionPane.showMessageDialog(rootPane, ee);
                            }
                        }
                    }

                }

                if (currentX < inix && currentY < iniy) {
                    if (fill == 0) {
                        g2.drawRect(currentX, currentY, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                        String l = "RECT";
                        int nunux = Math.abs(currentX - inix);
                        int nunuy = Math.abs(currentY - iniy);
                        if (write == 1) {
                            Color bb = g2.getColor();
                            try {
                                String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l + "', '" + currentX + "', '" + currentY + "', '" + nunux + "', '" + nunuy + "', '" + stroke + "', '" + bb.getRGB() + "')";

                                PreparedStatement pst = conn.prepareStatement(sql);
                                pst.execute();
                            } catch (SQLException ee) {
                                Component rootPane = null;
                                JOptionPane.showMessageDialog(rootPane, ee);
                            }
                        }
                    }
                    if (fill == 1) {
                        g2.fillRect(currentX, currentY, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                        String l1 = "FILLRECT";
                        int nunux1 = Math.abs(currentX - inix);
                        int nunuy1 = Math.abs(currentY - iniy);
                        if (write == 1) {
                            Color bb = g2.getColor();
                            try {
                                String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l1 + "', '" + currentX + "', '" + currentY + "', '" + nunux1 + "', '" + nunuy1 + "', '" + stroke + "', '" + bb.getRGB() + "')";

                                PreparedStatement pst = conn.prepareStatement(sql);
                                pst.execute();
                            } catch (SQLException ee) {
                                Component rootPane = null;
                                JOptionPane.showMessageDialog(rootPane, ee);
                            }
                        }
                    }
                }
                if (currentX > inix && currentY < iniy) {
                    if (fill == 0) {
                        g2.drawRect(inix, currentY, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                        String l = "RECT";
                        int nunux = Math.abs(currentX - inix);
                        int nunuy = Math.abs(currentY - iniy);
                        if (write == 1) {
                            Color bb = g2.getColor();
                            try {
                                String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l + "', '" + inix + "', '" + currentY + "', '" + nunux + "', '" + nunuy + "', '" + stroke + "', '" + bb.getRGB() + "')";

                                PreparedStatement pst = conn.prepareStatement(sql);
                                pst.execute();
                            } catch (SQLException ee) {
                                Component rootPane = null;
                                JOptionPane.showMessageDialog(rootPane, ee);
                            }
                        }
                    }
                    if (fill == 1) {
                        g2.fillRect(inix, currentY, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                        String l1 = "FILLRECT";
                        int nunux1 =  Math.abs(currentX - inix);
                        int nunuy1 = Math.abs(currentY - iniy);
                        if (write == 1) {
                            Color bb = g2.getColor();
                            try {
                                String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l1 + "', '" + inix + "', '" + currentY + "', '" + nunux1 + "', '" + nunuy1 + "', '" + stroke + "', '" + bb.getRGB() + "')";

                                PreparedStatement pst = conn.prepareStatement(sql);
                                pst.execute();
                            } catch (SQLException ee) {
                                Component rootPane = null;
                                JOptionPane.showMessageDialog(rootPane, ee);
                            }
                        }
                    }
                }
                //repaint();
                repaint();

            }

        };
        this.addMouseMotionListener(b);
        this.addMouseListener(a);
        //OKay YEs 

    }

    public void line() {
        this.removeMouseMotionListener(b);
        this.removeMouseListener(a);
        a = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();
                inix = e.getX();
                iniy = e.getY();

            }
        };
        this.addMouseListener(a);

        b = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // coord x,y when drag mouse
                currentX = e.getX();
                currentY = e.getY();

                if (g2 != null) {
                    // draw line if g2 context not null
                    //g2.setStroke(new BasicStroke(25));
                    g2.drawLine(oldX, oldY, currentX, currentY);
                    String l = "LINE";
                    if (write == 1) {
                        Color bb = g2.getColor();
                        try {
                            String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l + "', '" + oldX + "', '" + oldY + "', '" + currentX + "', '" + currentY + "', '" + stroke + "', '" + bb.getRGB() + "')";

                            PreparedStatement pst = conn.prepareStatement(sql);
                            pst.execute();

                        } catch (SQLException ee) {
                            Component rootPane = null;
                            JOptionPane.showMessageDialog(rootPane, ee);
                        }
                    }

                    // refresh draw area to repaint
                    repaint();
                    // store current coords x,y as olds x,y
                    oldX = currentX;
                    oldY = currentY;
                }
            }
        };
        this.addMouseMotionListener(b);
    }

    public void oval() {
        // removeMouseMotionListener(this);
        // removeMouseListener(this);
        removeMouseMotionListener(b);
        this.removeMouseListener(a);

        b = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Paint curr=g2.getPaint();
                // int currx=e.getX();
                // int curry=e.getY();

                // if(currx>inix&&curry>iniy)
                // { g2.drawOval(inix,iniy,Math.abs(currx-inix),Math.abs(curry-iniy));
                //   g2.setPaint(Color.WHITE);
                //   g2.fillOval(inix,iniy,Math.abs(prevx-inix),Math.abs(prevy-iniy));
                //   g2.setPaint(curr);
                // }
                // if(curry>iniy&&currx<inix)
                // { g2.drawOval(currx,iniy,Math.abs(currx-inix),Math.abs(curry-iniy));
                //   g2.setPaint(Color.WHITE);
                //   g2.fillOval(prevx,iniy,Math.abs(prevx-inix),Math.abs(prevy-iniy));
                //   g2.setPaint(curr);
                // }                  
                // if(currx<inix && curry<iniy)
                // { g2.drawOval(currx,curry,Math.abs(currx-inix),Math.abs(curry-iniy));
                //   g2.setPaint(Color.WHITE);
                //   g2.fillOval(prevx,prevy,Math.abs(prevx-inix),Math.abs(prevy-iniy));
                //   g2.setPaint(curr);
                // }
                // if(currx>inix && curry<iniy)
                // { g2.drawOval(inix,curry,Math.abs(currx-inix),Math.abs(curry-iniy));
                //   g2.setPaint(Color.WHITE);
                //   g2.fillOval(inix,prevy,Math.abs(prevx-inix),Math.abs(prevy-iniy));
                //   g2.setPaint(curr);
                // }
                // repaint();
                // prevx=currx;
                // prevy=curry;
            }
        };
        a = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();
                inix = e.getX();
                iniy = e.getY();
                prevx = inix;
                prevy = iniy;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                if (currentX > inix && currentY > iniy) {
                    if (fill == 0) {
                        g2.drawOval(inix, iniy, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                        String l = "OVAL";
                        int nunux = Math.abs(currentX - inix);
                        int nunuy = Math.abs(currentY - iniy);
                        if (write == 1) {
                            Color bb = g2.getColor();
                            try {
                                String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l + "', '" + inix + "', '" + iniy + "', '" + nunux + "', '" + nunuy + "', '" + stroke + "', '" + bb.getRGB() + "')";

                                PreparedStatement pst = conn.prepareStatement(sql);
                                pst.execute();
                            } catch (SQLException ee) {
                                Component rootPane = null;
                                JOptionPane.showMessageDialog(rootPane, ee);
                            }
                        }
                    }

                    if (fill == 1) {
                        g2.fillOval(inix, iniy, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                        String l1 = "FILLOVAL";
                        int nunux1 = Math.abs(currentX - inix);
                        int nunuy1 = Math.abs(currentY - iniy);
                        if (write == 1) {
                            Color bb = g2.getColor();
                            try {
                                String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l1 + "', '" + inix + "', '" + iniy + "', '" + nunux1 + "', '" + nunuy1 + "', '" + stroke + "', '" + bb.getRGB() + "')";

                                PreparedStatement pst = conn.prepareStatement(sql);
                                pst.execute();
                            } catch (SQLException ee) {
                                Component rootPane = null;
                                JOptionPane.showMessageDialog(rootPane, ee);
                            }
                        }
                    }
                }
                if (currentY > iniy && currentX < inix) {
                    if (fill == 0) {
                        g2.drawOval(currentX, iniy, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                    }
                    String l = "OVAL";
                    int nunux = Math.abs(currentX - inix);
                    int nunuy = Math.abs(currentY - iniy);
                    if (write == 1) {
                        Color bb = g2.getColor();
                        try {
                            String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l + "', '" + currentX + "', '" + iniy + "', '" + nunux + "', '" + nunuy + "', '" + stroke + "', '" + bb.getRGB() + "')";

                            PreparedStatement pst = conn.prepareStatement(sql);
                            pst.execute();
                        } catch (SQLException ee) {
                            Component rootPane = null;
                            JOptionPane.showMessageDialog(rootPane, ee);
                        }
                    }

                    if (fill == 1) {
                        g2.fillOval(currentX, iniy, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                    }
                    String l1 = "FILLOVAL";
                    int nunux1 = Math.abs(currentX - inix);
                    int nunuy1 = Math.abs(currentY - iniy);
                    if (write == 1) {
                        Color bb = g2.getColor();
                        try {
                            String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l + "', '" + currentX + "', '" + iniy + "', '" + nunux1 + "', '" + nunuy1 + "', '" + stroke + "', '" + bb.getRGB() + "')";

                            PreparedStatement pst = conn.prepareStatement(sql);
                            pst.execute();
                        } catch (SQLException ee) {
                            Component rootPane = null;
                            JOptionPane.showMessageDialog(rootPane, ee);
                        }
                    }

                }
                if (currentX < inix && currentY < iniy) {
                    if (fill == 0) {
                        g2.drawOval(currentX, currentY, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                    }
                    String l = "OVAL";
                    int nunux = Math.abs(currentX - inix);
                    int nunuy = Math.abs(currentY - iniy);
                    if (write == 1) {
                        Color bb = g2.getColor();
                        try {
                            String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l + "', '" + currentX + "', '" + currentY + "', '" + nunux + "', '" + nunuy + "', '" + stroke + "', '" + bb.getRGB() + "')";

                            PreparedStatement pst = conn.prepareStatement(sql);
                            pst.execute();
                        } catch (SQLException ee) {
                            Component rootPane = null;
                            JOptionPane.showMessageDialog(rootPane, ee);
                        }
                    }
                    if (fill == 1) {
                        g2.fillOval(currentX, currentY, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                    }
                    String l1 = "FILLOVAL";
                    int nunux1 = Math.abs(currentX - inix);
                    int nunuy1 = Math.abs(currentY - iniy);
                    if (write == 1) {
                        Color bb = g2.getColor();
                        try {
                            String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l1 + "', '" + currentX + "', '" + currentY + "', '" + nunux1 + "', '" + nunuy1 + "', '" + stroke + "', '" + bb.getRGB() + "')";

                            PreparedStatement pst = conn.prepareStatement(sql);
                            pst.execute();
                        } catch (SQLException ee) {
                            Component rootPane = null;
                            JOptionPane.showMessageDialog(rootPane, ee);
                        }
                    }

                }
                if (currentX > inix && currentY < iniy) {
                    if (fill == 0) {
                        g2.drawOval(inix, currentY, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                    }
                    String l = "OVAL";
                    int nunux = Math.abs(currentX - inix);
                    int nunuy = Math.abs(currentY - iniy);
                    if (write == 1) {
                        Color bb = g2.getColor();
                        try {
                            String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l + "', '" + inix + "', '" + currentY + "', '" + nunux + "', '" + nunuy + "', '" + stroke + "', '" + bb.getRGB() + "')";

                            PreparedStatement pst = conn.prepareStatement(sql);
                            pst.execute();
                        } catch (SQLException ee) {
                            Component rootPane = null;
                            JOptionPane.showMessageDialog(rootPane, ee);
                        }
                    }
                    if (fill == 1) {
                        g2.fillOval(inix, currentY, Math.abs(currentX - inix), Math.abs(currentY - iniy));
                    }
                    String l1 = "FILLOVAL";
                    int nunux1 = Math.abs(currentX - inix);
                    int nunuy1 = Math.abs(currentY - iniy);
                    if (write == 1) {
                        Color bb = g2.getColor();
                        try {
                            String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l + "', '" + inix + "', '" + currentY + "', '" + nunux1 + "', '" + nunuy1 + "', '" + stroke + "', '" + bb.getRGB() + "')";

                            PreparedStatement pst = conn.prepareStatement(sql);
                            pst.execute();
                        } catch (SQLException ee) {
                            Component rootPane = null;
                            JOptionPane.showMessageDialog(rootPane, ee);
                        }
                    }

                }//repaint();
                repaint();

            }

        };
        this.addMouseMotionListener(b);
        this.addMouseListener(a);

    }

    public void erase() {
        g2.setPaint(Color.WHITE);
        this.removeMouseMotionListener(b);
        this.removeMouseListener(a);
        a = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // save coord x,y when mouse is pressed
                oldX = e.getX();
                oldY = e.getY();
                inix = e.getX();
                iniy = e.getY();

            }
        };
        this.addMouseListener(a);

        b = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // coord x,y when drag mouse
                currentX = e.getX();
                currentY = e.getY();

                if (g2 != null) {
                    // draw line if g2 context not null
                    // g2.setStroke(new BasicStroke(25));
                    g2.drawLine(oldX, oldY, currentX, currentY);
                    String l = "ERASE";

                    if (write == 1) {
                        Color bb = g2.getColor();
                        try {
                            String sql = "INSERT INTO `javap`(`shapename`, `x1`, `y1`, `attr2x`, `attr2y`, `stroke`, `sRGB`) VALUES ('" + l + "', '" + oldX + "', '" + oldY + "', '" + currentX + "', '" + currentY + "', '" + stroke + "', '" + bb.getRGB() + "')";

                            PreparedStatement pst = conn.prepareStatement(sql);
                            pst.execute();
                        } catch (SQLException ee) {
                            Component rootPane = null;
                            JOptionPane.showMessageDialog(rootPane, ee);
                        }
                    }

                    // refresh draw area to repaint
                    repaint();
                    // store current coords x,y as olds x,y
                    oldX = currentX;
                    oldY = currentY;
                }
            }
        };
        this.addMouseMotionListener(b);
    }

    public void configurest(String a) {
        connstring = a;
    }

    public void connectmysql() {
//          DBConnect dbc=new DBConnect();
        int i = connectflag;
        conn = DBConnect.connect(connstring);
        if (conn != null) {
            if (connectflag == 0) {
                JOptionPane.showMessageDialog(null, "Connected");
            }
            connectflag = 1;
        }
        if (connectflag == 1 && i == 1) {
            JOptionPane.showMessageDialog(null, "Reconnected");
        }

    }

    public void write() {
        if (connectflag == 1) {
            write = 1;
        } else {
            JOptionPane.showMessageDialog(null, "Please Connect To Database");
        }

    }

    public void disconnect() {
        conn = null;
        dontwrite();

        if (connectflag == 1) {
            JOptionPane.showMessageDialog(null, "Disconnected");
            connectflag = 0;

        }
    }

    public void dontwrite() {
        write = 0;

    }

}
