package com.bm.sy.filmstudio.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.base.BaseActivity;
import com.bm.sy.filmstudio.entity.ProjectEntity;
import com.bm.sy.filmstudio.util.StringUtils;
import com.bm.sy.filmstudio.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建人: 李俊男
 * 类名称：ProjectChangeActivity
 * 类描述：
 * 创建时间：2017/2/13 17:38
 */
public class ProjectChangeActivity extends BaseActivity {
    private static final String TAG = "【ProjectChangeActivity】";
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.lv_project)
    ListView lvProject;
    @BindView(R.id.et_search)
    EditText etSearch;
    private List<ProjectEntity> mList = new ArrayList<>();//
    private ProjectAdapter mAdapter;//


    public static void start(Activity context) {
        Intent starter = new Intent(context, ProjectChangeActivity.class);
        context.startActivity(starter);
//        context.overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
    }


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.ac_project_change);
        ButterKnife.bind(this);
        tvTitleName.setVisibility(View.VISIBLE);
        ivBack.setVisibility(View.VISIBLE);
        tvTitleName.setText("项目切换");
    }

    @Override
    protected void initLogic() {



        //添加假数据


        for (int i = 0; i < 3; i++) {
            ProjectEntity bean = new ProjectEntity();
            bean.setTime("2017年2月14日 周五");
            bean.setActor("尼尔·伯格");
            bean.setStar("尼古拉斯· 凯奇，谢林");
            bean.setPic(R.drawable.demo_project);
            bean.setTicket("1252123.36");
            bean.setFilmName("分歧者3");
            mList.add(bean);
        }

        lvProject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("project", mList.get(position).getFilmName());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_SEND || (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String text = etSearch.getText().toString();
                    if (!StringUtils.isEmpty(text)) {
                        mAdapter = new ProjectAdapter(mList, ProjectChangeActivity.this);
                        lvProject.setAdapter(mAdapter);
                        removeKeyBord(etSearch);

                    } else {
                        Toast.makeText(ProjectChangeActivity.this, "请输入查询关键字", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * 收起软键盘
     * @param et
     */
    private void removeKeyBord(EditText et){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0) ;
    }

    public class ProjectAdapter extends BaseAdapter {

        private List<ProjectEntity> mList;

        private Context context;

        public ProjectAdapter(List<ProjectEntity> mList, Context context) {
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
                convertView = LayoutInflater.from(context).inflate(R.layout.item_project_change, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.ivPic.setImageResource(mList.get(position).getPic());
            holder.tvName.setText(mList.get(position).getFilmName());
            holder.tvName.setTypeface(Typeface.DEFAULT_BOLD);//字体加粗
            holder.tvTicket.setText(mList.get(position).getTicket());
            holder.tvTime.setText(mList.get(position).getTime());
            holder.tvActor.setText("导演:"+mList.get(position).getActor());
            holder.tvStar.setText("主演:"+mList.get(position).getStar());

            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.iv_pic)
            ImageView ivPic;
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.tv_ticket)
            TextView tvTicket;
            @BindView(R.id.tv_time)
            TextView tvTime;
            @BindView(R.id.tv_actor)
            TextView tvActor;
            @BindView(R.id.tv_star)
            TextView tvStar;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
