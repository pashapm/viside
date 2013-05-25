package hackday.viside.fragments;

import hackday.viside.fragments.BlockListFragment.BlockThumb.TYPE;
import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import hackday.viside.BlocksList;
import hackday.viside.R;

import java.util.LinkedList;
import java.util.List;

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        populateList();

        BlocksAdapter ad = new BlocksAdapter();
        setListAdapter(ad);
    }

    private void populateList()
    {
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.block_list, null);
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

        enum TYPE {MOVE, ROTATE, PAUSE, LOOP, SEND, RECEIVE};
    }

}
