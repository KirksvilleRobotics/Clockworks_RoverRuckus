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
    //private Servo armLift2;
    //private Servo armLift3;
    private CRServo tapeLift;
    private Servo winchLock;

    private double lift1Pos;
    private double lift2Pos;

    @Override
    public void init() {
        // TODO: fix arm lift continuity,
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        winchLift = hardwareMap.get(DcMotor.class, "winchLift");
        tapeLift = hardwareMap.get(CRServo.class, "tapeLift");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        armClaw = hardwareMap.get(Servo.class, "armClaw");
        armLift1 = hardwareMap.get(CRServo.class, "armLift1");
        //armLift2 = hardwareMap.get(Servo.class, "armLift2");
        //armLift3 = hardwareMap.get(Servo.class, "armLift3");
        winchLock = hardwareMap.get(Servo.class, "winchLock");

        lift1Pos = 0.0;
        lift2Pos = 0.0;

        telemetry.addData("Initalized","Initialization complete");
    }
    private int i = 0;
    @Override
    public void loop() {

        leftDrive.setPower(gamepad1.left_stick_y * 1.5);
        rightDrive.setPower(gamepad1.right_stick_y * 1.5);

        telemetry.addData("Left stick", gamepad1.left_stick_y);

        if(gamepad1.left_trigger == 0) {
            winchLift.setPower(gamepad1.right_trigger);
        } else if(gamepad1.right_trigger == 0) {
            winchLift.setPower(-1 * gamepad1.left_trigger);
        }
        if(gamepad1.y) {
            tapeLift.setPower(1.0);
        } else if(gamepad1.x) {
            tapeLift.setPower(-1.0);
            telemetry.addData("tape lift moving:", "backwards");
        } else if(!gamepad1.x && !gamepad2.y) {
            tapeLift.setPower(0.0);
        }

        if(gamepad2.x) {
            armClaw.setPosition(1.0);
        }
        else if(gamepad2.y) {
            armClaw.setPosition(0.2);
        }

        if(gamepad2.left_trigger > 0) {
            armLift1.setPower(-1.0);
        } else if(gamepad2.right_trigger > 0) {
            armLift1.setPower(1.0);
        } else if(gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0) {
            armLift1.setPower(0.0);
        }

        if(gamepad1.dpad_down) {
            winchLock.setPosition(0.1);
        } else if(gamepad1.dpad_up) {
            winchLock.setPosition(1);
        }

        /*if(gamepad2.dpad_up) {
            armLift1.setPower(1.0);
        }
        else if(gamepad2.dpad_down) {
            armLift1.setPower(1.0);
        }*/

        /*if(gamepad1.dpad_left) {
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
