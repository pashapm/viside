package hackday.viside;

import java.util.LinkedList;
import java.util.List;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BlocksList extends ListActivity {

	List<BlockThumb> mList = new LinkedList<BlockThumb>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		BlockThumb receive = new BlockThumb();
		receive.color = Color.parseColor("#ffb264");
		receive.name = "Получить сообщение";
		
		BlockThumb time = new BlockThumb();
		time.color = Color.parseColor("#85dc9a");
		time.name = "Пауза";
		
		BlockThumb move = new BlockThumb();
		move.color = Color.parseColor("#6491ff");
		move.name = "Двигать";
		
		BlockThumb rotate = new BlockThumb();
		rotate.color = Color.parseColor("#6491ff");
		rotate.name = "Вращать";
		
		BlockThumb loop = new BlockThumb();
		loop.color = Color.parseColor("#ffb700");
		loop.name = "Цикл";
		
		mList.add(receive);
		mList.add(rotate);
		mList.add(move);
		mList.add(time);
		mList.add(loop);
		
		BlocksAdapter ad = new BlocksAdapter();
		getListView().setAdapter(ad);
	}
	
	class BlocksAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int arg0) {
			return mList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			
			BlockThumb th = (BlockThumb) getItem(arg0);
			
			TextView tw = new TextView(BlocksList.this);
			tw.setText(th.name);
			tw.setBackgroundColor(th.color);
			tw.setTextSize(25);
			tw.setPadding(20, 20, 20, 20);
			return tw;
		}
		
	}
	
	class BlockThumb {
		String name;
		int color;
	}
	
}
