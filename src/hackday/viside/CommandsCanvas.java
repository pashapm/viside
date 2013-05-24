package hackday.viside;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
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
	
	protected List<Unit> getUnits() {
		if (mMaster == null) {
			return new LinkedList<Unit>();
		} else {
			return mMaster.mCommands;
		}
	}

}
