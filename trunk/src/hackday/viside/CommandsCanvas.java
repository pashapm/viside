package hackday.viside;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class CommandsCanvas extends ActorsCanvas {

	private Unit mMaster;
	
	public CommandsCanvas(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGrid = true;
	}

	public void setMasterActor(Unit unit) {
		mMaster = unit;
	}
	
	@Override
	protected List<Unit> getUnits() {
		if (mMaster == null) {
			return new LinkedList<Unit>();
		} else {
			return mMaster.mCommands;
		}
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		if (mMaster == null) {
			return;
		} else {
			for (Unit unit : mMaster.mCommands) {
				unit.draw(canvas, mPaint);
			}
		}
	}
	
	@Override
	protected void setActive() {};

}
