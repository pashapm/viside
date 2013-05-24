package hackday.viside;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class SnapGrid extends ImageView {

	List<Unit> mUnits = new LinkedList<Unit>();
	private Paint mPaint = new Paint();
	private Unit mActiveUnit;
	
	private int mLastX = -1;
	private int mLastY = -1;

	public SnapGrid(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SnapGrid(Context context) {
		super(context);
	}

	@Override
	public void onDraw(Canvas canvas) {

		for (Unit unit : mUnits) {
			canvas.drawBitmap(unit.bitmap, unit.x, unit.y, mPaint);
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int[] sceneTouch = new int[] {(int) event.getX(), (int) event.getY()};
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mActiveUnit = null;
			handleTouchDown(sceneTouch);
			break;
		case MotionEvent.ACTION_MOVE:
			if (mActiveUnit != null) {
				moveUnit(mActiveUnit, sceneTouch[0], sceneTouch[1]);
			}
			break;
		case MotionEvent.ACTION_UP:
			mActiveUnit = null;
			break;
		}

		invalidate();
		return true;
	}
	
	private void handleTouchDown(int[] sceneTouch) {
		for (Unit unit : mUnits) {
			if (unit.isPointInside(sceneTouch[0], sceneTouch[1])) {
				mActiveUnit = unit;
				break;
			}
		}
	}

	public void moveUnit(Unit unit, int tox, int toy) {
//		int dx = tox - captX;
//		int dy = toy - captY;
		
		unit.x = tox;
		unit.y = toy;
	}

}
