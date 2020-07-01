package org.firstinspires.ftc.teamcode.legacy.lib.motion;

import com.qualcomm.robotcore.hardware.DcMotorEx;

public abstract class Profile {
    public abstract Double[] generateProfile(double set_position);

    public void run(Double[] velocities, DcMotorEx[] drivers) {
        for (double i : velocities) {
            for (DcMotorEx motor : drivers) {
                motor.setVelocity(i);
            }
        }
    }

}
