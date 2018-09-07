package com.ibl.apps.myphotokeyboard.utils;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.util.Log;

import com.ibl.apps.myphotokeyboard.database.DatabaseHelper;
import com.ibl.apps.myphotokeyboard.model.Color;
import com.ibl.apps.myphotokeyboard.model.DefaultColor;
import com.ibl.apps.myphotokeyboard.model.DefaultColorFree;
import com.ibl.apps.myphotokeyboard.model.Fonts;
import com.ibl.apps.myphotokeyboard.model.FontsPaid;
import com.ibl.apps.myphotokeyboard.model.Free;
import com.ibl.apps.myphotokeyboard.model.Sound;
import com.ibl.apps.myphotokeyboard.model.Textual;
import com.ibl.apps.myphotokeyboard.model.Wallpaper;

import java.util.ArrayList;


public class MyDownloadService extends Service {
    private Context context;
    private AsyncDownload asyncDownload;
    private Cursor cursor;
    private DatabaseHelper dbHelper;
    private ArrayList<DefaultColorFree> defaultColorFrees;
    private ArrayList<FontsPaid> fontsArray;
    private ArrayList<FontsPaid> soundArray;

    public MyDownloadService() {
        GlobalClass.printLog("start service", "----MyDownloadService---construcor-------");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        context = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        getKeyboardData();
    }

//    private void getKeyboardData() {
//        GlobalClass.printLog("start service", "----MyDownloadService---getKeyboardData-------");
//        ApiInterface apiService = APIClient.getClient();
//        Call<ResponseObject<ResponseData>> call = apiService.getKeyboardData();
//        call.enqueue(new Callback<ResponseObject<ResponseData>>() {
//            @Override
//            public void onResponse(Call<ResponseObject<ResponseData>> call, final Response<ResponseObject<ResponseData>> response) {
//                try {
//                    if (response.body() != null) {
//                        dbHelper = new DatabaseHelper(context);
//                        GlobalClass.printLog("----onResponse-----", "Size" + response.body().getResponse_data().getWallpaper().getPaid().getTextual().size());
//
//                        MergeDefaultColorArray(response.body().getResponse_data().getColor());
//                        MergeWallPaperColorArray(response.body().getResponse_data().getWallpaper());
//                        MergeWallPaperTextualArray(response.body().getResponse_data().getWallpaper());
//                        MergeFontArray(response.body().getResponse_data().getFonts());
//                        MergeSoundArray(response.body().getResponse_data().getSound());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    GlobalClass.printLog("-------- e-------", "" + e.toString());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseObject<ResponseData>> call, Throwable t) {
//                GlobalClass.printLog("-------- t-------", "" + t.toString());
//                call.cancel();
//            }
//        });
//    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void MergeSoundArray(Sound sound) {
        ArrayList<Free> soundArrayList = new ArrayList<Free>();
        if (sound.getFree().size() != 0 ||
                sound.getPaid().size() != 0) {

            Free freeData = new Free();
            freeData.setSound_url("none");
            freeData.setId("none");
            freeData.setTitle("none");
            freeData.setPaid("false");
            soundArrayList.add(freeData);


            if (sound.getFree().size() != 0) {
                for (int i = 0; i < sound.getFree().size(); i++) {
                    Free free = new Free();
                    free.setId(sound.getFree().get(i).getId());
                    free.setTitle(sound.getFree().get(i).getTitle());
                    free.setSound_url(sound.getFree().get(i).getSound_url());
                    free.setSelected(false);
                    free.setPaid("false");
                    soundArrayList.add(free);
                }
            }
            if (sound.getPaid().size() != 0) {
                for (int i = 0; i < sound.getPaid().size(); i++) {
                    Free free = new Free();
                    free.setId(sound.getPaid().get(i).getId());
                    free.setTitle(sound.getPaid().get(i).getTitle());
                    free.setSound_url(sound.getPaid().get(i).getSound_url());
                    free.setSelected(false);
                    free.setPaid("true");
                    soundArrayList.add(free);
                }
            }
        }
        insertSoundArray(soundArrayList);


//            setSoundEffectGridView(soundArrayList);

    }

    private void MergeFontArray(Fonts fonts) {
        fontsArray = new ArrayList<>();

        if (fonts.getFree().size() != 0 || fonts.getPaid().size() != 0) {


            if (fonts.getFree().size() != 0) {
                for (int i = 0; i < fonts.getFree().size(); i++) {
                    fonts.getFree().get(i).setPaid("false");
                    fontsArray.add(fonts.getFree().get(i));
                }
            }
            if (fonts.getPaid().size() != 0) {
                for (int i = 0; i < fonts.getPaid().size(); i++) {
                    fonts.getPaid().get(i).setPaid("true");
                    fontsArray.add(fonts.getPaid().get(i));
                }
            }


            insertFontArray(fontsArray);
        }
    }

    private void MergeDefaultColorArray(DefaultColor defaultColor) {
        defaultColorFrees = new ArrayList<>();

        if (defaultColor.getFree().size() != 0 || defaultColor.getPaid().size() != 0) {
            if (defaultColor.getFree().size() != 0) {
                for (int i = 0; i < defaultColor.getFree().size(); i++) {
                    defaultColor.getFree().get(i).setPaid("false");
                    defaultColorFrees.add(defaultColor.getFree().get(i));
                }
            }
            if (defaultColor.getPaid().size() != 0) {
                for (int i = 0; i < defaultColor.getPaid().size(); i++) {
                    defaultColor.getPaid().get(i).setPaid("true");
                    defaultColorFrees.add(defaultColor.getPaid().get(i));
                }
            }
            insertColorArray(defaultColorFrees);
        }
    }

    private void MergeWallPaperColorArray(Wallpaper wallpaper) {

        ArrayList<Color> colorWallpaperArrayList = new ArrayList<Color>();
        if (wallpaper.getFree().getColor().size() != 0 ||
                wallpaper.getPaid().getColor().size() != 0) {

            if (wallpaper.getFree().getColor().size() != 0) {
                for (int i = 0; i < wallpaper.getFree().getColor().size(); i++) {
                    Color color = new Color();
                    color.setId(wallpaper.getFree().getColor().get(i).getId());
                    color.setTitle(wallpaper.getFree().getColor().get(i).getTitle());
                    color.setImage(wallpaper.getFree().getColor().get(i).getImage());
                    color.setThumb_image(wallpaper.getFree().getColor().get(i).getThumb_image());
                    color.setPaid("false");
                    colorWallpaperArrayList.add(color);

                }
            }

            if (wallpaper.getPaid().getColor().size() != 0) {
                for (int i = 0; i < wallpaper.getPaid().getColor().size(); i++) {
                    Color color = new Color();
                    color.setId(wallpaper.getPaid().getColor().get(i).getId());
                    color.setTitle(wallpaper.getPaid().getColor().get(i).getTitle());
                    color.setImage(wallpaper.getPaid().getColor().get(i).getImage());
                    color.setThumb_image(wallpaper.getPaid().getColor().get(i).getThumb_image());
                    color.setPaid("true");
                    colorWallpaperArrayList.add(color);
                }
            }

            insertColorWallPaperArray(colorWallpaperArrayList);

        }
    }

    private void MergeWallPaperTextualArray(Wallpaper wallpaper) {
        ArrayList<Textual> textualWallpaperArrayList = new ArrayList<Textual>();
        if (wallpaper.getFree().getTextual().size() != 0 ||
                wallpaper.getPaid().getTextual().size() != 0) {

            if (wallpaper.getFree().getTextual().size() != 0) {
                for (int i = 0; i < wallpaper.getFree().getTextual().size(); i++) {
                    Textual textual = new Textual();
                    textual.setId(wallpaper.getFree().getTextual().get(i).getId());
                    textual.setTitle(wallpaper.getFree().getTextual().get(i).getTitle());
                    textual.setImage(wallpaper.getFree().getTextual().get(i).getImage());
                    textual.setThumb_image(wallpaper.getFree().getTextual().get(i).getThumb_image());
                    textual.setPaid("false");
                    textualWallpaperArrayList.add(textual);
                }
            }

            if (wallpaper.getPaid().getTextual().size() != 0) {
                for (int i = 0; i < wallpaper.getPaid().getTextual().size(); i++) {
                    Textual textual = new Textual();
                    textual.setId(wallpaper.getPaid().getTextual().get(i).getId());
                    textual.setTitle(wallpaper.getPaid().getTextual().get(i).getTitle());
                    textual.setImage(wallpaper.getPaid().getTextual().get(i).getImage());
                    textual.setThumb_image(wallpaper.getPaid().getTextual().get(i).getThumb_image());
                    textual.setPaid("true");
                    textualWallpaperArrayList.add(textual);
                }
            }
            insertTextualWallPaperArray(textualWallpaperArrayList);
//            setTextualGridView(textualWallpaperArrayList);
        }
    }

    private void insertSoundArray(ArrayList<Free> soundArray) {
        final String selection = DatabaseHelper.KEY_SOUND_ID + " LIKE ?";

        String[] selectionArgs;
        for (int i = 0; i < soundArray.size(); i++) {
            selectionArgs = new String[]{String.valueOf(soundArray.get(i).getId())};
            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.KEY_SOUND_ID, soundArray.get(i).getId());
            values.put(DatabaseHelper.KEY_SOUND_TITLE, soundArray.get(i).getTitle());
            values.put(DatabaseHelper.KEY_SOUND_URL, soundArray.get(i).getSound_url());
            values.put(DatabaseHelper.KEY_SOUND_IS_PAID, soundArray.get(i).isPaid());

            cursor = dbHelper.getTableDataById(DatabaseHelper.TABLE_SOUND, DatabaseHelper.KEY_SOUND_ID, soundArray.get(i).getId() + "");

            if (cursor != null && cursor.getCount() > 0) {
                dbHelper.updateRowData(DatabaseHelper.TABLE_SOUND, values, selection, selectionArgs);
            } else {
                dbHelper.insertData(DatabaseHelper.TABLE_SOUND, values);
            }
        }
    }

    private void insertFontArray(ArrayList<FontsPaid> fontsArray) {
        final String selection = DatabaseHelper.KEY_FONT_ID + " LIKE ?";

        String[] selectionArgs;
        for (int i = 0; i < fontsArray.size(); i++) {
            selectionArgs = new String[]{String.valueOf(fontsArray.get(i).getId())};
            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.KEY_FONT_ID, fontsArray.get(i).getId());
            values.put(DatabaseHelper.KEY_FONT_TITLE, fontsArray.get(i).getTitle());
            values.put(DatabaseHelper.KEY_FONT_URL, fontsArray.get(i).getFont_url());
            values.put(DatabaseHelper.KEY_FONT_IS_PAID, fontsArray.get(i).isPaid());

            cursor = dbHelper.getTableDataById(DatabaseHelper.TABLE_FONT, DatabaseHelper.KEY_FONT_ID, fontsArray.get(i).getId() + "");

            if (cursor != null && cursor.getCount() > 0) {
                dbHelper.updateRowData(DatabaseHelper.TABLE_FONT, values, selection, selectionArgs);
            } else {
                dbHelper.insertData(DatabaseHelper.TABLE_FONT, values);
            }

        }

    }

    private void insertColorArray(ArrayList<DefaultColorFree> colorArrayList) {
        final String selection = DatabaseHelper.KEY_COLOR_ID + " LIKE ?";

        String[] selectionArgs;
        for (int i = 0; i < colorArrayList.size(); i++) {
            selectionArgs = new String[]{String.valueOf(colorArrayList.get(i).getId())};
            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.KEY_COLOR_ID, colorArrayList.get(i).getId());
            values.put(DatabaseHelper.KEY_COLOR_CODE, colorArrayList.get(i).getColor_code());
            values.put(DatabaseHelper.KEY_COLOR_IS_PAID, colorArrayList.get(i).isPaid());

            cursor = dbHelper.getTableDataById(DatabaseHelper.TABLE_COLOR, DatabaseHelper.KEY_COLOR_ID, colorArrayList.get(i).getId() + "");

            if (cursor != null && cursor.getCount() > 0) {
                dbHelper.updateRowData(DatabaseHelper.TABLE_COLOR, values, selection, selectionArgs);
            } else {
                dbHelper.insertData(DatabaseHelper.TABLE_COLOR, values);
            }

        }

    }

    private void insertColorWallPaperArray(ArrayList<Color> colorWallpaperArrayList) {
        final String selection = DatabaseHelper.KEY_COLOR_WALLPAPER_ID + " LIKE ?";

        String[] selectionArgs;
        for (int i = 0; i < colorWallpaperArrayList.size(); i++) {
            selectionArgs = new String[]{String.valueOf(colorWallpaperArrayList.get(i).getId())};
            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.KEY_COLOR_WALLPAPER_ID, colorWallpaperArrayList.get(i).getId());
            values.put(DatabaseHelper.KEY_COLOR_WALLPAPER_TITLE, colorWallpaperArrayList.get(i).getTitle());
            values.put(DatabaseHelper.KEY_COLOR_WALLPAPER_IMAGE, colorWallpaperArrayList.get(i).getImage());
            values.put(DatabaseHelper.KEY_COLOR_WALLPAPER_THUMB_IMAGE, colorWallpaperArrayList.get(i).getThumb_image());
            values.put(DatabaseHelper.KEY_COLOR_WALLPAPER_IS_PAID, colorWallpaperArrayList.get(i).isPaid());

            cursor = dbHelper.getTableDataById(DatabaseHelper.TABLE_COLOR_WALLPAPER, DatabaseHelper.KEY_COLOR_WALLPAPER_ID, colorWallpaperArrayList.get(i).getId() + "");

            if (cursor != null && cursor.getCount() > 0) {
                //  Log.e("--------------", "corsor:-----------if--");
                dbHelper.updateRowData(DatabaseHelper.TABLE_COLOR_WALLPAPER, values, selection, selectionArgs);
            } else {
                //Log.e("--------------", "corsor:-----------else--");
                dbHelper.insertData(DatabaseHelper.TABLE_COLOR_WALLPAPER, values);
            }

        }

    }

    private void insertTextualWallPaperArray(ArrayList<Textual> colorWallpaperArrayList) {
        final String selection = DatabaseHelper.KEY_TEXTUAL_WALLPAPER_ID + " LIKE ?";

        String[] selectionArgs;
        for (int i = 0; i < colorWallpaperArrayList.size(); i++) {
            selectionArgs = new String[]{String.valueOf(colorWallpaperArrayList.get(i).getId())};
            ContentValues values = new ContentValues();

            values.put(DatabaseHelper.KEY_TEXTUAL_WALLPAPER_ID, colorWallpaperArrayList.get(i).getId());
            values.put(DatabaseHelper.KEY_TEXTUAL_WALLPAPER_TITLE, colorWallpaperArrayList.get(i).getTitle());
            values.put(DatabaseHelper.KEY_TEXTUAL_WALLPAPER_IMAGE, colorWallpaperArrayList.get(i).getImage());
            values.put(DatabaseHelper.KEY_TEXTUAL_WALLPAPER_THUMB_IMAGE, colorWallpaperArrayList.get(i).getThumb_image());
            values.put(DatabaseHelper.KEY_TEXTUAL_WALLPAPER_IS_PAID, colorWallpaperArrayList.get(i).isPaid());

            cursor = dbHelper.getTableDataById(DatabaseHelper.TABLE_TEXTUAL_WALLPAPER, DatabaseHelper.KEY_TEXTUAL_WALLPAPER_ID, colorWallpaperArrayList.get(i).getId() + "");

            if (cursor != null && cursor.getCount() > 0) {
                Log.e("--------------", "corsor:-----------if--");
                dbHelper.updateRowData(DatabaseHelper.TABLE_TEXTUAL_WALLPAPER, values, selection, selectionArgs);
            } else {
                Log.e("--------------", "corsor:-----------else--");
                dbHelper.insertData(DatabaseHelper.TABLE_TEXTUAL_WALLPAPER, values);
            }

        }

    }

}
