package hackday.viside;

import hackday.viside.Unit.OnUnitClick;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.Window;

public class ActionActivity extends Activity {

//	Unit mGhost;
	Unit mPackman;
	Unit mButton1;
	Unit mButton2;
	
	ActorsCanvas mGrid; 
	
	Thread mAnimatingThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		mGrid = new ActorsCanvas(this);
		mGrid.mUseHandlers = true;
		setContentView(mGrid);
		
		makeUnits();
	}
	
	private void makeUnits() {
		
		mPackman = new Unit();
		mPackman.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pacman));
		mPackman.x = 10;
		mPackman.y = 310;
		mGrid.mUnits.add(mPackman);
		
		mButton1 = new Unit();
		mButton1.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.button));
		mButton1.x = 10;
		mButton1.y = 10;
		mButton1.mOnClick = new OnUnitClick() {
			
			@Override
			public void onClick() {
				mAnimatingThread.start();
			}
		};
		mGrid.mUnits.add(mButton1);
		
		mButton2 = new Unit();
		mButton2.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.button));
		mButton2.x = 160;
		mButton2.y = 10;
		mButton2.mOnClick = new OnUnitClick() {
			
			@Override
			public void onClick() {
				if (mAnimatingThread != null) {
					mAnimatingThread.interrupt();
				}
			}
		};
		mGrid.mUnits.add(mButton2);
		
		final Handler han = new Handler(new Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				mGrid.invalidate();
				return true;
			}
		});
		mAnimatingThread = new Thread(new Runnable() {

			@Override
			public void run() {
				int count = 0;
				while (!Thread.interrupted() && count < 10) {
					delayedUpdate();
					mPackman.x += 100;
					delayedUpdate();
					mPackman.y -= 100;
					delayedUpdate();
					mPackman.x -= 100;
					delayedUpdate();
					mPackman.y += 100;
					
					count++;
				}
			}

			void delayedUpdate() {
				try {
					han.obtainMessage().sendToTarget();
					Thread.sleep(500);
					han.obtainMessage().sendToTarget();
				} catch (Exception e) {
					return;
				}
			}
		});
		
	}
	
	@Override
	protected void onDestroy() {
		mAnimatingThread.interrupt();
		super.onDestroy();
	}
}
