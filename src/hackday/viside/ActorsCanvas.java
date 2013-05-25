package hackday.viside;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class ActorsCanvas extends ImageView {

	protected Paint mPaint = new Paint();
	public Unit mActiveUnit;
	
	private int mLastX = -1;
	private int mLastY = -1;
	
	private int mOffsetX = 0;
	private int mOffsetY = 0;
	
	boolean mGrid = false;
	boolean mUseHandlers = false;
	
	private int mGridSize = 60;
	
	public List<Unit> mUnits = new LinkedList<Unit>();
	
	public ActorsCanvas(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ActorsCanvas(Context context) {
		super(context);
		init();
	}
	
	void init() {
		mPaint.setAntiAlias(true);
		mPaint.setTypeface(Typeface.DEFAULT_BOLD);
	}

	@Override
	public void onDraw(Canvas canvas) {
		for (Unit unit : getUnits()) {
			unit.draw(canvas, mPaint);
			if (unit == mActiveUnit) {
				mPaint.setColor(Color.RED);
				mPaint.setStrokeWidth(3);
				
				canvas.drawLine(unit.x, unit.y, unit.x + unit.width, unit.y, mPaint);
				canvas.drawLine(unit.x + unit.width, unit.y, unit.x + unit.width, unit.y + unit.height, mPaint);
				canvas.drawLine(unit.x + unit.width, unit.y + unit.height, unit.x, unit.y + unit.height, mPaint);
				canvas.drawLine(unit.x, unit.y + unit.height, unit.x, unit.y, mPaint);
				
				mPaint.setColor(Color.BLACK);
			}
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
			setActive();
			break;
		case MotionEvent.ACTION_MOVE:
			if (mActiveUnit != null) {
				moveUnit(mActiveUnit, sceneTouch[0] - mOffsetX, sceneTouch[1] - mOffsetY);
			}
			break;
		case MotionEvent.ACTION_UP:
			setActive();
			mOffsetX = 0;
			mOffsetY = 0;
			break;
		}

		invalidate();
		return true;
	}
	
	protected void setActive() {
		((MainActivity)getContext()).setActiveActor(mActiveUnit);
	}
	
	private boolean handleTouchDown(int[] sceneTouch) {
		for (int i = getUnits().size()-1; i>=0; --i) {
			Unit unit = getUnits().get(i);
			if (unit.isPointInside(sceneTouch[0], sceneTouch[1])) {
				mActiveUnit = unit;
				mOffsetX = sceneTouch[0] - unit.x;
				mOffsetY = sceneTouch[1] - unit.y;
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
	
	protected List<Unit> getUnits() {
		return mUnits;
	}

}
