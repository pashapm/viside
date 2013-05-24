package hackday.viside;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SnapGrid grid = new SnapGrid(this);
		
		Unit unit1 = new Unit();
		unit1.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
		unit1.x = 100;
		unit1.y = 200;
		grid.mUnits.add(unit1);
		
		setContentView(grid);	
	}


}
