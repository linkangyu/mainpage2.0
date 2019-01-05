package com.example.smilewithu.mainpage;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.PriorityQueue;

/**

 */
public class frag1 extends Fragment {
    private List<Record> recordList ;
   private MyTextView showdate;
   private MyTextView showIn_day;
   private MyTextView showOut_day;
   private ImageView choosedate;
   private OnButtonClick onButtonClick;
   private MyGraphActivity mygraph;
   private String nowdate=new String();
    private float out_byday=0;
    private float in_byday=0;
    //private OnButtonClickdelete onButtonClickdelete;
    SideslipListView listView;

    private String stateChar[] = {"饮食","交通","购物","恋爱","旅游","书籍","医疗","零食","饮品","衣服"
            ,"日用品","娱乐","数码","美容","水果","快递","烟酒","社交","通讯","住房","彩票","发红包","学费"
            ,"礼物","运动","宠物" ,"其它支出","工资","兼职","理财","红包","其它收入"};   //0-26表示支出，27-32表示收入
    private int ImageId[]={R.drawable.yinshi,R.drawable.jiaotongditiedongchegaotiexianxing,R.drawable.gouwu,
            R.drawable.lianai,R.drawable.lvyou,R.drawable.shuben,R.drawable.yiliao,R.drawable.lingshi,
            R.drawable.yinliao,R.drawable.yifu,R.drawable.riyongpin,R.drawable.yule,R.drawable.shuma,
            R.drawable.meirong,R.drawable.shuiguo,R.drawable.kuaidi,R.drawable.yanjiu,R.drawable.shejiao,
            R.drawable.tongxun,R.drawable.zhufang,R.drawable.caipiao,R.drawable.fahongbao,R.drawable.xuefei,
            R.drawable.liwu,R.drawable.yundong,R.drawable.pet,R.drawable.zhichuqita,R.drawable.salary,
            R.drawable.jianzhi,R.drawable.licai,R.drawable.hongbao,R.drawable.shouru};
    SQLiteDAOImpl sqlieDao;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);

        sqlieDao=new SQLiteDAOImpl(getActivity());
       //Record myRecord=new Record(initDate(),"红包","+200","来自老妈",R.drawable.hongbao);
        //sqlieDao.delete(myRecord);
     // sqlieDao.insert(myRecord);
        recordList = new ArrayList<Record>();
        showdate=(MyTextView)view.findViewById(R.id.showthedate);
        showIn_day=(MyTextView)view.findViewById(R.id.in_day);
        showOut_day=(MyTextView)view.findViewById(R.id.out_day);
        listView = (SideslipListView) view.findViewById(R.id.list_view);
        choosedate=(ImageView)view.findViewById(R.id.rili);
        initRecord(initDate());
/*
delete.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        if(onButtonClickdelete!=null){
            onButtonClickdelete.onClickdelete(delete);
        }
    }
});
*/
      choosedate.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {
              if(onButtonClick!=null){
                  onButtonClick.onClick(choosedate);
              }
          }
      });
             return view;

    }


    public class RecordAdapter extends ArrayAdapter {
        private final int resourceId;
        public RecordAdapter(Context context, int textViewResourceId, List<Record> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Record record = (Record) getItem(position); // 获取当前项的Fruit实例
            View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
            //  View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            // 使用Inflater对象来将布局文件解析成一个View
            TextView dele;

            dele =(TextView)view.findViewById(R.id.txtv_delete);

            ImageView recodeImage = (ImageView) view.findViewById(R.id.iv_image);//获取该布局内的图片视图
            TextView kind = (TextView) view.findViewById(R.id.tv_kind);//获取该布局内的文本视图
            TextView money=(TextView) view.findViewById(R.id.tv_money);
            TextView message=(TextView) view.findViewById(R.id.tv_message);
            recodeImage.setImageResource(record.getImageId());//为图片视图设置图片资源
            kind.setText(record.getKind());//为文本视图设置文本内容
            money.setText(record.getMoney());
            message.setText(record.getMessage());

          dele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getContext(), record.getKind() + "被删除了",
                            Toast.LENGTH_SHORT).show();
                   sqlieDao.delete(record);
                   initRecord(initDate());

                }
            });

            return view;
        }
    }

    public String initDate()
    {
        Calendar calendar = Calendar.getInstance();
//获取系统的日期
//年
        int year = calendar.get(Calendar.YEAR);
//月
        int month = calendar.get(Calendar.MONTH)+1;
//日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return String.format("%d-%d-%d",year,month,day);
    }
    public void initRecord(String date)      //根据日期查找收支纪录显示在主界面
    {
        recordList.clear();
        nowdate=date;
        getOutOrIn(nowdate);
        showIn_day.setText("收入:"+String.valueOf(in_byday));
        showOut_day.setText("支出:"+String.valueOf(out_byday));
        in_byday=0;
        out_byday=0;
        showdate.setText(date);
        Record record=new Record();
        recordList=sqlieDao.findAll(date);
        for(int i=0;i<recordList.size();i++)
        {
            record=recordList.get(i);
            record.setID(ImageId[getKind(record.getKind())]);    //把对应的消费类型的图片添加到列表
        }
        if(recordList.isEmpty()) {
           showdate.append("没有记录哦");
        }
        //Toast.makeText(getActivity(),String.valueOf(recordList.size()),Toast.LENGTH_SHORT).show();
        RecordAdapter adapter=new RecordAdapter(getActivity(), R.layout.list_item, recordList);
        listView.setAdapter(adapter);


        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                            @Override
                                            public void onItemClick(AdapterView parent, View view, int position, long id) {

                                                if (listView.isAllowItemClick()) {
                                                    Record f=recordList.get(position);
                                                    if(f!=null){

                                                        getActivity().setContentView(R.layout.seediary);
                                                        TextView tit=(TextView)findViewById(R.id.seetitle_text_tv);
                                                        TextView ti=(TextView)findViewById(R.id.seetime);
                                                        TextView con=(TextView)findViewById(R.id.seecontext);
                                                        tit.setText(f.title);
                                                        ti.setText(f.time);
                                                        con.setText(f.context);
                                                    }
                                                }

                                            }
                                        }

        );
*/
    }
    public int getKind(String kind)
    {
        for(int i=0;i<stateChar.length;i++)
        {

            if(stateChar[i].equals(kind))
                return i;
        }
        return -1;
    }
    public OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }
    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }
    public interface OnButtonClick{
        public void onClick(View view);
    }
    public void getOutOrIn(String date)
    {
        Record record;
        List<Record> recordList = new ArrayList<Record>();
        recordList=sqlieDao.findAll(date);  //找到对应日期的所有收支纪录
        for(int i=0;i<recordList.size();i++)
        {
            record=recordList.get(i);
            float f=Float.parseFloat(record.getMoney());
            if(f<0)
                out_byday-=f;
            else if(f>0)
                in_byday+=f;
        }
    }
/*
    public OnButtonClickdelete getOnButtonClickdelete() {
        return onButtonClickdelete;
    }
    public void setOnButtonClickdelte(OnButtonClickdelete onButtonClick) {
        this.onButtonClickdelete = onButtonClick;
    }
    public interface OnButtonClickdelete{
        public void onClickdelete(View view);
    }
    */
}

