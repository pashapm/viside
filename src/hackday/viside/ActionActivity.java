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
	Handler mHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ScrProps.initialize(this);
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
		mPackman.y = 160;
		mGrid.mUnits.add(mPackman);
		
		mButton1 = new Unit();
		mButton1.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game_btn_play));
		mButton1.x = 10;
		mButton1.y = 10;
		mButton1.mOnClick = new OnUnitClick() {
			
			@Override
			public void onClick() {
				if (mAnimatingThread != null) {
					mAnimatingThread.interrupt();
				}
				mAnimatingThread = new AnimatingThread();
				mAnimatingThread.start();
			}
		};
		mGrid.mUnits.add(mButton1);
		
		mButton2 = new Unit();
		mButton2.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game_btn_stop));
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
		
		mHandler = new Handler(new Callback() {
			
			@Override
			public boolean handleMessage(Message msg) {
				mGrid.invalidate();
				return true;
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		if (mAnimatingThread != null) {
			mAnimatingThread.interrupt();
		}
		super.onDestroy();
	}
	
	class AnimatingThread extends Thread {
		
		@Override
		public void run() {
			try {
				int count = 0;
				while (!Thread.interrupted() && count < 10) {
					delayedUpdate();
					mPackman.x += 100;
					delayedUpdate();
					mPackman.y += 100;
					delayedUpdate();
					mPackman.x -= 100;
					delayedUpdate();
					mPackman.y -= 100;
					
					count++;
				} 
			} catch (InterruptedException e) {
				return;
			}
		}
		
		void delayedUpdate() throws InterruptedException {
			mHandler.obtainMessage().sendToTarget();
			Thread.sleep(500);
			mHandler.obtainMessage().sendToTarget();
		}
	}
}
