package hackday.viside;

import android.content.Intent;
import android.view.View;
import hackday.viside.blocks.SendMessageBlock;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import hackday.viside.fragments.BlockListFragment;

public class MainActivity extends Activity {

	private ActorsCanvas mActors;
	private CommandsCanvas mCommands;
	private View mBlocksFrag;
	private View mStartBtn;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		ScrProps.initialize(this);
		setContentView(R.layout.activity_main);
		mActors = (ActorsCanvas)findViewById(R.id.actors);
		mCommands = (CommandsCanvas)findViewById(R.id.commands);
		mCommands.setBackgroundColor(Color.LTGRAY);

        mBlocksFrag = (View)findViewById(R.id.blockListFrag);
        mBlocksFrag.setVisibility(View.GONE);

        mStartBtn = (View)findViewById(R.id.startButton);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActionActivity.class);
                //intent.putExtra(ActionActivity.EXTRA_URL, link);
                startActivity(intent);
            }
        });

//		Unit cat = new Unit();
//		cat.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cat));
//		cat.x = 200;
//		cat.y = 200;
//		grid.mUnits.add(cat);
//		
//		Unit b1 = new Unit();
//		b1.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.button));
//		b1.x = 50;
//		b1.y = 50;
//		grid.mUnits.add(b1);
//		
//		Unit b2 = new Unit();
//		b2.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.button));
//		b2.x = 150;
//		b2.y = 50;
//		grid.mUnits.add(b2);
		
		
		
		
		
//		LoopBlock loop = new LoopBlock(this, 10);
//		loop.x = 150;
//		loop.y = 50;
//		grid.mUnits.add(loop);
//		
//		MoveBlock move = new MoveBlock(this, 20);
//		move.x = 150;
//		move.y = 50;
//		grid.mUnits.add(move);
//		
//		RotateBlock rotate = new RotateBlock(this, 90);
//		rotate.x = 150;
//		rotate.y = 50;
//		grid.mUnits.add(rotate);
//		
//		PauseBlock pause = new PauseBlock(this, 500);
//		pause.x = 150;
//		pause.y = 50;
//		grid.mUnits.add(pause);
//		
//		ReceiveMessageBlock receive = new ReceiveMessageBlock(this, getString(R.string.start));
//		receive.x = 150;
//		receive.y = 50;
//		grid.mUnits.add(receive);
//		
//		SendMessageBlock send = new SendMessageBlock(this, getString(R.string.start));
//		send.x = 150;
//		send.y = 50;
//		grid.mUnits.add(send);
		
		addActors();
	}

	void addActors() {
		Unit mPackman = new Unit();
		mPackman.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pacman));
		mPackman.x = 310;
		mPackman.y = 10;
		mActors.mUnits.add(mPackman);
		
//		Unit mGhost = new Unit();
//		mGhost.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ghost));
//		mGhost.x = 10;
//		mGhost.y = 460;
//		mActors.mUnits.add(mGhost);
		
		Unit mButton1 = new Unit();
		mButton1.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game_btn_play));
		mButton1.x = 10;
		mButton1.y = 400;
		mActors.mUnits.add(mButton1);
		
		Unit mButton2 = new Unit();
		mButton2.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game_btn_stop));
		mButton2.x = 160;
		mButton2.y = 300;
		mActors.mUnits.add(mButton2);
		
		
//		SendMessageBlock send = new SendMessageBlock(this, getString(R.string.start));
//		send.x = 150;
//		send.y = 50;
//		mButton1.mCommands.add(send);
	}
	
	public void setActiveActor(Unit master) {
		mActors.mActiveUnit = master;
		mCommands.setMasterActor(master);
		mCommands.invalidate();

        mBlocksFrag.setVisibility( (master == null) ? View.GONE
                                                    : View.VISIBLE );
	}
	
	public Unit getActiveActor() {
		return mActors.mActiveUnit;
	}
	
	public void addCommandToMaster(Unit command) {
		if (mActors.mActiveUnit == null) {
			return;
		}
		
		mActors.mActiveUnit.mCommands.add(command);
		mCommands.invalidate();
	}
}
