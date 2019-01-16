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

        leftDrive.setPower(getVelocity(gamepad1.left_stick_y));
        rightDrive.setPower(getVelocity(gamepad1.right_stick_y));

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

    private double lin_map(double value, double in_min, double in_max, double out_min, double out_max) {
        return (value - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    private double getVelocity(double value) {
        double magnitude = Math.abs(value);
        int direction;
        if(value > 0) direction = 1;
        else if(value < 0) direction = -1;
        else direction = 0;

        if(magnitude > 0.0 && magnitude <= 0.3) {
            return lin_map(magnitude, 0.0, 0.3, 0.0, 0.2) * direction;
        } else if(magnitude > 0.3 && magnitude <= 0.6) {
            return lin_map(magnitude, 0.3, 0.6, 0.2, 0.4) * direction;
        } else if(magnitude > 0.6) {
            return lin_map(magnitude, 0.6, 1.0, 0.4, 1.0) * direction;
        }
        return 0;
    }

}
