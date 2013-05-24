package hackday.viside;

import hackday.viside.blocks.LoopBlock;
import hackday.viside.blocks.MoveBlock;
import hackday.viside.blocks.PauseBlock;
import hackday.viside.blocks.ReceiveMessageBlock;
import hackday.viside.blocks.RotateBlock;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		OpCanvas grid = new OpCanvas(this);
		
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
		
		grid.grid = true;
		
		LoopBlock loop = new LoopBlock(this, 10);
		loop.x = 150;
		loop.y = 50;
		grid.mUnits.add(loop);
		
		MoveBlock move = new MoveBlock(this, 20);
		move.x = 150;
		move.y = 50;
		grid.mUnits.add(move);
		
		RotateBlock rotate = new RotateBlock(this, 90);
		rotate.x = 150;
		rotate.y = 50;
		grid.mUnits.add(rotate);
		
		PauseBlock pause = new PauseBlock(this, 500);
		pause.x = 150;
		pause.y = 50;
		grid.mUnits.add(pause);
		
		ReceiveMessageBlock receive = new ReceiveMessageBlock(this, "�����");
		receive.x = 150;
		receive.y = 50;
		grid.mUnits.add(receive);
		
		setContentView(grid);	
	}


}
