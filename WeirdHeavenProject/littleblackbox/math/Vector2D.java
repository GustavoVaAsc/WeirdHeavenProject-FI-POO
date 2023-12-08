package littleblackbox.math;

public class Vector2D {
    private double x,y;

    public Vector2D(double x,double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(){
        x = 0;
        y = 0;
    }

    public Vector2D vectorSum(Vector2D v) {
        return new Vector2D(x+v.getX(),y+v.getY());
    }

    public Vector2D multWithScalar(double alpha){
        return new Vector2D(x*alpha,y*alpha);
    }

    public Vector2D normalize(){
        double modulo = getModulo();
        return new Vector2D(x/modulo,y/modulo);
    }

    public void limit(double value){
        if (x > value)
            x = value;
        if (x < -value)
            x = -value;
        
        if (y > value)
            y = value;
        if (y < -value)
            y = -value;
    }

    public double getModulo(){return Math.sqrt(x*x+y*y);}
    public double getX() {return x;}
    public double getY() {return y;}

    public void setX(double x){this.x = x;}
    public void setY(double y){this.y = y;}
    public Vector2D setDirection(double angle){
        double modulo = getModulo();
        return new Vector2D(Math.cos(angle)*modulo,Math.sin(angle*modulo));
    }
    
    public boolean isZero() {
        return x == 0 && y == 0;
    }
    
}
