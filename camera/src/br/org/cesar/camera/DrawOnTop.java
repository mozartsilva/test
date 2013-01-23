package br.org.cesar.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

class DrawOnTop extends View { 

		private int x;
		private int y;
		private int color;
		
        public DrawOnTop(Context context, int x, int y, int color) { 
                super(context); 
                // TODO Auto-generated constructor stub
                this.x = x;
                this.y = y;
                this.color = color;
        } 

        @Override 
        protected void onDraw(Canvas canvas) { 
                // TODO Auto-generated method stub 

                Paint paint = new Paint(); 
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(50);
                paint.setColor(this.color);
                canvas.drawText("Test Text", x, y, paint); 

                super.onDraw(canvas); 
        } 

} 