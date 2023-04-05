package testGui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

import mz.RotatingFigure;

public class TestPanel
extends JPanel
implements ActionListener{
    private Timer timer;
    private int x;
    private int y;
    private int width;
    private int height;
    private double alfa = 0;
    private double theta = 0;

    TestPanel(
        int x,
        int y,
        int width,
        int height
    ){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        circlePanle();
    }

    private void circlePanle(){
        this.setSize(new Dimension(width+1,height+1));
        this.setBackground(Color.BLACK);
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(alfa<360){
            alfa+=0.5;
        }
        else{
            alfa=0;
        }
        if(theta<360){
            theta+=0.5;
        }
        else{
            theta=0;
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        RotatingFigure rf = new RotatingFigure((Graphics2D) g, x, y, width, height, alfa);
        rf.setDrawGuideLines(true);
        rf.setTheta(theta);
        rf.setColor(Color.BLUE);
        rf.tetrahedron();
    }
}