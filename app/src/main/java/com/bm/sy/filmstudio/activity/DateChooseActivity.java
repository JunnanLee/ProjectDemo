package com.bm.sy.filmstudio.activity;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.adapter.CalendarAdapter;
import com.bm.sy.filmstudio.adapter.SignListAdapter;
import com.bm.sy.filmstudio.base.BaseActivity;
import com.bm.sy.filmstudio.entity.SignListEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 创建人: 李俊男
 * 类名称：DateChooseActivity
 * 类描述：日期选择页面
 * 创建时间：2017/2/13 10:32
 */
public class DateChooseActivity extends BaseActivity implements OnGestureListener, View.OnClickListener {

    private GestureDetector gestureDetector = null;
    private CalendarAdapter calV = null;
    private GridView gridView = null;
    private TextView topText = null;
    private static int jumpMonth = 0;      //每次滑动，增加或减去一个月,默认为0（即显示当前月）
    private static int jumpYear = 0;       //滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private String currentDate = "";
    private Bundle bd = null;//发送参数
    private Bundle bun = null;//接收参数
    private String ruzhuTime;
    private String lidianTime;
    private String state = "";
    private ImageView iv_up;//上一月
    private ImageView iv_down;//下一月
    private TextView tv_now_ck;//当前选中
    private List<SignListEntity> mList = new ArrayList<>();
    private SignListAdapter listAdapter;
    private ImageView iv_back;//返回键
    private TextView tv_title;//标题栏

    public DateChooseActivity() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentDate = sdf.format(date);  //当期日期
        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);


    }

    public static void start(Activity context) {
        Intent starter = new Intent(context, DateChooseActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.ac_date_choose);


        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title_name);
        iv_up = (ImageView) findViewById(R.id.left_img);
        iv_down = (ImageView) findViewById(R.id.right_img);
        tv_now_ck = (TextView) findViewById(R.id.tv_now_ck);

        iv_back.setVisibility(View.VISIBLE);
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText("日期选择");
        iv_back.setOnClickListener(this);
    }

    @Override
    protected void initLogic() {
        bd = new Bundle();//out
        bun = getIntent().getExtras();//in


        if (bun != null && bun.getString("state").equals("ruzhu")) {
            state = bun.getString("state");
            System.out.println("%%%%%%" + state);
        } else if (bun != null && bun.getString("state").equals("lidian")) {

            state = bun.getString("state");
            System.out.println("|||||||||||" + state);
        }

        gestureDetector = new GestureDetector(this);
//		bd=new Bundle();
        calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c,true);
        addGridView();
        gridView.setAdapter(calV);

        topText = (TextView) findViewById(R.id.tv_month);
        addTextToTopTextView(topText);
        iv_up.setOnClickListener(this);
        iv_down.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int gvFlag = 0;
        switch (v.getId()) {
            case R.id.right_img:
                addGridView();   //添加一个gridView
                jumpMonth++;     //下一个月

                calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c,true);
                gridView.setAdapter(calV);
                addTextToTopTextView(topText);
                gvFlag++;
                break;
            case R.id.left_img:
                addGridView();   //添加一个gridView
                jumpMonth--;     //上一个月

                calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c,true);
                gridView.setAdapter(calV);
                gvFlag++;
                addTextToTopTextView(topText);
                break;

            case R.id.iv_back:
                jumpMonth = 0;
                jumpYear = 0;
                finish();
                break;
        }
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
//        int gvFlag = 0;         //每次添加gridview到viewflipper中时给的标记
//        if (e1.getX() - e2.getX() > 120) {
//            //像左滑动
//            addGridView();   //添加一个gridView
//            jumpMonth++;     //下一个月
//
//            calV = new CalendarAdapter(this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c,true);
//            gridView.setAdapter(calV);
//            addTextToTopTextView(topText);
//            gvFlag++;
//
//            return true;
//        } else if (e1.getX() - e2.getX() < -120) {
//            //向右滑动
//            addGridView();   //添加一个gridView
//            jumpMonth--;     //上一个月
//
//            calV = new CalendarAdapter(this,getResources(),jumpMonth,jumpYear,year_c,month_c,day_c,true);
//            gridView.setAdapter(calV);
//            gvFlag++;
//            addTextToTopTextView(topText);
//
//            return true;
//        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        jumpMonth = 0;
        jumpYear = 0;
        finish();
    }

    /**
     * 创建菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0, menu.FIRST, menu.FIRST, "今天");
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 选择菜单
     */
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST:
                //跳转到今天
                int xMonth = jumpMonth;
                int xYear = jumpYear;
                int gvFlag = 0;
                jumpMonth = 0;
                jumpYear = 0;
                addGridView();   //添加一个gridView
                year_c = Integer.parseInt(currentDate.split("-")[0]);
                month_c = Integer.parseInt(currentDate.split("-")[1]);
                day_c = Integer.parseInt(currentDate.split("-")[2]);
                calV = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c,true);
                gridView.setAdapter(calV);
                addTextToTopTextView(topText);
                gvFlag++;

                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return this.gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }

//添加头部的年份 闰哪月等信息2

    public void addTextToTopTextView(TextView view) {
        StringBuffer textDate = new StringBuffer();
        textDate.append(calV.getShowYear()).append("年").append(
                calV.getShowMonth()).append("月").append("\t");
        view.setText(textDate);
//        view.setTextColor(Color.WHITE);
//        view.setTypeface(Typeface.DEFAULT_BOLD);//字体加粗
    }

    //添加gridview
    private void addGridView() {

        gridView = (GridView) findViewById(R.id.gridview);

        gridView.setOnTouchListener(new View.OnTouchListener() {
            //将gridview中的触摸事件回传给gestureDetector
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return DateChooseActivity.this.gestureDetector.onTouchEvent(event);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//gridView中的每一个item的点击事件

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                //点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))
                int startPosition = calV.getStartPositon();
                int endPosition = calV.getEndPosition();
                if (startPosition <= position + 7 && position <= endPosition - 7) {
                    String scheduleDay = calV.getDateByClickItem(position).split("\\.")[0];  //这一天的阳历
                    //String scheduleLunarDay = calV.getDateByClickItem(position).split("\\.")[1];  //这一天的阴历
                    String scheduleYear = calV.getShowYear();
                    String scheduleMonth = calV.getShowMonth();
//	                  Toast.makeText(CalendarActivity.this, scheduleYear+"-"+scheduleMonth+"-"+scheduleDay, 2000).show();
                    ruzhuTime = scheduleMonth + "月" + scheduleDay + "日";
                    lidianTime = scheduleMonth + "月" + scheduleDay + "日";
                    Intent intent = new Intent();
                    if (state.equals("ruzhu")) {

                        bd.putString("ruzhu", ruzhuTime);
                        System.out.println("ruzhuuuuuu" + bd.getString("ruzhu"));
                    } else if (state.equals("lidian")) {

                        bd.putString("lidian", lidianTime);
                    }
//	                intent.setClass(CalendarActivity.this, HotelActivity.class);
//	                intent.putExtras(bd);
//	                startActivity(intent);
//	                finish();
                    calV.setSelectItem(position);
                    calV.notifyDataSetChanged();
                    String time = scheduleYear+"年"+scheduleMonth+"月"+scheduleDay+"日";
                    intent.putExtra("time",time);
                    setResult(RESULT_OK,intent);
                    finish();

                }
            }

        });
    }


}