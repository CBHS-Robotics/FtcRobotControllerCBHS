package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import Libraries.Vector;
import Libraries.AdvancedMovement.MecanumController;

import java.text.SimpleDateFormat;
import java.util.Date;

@TeleOp
public class Controller extends OpMode {
    MecanumController mController;
    boolean fast = false;

    @Override
    public void init() {
        DcMotor lb = hardwareMap.get(DcMotor.class, "leftback");
        DcMotor lf = hardwareMap.get(DcMotor.class, "leftfront");
        DcMotor rb = hardwareMap.get(DcMotor.class, "rightback");
        DcMotor rf = hardwareMap.get(DcMotor.class, "rightfront");

        //New and improved controller (previously just all included in the file so it was 1043989 lines long)
        mController = new MecanumController(rf,lf,rb,lb);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + "Oops. I removed the value and forgot to put it back.");
        telemetry.addData("A Button: ", gamepad1.a);

        if(gamepad1.a){
            fast = true;
        }
        else{
            fast = false;
        }
        //why is it so ugly
        if(fast){
            mController.frontSpeed = 2.0;
            Vector leftStick = new Vector((float)-gamepad1.left_stick_x,(float)gamepad1.left_stick_y);
            mController.SetDirection(leftStick,gamepad1.right_stick_x);
        }
        else{
            mController.frontSpeed = 0.55;
            Vector leftStick = new Vector((float)-gamepad1.left_stick_x,(float)gamepad1.left_stick_y);
            mController.SetDirection(leftStick,gamepad1.right_stick_x);
        }


        telemetry.update();
    }
}
