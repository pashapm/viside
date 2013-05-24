package hackday.viside.blocks;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import hackday.viside.R;
import hackday.viside.Unit;

public class MoveBlock extends Unit {

	int mMove = 500;
	
	public MoveBlock(Context ctx, int move) {
		mCtx = ctx;
		setBitmap(BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.move));
		mMove = move;
	}
	
	@Override
	public void draw(Canvas canvas, Paint paint) {
		super.draw(canvas, paint);
		
		paint.setTextSize(24);
		canvas.drawText("Вперед на " + mMove, x + 100, y + height - 30, paint);
	}
}
