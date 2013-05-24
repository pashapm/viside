package hackday.viside;

import android.graphics.Bitmap;
import android.os.Bundle;

public class Unit {

	public Bitmap bitmap;
	public int x;
	public int y;
	public int width;
	public int height;
	
	public Bundle data = new Bundle();
	
	void setBitmap(Bitmap b) {
		bitmap = b;
		width = b.getWidth();
		height = b.getHeight();
	}
	
	public boolean isPointInside(int px, int py) {
		return px > x && py > y && px < (x + width) && py < (y + height);
	}
}
