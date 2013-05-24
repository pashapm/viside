package hackday.viside;

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
		
		Unit yellow = new Unit();
		yellow.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.yellow));
		yellow.x = 150;
		yellow.y = 50;
		grid.mUnits.add(yellow);
		
		
		Unit blue = new Unit();
		blue.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.move));
		blue.x = 150;
		blue.y = 50;
		grid.mUnits.add(blue);
		
		Unit green = new Unit();
		green.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.time));
		green.x = 150;
		green.y = 50;
		grid.mUnits.add(green);
		
		setContentView(grid);	
	}


}
