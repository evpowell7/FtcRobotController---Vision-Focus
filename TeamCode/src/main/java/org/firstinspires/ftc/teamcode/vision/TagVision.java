package org.firstinspires.ftc.teamcode.vision;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.ExposureControl;
import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.GainControl;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TagVision {
    AprilTagProcessor tags;
    public final VisionPortal portal;

    public TagVision(HardwareMap hwm) {

        AprilTagLibrary sdkLib = AprilTagGameDatabase.getCurrentGameTagLibrary();
        AprilTagLibrary.Builder lib = new AprilTagLibrary.Builder().addLibrary(sdkLib);

        tags = new AprilTagProcessor.Builder()
                .setTagLibrary(lib.build())
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                .setDrawTagOutline(true)
                .setDrawAxes(true)
                .build();
        tags.setDecimation(2);

        portal = new VisionPortal.Builder()
                .setCamera(hwm.get(WebcamName.class, VisionConstants.CAMERA_NAME))
                .setCameraResolution(new Size(VisionConstants.IMAGE_W, VisionConstants.IMAGE_H))
                .addProcessor(tags)
                .build();
    }

    public void setManualExposure(LinearOpMode op) {
        while (!op.isStopRequested() && portal.getCameraState() != VisionPortal.CameraState.STREAMING) {
            op.sleep(20);
        }
        if (op.isStopRequested()) return;

        ExposureControl exposure = portal.getCameraControl(ExposureControl.class);
        if (exposure.getMode() != ExposureControl.Mode.Manual) {
            exposure.setMode(ExposureControl.Mode.Manual);
            op.sleep(50);
        }
        exposure.setExposure(VisionConstants.EXPOSURE_MS, TimeUnit.MILLISECONDS);
        op.sleep(20);
        portal.getCameraControl(GainControl.class).setGain(VisionConstants.GAIN);
        op.sleep(20);
    }

    public List<AprilTagDetection> getDetections() {
        return tags.getDetections();
    }

    public void close() {
        portal.close();
    }

}