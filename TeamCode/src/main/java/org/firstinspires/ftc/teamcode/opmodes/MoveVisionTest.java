package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.drive.MechanumDrive;
import org.firstinspires.ftc.teamcode.vision.TagVision;
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

@TeleOp(name = "Move Vision Test", group = "Vision")
public class MoveVisionTest extends LinearOpMode {

    @Override
    public void runOpMode() {
        TagVision vision = new TagVision(hardwareMap);
        MechanumDrive drive = new MechanumDrive(hardwareMap);
        vision.setManualExposure(this);
        double MAX_AXIAL = 0.3;
        double MAX_LATERAL = 0.3;
        double MAX_YAW = 0.3;
        double AXIAL_GAIN = 0.02;
        double LATERAL_GAIN = 0.015;
        double YAW_GAIN = 0.01;
        double DISTANCE_IN = 68;
        double CLUSTER_Y_ORIGIN = 7.19;
        double CLUSTER_Z_ORIGIN = -5.62;
        telemetry.addLine("waiting for start");
        telemetry.update();


        waitForStart();
        while(opModeIsActive()){
            List<AprilTagDetection> detections = vision.getDetections();
            telemetry.addData("# Tags", detections.size());

            // Use the first cluster we see; skip single tags so the cast can't crash
            AprilTagClusterDetection target = null;
            for (AprilTagDetection detection : detections) {
                if (detection instanceof AprilTagClusterDetection) {
                    target = (AprilTagClusterDetection) detection;
                    break;
                }
            }

            if (target != null) {

                telemetry.addLine(String.format("\n==== Tag Cluster (%s)", target.metadata.name));
                telemetry.addLine(String.format("Percent tags found: %d", target.percentClusterFound));
                telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", target.ftcPose.range, target.ftcPose.bearing, target.ftcPose.elevation));
            }

            if (gamepad1.dpad_left && target != null) {
                // bearing is + when the cluster is to the LEFT, but drive()'s yaw is + for clockwise (right),
                // so negate it to turn toward the cluster
                double rangeError = target.ftcPose.range - DISTANCE_IN;
                double headingError = target.ftcPose.bearing + CLUSTER_Y_ORIGIN;
                double yawError = target.ftcPose.yaw;
                double axial = Range.clip(rangeError * AXIAL_GAIN, -MAX_AXIAL, MAX_AXIAL);
                double lateral = Range.clip(headingError * LATERAL_GAIN, -MAX_LATERAL, MAX_LATERAL);
                double yaw = Range.clip(-yawError * YAW_GAIN, -MAX_YAW, MAX_YAW);
                drive.drive(axial, lateral, yaw);
                telemetry.addLine(String.format("ALY %6.2f %6.2f %6.2f", axial, lateral, yaw));
            } else {
                // button released or cluster lost: never leave the motors running on old data
                drive.stop();
            }
            telemetry.update();
        }


        vision.close();
    }
}