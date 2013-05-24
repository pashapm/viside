package hackday.viside.blocks;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import hackday.viside.R;
import hackday.viside.Unit;

public class LoopBlock extends Unit {

	int mLoop = 10;
	
	public LoopBlock(Context ctx, int loop) {
		mCtx = ctx;
		setBitmap(BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.yellow));
		mLoop = loop;
	}
	
	@Override
	public void draw(Canvas canvas, Paint paint) {
		super.draw(canvas, paint);
		
		paint.setTextSize(24);
		canvas.drawText("Выполнить " + mLoop + " раз", x + 20, y + 40, paint);
	}
}
