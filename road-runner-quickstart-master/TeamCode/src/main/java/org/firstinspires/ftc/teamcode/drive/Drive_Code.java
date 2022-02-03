

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="Meccanum Drive Code")
@Disabled

public class Drive_Code extends OpMode {



    private DcMotor frontRight, frontLeft, backLeft, backRight;
    private DcMotorEx spinner, lift, intake, turnTable;



    public void init(){
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        frontRight  = hardwareMap.get(DcMotor.class, "Q1/fr");
        frontLeft = hardwareMap.get(DcMotor.class, "Q2/fl");
        backLeft  = hardwareMap.get(DcMotor.class, "Q3/bl");
        backRight = hardwareMap.get(DcMotor.class, "Q4/br");
        spinner = hardwareMap.get(DcMotorEx.class, "carousel");
        lift = hardwareMap.get(DcMotorEx.class, "lift");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        turnTable = hardwareMap.get(DcMotorEx.class, "turnTable");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Active");
        telemetry.update();
        setVelos();

        if(gamepad2.a){
            intake.setPower(1);
        }else if(gamepad2.b){
            intake.setPower(-1);
        }else{
            intake.setPower(0);
        }

        if(gamepad2.x){
            turnTable.setPower(1);
        }else if(gamepad2.y){
            turnTable.setPower(-1);
        }else{
            turnTable.setPower(0);
        }

        if(gamepad2.left_trigger != 0){
            lift.setPower(1);
        }else if(gamepad2.right_trigger != 0){
            lift.setPower(-1);
        }else{
            lift.setPower(0);
        }

        if(gamepad2.right_bumper){
            spinner.setPower(1);
        }else{
            spinner.setPower(0);
        }

    }

    public void setVelos(){
        frontRight.setPower((gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.right_stick_x) * 0.8);
        frontLeft.setPower((gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.right_stick_x) * 0.8);
        backLeft.setPower((gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.right_stick_x) * 0.8);
        backRight.setPower((gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x) * 0.8);
    }


}