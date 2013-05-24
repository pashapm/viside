package hackday.viside;

import hackday.viside.Unit.OnUnitClick;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class ActionActivity extends Activity {

	Unit mGhost;
	Unit mPackman;
	Unit mButton1;
	Unit mButton2;
	
	OpCanvas mGrid; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mGrid = new OpCanvas(this);
		mGrid.mUseHandlers = true;
		setContentView(mGrid);
		
		makeUnits();
	}
	
	private void makeUnits() {
		
		mPackman = new Unit();
		mPackman.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pacman));
		mPackman.x = 200;
		mPackman.y = 500;
		mGrid.mUnits.add(mPackman);
		
		mGhost = new Unit();
		mGhost.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ghost));
		mGhost.x = 300;
		mGhost.y = 800;
		mGrid.mUnits.add(mGhost);
		
		mButton1 = new Unit();
		mButton1.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.button));
		mButton1.x = 10;
		mButton1.y = 10;
		mButton1.mOnClick = new OnUnitClick() {
			
			@Override
			public void onClick() {
				mPackman.x -= 40;
			}
		};
		mGrid.mUnits.add(mButton1);
		
		mButton2 = new Unit();
		mButton2.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.button));
		mButton2.x = 150;
		mButton2.y = 10;
		mButton2.mOnClick = new OnUnitClick() {
			
			@Override
			public void onClick() {
				mPackman.x += 40;
			}
		};
		mGrid.mUnits.add(mButton2);
		
	}
}
