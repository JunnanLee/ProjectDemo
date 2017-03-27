package com.bm.sy.filmstudio.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.base.BaseActivity;
import com.bm.sy.filmstudio.entity.SignEntity;
import com.bm.sy.filmstudio.util.StringUtils;
import com.bm.sy.filmstudio.view.BMRadioButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: 李俊男
 * 类名称：JournalSearchActivity
 * 类描述：日志搜索页面
 * 创建时间：2017/2/14 10:01
 */
public class JournalSearchActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {
    private static final String TAG = "【JournalSearchActivity】";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.menu_tab_film)
    BMRadioButton menuTabFilm;
    @BindView(R.id.menu_tab_park)
    BMRadioButton menuTabPark;
    @BindView(R.id.btn_tab_manage)
    BMRadioButton btnTabManage;
    @BindView(R.id.btn_tab_line)
    BMRadioButton btnTabLine;
    @BindView(R.id.btn_tab_pull)
    BMRadioButton btnTabPull;
    @BindView(R.id.rg_tab_menu)
    RadioGroup rgTabMenu;
    @BindView(R.id.listView)
    ListView listView;
    List<SignEntity> mList = new ArrayList<>();
    private JournalAdapter mAdapter;


    public static void start(Activity context) {
        Intent starter = new Intent(context, JournalSearchActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.ac_journal_search);
        ButterKnife.bind(this);

        ivBack.setVisibility(View.VISIBLE);
        tvTitleName.setVisibility(View.VISIBLE);
        tvTitleName.setText("日志搜索");
    }

    @Override
    protected void initLogic() {
        rgTabMenu.setOnCheckedChangeListener(JournalSearchActivity.this);
        listView.setOnItemClickListener(this);
        mAdapter = new JournalAdapter(mList, JournalSearchActivity.this);
        listView.setAdapter(mAdapter);

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_SEND || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String text = etSearch.getText().toString();
                    if (!StringUtils.isEmpty(text)) {
                        initData();
                        mAdapter.notifyDataSetChanged();
                        removeKeyBord(etSearch);
//                        etSearch.requestFocus();
//                        etSearch.setFocusable(true);
//                        listView.setSelection(0);

                    } else {
                        Toast.makeText(JournalSearchActivity.this, "请输入查询关键字", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });

    }

    /**
     * 添加假数据
     */
    private void initData() {
        for (int i = 0; i < 4; i++) {
            SignEntity bean = new SignEntity();
            bean.setShopName("SC上影影城新衡山店");
            bean.setPlace("上海市徐汇区横上路220号");
            mList.add(bean);
        }
    }

    /**
     * 收起软键盘
     * @param et
     */
    private void removeKeyBord(EditText et){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0) ;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.menu_tab_film:
                menuTabFilm.setTextColor(getResources().getColor(R.color.main_text_color_black_dark));
                menuTabPark.setTextColor(getResources().getColor(R.color.tab_text_color));
                btnTabManage.setTextColor(getResources().getColor(R.color.tab_text_color));
                btnTabLine.setTextColor(getResources().getColor(R.color.tab_text_color));
                btnTabPull.setTextColor(getResources().getColor(R.color.tab_text_color));
                break;
            case R.id.menu_tab_park:
                menuTabFilm.setTextColor(getResources().getColor(R.color.tab_text_color));
                menuTabPark.setTextColor(getResources().getColor(R.color.main_text_color_black_dark));
                btnTabManage.setTextColor(getResources().getColor(R.color.tab_text_color));
                btnTabLine.setTextColor(getResources().getColor(R.color.tab_text_color));
                btnTabPull.setTextColor(getResources().getColor(R.color.tab_text_color));
                break;
            case R.id.btn_tab_manage:
                menuTabFilm.setTextColor(getResources().getColor(R.color.tab_text_color));
                menuTabPark.setTextColor(getResources().getColor(R.color.tab_text_color));
                btnTabManage.setTextColor(getResources().getColor(R.color.main_text_color_black_dark));
                btnTabLine.setTextColor(getResources().getColor(R.color.tab_text_color));
                btnTabPull.setTextColor(getResources().getColor(R.color.tab_text_color));
                break;
            case R.id.btn_tab_line:
                menuTabFilm.setTextColor(getResources().getColor(R.color.tab_text_color));
                menuTabPark.setTextColor(getResources().getColor(R.color.tab_text_color));
                btnTabManage.setTextColor(getResources().getColor(R.color.tab_text_color));
                btnTabLine.setTextColor(getResources().getColor(R.color.main_text_color_black_dark));
                btnTabPull.setTextColor(getResources().getColor(R.color.tab_text_color));
                break;
            case R.id.btn_tab_pull:
                menuTabFilm.setTextColor(getResources().getColor(R.color.tab_text_color));
                menuTabPark.setTextColor(getResources().getColor(R.color.tab_text_color));
                btnTabManage.setTextColor(getResources().getColor(R.color.tab_text_color));
                btnTabLine.setTextColor(getResources().getColor(R.color.tab_text_color));
                btnTabPull.setTextColor(getResources().getColor(R.color.main_text_color_black_dark));
                break;
        }
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mAdapter.setSelectItem(position);
        mAdapter.notifyDataSetChanged();
    }


    public class JournalAdapter extends BaseAdapter {

        private List<SignEntity> mList;

        private Context context;

        public JournalAdapter(List<SignEntity> mList, Context context) {
            this.mList = mList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (null == convertView) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_journal_search, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {

                holder = (ViewHolder) convertView.getTag();
            }
            if(0==position){
                holder.appLine.setVisibility(View.GONE);
            }else{
                holder.appLine.setVisibility(View.VISIBLE);
            }
            holder.tvShopName.setText(mList.get(position).getShopName());
            holder.tvPlace.setText(mList.get(position).getPlace());
            if (selectItem == position) {
                holder.ivChooseLogo.setImageResource(R.drawable.checked_logo);
            } else {
                holder.ivChooseLogo.setImageResource(R.drawable.un_xuanze);
            }
            return convertView;
        }

        private int selectItem = -1;

        public void setSelectItem(int selectItem) {
            this.selectItem = selectItem;
        }

        class ViewHolder {
            @BindView(R.id.app_line)
            View appLine;
            @BindView(R.id.tv_shop_name)
            TextView tvShopName;
            @BindView(R.id.tv_place)
            TextView tvPlace;
            @BindView(R.id.iv_choose_logo)
            ImageView ivChooseLogo;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
