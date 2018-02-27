package cn.xiwu.util;

import java.util.ArrayList;
import java.util.List;

import cn.xiwu.R;


/**
 * Created by zuzu on 2017/9/21.
 */

public class IrregularUtil
{

    //static修饰的变量初始化加载，比较占内存，所以不经常用的变量，不建议加此关键字。

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


}
