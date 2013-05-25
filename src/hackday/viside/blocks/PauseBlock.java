package hackday.viside.blocks;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import hackday.viside.R;
import hackday.viside.Unit;

public class PauseBlock extends Unit {

	long mPause = 500;
	
	public PauseBlock(Context ctx, long pause) {
		mCtx = ctx;
		setBitmap(BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.time));
		mPause = pause;
	}
	
	@Override
	public void draw(Canvas canvas, Paint paint) {
		super.draw(canvas, paint);
		
		paint.setTextSize(24);
		canvas.drawText(mCtx.getString(R.string.pause_) + ": " + mPause + mCtx.getString(R.string.ms), x + 120, y + height - 30, paint);
	}
}
