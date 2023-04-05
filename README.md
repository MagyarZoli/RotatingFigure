## RotatingFigure

## Description
shape drawing, rotation, scaling

## Features
Inviting desired shape easily, responsive design, rotation in several ways.

## Gif
![alt text](https://github.com/MagyarZoli/RotatingFigure/blob/master/gif/giphy1.gif)

![alt text](https://github.com/MagyarZoli/RotatingFigure/blob/master/gif/giphy2.gif)

## CircleIntersectionPoint
for calculations, it is possible to request and rewrite values with an external class call.
 - [CircleIntersectionPoint](https://github.com/MagyarZoli/CircleIntersectionPoint)

## Example
to draw a cube, declare class, call initialization method:
```java
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        RotatingFigure rf = new RotatingFigure((Graphics2D) g, x, y, width, height, alfa);
        rf.setDrawGuideLines(true);
        rf.setColor(Color.BLUE);
        rf.cube();
    }
```

Detail of drawing a cube shape:
```java
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
```

## Authors
Magyar Zoltán

## Contact
magyarz95@gmail.com