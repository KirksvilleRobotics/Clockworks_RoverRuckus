package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Rover Rukkus Autonomous")
public class Autonomous extends LinearOpMode {

    // Motors
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor winchLift;

    // Servos
    private CRServo tapeLift;

    @Override
    public void runOpMode() throws InterruptedException {

        // INIT
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        winchLift = hardwareMap.get(DcMotor.class, "winchLift");

        tapeLift = hardwareMap.get(CRServo.class, "tapeLift");

        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("Initialized", "Initialization Complete.");
        
        waitForStart();

        leftDrive.setPower(1.0);
        rightDrive.setPower(1.0);

        sleep(5000);

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);

    }
}
