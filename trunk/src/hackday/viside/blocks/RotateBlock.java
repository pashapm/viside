package hackday.viside.blocks;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import hackday.viside.R;
import hackday.viside.Unit;

public class RotateBlock extends Unit {

	int mRotate = 500;
	
	public RotateBlock(Context ctx, int rotate) {
		mCtx = ctx;
		setBitmap(BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.rotate));
		mRotate = rotate;
	}
	
	@Override
	public void draw(Canvas canvas, Paint paint) {
		super.draw(canvas, paint);
		
		paint.setTextSize(24);
		canvas.drawText("Вращать на " + mRotate, x + 100, y + height - 30, paint);
	}
}
