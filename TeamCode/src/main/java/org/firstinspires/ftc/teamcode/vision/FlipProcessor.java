package org.firstinspires.ftc.teamcode.vision;

import android.graphics.Canvas;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;

/**
 * Rotates every frame 180 degrees in place, for a camera mounted upside-down.
 * Must be added to the VisionPortal BEFORE any other processor: the portal passes the same
 * Mat to each processor in order, so everything after this one sees an upright image.
 */
public class FlipProcessor implements VisionProcessor {
    @Override
    public void init(int width, int height, CameraCalibration calibration) {}

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        Core.rotate(frame, frame, Core.ROTATE_180);
        return null;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight,
                            float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {}
}
