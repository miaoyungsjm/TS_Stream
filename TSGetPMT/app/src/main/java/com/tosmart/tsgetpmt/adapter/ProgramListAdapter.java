package com.tosmart.tsgetpmt.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tosmart.tsgetpmt.R;
import com.tosmart.tsgetpmt.beans.tables.PatProgram;

import java.util.List;

import static java.lang.Integer.toHexString;

/**
 * ProgramListAdapter
 *
 * @author ggz
 * @date 2018/3/27
 */

public class ProgramListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<PatProgram> mList;

    private OnItemClickListener mOnItemClickListener;

    class MyViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView programNumTv;
        TextView programMapPidTv;

        public MyViewHolder(View v) {
            super(v);
            itemView = v;
            programNumTv = v.findViewById(R.id.tv_program_number);
            programMapPidTv = v.findViewById(R.id.tv_program_map_pid);
        }
    }

    public ProgramListAdapter(Context context, List<PatProgram> list) {
        super();
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.program_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;

        String str = "0x" + toHexString(mList.get(position).getProgramNumber());
        String strResult = mContext.getResources().getString(R.string.program_list_item_tv_program_number_result);
        str = String.format(strResult, str);
        myViewHolder.programNumTv.setText(str);

        str = "0x" + toHexString(mList.get(position).getProgramMapPid());
        strResult = mContext.getResources().getString(R.string.program_list_item_tv_program_map_pid_result);
        str = String.format(strResult, str);
        myViewHolder.programMapPidTv.setText(str);

        if (mOnItemClickListener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = myViewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(myViewHolder.itemView, pos);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface OnItemClickListener {
        /**
         * onItemClick
         *
         * @param view     itemView
         * @param position item position
         */
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }
}
