package cn.xiwu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.xiwu.myview.IrregularView;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IrregularView irView  = (IrregularView) findViewById(R.id.il);
        irView.setImgResIds(new int[]{
                        R.drawable.b,
                        R.drawable.c,
                        R.drawable.d,
                        R.drawable.e,
                        R.drawable.f,
                        R.drawable.g,
                        R.drawable.h,
                        R.drawable.i,
                        R.drawable.j,
                        R.drawable.k,
                        R.drawable.l,
                        R.drawable.m,
                        R.drawable.n,
                        R.drawable.o,
                        R.drawable.p,
                        R.drawable.q,
                        R.drawable.r,
                        R.drawable.s,
                        R.drawable.t,
                        R.drawable.u});
    }
}
