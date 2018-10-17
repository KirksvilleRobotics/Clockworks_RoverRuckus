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

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        //armClaw = hardwareMap.get(Servo.class, "armClaw");
        //armLift1 = hardwareMap.get(CRServo.class, "armLift1");
        //armLift2 = hardwareMap.get(Servo.class, "armLift2");
        //armLift3 = hardwareMap.get(Servo.class, "armLift3");

        lift1Pos = 0.0;
        lift2Pos = 0.0;

        telemetry.addData("Initalized","Initialization complete");
    }
    private int i = 0;
    @Override
    public void loop() {

        telemetry.addData("WARNING", "ROBOT WILL SELF DESTRUCT IN " + (10 - i) + "...");
        i++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(i == 11) {
            for(int j = 0; j < 1000; j++) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        leftDrive.setPower(gamepad1.left_stick_y);
        rightDrive.setPower(gamepad1.right_stick_y);

        if(gamepad1.left_trigger == 1.0f) {
            winchLift.setPower(0.5);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                telemetry.addData("Error", e.toString());
            }
            winchLift.setPower(0.0);
        }
        if(gamepad1.right_trigger == 1.0f) {
            winchLift.setPower(-0.5);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                telemetry.addData("Error", e.toString());
            }
            winchLift.setPower(0.0);
        }
        /*if(gamepad1.x) {
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
        }*/
    }
}
