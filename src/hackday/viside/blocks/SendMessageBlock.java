package hackday.viside.blocks;

import hackday.viside.R;
import hackday.viside.Unit;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class SendMessageBlock extends Unit {

	String mMessage = "";
	
	public SendMessageBlock(Context ctx, String msgName) {
		mCtx = ctx;
		setBitmap(BitmapFactory.decodeResource(mCtx.getResources(), R.drawable.send));
		mMessage = msgName;
	}
	
	@Override
	public void draw(Canvas canvas, Paint paint) {
		super.draw(canvas, paint);
		
		paint.setTextSize(24);
		canvas.drawText(mCtx.getString(R.string.send) + ": " + mMessage, x + 20, y + height - 20, paint);
	}
	
}
