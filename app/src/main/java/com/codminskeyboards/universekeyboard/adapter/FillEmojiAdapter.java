package com.codminskeyboards.universekeyboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.codminskeyboards.universekeyboard.R;

public class FillEmojiAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private String[] emojiArrayList;

    public FillEmojiAdapter(Context context, String[] emojiArrayList) {
        super();
        this.emojiArrayList = emojiArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return emojiArrayList.length;
    }

    @Override
    public Object getItem(int position) {
        return emojiArrayList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();

        if (convertView == null)
            convertView = inflater.inflate(R.layout.row_emoji_item, parent, false);

        viewHolder.emojiTextView = convertView.findViewById(R.id.emojiTextView);

        if (emojiArrayList[position] != null && !emojiArrayList[position].isEmpty())
            viewHolder.emojiTextView.setText(emojiArrayList[position]);

        return convertView;
    }

    private static class ViewHolder {
        TextView emojiTextView;
    }
}
