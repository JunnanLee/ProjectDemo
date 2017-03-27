package com.bm.sy.filmstudio.text;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.activity.ForgetPassActivity;
import com.bm.sy.filmstudio.base.BaseActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人: 李俊男
 * 类名称：RecyclerListActivity
 * 类描述：
 * 创建时间：2017/3/8 13:52
 */
public class RecyclerListActivity extends BaseActivity {
    private static final String TAG = "【RecyclerListActivity】";
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    List<TextEntity> data ;
    RecyclerTextAdapter adapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.ac_recycler_list);
        ButterKnife.bind(this);

    }


    public static void start(Activity context) {
        Intent starter = new Intent(context, RecyclerListActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected void initLogic() {
        initData();
        rvList.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new RecyclerTextAdapter(R.layout.item_recycler_text,data);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.isFirstOnly(false);
        rvList.setAdapter(adapter);
    }

    /**
     *添加假数据
     */
    private void initData() {
        data = new ArrayList<>();
        for(int i=0;i<10;i++){
            TextEntity dataTmp = new TextEntity();

            if(i%2==0){
                dataTmp.setType(1);
            }else{
                dataTmp.setType(2);
            }
            dataTmp.setPic(R.drawable.demo_pic_head);
            dataTmp.setTitle("Hello Text");
            data.add(dataTmp);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
