package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "ClockWorks TeleOp")
public class TeleOp extends OpMode {

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor winchLift;
    private DcMotor tubeArm;

    @Override
    public void init() {
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        winchLift = hardwareMap.get(DcMotor.class, "winchLift");
        winchLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        tubeArm = hardwareMap.get(DcMotor.class, "tubeArm");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Initalized","Initialization complete");
    }
    @Override
    public void loop() {

        leftDrive.setPower(gamepad1.left_stick_y * 1.5);
        rightDrive.setPower(gamepad1.right_stick_y * 1.5);

        if(gamepad1.right_trigger > 0.1) {
            winchLift.setPower(gamepad1.right_trigger);
        } else if(gamepad1.left_trigger > 0.1) {
            winchLift.setPower(-gamepad1.left_trigger);
        } else {
            winchLift.setPower(0.0);
        }

        if(gamepad2.right_trigger > 0.1) {
            tubeArm.setPower(0.35 * gamepad2.right_trigger);
        } else if(gamepad2.left_trigger > 0.1) {
            tubeArm.setPower(-0.42 * gamepad2.left_trigger);
        } else {
            tubeArm.setPower(0.0);
        }
    }
}
