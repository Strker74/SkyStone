package org.firstinspires.ftc.teamcode.legacy.subsystems.Drive.Odometry;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.legacy.src.Constants;
import org.firstinspires.ftc.teamcode.legacy.subsystems.Subsystem;

public class OdometryGPS extends Subsystem {

    private DcMotor left, right, horizontal;
    private double ticksPerInch;
    private int dt, leftM, rightM, horizontalM;

    private double rightPos = 0, leftPos = 0, horizontalPos = 0,  dTheta = 0, x = 0, y = 0,
            theta = 0, rightPrev = 0, leftPrev = 0, horizontalPrev = 0;

    public OdometryGPS(double ticksPerInch, int dt) {
        this.ticksPerInch = ticksPerInch;
        this.dt = dt;
        leftM = 1;
        rightM = 1;
        horizontalM = 1;
    }

    public OdometryGPS(double ticksPerInch, int dt,
                       double x0, double y0, double theta0) {
        this.ticksPerInch = ticksPerInch;
        this.dt = dt;
        x = x0;
        y = y0;
        theta = theta0;
        leftM = 1;
        rightM = 1;
        horizontalM = 1;
    }

    @Override
    public void init(HardwareMap ahMap) {
        left = ahMap.get(DcMotor.class, Constants.frontLeft);
        right = ahMap.get(DcMotor.class, Constants.frontRight);
        horizontal = ahMap.get(DcMotor.class, Constants.horizontal);

        right.setDirection(DcMotor.Direction.REVERSE);
    }

    public void run() {
        //Get Current Positions
        leftPos = (left.getCurrentPosition() * leftM);
        rightPos = (right.getCurrentPosition() * rightM);

        double dLeft = leftPos - leftPrev;
        double dRight = rightPos - rightPrev;

        //Calculate Angle
        dTheta = (dLeft - dRight) / (Constants.ENCODER_DIFFERENCE);
        theta += dTheta;

        //Get the components of the motion
        horizontalPos = (horizontal.getCurrentPosition()*horizontalM);
        double rawDHorizontal = horizontalPos - horizontalPrev;
        double DHorizontal = rawDHorizontal - (dTheta*Constants.HORIZONTAL_OFFSET);

        double p = ((dRight + dLeft) / 2);
        double n = DHorizontal;

        //Calculate and update the position values
        x += (p*Math.sin(theta) + n*Math.cos(theta));
        y += (p*Math.cos(theta) - n*Math.sin(theta));

        leftPrev = leftPos;
        rightPrev = rightPos;
        horizontalPrev = horizontalPos;
    }


    public double getX() {
        run();
        return x;
    }

    public double getY() {
        run();
        return y;
    }

    public double getTheta() {
        run();
        return theta;
    }

}
