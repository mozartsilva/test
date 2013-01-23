package br.org.cesar.camera;

import java.io.IOException;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

public class CustomCameraView extends SurfaceView implements SurfaceHolder.Callback {

	private static final String TAG = CustomCameraView.class.getSimpleName();

	private Camera camera;

	private SurfaceHolder previewHolder;
	
	private Camera.PreviewCallback previewCallback;

	public CustomCameraView(Context context, Camera.PreviewCallback previewCallback) {
		super(context);
		previewHolder = this.getHolder();
		previewHolder.addCallback(this);
		previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		this.previewCallback = previewCallback;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Camera.Parameters parameters = camera.getParameters();
//		parameters.setPreviewSize(width, height);
		parameters.setPictureFormat(PixelFormat.JPEG);
		camera.setDisplayOrientation(90);
		camera.setParameters(parameters);
		camera.startPreview();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// The Surface has been created, acquire the camera and tell it where
		// to draw.
		camera = Camera.open();
		try {
			camera.setPreviewDisplay(holder);
			camera.setPreviewCallback(this.previewCallback);
//			camera.setPreviewCallback(new PreviewCallback() {
//
//				public void onPreviewFrame(byte[] data, Camera camera) {
//	                
//					RelativeLayout view = (RelativeLayout) findViewById(R.id.screen);
//					DrawOnTop d = new DrawOnTop(getContext(), 10, data.length % 80);
//					view.addView(d);
//					
//					Log.d(TAG, "onPreviewFrame - wrote bytes: " + data.length);
//					CustomCameraView.this.invalidate();
//				}
//			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			camera.setPreviewDisplay(previewHolder);
		} catch (Throwable t) {

		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopPreview();
		camera.release();
		camera = null;
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		Paint p = new Paint(Color.RED);
		Log.d(TAG, "draw");
		canvas.drawText("PREVIEW", 10,
				10, p);
	}	

}