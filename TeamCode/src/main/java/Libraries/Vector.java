package Libraries;

public class Vector {
    public double x;
    public double y;
    public Vector(double _x, double _y) {
        x = _x;
        y = _y;
    }

    public double magnitude() {
        return Math.sqrt((x*x)+(y*y));
    }

    public Vector normalized() {
        return new Vector(x/magnitude(),y/magnitude());
    }

    public double toAngle() {
        return Math.atan2(y,x);
    }
}
