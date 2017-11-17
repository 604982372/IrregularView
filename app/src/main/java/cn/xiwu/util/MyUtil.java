package cn.xiwu.util;

/**
 * Created by xiwu on 2017/10/24.
 */

public class MyUtil
{
    /**
     * @param str
     * @return 过滤换行,空格,前后空格再判空
     */
    public static boolean isEmpty(String str)
    {

        return removeSpace(str).trim().isEmpty();
    }

    /**
     * @param str
     * @return 过滤空格和换行
     */
    public static String removeSpace(String str)
    {
        String tempStr = str.replace('\n', ' ');
        return tempStr.replace(" ", "");
    }

}
