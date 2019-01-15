/***************************************************************
 * Autonomous file for when the robot begins facing the crater
 * (Ideally), this file makes the robot delatch from the landing
 * area, place a team marker in the corner, and drive into the crater
 * to earn a total of 60 points
 **************************************************************/

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Autonomous Crater Start")
public class AutonomousCrater extends LinearOpMode {

    // Robot Attributes
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor winchLift;

    private Servo winchLock;

    private CRServo tapeLift;
    private CRServo tapeLift2;

    // Encoder Setup
    private ElapsedTime runTime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1440.0;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 4.125;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);

    @Override
    public void runOpMode() {

        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        winchLift = hardwareMap.get(DcMotor.class, "winchLift");

        winchLock = hardwareMap.get(Servo.class, "winchLift");

        tapeLift = hardwareMap.get(CRServo.class, "tapeLift");
        tapeLift2 = hardwareMap.get(CRServo.class, "tapeLift2");

        waitForStart();

        unlockWinch();

        sleep(300);
    }

    public void unlockWinch() {
        winchLock.setPosition(1.0);
    }

    public void lockWinch() {
        winchLock.setPosition(0.1);
    }

    public static double encoderConvert(double inches) {

        /*
        Encoder constants are off, so this function converts the desired value in inches into a
        pretty closely accurate value to input into encoderDrive()
         */

        return (31 * inches) / 20;
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
