package org.firstinspires.ftc.teamcode.opmodes;

import android.util.Size;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.vision.VisionConstants;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name="Vision Test", group="Vision")
public class VisionTest extends LinearOpMode {
    @Override
    public void runOpMode() {
        AprilTagLibrary lib = AprilTagGameDatabase.getCurrentGameTagLibrary();
        for (int id = 30; id <= 45; id++) {
        telemetry.addLine(id + ": " + (lib.lookupTag(id) != null ? "IN SDK" : "missing"));
    }
        telemetry.update();
        AprilTagProcessor tags = AprilTagProcessor.easyCreateWithDefaults();
        VisionPortal portal = new VisionPortal.Builder()
        .setCamera(hardwareMap.get(WebcamName.class, VisionConstants.CAMERA_NAME))
        .setCameraResolution(new Size(VisionConstants.IMAGE_W, VisionConstants.IMAGE_H))
        .addProcessor(tags)
        .build();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Camera", portal.getCameraState());
            telemetry.addData("FPS", portal.getFps());
            telemetry.addData("Tags seen", tags.getDetections().size());
            telemetry.update();
        }
        portal.close();
    }
}
