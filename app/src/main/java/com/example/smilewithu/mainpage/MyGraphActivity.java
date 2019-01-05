package com.example.smilewithu.mainpage;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class MyGraphActivity extends AppCompatActivity {

    SQLiteDAOImpl sqlDao;
    private int month_days[]={31,28,31,30,31,30,31,31,30,31,30,31}; //十二个月的天数
    Calendar calendar = Calendar.getInstance();//获取系统的日期
    private int year = calendar.get(Calendar.YEAR);
    private int month = calendar.get(Calendar.MONTH)+1;
    private int day = calendar.get(Calendar.DAY_OF_MONTH);
    boolean choose_out = true;
    private Button btn_return;
    private RadioGroup rdg;
    private LineChartView lineChart;        //线状图
    private String stateChar[] = {"饮食","交通","购物","恋爱","旅游","书籍","医疗","零食","饮品","衣服"
            ,"日用品","娱乐","数码","美容","水果","快递","烟酒","社交","通讯","住房","彩票","发红包","学费"
            ,"礼物","运动","宠物" ,"其它支出","工资","兼职","理财","红包","其它收入"};   //0-22表示支出，23-27表示收入
    private String[] date = {"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1",};    //最近十五天的日期
    private float[] score_out ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}; //获得最近十五天的支出
    private float[] score_in = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};//获得最近十五天的收入
    private float[] score_kind={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    private List<PointValue> mPointValues = new ArrayList<PointValue>();
    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();
    int flag_out[]=new int[32];
    int flag_in[]=new int[32];
    //饼状图
    private PieChartView pie_chart;
    //数据
    private PieChartData pieChardata;
    List<SliceValue> values = new ArrayList<SliceValue>();
    private int[] colorData_in = {
            Color.parseColor("#ec063d"),
            Color.parseColor("#f1c704"),
            Color.parseColor("#c9c9c9"),
            Color.parseColor("#2bc208"),
            Color.parseColor("#333333")};    //收入的颜色
    private int[] colorData_out = {
            Color.parseColor("#ffffcc"),
            Color.parseColor("#ffff00"),
            Color.parseColor("#ffcc00"),
            Color.parseColor("#ffccff"),
            Color.parseColor("#ff33ff"),
            Color.parseColor("#cc33ff"),
            Color.parseColor("#c9c9c9"),
            Color.parseColor("#919191"),
            Color.parseColor("#383838"),

            Color.parseColor("#98FB98"),
            Color.parseColor("#32CD32"),
            Color.parseColor("#99CC00"),
            Color.parseColor("#87CEFF"),
            Color.parseColor("#6495ED"),
            Color.parseColor("#0000FF"),
            Color.parseColor("#FF6666"),
            Color.parseColor("#FF0000"),
            Color.parseColor("#CC3300"),
            Color.parseColor("#8E8E38"),
            Color.parseColor("#8B6508"),
            Color.parseColor("#8B3E2F"),
            Color.parseColor("#00ff00"),
            Color.parseColor("#009933"),
            Color.parseColor("#006666"),
            Color.parseColor("#FF6699"),
            Color.parseColor("#FF3399"),
            Color.parseColor("#CC3366")

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_graph);
        sqlDao=new SQLiteDAOImpl(this);
        rdg=(RadioGroup)findViewById(R.id.rdg);
        rdg.check(R.id.rdg_out);
        initMonth();
        initMoney();
        intiAllGraph();

    }

    public void RdgOnclick(View v)
    {
        switch(v.getId())
        {
            case R.id.rdg_in:
               //Toast.makeText(MyGraphActivity.this, "点击了收入按钮", Toast.LENGTH_SHORT).show();
                if(choose_out)
                {
                    setContentView(R.layout.activity_my_graph_in);//跳转到收入界面
                    rdg=(RadioGroup)findViewById(R.id.rdg);
                    rdg.check(R.id.rdg_in);
                    choose_out=false;
                    intiAllGraph();
                }
                break;
            case R.id.rdg_out:
                //Toast.makeText(MyGraphActivity.this, "点击了支出按钮", Toast.LENGTH_SHORT).show();
                if(!choose_out)
                {
                    setContentView(R.layout.activity_my_graph);//跳转到支出界面
                    rdg=(RadioGroup)findViewById(R.id.rdg);
                    rdg.check(R.id.rdg_out);
                    choose_out=true;
                    intiAllGraph();
                }
                break;
            case R.id.btn_return:
                finish();
                break;
            default:
        }
    }

    private void initLineChart() {
        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //折线的颜色
        List<Line> lines = new ArrayList<Line>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.SQUARE）
        line.setCubic(false);//曲线是否平滑
//	    line.setStrokeWidth(3);//线条的粗细，默认是3
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
//		line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用直线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(true);  //X轴下面坐标轴字体是斜的显示还是直的，true是斜的显示
//	    axisX.setTextColor(Color.WHITE);  //设置字体颜色
        axisX.setTextColor(Color.parseColor("#303F9F"));
        if (choose_out)
            axisX.setName("支出");  //表格名称
        else
            axisX.setName("收入");  //表格名称
        axisX.setTextSize(11);//设置字体大小
        axisX.setMaxLabelChars(7); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
//	    data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线


        Axis axisY = new Axis();  //Y轴
        axisY.setName("金额");//y轴标注
        axisY.setTextSize(11);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边
        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);  //缩放类型，水平
        lineChart.setMaxZoom((float) 3);//缩放比例
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);

        Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.left = 0;
        v.right = 7;
        lineChart.setCurrentViewport(v);
    }


    private void getAxisXLables() {
        mAxisXValues.clear();
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }


    private void getAxisPoints() {
        mPointValues.clear();
        if (choose_out) {
            for (int i = 0; i < score_out.length; i++) {
                mPointValues.add(new PointValue(i, score_out[i]));
            }
        } else {
            for (int i = 0; i < score_in.length; i++) {
                mPointValues.add(new PointValue(i, score_in[i]));
            }
        }
    }

//饼状图初始化


    private void setPieChartData() {
        values.clear();
        int count_out=0;
        int count_in=0;
        SliceValue sliceValue;
        if (choose_out) {
            for (int i = 0; i < 27; i++) {
                if(score_kind[i]>0)
                {sliceValue = new SliceValue(score_kind[i], colorData_out[i]);
                flag_out[count_out]=i;
                count_out++;
                values.add(sliceValue);}
            }
        } else {

            for (int i = 27; i < 32; i++) {
                if(score_kind[i]>0){
                sliceValue = new SliceValue(score_kind[i], colorData_in[i - 27]);
                    flag_in[count_in]=i;
                    count_in++;
                values.add(sliceValue);}
            }
        }
    }



    private void initPieChart() {
        pieChardata = new PieChartData();
        pieChardata.setHasLabels(true);//显示表情
        pieChardata.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        pieChardata.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        pieChardata.setHasCenterCircle(true);//是否是环形显示
        pieChardata.setValues(values);//填充数据
        pieChardata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
        pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
        pie_chart.setPieChartData(pieChardata);
        pie_chart.setValueSelectionEnabled(true);//选择饼图某一块变大
        pie_chart.setAlpha(0.9f);//设置透明度
        pie_chart.setCircleFillRatio(1f);//设置饼图大小

    }


    private PieChartOnValueSelectListener selectListener = new PieChartOnValueSelectListener() {
        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onValueSelected(int arg0, SliceValue value) {
            //选择对应图形后，在中间部分显示相应信息
            if (choose_out) {
                pieChardata.setCenterText1(stateChar[flag_out[arg0]]);   //0-26表示支出
                pieChardata.setCenterText1Color(colorData_out[flag_out[arg0]]);
            } else {
                pieChardata.setCenterText1(stateChar[flag_in[arg0]]);
                pieChardata.setCenterText1Color(colorData_in[flag_in[arg0]-27]);
            }
            pieChardata.setCenterText1FontSize(50);
            pieChardata.setCenterText2(value.getValue() + "（" + calPercent(arg0) + ")");
            pieChardata.setCenterText2FontSize(50);
            if (choose_out)
                pieChardata.setCenterText2Color(colorData_out[flag_out[arg0]]);
            else
                pieChardata.setCenterText2Color(colorData_in[flag_in[arg0]-27]);
            /*
            if (choose_out)
                Toast.makeText(MyGraphActivity.this, stateChar[arg0] + ":" + value.getValue(), Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MyGraphActivity.this, stateChar[arg0+27] + ":" + value.getValue(), Toast.LENGTH_SHORT).show();
      */
        }
    };

    private String calPercent(int i) {
        String result = "";
        int sum = 0;
        if (choose_out) {
            for (int i1 = 0; i1 < score_out.length; i1++) {
                sum += score_out[i1];
            }

            result = String.format("%.2f", (float) score_kind[flag_out[i]] * 100 / sum) + "%";
        } else {
            for (int i1 = 0; i1 < score_in.length; i1++) {
                sum += score_in[i1];
            }
            result = String.format("%.2f", (float) score_kind[flag_in[i]]* 100 / sum) + "%";
        }
        return result;
    }
    public void intiAllGraph()
    {

        lineChart=(LineChartView)findViewById(R.id.line_chart) ;
        //饼状图
        pie_chart = (PieChartView) findViewById(R.id.pie_chart);
        pie_chart.setOnValueTouchListener(selectListener);//设置点击事件监听
        getAxisXLables();//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();//初始化
        setPieChartData();  //设置数据
        initPieChart();   //初始化
    }
    public String getToday()
    {
        return String.format("%d-%d-%d",year,month,day);
    }
    public void initMonth() {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))     //闰年
        {
            month_days[1] = 29;
        }
    }

    public void getNearlyDay()      //获取最近十五天的日期
    {

        int day1=day,month1=month,year1=year;
        for(int i=0;i<15;i++)
        {
            if(day1>0)
            {
                date[14-i]=String.format("%d-%d-%d",year1,month1,day1);
            }
            else
            {
                if(month1==1)
                {
                    day1=31;
                    month1=12;
                    year1=year1-1;
                    date[i]=String.format("%d-%d-%d",year-1,12,day1);

                }
                day1=month_days[month-2];
                month1=month1-1;
                date[i]=String.format("%d-%d-%d",year1,month1,day1);
            }
            day1--;
        }
    }
    public int getKind(String kind)   //传进来收支类型,找到对应的图片ID等
    {
        for(int i=0;i<stateChar.length;i++)
        {

            if(stateChar[i].equals(kind))
                return i;
        }
        return -1;
    }

    public void initMoney()       //获取最近十五天的收支i，i=1时返回收入，i=-1时返回支出
    {
        getNearlyDay();
        List<Record> recordList = new ArrayList<Record>();
        Record record;
        for(int j=0;j<15;j++)
        {
            recordList=sqlDao.findAll(date[j]);  //找到对应日期的所有收支纪录
            for(int k=0;k<recordList.size();k++)
            {
                record=recordList.get(k);
                float f=Float.parseFloat(record.getMoney());
                if (f<0)           //支出
                {

                    score_out[j]-=f;

                    score_kind[getKind(record.getKind())]-=f;  //对应的类型收入
                }
                else   //收入
                {
                    score_in[j]+=f;
                    //Toast.makeText(MyGraphActivity.this, record.getKind()+" "+record.getMoney()+" "+record.getDate(), Toast.LENGTH_SHORT).show();
                    score_kind[getKind(record.getKind())]+=f;

                }

            }
        }
    }

}
