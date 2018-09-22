package com.codminskeyboards.universekeyboard.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.codminskeyboards.universekeyboard.R;
import com.codminskeyboards.universekeyboard.subscriptionmenu.PackageActivity;
import com.codminskeyboards.universekeyboard.utils.GlobalClass;

import de.hdodenhof.circleimageview.CircleImageView;

public class FillDefaultColorAdapter extends RecyclerView.Adapter<FillDefaultColorAdapter.ViewHolder> {
    private Context context;
    private int[] colorArrayList;

    public FillDefaultColorAdapter(Context context, int[] colorArrayList) {
        super();
        this.context = context;
        this.colorArrayList = colorArrayList;
    }

    @Override
    public FillDefaultColorAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_keyborad_bg_color_item, viewGroup, false);
        FillDefaultColorAdapter.ViewHolder viewHolder = new FillDefaultColorAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FillDefaultColorAdapter.ViewHolder holder, final int position) {

        holder.ivColorItem.setImageResource(colorArrayList[position]);

        if (position == GlobalClass.selectcolor && GlobalClass.selview == 1) {
            holder.flBg.setVisibility(View.VISIBLE);
        } else {
            holder.flBg.setVisibility(View.GONE);
        }

        if (GlobalClass.getPrefrenceBoolean(context, GlobalClass.key_isColorLock, true)) {
            if (position > 26) {
                holder.ivLock.setVisibility(View.VISIBLE);
                holder.ivLock.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(context, PackageActivity.class);
                        context.startActivity(i);
                    }
                });
            } else {
                holder.ivLock.setVisibility(View.GONE);
            }
        } else {
            holder.ivLock.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return colorArrayList.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView ivColorItem;
        FrameLayout flBg;
        CircleImageView ivLock;

        ViewHolder(final View itemView) {
            super(itemView);
            ivColorItem = itemView.findViewById(R.id.ivColorItem);
            ivLock = itemView.findViewById(R.id.ivLock);
            flBg = itemView.findViewById(R.id.flBg);
        }
    }
}