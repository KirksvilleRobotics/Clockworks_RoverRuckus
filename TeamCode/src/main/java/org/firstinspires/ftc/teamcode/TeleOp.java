package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "ClockWorks TeleOp")
public class TeleOp extends OpMode {

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor winchLift;

    private Servo armClaw;
    private CRServo armLift1;
    private Servo armLift2;
    private Servo armLift3;
    private double lift1Pos;
    private double lift2Pos;

    @Override
    public void init() {
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        winchLift = hardwareMap.get(DcMotor.class, "winchLift");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        armClaw = hardwareMap.get(Servo.class, "armClaw");
        armLift1 = hardwareMap.get(CRServo.class, "armLift1");
        armLift2 = hardwareMap.get(Servo.class, "armLift2");
        armLift3 = hardwareMap.get(Servo.class, "armLift3");

        lift1Pos = 0.0;
        lift2Pos = 0.0;

        telemetry.addData("Initalized","Initialization complete");
    }

    @Override
    public void loop() {
        leftDrive.setPower(gamepad1.left_stick_y);
        rightDrive.setPower(gamepad1.right_stick_y);

        if(gamepad1.a) {
            winchLift.setPower(0.5);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                telemetry.addData("Error", e.toString());
            }
        }
        if(gamepad1.x) {
            armClaw.setPosition(0.5);
        }
        else if(gamepad1.y) {
            armClaw.setPosition(0.0);
        }
        
        if(gamepad1.dpad_up) {
            armLift1.setPower(1.0);
        }
        else if(gamepad1.dpad_down) {
            armLift1.setPower(1.0);
        }

        if(gamepad1.dpad_left) {
            lift2Pos += 0.1;
            armLift2.setPosition(lift2Pos);
            armLift3.setPosition(lift2Pos);
        }
        else if (gamepad1.dpad_right) {
            lift2Pos -= 0.1;
            armLift2.setPosition(lift2Pos);
            armLift3.setPosition(lift2Pos);
        }
    }
}
