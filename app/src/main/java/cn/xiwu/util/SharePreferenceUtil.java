package cn.xiwu.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author 王维波
 * @time 2016/8/11  11:50
 * @desc ${TODD}
 */
public class SharePreferenceUtil {

    public static final String name ="configaaa";

    public static  void saveString(Context context, String title, String content, String filename){
      SharedPreferences sp =  context.getSharedPreferences(filename, Context.MODE_PRIVATE);
      SharedPreferences.Editor editor =  sp.edit();
      editor.putString(title,content);
        //commit ->  apply ->
        //editor.commit();
        editor.commit();
    }

    public static String getString(Context context, String title, String filename){
        SharedPreferences sp =  context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return sp.getString(title,"");
    }


    public static  void saveInt(Context context, String title, int index){
        SharedPreferences sp =  context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sp.edit();
        editor.putInt(title,index);
        editor.apply();
    }

    public static  int getInt(Context context, String title){
        SharedPreferences sp =  context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getInt(title,0);
    }


    public static  void saveLong(Context context, String title, long now){
        SharedPreferences sp =  context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sp.edit();
        editor.putLong(title,now);
        editor.apply();
    }

    public static  long getLong(Context context, String title){
        SharedPreferences sp =  context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sp.getLong(title,0);
    }
}
