package org.firstinspires.ftc.teamcode.team18103.programs.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.team18103.src.Robot;

@TeleOp
public class TestChassis extends OpMode {
    Robot robot = new Robot(hardwareMap, telemetry);

    @Override
    public void init() {
        robot.init();
    }

    @Override
    public void loop() {
        robot.getDriveSubsystem().ultimateDriveController(gamepad1.left_stick_y, gamepad1.left_stick_x,
                gamepad1.right_stick_x, gamepad1.left_trigger, gamepad1.right_trigger,
                gamepad1.left_bumper, gamepad1.right_bumper, gamepad1.x);
        robot.Telemetry();

    }

}
