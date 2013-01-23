package br.org.cesar.camera;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements Camera.PreviewCallback {

	
	private int y = 20;
	private DrawOnTop mDraw;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		CustomCameraView cv = new CustomCameraView(this.getApplicationContext(), this);
		
		((FrameLayout) findViewById(R.id.preview)).addView(cv);
		mDraw = new DrawOnTop(this, 10, 50, Color.BLACK); 

		addContentView(mDraw, new LayoutParams 
				(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onPreviewFrame(byte[] data, Camera camera) {
		// TODO Auto-generated method stub
		
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.screen);
		
		y = y + 60;
		
		if(y > 900){
			y = 20;
		}
			
		rl.removeView(mDraw);
		mDraw = new DrawOnTop(this, 10, y, Color.RED); 
		rl.addView(mDraw);
		
	}

}
