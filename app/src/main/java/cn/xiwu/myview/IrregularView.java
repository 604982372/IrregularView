package cn.xiwu.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.ArrayList;

import cn.xiwu.R;
import cn.xiwu.listener.IOnclickListener;
import cn.xiwu.util.IrregularUtil;
import cn.xiwu.util.L;
import cn.xiwu.util.MyUtil;
import cn.xiwu.util.SharePreferenceUtil;


/**
 * Created by x on 2017/7/24.
 */

public class IrregularView extends ImageView
{
    private int locationInBitmapX;   //点击区域坐标相当于样本中的x轴坐标
    private int locationInBitmapY;      //点击区域坐标相当于样本中的y轴坐标
    private Bitmap mainBitmap;

    private Context mContext;
    private int[] resIds;//不规则图片
    private int[] mCoordinatesCounts;//不规则图片每个区域坐标个数
    //获取图片实际的长宽
    private int mWidgetHeight;//获取图片实际的长宽
    private int mWidgetWidth;
    //样本坐标图片的高宽
    private int mSampleHeight;
    private int mSampleWidth;
    //bitmap加载到屏幕上的实际长宽
    int mainBitmapHeight;
    int mainBitmapWidth;
    //获取图片左边距和上边距
    int paddingLeft;
    int paddingTop;
    //获取样本坐标
    private ArrayList<String> mXY = new ArrayList<>();
    private int mBitWidth;//
    private int mBitHeight;
    private ArrayList<Bitmap> bitmapLists = new ArrayList<>();
    private IOnclickListener mListener;

    public IrregularView(Context context)
    {
        super(context);
    }

    public IrregularView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
        int src_resource = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "src", 0);

        mainBitmap = getBitmap(src_resource);
        //bitmap的实际长宽
        mainBitmapHeight = mainBitmap.getHeight();//布局中设置宽高后加载出来的图片宽高
        mainBitmapWidth = mainBitmap.getWidth();
        Log.v("3699main", mainBitmapHeight + "*" + mainBitmapWidth + "!");
        //获取图片左边距和上边距
        paddingLeft = this.getPaddingLeft();
        paddingTop = this.getPaddingTop();
        mSampleWidth = 30;//保存在Strings.xml里面的坐标样本图片宽度
        mSampleHeight = 71;//保存在Strings.xml里面的坐标样本图片高度
    }

    public void setImgResIds(int[] resIds)
    {
        this.resIds = resIds;
        //new BitmapLoad().start();
        checkIfLoaded();
    }

    private void checkIfLoaded()
    {
        try
        {
            String coordinates = mContext.getResources().getString(R.string.bodyXY);
            String coordinatesCount = mContext.getResources().getString(R.string.bodyXYCount);

            if (!MyUtil.isEmpty(coordinates) && !MyUtil.isEmpty(coordinatesCount))
            {
                String[] coordinatesSplit = coordinates.split("====");
                for (int j = 0; j < coordinatesSplit.length; j++)
                {
                    mXY.add(coordinatesSplit[j]);
                }
                Log.v("3699xxx    2", coordinatesSplit.length + "");
                String[] coorCount = coordinatesCount.split("====");
                mCoordinatesCounts = new int[coorCount.length];
                for (int k = 0; k < coorCount.length; k++)
                {
                    mCoordinatesCounts[k] = Integer.parseInt(coorCount[k]);
                }
            }
            else
            {
                coordinates = SharePreferenceUtil.getString(mContext, "coordinates", "irregularview");
                coordinatesCount = SharePreferenceUtil.getString(mContext, "coordinatescount", "irregularview");
                if (!MyUtil.isEmpty(coordinates) && !MyUtil.isEmpty(coordinatesCount))
                {
                    String[] coordinatesSplit = coordinates.split("====");
                    for (int j = 0; j < coordinatesSplit.length; j++)
                    {
                        mXY.add(coordinatesSplit[j]);
                    }
                    Log.v("3699xxx 120", coordinatesSplit.length + "");
                    String[] coorCount = coordinatesCount.split("====");
                    Log.v("3699xxx 121", coorCount.length + "");
                    mCoordinatesCounts = new int[coorCount.length];
                    for (int k = 0; k < coorCount.length; k++)
                    {
                        mCoordinatesCounts[k] = Integer.parseInt(coorCount[k]);
                    }

                }
                else
                {
                    new BitmapLoad().start();
                }
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    class BitmapLoad extends Thread
    {
        @Override
        public void run()
        {
            String coordinatesCount = "";
            String coordinatesStr = "";
            String coordinates = "";
            int count = 0;
            for (int i = 0; i < resIds.length; i++)
            {
                bitmapLists.add(getBitmap(resIds[i]));

                Bitmap bitmap = bitmapLists.get(i);
                int height = bitmap.getHeight();
                int width = bitmap.getWidth();
                coordinates = "";
                count = 0;
                Log.v("c", width + "--" + height);
                for (int j = 0; j < width; j++)
                {
                    for (int k = 0; k < height; k++)
                    {
                        int pixel = bitmap.getPixel(j, k);
                        int red = Color.red(pixel);
                        if (red >= 150)
                        {
                            int x = j * (width / mainBitmapWidth) + paddingLeft;
                            int y = k * (height / mainBitmapHeight) + paddingTop;
                            coordinates = coordinates + x + "%%" + y + "====";
                            count++;
                        }
                    }
                }
                coordinatesStr = coordinatesStr + coordinates;
                coordinatesCount = coordinatesCount + count + "====";
            }
            SharePreferenceUtil.saveString(mContext, "coordinates", coordinatesStr, "irregularview");
            SharePreferenceUtil.saveString(mContext, "coordinatescount", coordinatesCount, "irregularview");
            L.showLogCompletion(coordinatesStr, coordinatesStr.length(),"3699coorstr");
            L.showLogCompletion(coordinatesCount, coordinatesCount.length(),"3699coorcount");
        }
    }

    private Bitmap getBitmap(int resId)
    {
        Bitmap bitmap = null;
        try
        {
            InputStream ins = this.getResources().openRawResource(resId);
            BitmapFactory.Options options = new BitmapFactory.Options();
            //inJustDecodeBounds为true，不返回bitmap，只返回这个bitmap的尺寸
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), resId, options);
            //利用返回的原图片的宽高，我们就可以计算出缩放比inSampleSize，获取指定宽度为300像素，等长宽比的缩略图，减少图片的像素
            int toWidth = 20;
            int toHeight = options.outHeight * toWidth / options.outWidth;
            options.inSampleSize = options.outWidth / toWidth;
            options.outWidth = toWidth;
            options.outHeight = toHeight;
            //使用RGB_565减少图片大小
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            //释放内存，共享引用（21版本后失效）
            options.inPurgeable = true;
            options.inInputShareable = true;
            //inJustDecodeBounds为false，返回bitmap
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeStream(ins, null, options);
            mBitWidth = bitmap.getWidth();
            mBitHeight = bitmap.getHeight();
            Log.v("3699daxiao", mBitWidth + "*" + mBitHeight);
        }
        catch (OutOfMemoryError e)
        {
            e.printStackTrace();
        }
        catch (ArithmeticException e)
        {
            e.printStackTrace();
        }
        if (bitmap == null)
        {
            // 如果实例化失败 返回默认的Bitmap对象
            return mainBitmap;
        }
        return bitmap;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_UP:
                int x = (int) event.getX();
                int y = (int) event.getY();
                //获取控件高宽
                mWidgetHeight = IrregularView.this.getHeight();
                mWidgetWidth = IrregularView.this.getWidth();
               // float v = (x - paddingLeft) * mSampleWidth * (float) mainBitmapWidth / (mWidgetWidth * mBitWidth);//mWidgetWidth/x;
                float v = (x - paddingLeft) * (float)mSampleWidth / (mWidgetWidth);//mWidgetWidth/x;
                locationInBitmapX = (int) (v);
               // float v1 = (y - paddingTop) * mSampleHeight * (float) mainBitmapHeight / (mWidgetHeight * mBitHeight);//mWidgetHeight/y;
                float v1 = (y - paddingTop) * (float)mSampleHeight / (mWidgetHeight );//mWidgetHeight/y;
                locationInBitmapY = (int) (v1);
                Log.v("3699bodyxy", locationInBitmapX + "%%" + locationInBitmapY);
                int index = -1;
                for (int i = 0; i < mXY.size(); i++)
                {
                    if (mXY.get(i).equals(locationInBitmapX + "%%" + locationInBitmapY))
                    {
                        index = i;
                    }
                }
                if (index > 0)
                {
                    int selectedResId = IrregularUtil.getSelectedResId(resIds, mCoordinatesCounts, index);
                    if (selectedResId != -1)
                    {
                        this.setImageResource(selectedResId);
                        //String flag = IrregularUtil.getFlagByRedId(selectedResId);
                        /*Log.v("36969flag", flag);
                        if (mListener != null && !TextUtils.isEmpty(flag))
                        {
                            mListener.handleOnclick(flag);
                        }*/
                    }
                }

                break;
        }
        return true;
    }

    /**
     * 记得在ondestroy方法调用回收，否则会一直占有内存
     */
    public void recycle()
    {
        if (mainBitmap != null && !mainBitmap.isRecycled())
        {
            // 回收并且置为null
            mainBitmap.recycle();
            mainBitmap = null;
        }
        this.setImageResource(0);
        System.gc();
        System.runFinalization();
    }
}
