package scut.com.learnvlayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;

import java.util.List;

/**
 * Created by yany on 2017/3/31.
 */

public class MyVLayoutAdapter extends VirtualLayoutAdapter {
    private Context context;

    public MyVLayoutAdapter(@NonNull VirtualLayoutManager layoutManager, Context context) {
        super(layoutManager);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainViewHolder(new TextView(context));
    }

    //定义Viewholder
    class MyViewholder extends RecyclerView.ViewHolder {
        public TextView tv1;
        public MyViewholder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.item_tv1);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 100);
        holder.itemView.setLayoutParams(layoutParams);

        ((TextView) holder.itemView).setText(Integer.toString(position));
        ((TextView) holder.itemView).setGravity(Gravity.CENTER);

//        if (position == 7) {
//            layoutParams.height = 60;
//            layoutParams.width = 60;
//        } else if (position > 35) {
//            layoutParams.height = 200 + (position - 30) * 100;
//        }

        if (position > 35) {
            holder.itemView.setBackgroundColor(0x66cc0000 + (position - 30) * 128);
        } else if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(0xaa00ff00);
        } else {
            holder.itemView.setBackgroundColor(0xccff00ff);
        }

//        MyViewholder myViewholder = (MyViewholder) holder;
//        myViewholder.tv1.setText(Integer.toString(position));
    }

    @Override
    public int getItemCount() {
        List<LayoutHelper> helpers = getLayoutHelpers();
        if (helpers == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0, size = helpers.size(); i < size; i++) {
            count += helpers.get(i).getItemCount();
        }
        System.out.println("count:" + count);
        return count;
    }


     private static class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View itemView) {
            super(itemView);
        }
    }
}
