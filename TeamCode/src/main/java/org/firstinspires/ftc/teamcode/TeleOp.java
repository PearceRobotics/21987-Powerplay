package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Main TeleOp", group="Main")
public class TeleOp extends OpMode {
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor driveFrontLeft  = null;
    private DcMotor driveBackLeft = null;
    private DcMotor driveFrontRight = null;
    private DcMotor driveBackRight  = null;

    private Servo intake = null;

    private DcMotor armLeft = null;
    private DcMotor armRight = null;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        // init hardware variables.
        driveFrontLeft  = hardwareMap.get(DcMotor.class, "driveFrontLeft");
        driveBackLeft   = hardwareMap.get(DcMotor.class, "driveBackLeft");
        driveFrontRight = hardwareMap.get(DcMotor.class, "driveFrontRight");
        driveBackRight  = hardwareMap.get(DcMotor.class, "driveBackRight");
//        intake = hardwareMap.get(Servo.class, "intake");
//        armLeft = hardwareMap.get(DcMotor.class, "armLeft");
//        armRight = hardwareMap.get(DcMotor.class, "armRight");

        /**
         * Set up Drive Motors
         */
        driveFrontRight.setDirection(DcMotor.Direction.REVERSE);
        driveBackRight.setDirection(DcMotor.Direction.REVERSE);
        driveFrontLeft.setDirection(DcMotor.Direction.FORWARD);
        driveBackLeft.setDirection(DcMotor.Direction.FORWARD);

        /**
         * Set up Arm Motors
         */
//        armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        armLeft.setTargetPosition(0);
//        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        armLeft.setPower(1.0);
//        armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        armRight.setTargetPosition(0);
//        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        armRight.setPower(1.0);
//        armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//

        // telemetry
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
//        telemetry.addData("Left Arm Position", armLeft.getCurrentPosition());
//        telemetry.addData("Right Arm Position", armRight.getCurrentPosition());
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // move robot
        drive();

        // move arm & intake


        // telemetry
        telemetry.addData("Status", "Run Time: " + runtime.toString());
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        driveFrontLeft.setPower(0);
        driveBackLeft.setPower(0);
        driveFrontRight.setPower(0);
        driveBackRight.setPower(0);

//        armLeft.setTargetPosition(0);
//        armRight.setTargetPosition(0);
    }

    private void drive() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        driveFrontLeft.setPower(frontLeftPower);
        driveBackLeft.setPower(backLeftPower);
        driveFrontRight.setPower(frontRightPower);
        driveBackRight.setPower(backRightPower);
    }
}
