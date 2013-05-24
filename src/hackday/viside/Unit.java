package hackday.viside;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View.OnClickListener;

public class Unit {

	public Bitmap bitmap;
	public int x;
	public int y;
	public int width;
	public int height;
	
	protected Context mCtx;
	public OnUnitClick mOnClick;
	
	public Bundle data = new Bundle();
	
	public void setBitmap(Bitmap b) {
		bitmap = b;
		width = b.getWidth();
		height = b.getHeight();
	}
	
	public boolean isPointInside(int px, int py) {
		return px > x && py > y && px < (x + width) && py < (y + height);
	}
	
	public void draw(Canvas canvas, Paint paint) {
		canvas.drawBitmap(bitmap, x, y, paint);
	}

	public void onClick() {	
		if (mOnClick != null) {
			mOnClick.onClick();
		}
	};
	
	static interface OnUnitClick {
		void onClick();
	}
}
