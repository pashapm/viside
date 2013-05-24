package hackday.viside;

import hackday.viside.Unit.OnUnitClick;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

public class ActionActivity extends Activity {

	Unit mGhost;
	Unit mPackman;
	Unit mButton1;
	Unit mButton2;
	
	OpCanvas mGrid; 
	
	Thread mGhostThread;
	
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
		mPackman.x = 10;
		mPackman.y = 310;
		mGrid.mUnits.add(mPackman);
		
		mGhost = new Unit();
		mGhost.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ghost));
		mGhost.x = 10;
		mGhost.y = 460;
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
		mButton2.x = 10;
		mButton2.y = 160;
		mButton2.mOnClick = new OnUnitClick() {
			
			@Override
			public void onClick() {
				mPackman.x += 40;
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
		mGhostThread = new Thread(new Runnable() {

			@Override
			public void run() {
				int count = 0;
				while (!Thread.interrupted() || count < 10) {
					delayedUpdate();
					mGhost.x += 100;
					delayedUpdate();
					mGhost.y += 100;
					delayedUpdate();
					mGhost.x -= 100;
					delayedUpdate();
					mGhost.y -= 100;
					
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
		mGhostThread.start();
	}
	
	@Override
	protected void onDestroy() {
		mGhostThread.interrupt();
		super.onDestroy();
	}
}
