package cn.xiwu.util;

import java.util.ArrayList;
import java.util.List;

import cn.xiwu.R;


/**
 * Created by zuzu on 2017/9/21.
 */

public class IrregularUtil
{
    /*private static final int[] mResIds1 = new int[]{
            R.drawable.body_man_front_belly,
            R.drawable.body_man_front_chest,
            R.drawable.body_man_front_head,
            R.drawable.body_man_front_left_arm,
            R.drawable.body_man_front_left_crus,
            R.drawable.body_man_front_left_foot,
            R.drawable.body_man_front_left_knee,
            R.drawable.body_man_front_left_palm,
            R.drawable.body_man_front_left_shoulder,
            R.drawable.body_man_front_left_thigh,
            R.drawable.body_man_front_left_wrist,
            R.drawable.body_man_front_neck,
            R.drawable.body_man_front_right_arm,
            R.drawable.body_man_front_right_crus,
            R.drawable.body_man_front_right_foot,
            R.drawable.body_man_front_right_knee,
            R.drawable.body_man_front_right_palm,
            R.drawable.body_man_front_right_shoulder,
            R.drawable.body_man_front_right_thigh,
            R.drawable.body_man_front_right_wrist};//
    private static final int[] mCount1 =
            {100, 124, 63, 28, 46, 25, 11, 49, 22, 94, 37, 33, 31, 38, 28, 12, 37, 23, 86, 45};*/

    private String mSelectedPainCode;
    //static修饰的变量初始化加载，比较占内存，所以不经常用的变量，不建议加此关键字。
    private List<int[]> mResIdList = new ArrayList<>();
    private List<int[]> mCountList = new ArrayList<>();

    public static int getCoordinatesCount(int[] coordinatesCount, int num)
    {
        int result = 0;

        for (int i = 0; i <= num; i++)
        {
            result += coordinatesCount[i];
        }
        return result;
    }

    public static int getSelectedResId(int[] resIds, int[] coordinatesCount,int index)
    {
        //int[] coordinatesCount = maleBody && front ? mBodyManFrontCoordinatesCount : maleBody && !front ?
                //mBodyManBackCoordinatesCount : !maleBody && front ? mBodyWomanFrontCoordinatesCount : mBodyWomanBackCoordinatesCount;
        //int[] resIds = getResIds(maleBody, front);
        for (int i = 0; i < coordinatesCount.length; i++)
        {
            if ((i == 0 && index < getCoordinatesCount(coordinatesCount, 0)) || index == coordinatesCount.length - 1 || (index > getCoordinatesCount(coordinatesCount, i - 1) && index < getCoordinatesCount(coordinatesCount, i)))
            {
                return resIds[i];
            }
        }
        return -1;
    }

    public static int getResIdsByFlag( String codeStr)
    {
        switch (codeStr)
        {
            //处方详情显示新版本人体图
            case "MF04":
                return R.drawable.b;
            case "MF03":
                return R.drawable.c;
            case "MF01":
                return R.drawable.d;
            case "MF10":
                return R.drawable.e;
            case "MF19":
                return R.drawable.f;
            case "MF20":
                return R.drawable.g;
            case "MF18":
                return R.drawable.h;
            case "MF12":
                return R.drawable.i;
            case "MF09":
                return R.drawable.j;
            case "MF17":
                return R.drawable.k;
            case "MF11":
                return R.drawable.l;
            case "MF02":
                return R.drawable.m;
            case "MF06":
                return R.drawable.n;
            case "MF15":
                return R.drawable.o;
            case "MF16":
                return R.drawable.p;
            case "MF14":
                return R.drawable.q;
            case "MF08":
                return R.drawable.r;
            case "MF05":
                return R.drawable.s;
            case "MF13":
                return R.drawable.t;
            case "MF07":
                return R.drawable.u;
        }
        return -1;
    }

    public static boolean isBackBody(String codeStr)
    {
        String[] codeArray = codeStr.split("");
        return codeArray[2].equals("B") ? true : false;

    }
    public static String getPainCodeNameByCode(String codeStr)
    {
        switch (codeStr)
        {
            case "MF01":
            case "FF01":
                return "面部";
            case "MF02":
            case "FF02":
                return "颈部正面";
            case "MF03":
            case "FF03":
                return "前胸";
            case "MF04":
            case "FF04":
                return "腹部";
            case "MF05":
            case "FF05":
                return "左肩前侧";
            case "MF06":
            case "FF06":
                return "左上臂前侧";
            case "MF07":
            case "FF07":
                return "左肘和前臂正面";
            case "MF08":
            case "FF08":
                return "左腕和手掌";
            case "MF09":
            case "FF09":
                return "右肩前侧";
            case "MF10":
            case "FF10":
                return "右上臂前侧";
            case "MF11":
            case "FF11":
                return "右肘和前臂正面";
            case "MF12":
            case "FF12":
                return "右腕和手掌";
            case "MF13":
            case "FF13":
                return "左大腿前侧";
            case "MF14":
            case "FF14":
                return "左膝";
            case "MF15":
            case "FF15":
                return "左小腿前侧";
            case "MF16":
            case "FF16":
                return "左足踝正面";
            case "MF17":
            case "FF17":
                return "右大腿前侧";
            case "MF18":
            case "FF18":
                return "右膝";
            case "MF19":
            case "FF19":
                return "右小腿前侧";
            case "MF20":
            case "FF20":
                return "右足踝正面";

            case "MB01":
            case "FB01":
                return "头颈";
            case "MB02":
            case "FB02":
                return "胸腰";
            case "MB03":
            case "FB03":
                return "腰和骨盆";
            case "MB04":
            case "FB04":
                return "左肩胛";
            case "MB05":
            case "FB05":
                return "左上臂后侧";
            case "MB06":
            case "FB06":
                return "左肘和前臂背面";
            case "MB07":
            case "FB07":
                return "左腕和手背";
            case "MB08":
            case "FB08":
                return "右肩胛";
            case "MB09":
            case "FB09":
                return "右上臂后侧";
            case "MB10":
            case "FB10":
                return "右肘和前臂背面";
            case "MB11":
            case "FB11":
                return "右腕和手背";
            case "MB12":
            case "FB12":
                return "左大腿后侧";
            case "MB13":
            case "FB13":
                return "左腘窝";
            case "MB14":
            case "FB14":
                return "左小腿后侧";
            case "MB15":
            case "FB15":
                return "左踝和足跟";
            case "MB16":
            case "FB16":
                return "右大腿后侧";
            case "MB17":
            case "FB17":
                return "右腘窝";
            case "MB18":
            case "FB18":
                return "右小腿后侧";
            case "MB19":
            case "FB19":
                return "右踝和足跟";

        }
        return "";

    }

    public static String getFlagByRedId(int selectedResId)
    {
        switch (selectedResId)
        {
            case R.drawable.b:
                return "MF04";
            case R.drawable.c:
                return "MF03";
            case R.drawable.d:
                return "MF01";
            case R.drawable.e:
                return "MF10";
            case R.drawable.f:
                return "MF19";
            case R.drawable.g:
                return "MF20";
            case R.drawable.h:
                return "MF18";
            case R.drawable.i:
                return "MF12";
            case R.drawable.j:
                return "MF09";
            case R.drawable.k:
                return "MF17";
            case R.drawable.l:
                return "MF11";
            case R.drawable.m:
                return "MF02";
            case R.drawable.n:
                return "MF06";
            case R.drawable.o:
                return "MF15";
            case R.drawable.p:
                return "MF16";
            case R.drawable.q:
                return "MF14";
            case R.drawable.r:
                return "MF08";
            case R.drawable.s:
                return "MF05";
            case R.drawable.t:
                return "MF13";
            case R.drawable.u:
                return "MF07";

        }
        return "";
    }
}
