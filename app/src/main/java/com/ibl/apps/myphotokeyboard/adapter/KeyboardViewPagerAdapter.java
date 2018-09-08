package com.ibl.apps.myphotokeyboard.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibl.apps.myphotokeyboard.BuildConfig;
import com.ibl.apps.myphotokeyboard.R;
import com.ibl.apps.myphotokeyboard.activity.CreateKeyboardActivity;
import com.ibl.apps.myphotokeyboard.model.KeyboardData;
import com.ibl.apps.myphotokeyboard.utils.GlobalClass;

import java.util.ArrayList;

public class KeyboardViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<KeyboardData> keyboardArrayList;

    public KeyboardViewPagerAdapter(Context context, ArrayList<KeyboardData> keyboardArrayList) {
        this.context = context;
        this.keyboardArrayList = keyboardArrayList;
    }

    @Override
    public int getCount() {
        return keyboardArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View instantiateItem(final ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.layout_keyboard, null);

        ImageView ivKeyboardBg = view.findViewById(R.id.ivKeyboardBg);
        LinearLayout linFirstRowKeyboard = view.findViewById(R.id.linFirstRowKeyboard);
        LinearLayout linTwoRowKeyboard = view.findViewById(R.id.linTwoRowKeyboard);
        LinearLayout linThreeRowKeyboard = view.findViewById(R.id.linThreeRowKeyboard);
        LinearLayout linFourRowKeyboard = view.findViewById(R.id.linFourRowKeyboard);

        ImageView ivDone = view.findViewById(R.id.ivDone);
        ImageView ivSpace = view.findViewById(R.id.ivSpace);
        ImageView ivShift = view.findViewById(R.id.ivShift);
        ImageView ivCancel = view.findViewById(R.id.ivCancel);
        ImageView ivEmoji = view.findViewById(R.id.ivEmoji);

        if (BuildConfig.VERSION_CODE >= 21)
            view.setClipToOutline(true);

        if (keyboardArrayList.get(position).getBitmapback() != null) {
            byte[] decodedString = Base64.decode(keyboardArrayList.get(position).getBitmapback(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            ivKeyboardBg.setImageBitmap(decodedByte);
        } else {
            ivKeyboardBg.setImageResource(keyboardArrayList.get(position).getKeyboardBgImage());
        }

        int color = Color.parseColor(keyboardArrayList.get(position).getFontColor());
        ivShift.setColorFilter(color);
        ivDone.setColorFilter(color);
        ivCancel.setColorFilter(color);

        GradientDrawable npd1;
        for (int i = 0; i < linFirstRowKeyboard.getChildCount(); i++) {
            final View mChild = linFirstRowKeyboard.getChildAt(i);

            if (mChild instanceof ImageView || mChild instanceof TextView) {
                // Recursively attempt another ViewGroup.
                npd1 = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{keyboardArrayList.get(position).getKeyBgColor(),
                                keyboardArrayList.get(position).getKeyBgColor()});

                npd1.setBounds(mChild.getLeft() + 5, mChild.getTop() + 5, mChild.getRight() - 5, mChild.getBottom() - 5);

                npd1.setCornerRadius(Float.parseFloat(keyboardArrayList.get(position).getKeyRadius()));
                npd1.setAlpha(Integer.parseInt(keyboardArrayList.get(position).getKeyOpacity()));

                switch (keyboardArrayList.get(position).getKeyStroke()) {
                    case "1":
                        npd1.setStroke(0, context.getResources().getColor(R.color.colorPrimary));
                        break;
                    case "2":
                        npd1.setStroke(2, android.graphics.Color.WHITE);
                        break;
                    case "3":
                        npd1.setStroke(2, android.graphics.Color.BLACK);
                        break;
                    case "4":
                        npd1.setStroke(4, android.graphics.Color.BLACK);
                        break;
                    case "5":
                        npd1.setStroke(3, android.graphics.Color.GRAY);
                        break;
                }

                mChild.setBackground(npd1);

                if (mChild instanceof TextView) {

                    ((TextView) mChild).setTextColor(android.graphics.Color.parseColor(keyboardArrayList.get(position).getFontColor()));
                    ((TextView) mChild).setTextSize(10);
                    if (keyboardArrayList.get(position).getFontName().length() != 0 && keyboardArrayList.get(position).getFontName() != null
                            && !keyboardArrayList.get(position).getFontName().isEmpty()) {
                        try {
                            Typeface font = Typeface.createFromAsset(context.getAssets(), keyboardArrayList.get(position).getFontName());
                            ((TextView) mChild).setTypeface(font);

                        } catch (Exception ignored) {
                        }
                    }
                }
            }
        }

        for (int i = 0; i < linTwoRowKeyboard.getChildCount(); i++) {
            final View mChild = linTwoRowKeyboard.getChildAt(i);

            if (mChild instanceof ImageView || mChild instanceof TextView) {
                // Recursively attempt another ViewGroup.
                npd1 = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{keyboardArrayList.get(position).getKeyBgColor(),
                                keyboardArrayList.get(position).getKeyBgColor()});
                npd1.setBounds(mChild.getLeft() + 5, mChild.getTop() + 5, mChild.getRight() - 5, mChild.getBottom() - 5);

                npd1.setCornerRadius(Float.parseFloat(keyboardArrayList.get(position).getKeyRadius()));
                npd1.setAlpha(Integer.parseInt(keyboardArrayList.get(position).getKeyOpacity()));

                switch (keyboardArrayList.get(position).getKeyStroke()) {
                    case "1":
                        npd1.setStroke(0, context.getResources().getColor(R.color.colorPrimary));
                        break;
                    case "2":
                        npd1.setStroke(2, android.graphics.Color.WHITE);
                        break;
                    case "3":
                        npd1.setStroke(2, android.graphics.Color.BLACK);
                        break;
                    case "4":
                        npd1.setStroke(4, android.graphics.Color.BLACK);
                        GlobalClass.printLog("click on four", "---------apply stroke----------");
                        break;
                    case "5":
                        npd1.setStroke(3, android.graphics.Color.GRAY);
                        GlobalClass.printLog("click on five", "---------apply stroke----------");
                        break;
                }

                mChild.setBackground(npd1);

                if (mChild instanceof TextView) {
                    ((TextView) mChild).setTextColor(android.graphics.Color.parseColor(keyboardArrayList.get(position).getFontColor()));
                    ((TextView) mChild).setTextSize(10);

                    if (keyboardArrayList.get(position).getFontName().length() != 0 && keyboardArrayList.get(position).getFontName() != null
                            && !keyboardArrayList.get(position).getFontName().isEmpty()) {
                        try {
                            Typeface font = Typeface.createFromAsset(context.getAssets(), keyboardArrayList.get(position).getFontName());
                            ((TextView) mChild).setTypeface(font);
                        } catch (Exception ignored) {
                        }
                    }
                }
            } else {
                // Set the font if it is a TextView.
                GlobalClass.printLog("call the setRadius", "------else-------" + i);

            }
        }

        for (int i = 0; i < linThreeRowKeyboard.getChildCount(); i++) {
            final View mChild = linThreeRowKeyboard.getChildAt(i);

            GlobalClass.printLog("call the setRadius", "----if---------" + i);
            // Set the font if it is a TextView.
            if (mChild instanceof ImageView || mChild instanceof TextView) {
                // Recursively attempt another ViewGroup.
                npd1 = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{keyboardArrayList.get(position).getKeyBgColor(),
                                keyboardArrayList.get(position).getKeyBgColor()});
                npd1.setBounds(mChild.getLeft() + 5, mChild.getTop() + 5, mChild.getRight() - 5, mChild.getBottom() - 5);

                npd1.setCornerRadius(Float.parseFloat(keyboardArrayList.get(position).getKeyRadius()));
                npd1.setAlpha(Integer.parseInt(keyboardArrayList.get(position).getKeyOpacity()));

                switch (keyboardArrayList.get(position).getKeyStroke()) {
                    case "1":
                        npd1.setStroke(0, context.getResources().getColor(R.color.colorPrimary));
                        break;
                    case "2":
                        npd1.setStroke(2, android.graphics.Color.WHITE);
                        break;
                    case "3":
                        npd1.setStroke(2, android.graphics.Color.BLACK);
                        break;
                    case "4":
                        npd1.setStroke(4, android.graphics.Color.BLACK);
                        GlobalClass.printLog("click on four", "---------apply stroke----------");
                        break;
                    case "5":
                        npd1.setStroke(3, android.graphics.Color.GRAY);
                        GlobalClass.printLog("click on five", "---------apply stroke----------");
                        break;
                }

                mChild.setBackground(npd1);

                if (mChild instanceof TextView) {
                    ((TextView) mChild).setTextColor(android.graphics.Color.parseColor(keyboardArrayList.get(position).getFontColor()));
                    ((TextView) mChild).setTextSize(10);

                    if (keyboardArrayList.get(position).getFontName().length() != 0 && keyboardArrayList.get(position).getFontName() != null
                            && !keyboardArrayList.get(position).getFontName().isEmpty()) {
                        try {

                            Typeface font = Typeface.createFromAsset(context.getAssets(), keyboardArrayList.get(position).getFontName());
                            ((TextView) mChild).setTypeface(font);
                        } catch (Exception ignored) {
                        }
                    }
                }
            } else GlobalClass.printLog("call the setRadius", "------else-------" + i);
        }

        for (int i = 0; i < linFourRowKeyboard.getChildCount(); i++) {
            final View mChild = linFourRowKeyboard.getChildAt(i);

            GlobalClass.printLog("call the setRadius", "----if---------" + i);
            if (mChild instanceof ImageView || mChild instanceof TextView) {
                // Recursively attempt another ViewGroup.
                npd1 = new GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM,
                        new int[]{keyboardArrayList.get(position).getKeyBgColor(),
                                keyboardArrayList.get(position).getKeyBgColor()});
                npd1.setBounds(mChild.getLeft() + 5, mChild.getTop() + 5, mChild.getRight() - 5, mChild.getBottom() - 5);

                npd1.setCornerRadius(Float.parseFloat(keyboardArrayList.get(position).getKeyRadius()));
                npd1.setAlpha(Integer.parseInt(keyboardArrayList.get(position).getKeyOpacity()));

                switch (keyboardArrayList.get(position).getKeyStroke()) {
                    case "1":
                        npd1.setStroke(0, context.getResources().getColor(R.color.colorPrimary));
                        break;
                    case "2":
                        npd1.setStroke(2, android.graphics.Color.WHITE);
                        break;
                    case "3":
                        npd1.setStroke(2, android.graphics.Color.BLACK);
                        break;
                    case "4":
                        npd1.setStroke(4, android.graphics.Color.BLACK);

                        GlobalClass.printLog("click on four", "---------apply stroke----------");
                        break;
                    case "5":
                        npd1.setStroke(3, android.graphics.Color.GRAY);
                        GlobalClass.printLog("click on five", "---------apply stroke----------");
                        break;
                }

                mChild.setBackground(npd1);

                if (mChild instanceof TextView) {
                    ((TextView) mChild).setTextColor(android.graphics.Color.parseColor(keyboardArrayList.get(position).getFontColor()));
                    ((TextView) mChild).setTextSize(10);

                    if (keyboardArrayList.get(position).getFontName().length() != 0 && keyboardArrayList.get(position).getFontName() != null
                            && !keyboardArrayList.get(position).getFontName().isEmpty()) {
                        try {
                            Typeface font = Typeface.createFromAsset(context.getAssets(), keyboardArrayList.get(position).getFontName());
                            ((TextView) mChild).setTypeface(font);
                        } catch (Exception ignored) {

                        }
                    }
                }
            } else GlobalClass.printLog("call the setRadius", "------else-------" + i);
        }

        container.addView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalClass.tempIsColor = keyboardArrayList.get(position).getIsColor();
                GlobalClass.tempKeyboardBgImage = keyboardArrayList.get(position).getKeyboardBgImage();
                GlobalClass.tempKeyboardColorCode = keyboardArrayList.get(position).getKeyboardColorCode();
                GlobalClass.tempKeyColor = keyboardArrayList.get(position).getKeyBgColor();
                GlobalClass.tempKeyRadius = keyboardArrayList.get(position).getKeyRadius();
                GlobalClass.tempKeyStroke = keyboardArrayList.get(position).getKeyStroke();
                GlobalClass.tempKeyOpacity = keyboardArrayList.get(position).getKeyOpacity();
                GlobalClass.tempFontColor = keyboardArrayList.get(position).getFontColor();
                GlobalClass.tempFontName = keyboardArrayList.get(position).getFontName();
                GlobalClass.tempSoundStatus = keyboardArrayList.get(position).getSoundStatus();
                GlobalClass.tempSoundName = keyboardArrayList.get(position).getSoundName();
                GlobalClass.selectwallpaper = keyboardArrayList.get(position).getSelectwallpaper();
                GlobalClass.selecttextwallpaper = keyboardArrayList.get(position).getSelecttextwallpaper();
                GlobalClass.selectcolor = keyboardArrayList.get(position).getSelectcolor();
                GlobalClass.selview = keyboardArrayList.get(position).getSelview();
                GlobalClass.keyboardBitmapBack = keyboardArrayList.get(position).getBitmapback();

                GlobalClass.setPreferencesString(context, GlobalClass.IS_COLOR, keyboardArrayList.get(position).getIsColor());
                GlobalClass.setPreferencesInt(context, GlobalClass.KEYBOARD_COLOR_CODE, keyboardArrayList.get(position).getKeyboardColorCode());
                GlobalClass.setPreferencesInt(context, GlobalClass.KEYBOARD_BG_IMAGE, keyboardArrayList.get(position).getKeyboardBgImage());
                GlobalClass.setPreferencesInt(context, GlobalClass.KEY_BG_COLOR, keyboardArrayList.get(position).getKeyBgColor());
                GlobalClass.setPreferencesString(context, GlobalClass.KEY_RADIUS, keyboardArrayList.get(position).getKeyRadius());
                GlobalClass.setPreferencesString(context, GlobalClass.KEY_STROKE, keyboardArrayList.get(position).getKeyStroke());
                GlobalClass.setPreferencesString(context, GlobalClass.KEY_OPACITY, keyboardArrayList.get(position).getKeyOpacity());
                GlobalClass.setPreferencesString(context, GlobalClass.FONT_COLOR, keyboardArrayList.get(position).getFontColor());
                GlobalClass.setPreferencesString(context, GlobalClass.FONT_NAME, keyboardArrayList.get(position).getFontName());
                GlobalClass.setPreferencesString(context, GlobalClass.SOUND_STATUS, keyboardArrayList.get(position).getSoundStatus());
                GlobalClass.setPreferencesInt(context, GlobalClass.SOUND_NAME, keyboardArrayList.get(position).getSoundName());
                GlobalClass.setPreferencesInt(context, GlobalClass.SELECTWALLPAPER, keyboardArrayList.get(position).getSelectwallpaper());
                GlobalClass.setPreferencesInt(context, GlobalClass.SELECTTEXTWALLPAPER, keyboardArrayList.get(position).getSelecttextwallpaper());
                GlobalClass.setPreferencesInt(context, GlobalClass.SELECTCOLOR, keyboardArrayList.get(position).getSelectcolor());
                GlobalClass.setPreferencesInt(context, GlobalClass.SELECTVIEW, keyboardArrayList.get(position).getSelview());
                GlobalClass.setPreferencesString(context, GlobalClass.KEYBOARDBITMAPBACK, keyboardArrayList.get(position).getBitmapback());

                Intent intent = new Intent(context, CreateKeyboardActivity.class);
                intent.putExtra("isEdit", true);
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });
        return view;
    }

}
