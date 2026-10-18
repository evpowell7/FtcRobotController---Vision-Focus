package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MechanumDrive{

    private DcMotor frontLeftDrive;
    private DcMotor backLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backRightDrive;
    double frontLeftPower;
    double frontRightPower;
    double backLeftPower;
    double backRightPower;
    double max;
    public MechanumDrive(HardwareMap hardwareMap){
        frontLeftDrive = hardwareMap.get(DcMotor.class, "fl");
        backLeftDrive = hardwareMap.get(DcMotor.class, "bl");
        frontRightDrive = hardwareMap.get(DcMotor.class, "fr");
        backRightDrive = hardwareMap.get(DcMotor.class, "br");
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
    }

public void drive(double axial, double lateral, double yaw){ //axial = forward; lateral = sideways; yaw = rotation
    frontLeftPower  = axial + lateral + yaw;
    frontRightPower = axial - lateral - yaw;
    backLeftPower   = axial - lateral + yaw;
    backRightPower  = axial + lateral - yaw;
    max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
    max = Math.max(max, Math.abs(backLeftPower));
    max = Math.max(max, Math.abs(backRightPower));
    if (max > 1.0) {
        frontLeftPower  /= max;
        frontRightPower /= max;
        backLeftPower   /= max;
        backRightPower  /= max;
    }
    frontLeftDrive.setPower(frontLeftPower);
    frontRightDrive.setPower(frontRightPower);
    backLeftDrive.setPower(backLeftPower);
    backRightDrive.setPower(backRightPower);
    }

public void stop(){
    drive(0, 0, 0);
    }

}
