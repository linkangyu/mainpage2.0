package com.example.smilewithu.mainpage;

import android.graphics.Typeface;
import android.content.Context;
public class FontCustom {



    // fongUrl是自定义字体分类的名称

    private static String fongUrl = "qnyy.ttf";

    //Typeface是字体，这里我们创建一个对象

    private static Typeface tf;



    /**

     * 设置字体

     */
public  Typeface getTf()
{
    return tf;
}
    public static Typeface setFont(Context context)

    {

        if (tf == null)

        {

            //给它设置你传入的自定义字体文件，再返回回来

            tf = Typeface.createFromAsset(context.getAssets(),fongUrl);

        }

        return tf;

    }

}
