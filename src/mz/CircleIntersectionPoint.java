package mz;

/**
 * It calculates the points of intersection of a circle or ellipse,
 * plus other parameters can be specified and retrieved using set/get method.
 * @since 1.0
 * @author <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class CircleIntersectionPoint {
    private double width;
    private double height;
    private double alfa;
    private double xOrigo;
    private double yOrigo;
    private double theta = 0;

    public CircleIntersectionPoint(
        double R,
        double alfa
    ){
        this.width = 2*R;
        this.height = 2*R;
        this.alfa = alfa;
        this.xOrigo = R;
        this.yOrigo = R;
    }

    public CircleIntersectionPoint(
        double R,
        double alfa,
        double xOrigo,
        double yOrigo
    ){
        this.width = 2*R;
        this.height = 2*R;
        this.alfa = alfa;
        this.xOrigo = xOrigo;
        this.yOrigo = yOrigo;
    }

    public CircleIntersectionPoint(
        double width,
        double height,
        double alfa
    ){
        this.width = width;
        this.height = height;
        this.alfa = alfa;
        this.xOrigo = (width/2);
        this.yOrigo = (height/2);
    }

    public CircleIntersectionPoint(
        double width,
        double height,
        double alfa,
        double xOrigo,
        double yOrigo
    ){
        this.width = width;
        this.height = height;
        this.alfa = alfa;
        this.xOrigo = xOrigo;
        this.yOrigo = yOrigo;
    }

    public void setR(double R){
        this.width = 2*R;
        this.height = 2*R;
    }

    public void setWidth(double width){
        this.width = width;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setAlfa(double alfa){
        this.alfa = alfa;
    }

    public void setxOrigo(double xOrigo){
        this.xOrigo = xOrigo;
    }

    public void setyOrigo(double yOrigo){
        this.yOrigo = yOrigo;
    }

    public void setTheta(double theta){
        this.theta = theta;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public double getAlfa(){
        return alfa;
    }

    public double getxOrigo(){
        return xOrigo;
    }

    public double getyOrigo(){
        return yOrigo;
    }

    public double getTheta(){
        return theta;
    }

    public double getxPoint(){
        if(theta==0){
            return xOrigo+(Math.cos(Math.toRadians(alfa))*(width/2));
        }
        else{
            return xOrigo+((Math.cos(Math.toRadians(alfa))*(width/2))*Math.cos(Math.toRadians(theta))-(Math.sin(Math.toRadians(alfa))*(height/2))*Math.sin(Math.toRadians(theta)));
        }
    }

    public double getyPoint(){
        if(theta==0){
            return yOrigo+(Math.sin(Math.toRadians(alfa))*(height/2));
        }
        else{
            return yOrigo+((Math.cos(Math.toRadians(alfa))*(width/2))*Math.sin(Math.toRadians(theta))+(Math.sin(Math.toRadians(alfa))*(height/2))*Math.cos(Math.toRadians(theta)));
        }
    }
}