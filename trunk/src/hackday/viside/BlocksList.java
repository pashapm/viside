package hackday.viside;

import hackday.viside.BlocksList.BlockThumb.TYPE;
import hackday.viside.blocks.ReceiveMessageBlock;
import hackday.viside.blocks.SendMessageBlock;

import java.util.LinkedList;
import java.util.List;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BlocksList extends ListActivity {

	List<BlockThumb> mList = new LinkedList<BlockThumb>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerForContextMenu(getListView());
		
		BlockThumb receive = new BlockThumb();
		receive.color = Color.parseColor("#ffb264");
		receive.name = "Получить сообщение";
		receive.type = TYPE.RECEIVE;
		
		BlockThumb send = new BlockThumb();
		send.color = Color.parseColor("#ffb264");
		send.name = "Послать сообщение";
		send.type = TYPE.SEND;
		
		BlockThumb time = new BlockThumb();
		time.color = Color.parseColor("#85dc9a");
		time.name = "Пауза";
		time.type = TYPE.PAUSE;
		
		BlockThumb move = new BlockThumb();
		move.color = Color.parseColor("#6491ff");
		move.name = "Двигать";
		move.type = TYPE.MOVE;
		
		BlockThumb rotate = new BlockThumb();
		rotate.color = Color.parseColor("#6491ff");
		rotate.name = "Вращать";
		rotate.type = TYPE.ROTATE;
		
		BlockThumb loop = new BlockThumb();
		loop.color = Color.parseColor("#ffb700");
		loop.name = "Цикл";
		loop.type = TYPE.LOOP;
		
		mList.add(receive);
		mList.add(send);
		mList.add(loop);
		mList.add(rotate);
		mList.add(move);
		mList.add(time);
		
		
		BlocksAdapter ad = new BlocksAdapter();
		getListView().setAdapter(ad);


		getListView().setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				menu.add(0, 1, 0, "START");
				menu.add(0, 2, 1, "LEFT");
				menu.add(0, 3, 3, "RIGHT");
			}
		});
	}
	
	@Override
    public boolean onContextItemSelected(MenuItem aItem) {
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) aItem.getMenuInfo();
			BlockThumb thumb = (BlockThumb) getListView().getAdapter().getItem(info.position);
			String msg = "";
            switch (aItem.getItemId()) {
            case 1:
            	msg = "START";
            	break;
            case 2:
            	msg = "LEFT";
            	break;
            case 3:
            	msg = "RIGHT";
            	break;
            }
            
            if (thumb.type == TYPE.RECEIVE) {
            	ReceiveMessageBlock rec = new ReceiveMessageBlock(this, msg);
            } else if (thumb.type == TYPE.SEND) {
            	SendMessageBlock send = new SendMessageBlock(this, msg);
            }
            
            return false;
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
			tw.setTag(th);
			return tw;
		}
		
	}
	
	static class BlockThumb {
		String name;
		int color;
		TYPE type;
		
		enum TYPE {MOVE, ROTATE, PAUSE, LOOP, SEND, RECEIVE};
	}
	
}
