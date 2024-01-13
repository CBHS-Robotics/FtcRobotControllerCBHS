package Libraries.AdvancedMovement;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;

import Libraries.Vector;

//Copyright absolutely no one because it really doesn't matter
//(You're seeing the code anways, and stealing code is half the reason this works)
public class MecanumController {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor         leftback   ;
    private DcMotor         leftfront  ;
    private DcMotor         rightfront ;
    private DcMotor         rightback  ;

    public double frontSpeed = 0.55;

    public MecanumController(
            DcMotor frontRight, DcMotor frontLeft,
            DcMotor backRight, DcMotor backLeft
    ) {
        //This looks dumb but I'm too lazy to rename the private vars
        leftback = backLeft;
        leftfront = frontLeft;
        rightback = backRight;
        rightfront = frontRight;
    }

    //Sets the direction using a vector2,
    //and a rotation using an angle in degrees (probably, I forgot)
    public void SetDirection(Vector vector, float rotation) {
        double mag = vector.magnitude();

        double angle = vector.toAngle();

        //Fancy Mecanum math that I stole
        double FRs = Math.sin(angle-(0.25f*Math.PI))*mag;
        double FLs = Math.sin(angle+(0.25f*Math.PI))*mag;

        SetMotors(FRs,FLs,rotation);
    }
    //Sets the direction using an x and y
    //and a rotation using an angle in degrees (probably, I forgot)
    public void SetDirection(float x, float y, float rotation) {
        Vector vector = new Vector(-x,-y);

        double mag = vector.magnitude();

        double angle = vector.toAngle();

        //Fancy Mecanum math that I stole
        double FRs = Math.sin(angle-(0.25f*Math.PI))*mag;
        double FLs = Math.sin(angle+(0.25f*Math.PI))*mag;

        SetMotors(FRs,FLs,rotation);
    }
    //I don't remember what this does but it's probably important
    public void SetDirectionA(float angle, float magnitude, float rotation) {
        double FRs = Math.sin(angle-(0.25f*Math.PI))*magnitude;
        double FLs = Math.sin(angle+(0.25f*Math.PI))*magnitude;

        SetMotors(FRs,FLs,rotation);
    }
    //This probably shouldn't be public, but it was so its staying that way
    public void SetMotors(double FR, double FL, double rot) {
        leftback.setPower(-FR+rot);
        leftfront.setPower((-FL+rot)*frontSpeed);
        rightback.setPower(FL+rot);
        rightfront.setPower((FR+rot)*frontSpeed);
    }
}