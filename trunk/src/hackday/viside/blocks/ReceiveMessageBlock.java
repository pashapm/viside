package hackday.viside.blocks;

import hackday.viside.R;
import hackday.viside.Unit;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class ReceiveMessageBlock extends Unit {

	String mMessage = "";
	
	public ReceiveMessageBlock(Context ctx, String msgName) {
		mCtx = ctx;
		setBitmap(BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.receive));
		mMessage = msgName;
	}
	
	@Override
	public void draw(Canvas canvas, Paint paint) {
		super.draw(canvas, paint);
		
		paint.setTextSize(24);
		canvas.drawText("Принять: " + mMessage, x + 20, y + height - 20, paint);
	}
	
}
