package hackday.viside.fragments;

import android.content.res.Resources;
import hackday.viside.MainActivity;
import hackday.viside.R;
import hackday.viside.Unit;
import hackday.viside.blocks.LoopBlock;
import hackday.viside.blocks.MoveBlock;
import hackday.viside.blocks.PauseBlock;
import hackday.viside.blocks.ReceiveMessageBlock;
import hackday.viside.blocks.RotateBlock;
import hackday.viside.blocks.SendMessageBlock;
import hackday.viside.fragments.BlockListFragment.BlockThumb.TYPE;

import java.util.LinkedList;
import java.util.List;

import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: Yuri Ketov
 * Date: 25.05.13
 * Time: 1:36
 */
public class BlockListFragment extends ListFragment
{
    List<BlockThumb> mList = new LinkedList<BlockThumb>();

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        populateList();

        BlocksAdapter ad = new BlocksAdapter();
        setListAdapter(ad);

        getListView().setOnCreateContextMenuListener(new View.OnCreateContextMenuListener()
        {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v,
                                            ContextMenu.ContextMenuInfo menuInfo) {
            	AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            	BlockThumb thumb = (BlockThumb) getListView().getAdapter().getItem(info.position);
            	if (thumb.type == TYPE.RECEIVE || thumb.type == TYPE.SEND) {
            		menu.add(0, 1, 0, "START");
                    menu.add(0, 2, 1, "STOP");
            	}
            }
        });
        
		getListView().setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                BlockThumb thumb = (BlockThumb) arg0.getItemAtPosition(arg2);
                MainActivity host = (MainActivity) getActivity();
                switch (thumb.type) {
                    case LOOP:
                        LoopBlock loop = new LoopBlock(getActivity(), 10);
                        host.addCommandToMaster(loop);
                        break;
                    case PAUSE:
                        PauseBlock p = new PauseBlock(getActivity(), 500);
                        host.addCommandToMaster(p);
                        break;
                    case MOVE:
                        MoveBlock m = new MoveBlock(getActivity(), 20);
                        host.addCommandToMaster(m);
                        break;
                    case ROTATE:
                        RotateBlock r = new RotateBlock(getActivity(), 90);
                        host.addCommandToMaster(r);
                        break;

                    default:
                        break;
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.block_list, null);
    }

    @Override
    public boolean onContextItemSelected(MenuItem aItem)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) aItem.getMenuInfo();
        BlockThumb thumb = (BlockThumb) getListView().getAdapter().getItem(info.position);
        String msg = "";
        switch (aItem.getItemId()) {
            case 1:
                msg = "START";
                break;
            case 2:
                msg = "STOP";
                break;
        }

        MainActivity host = (MainActivity) getActivity();
        if (thumb.type == TYPE.RECEIVE) {
            ReceiveMessageBlock rec = new ReceiveMessageBlock(getActivity(), msg);
            host.addCommandToMaster(rec);
        } else if (thumb.type == TYPE.SEND) {
            SendMessageBlock send = new SendMessageBlock(getActivity(), msg);
            host.addCommandToMaster(send);
        }

        return false;
    }


    private void populateList()
    {
        final Resources res = getActivity().getResources();

        BlockThumb receive = new BlockThumb();
        receive.color = Color.parseColor("#ffb264");
        receive.name = res.getString(R.string.receive_msg);
        receive.type = TYPE.RECEIVE;

        BlockThumb send = new BlockThumb();
        send.color = Color.parseColor("#ffb264");
        send.name = res.getString(R.string.send_msg);
        send.type = TYPE.SEND;

        BlockThumb time = new BlockThumb();
        time.color = Color.parseColor("#85dc9a");
        time.name = res.getString(R.string.pause);;
        time.type = TYPE.PAUSE;

        BlockThumb move = new BlockThumb();
        move.color = Color.parseColor("#6491ff");
        move.name = res.getString(R.string.move);;
        move.type = TYPE.MOVE;

        BlockThumb rotate = new BlockThumb();
        rotate.color = Color.parseColor("#6491ff");
        rotate.name = res.getString(R.string.rotate);;
        rotate.type = TYPE.ROTATE;

        BlockThumb loop = new BlockThumb();
        loop.color = Color.parseColor("#ffb700");
        loop.name = res.getString(R.string.loop);
        loop.type = TYPE.LOOP;

        BlockThumb  dummy = new BlockThumb();
                    dummy.color = Color.parseColor("#cccccc");
                    dummy.name = "if...then...";
                    dummy.type = TYPE.DUMMY;

        BlockThumb  dummy2 = new BlockThumb();
                    dummy2.color = Color.parseColor("#cccccc");
                    dummy2.name = "Open...";
                    dummy2.type = TYPE.DUMMY;

        BlockThumb  dummy3 = new BlockThumb();
                    dummy3.color = Color.parseColor("#ffd42a");
                    dummy3.name = "Angry Bird";
                    dummy3.type = TYPE.DUMMY;

        BlockThumb  dummy4 = new BlockThumb();
                    dummy4.color = Color.parseColor("#00d42a");
                    dummy4.name = "Points";
                    dummy4.type = TYPE.DUMMY;

        mList.add(receive);
        mList.add(send);
        mList.add(loop);
        mList.add(move);
        mList.add(rotate);
        mList.add(time);
        mList.add(dummy);
        mList.add(dummy2);
        mList.add(dummy4);
        mList.add(dummy3);
    }


    /* --------- adapter ------------ */

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

            TextView tw = new TextView(getActivity());
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

        enum TYPE {MOVE, ROTATE, PAUSE, LOOP, SEND, RECEIVE, DUMMY};
    }

}
