package mz;

import java.awt.Color;
import java.awt.Graphics2D;

import mz.CircleIntersectionPoint;

/**
 * Can be called in the paintComponent(Graphics g) method of JComponent,
 * passing the Graphics variable as an argument and converting it to Graphics2D.
 * for calculations, it is necessary to call another external class!
 * @see mz.CircleIntersectionPoint
 * @since 1.0
 * @author <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class RotatingFigure{
    private Graphics2D g2d;
    private int x;
    private int y;
    private int width;
    private int height;
    private double alfa;
    private double theta = 0;
    private double xOrigo;
    private double yOrigo;
    private int Ax, Ay, Bx, By, Cx, Cy, Dx, Dy, Ex, Ey, Fx, Fy, Gx, Gy, Hx, Hy;
    private boolean drawGL = false;
    private Color color = Color.darkGray;

    /**
     * @param g2d (Graphics2d) Graphics
     * @param x axis Initial value of the shape on the axis, from which the drawing should start, specified in the coordinate system.
     * @param y axis Initial value of the shape on the axis, from which the drawing should start, specified in the coordinate system.
     * @param width & xOrigo = width/2
     * @param height & yOrigo = height/2
     * @param alfa the angle of rotation of the shape
     */
    public RotatingFigure(
        Graphics2D g2d,
        int x,
        int y,
        int width,
        int height,
        double alfa
    ){
        this.g2d = g2d;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.alfa = alfa;
        this.xOrigo = width/2;
        this.yOrigo = height/2;
    }

    /**
     * The figure can be rotated by setting the Theta angle
     * @param theta
     */
    public void setTheta(double theta){
        this.theta = theta;
    }

    /**
     * Draws a guide line for the shape. you draw a circle in which the shape is located, two ellipses due to the axis of rotation.
     * if it is {@code true}, it will draw the auxiliary lines, if it is {@code false}, it will not. the helplines do not appear after being invited!
     * @param drawGL 
     */
    public void setDrawGuideLines(boolean drawGL){
        this.drawGL = drawGL;
    }

    /**
     * color can be specified by calling method. it will no longer continue, it will only be valid for the specified figure. protected against overflow!
     * @see RotatingFigure#stopOverflow()
     * @param color
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * you draw a cube that is set on one corner
     * parameters must be specified in the RotatingFigure class.
     */
    public void cube(){
        double beta =20;
        CircleIntersectionPoint circle1a = new CircleIntersectionPoint(width, height, beta, xOrigo, yOrigo);
        CircleIntersectionPoint circle1b = new CircleIntersectionPoint(width, height, (180-beta), xOrigo, yOrigo);
        CircleIntersectionPoint circle2a = new CircleIntersectionPoint(width, height, (360-beta), xOrigo, yOrigo);
        CircleIntersectionPoint circle2b = new CircleIntersectionPoint(width, height, (180+beta), xOrigo, yOrigo);
        int widthc1 = (int) (circle1a.getxPoint()-circle1b.getxPoint());
        int widthc2 = (int) (circle2a.getxPoint()-circle2b.getxPoint());
        double[] xpoints = new double[]{circle1a.getxPoint()+x, circle1b.getxPoint()+x, circle2a.getxPoint()+x, circle2b.getxPoint()+x};
        double[] ypoints = new double[]{circle1a.getyPoint()+y, circle1b.getyPoint()+y, circle2a.getyPoint()+y, circle2b.getyPoint()+y};
        g2d.rotate(Math.toRadians(theta), (width/2)+x, (height/2)+y);
        if(drawGL){
            drawGuideLines(xpoints, ypoints);
        }
        g2d.setColor(color);
        drawCube(widthc1, widthc2, xpoints, ypoints);
        stopOverflow();
    }

    private void drawGuideLines(
        double[] xpoints,
        double[] ypoints
    ){
        g2d.drawOval(x, y, width, height);
        g2d.drawLine((int) xpoints[1], (int) ypoints[1], (int) xpoints[0], (int) ypoints[0]);
        g2d.drawLine((int) xpoints[3], (int) ypoints[3], (int) xpoints[2], (int) ypoints[2]);
        g2d.drawLine((int) (width/2)+x, (int) ypoints[2], (int) (width/2)+x, (int) ypoints[0]);
        g2d.drawOval((int) xpoints[1], (int) (ypoints[1]-(height/12)), (int) (xpoints[0]-xpoints[1]), (int) (height/6));
        g2d.drawOval((int) xpoints[3], (int) (ypoints[3]-(height/12)), (int) (xpoints[2]-xpoints[3]), (int) (height/6));
    }

    private void drawCube(
        int widthc1,
        int widthc2,
        double[] xpoints,
        double[] ypoints
    ){
        int h6 = (height/6);
        int w2 = (width/2)+x;
        Ax = w2;
        Ay = 0+y;
        Hx = w2;
        Hy = height+y;
        Bx = (int) (new CircleIntersectionPoint(widthc2, h6, alfa, w2, ypoints[2]).getxPoint());
        By = (int) (new CircleIntersectionPoint(widthc2, h6, alfa, w2, ypoints[2]).getyPoint());
        Cx = (int) (new CircleIntersectionPoint(widthc2, h6, alfa+(360/3), w2, ypoints[2]).getxPoint());
        Cy = (int) (new CircleIntersectionPoint(widthc2, h6, alfa+(360/3), w2, ypoints[2]).getyPoint());
        Dx = (int) (new CircleIntersectionPoint(widthc2, h6, alfa+(2*360/3), w2, ypoints[2]).getxPoint());
        Dy = (int) (new CircleIntersectionPoint(widthc2, h6, alfa+(2*360/3), w2, ypoints[2]).getyPoint());
        Ex = (int) (new CircleIntersectionPoint(widthc1, h6, alfa+(360/6), w2, ypoints[0]).getxPoint());
        Ey = (int) (new CircleIntersectionPoint(widthc1, h6, alfa+(360/6), w2, ypoints[0]).getyPoint());
        Fx = (int) (new CircleIntersectionPoint(widthc1, h6, alfa+(360/3)+(360/6), w2, ypoints[0]).getxPoint());
        Fy = (int) (new CircleIntersectionPoint(widthc1, h6, alfa+(360/3)+(360/6), w2, ypoints[0]).getyPoint());
        Gx = (int) (new CircleIntersectionPoint(widthc1, h6, alfa+(2*360/3)+(360/6), w2, ypoints[0]).getxPoint());
        Gy = (int) (new CircleIntersectionPoint(widthc1, h6, alfa+(2*360/3)+(360/6), w2, ypoints[0]).getyPoint());
        g2d.drawPolygon(new int[]{Ax,Bx,Ex,Cx}, new int[]{Ay,By,Ey,Cy}, 4);
        g2d.drawPolygon(new int[]{Ax,Bx,Gx,Dx}, new int[]{Ay,By,Gy,Dy}, 4);
        g2d.drawPolygon(new int[]{Ax,Cx,Fx,Dx}, new int[]{Ay,Cy,Fy,Dy}, 4);
        g2d.drawPolygon(new int[]{Hx,Ex,Cx,Fx}, new int[]{Hy,Ey,Cy,Fy}, 4);
        g2d.drawPolygon(new int[]{Hx,Ex,Bx,Gx}, new int[]{Hy,Ey,By,Gy}, 4);
        g2d.drawPolygon(new int[]{Hx,Fx,Dx,Gx}, new int[]{Hy,Fy,Dy,Gy}, 4);
    }

    private void stopOverflow(){
        g2d.setColor(Color.darkGray);
        g2d.rotate(Math.toRadians(-theta), (width/2)+x, (height/2)+y);
    }
}
