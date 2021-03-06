package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Rover Rukkus Autonomous")
public class Autonomous extends LinearOpMode {
    /*TODO:
        1 - delatch from hook with tape lift
        2 - push the correct block into the corner using a color sensor
        3 - use arm to lower the marker
        4 - drive over into crater and drop arm in crater
     */

    // Motors
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor winchLift;
    private DcMotor tubeArm;

    // Servos
    private CRServo tapeLift;
    private Servo winchLock;

    // Encoder Setup
    private ElapsedTime runTime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1440.0;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 4.125;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);
    static final double DRIVE_SPEED = 1.0;
    static final double TURN_SPEED  = 0.8;


    @Override
    public void runOpMode() throws InterruptedException {

        // HardwareMap Attributes
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        winchLift = hardwareMap.get(DcMotor.class, "winchLift");
        winchLock = hardwareMap.get(Servo.class, "winchLock");
        tapeLift = hardwareMap.get(CRServo.class, "tapeLift");
        tubeArm = hardwareMap.get(DcMotor.class, "tubeArm");
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        // Encoder Setup
        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Path", "Starting at position %7d :%7d", leftDrive.getCurrentPosition(), rightDrive.getCurrentPosition());
        telemetry.update();

        waitForStart();

        encoderDrive(0.5, 175/6, 175/6, 10);
        sleep(300);
        tubeArm.setPower(-0.35);
        sleep(600);
        tubeArm.setPower(0.30);
        sleep(1000);
        tubeArm.setPower(0.0);
        sleep(300);
        encoderDrive(0.5, 8.5, -8.5, 10);
        sleep(300);
        encoderDrive(1.0, 300/6, 300/6, 10);
        sleep(300);
        // Delatch from hook

        // Move Correct block over

        // Lower the marker

        // Drive into crater

    }

    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {

        int newLeftTarget;
        int newRightTarget;

        if(opModeIsActive()) {
            newLeftTarget = leftDrive.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = rightDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            leftDrive.setTargetPosition(newLeftTarget);
            rightDrive.setTargetPosition(newRightTarget);

            leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runTime.reset();
            leftDrive.setPower(Math.abs(speed));
            rightDrive.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runTime.seconds() < timeoutS) &&
                    (leftDrive.isBusy() && rightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        leftDrive.getCurrentPosition(),
                        rightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            leftDrive.setPower(0);
            rightDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

    }
}
