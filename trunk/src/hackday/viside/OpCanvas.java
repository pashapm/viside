package hackday.viside;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class OpCanvas extends ImageView {

	List<Unit> mUnits = new LinkedList<Unit>();
	private Paint mPaint = new Paint();
	private Unit mActiveUnit;
	
	private int mLastX = -1;
	private int mLastY = -1;
	
	boolean mGrid = false;
	boolean mUseHandlers = false;
	
	private int mGridSize = 60;
	
	

	public OpCanvas(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public OpCanvas(Context context) {
		super(context);
		init();
	}
	
	void init() {
		mPaint.setAntiAlias(true);
		mPaint.setTypeface(Typeface.DEFAULT_BOLD);
	}

	@Override
	public void onDraw(Canvas canvas) {
		
		for (Unit unit : mUnits) {
			unit.draw(canvas, mPaint);
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int[] sceneTouch = new int[] {(int) event.getX(), (int) event.getY()};
		
		if (mUseHandlers) { 
			if (event.getAction() != MotionEvent.ACTION_DOWN) {
				return true;
			}
			
			if (handleTouchDown(sceneTouch)) {
				mActiveUnit.onClick();
			} 
			invalidate();
			return true;
		}
		
		
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
	
	private boolean handleTouchDown(int[] sceneTouch) {
		for (int i=mUnits.size()-1; i>=0; --i) {
			Unit unit = mUnits.get(i);
			if (unit.isPointInside(sceneTouch[0], sceneTouch[1])) {
				mActiveUnit = unit;
				return true;
			}
		}
		return false;
	}

	public void moveUnit(Unit unit, int tox, int toy) {
		if (mGrid) {
			unit.x = tox - tox % mGridSize;
			unit.y = toy - toy % mGridSize;
		} else {
			unit.x = tox;
			unit.y = toy;
		}
	}

}
