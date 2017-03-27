package com.bm.sy.filmstudio.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.sy.filmstudio.R;
import com.bm.sy.filmstudio.activity.JournalExamineActivity;
import com.bm.sy.filmstudio.activity.JournalPassActivity;
import com.bm.sy.filmstudio.activity.NewLogActivity;
import com.bm.sy.filmstudio.base.AFI;
import com.bm.sy.filmstudio.base.BaseFragment;
import com.bm.sy.filmstudio.entity.WorkListBean;
import com.bm.sy.filmstudio.util.LogUtil;
import com.bm.sy.filmstudio.util.ToastUtil;
import com.bm.sy.filmstudio.util.ZQExpandableListView;
import com.bm.sy.filmstudio.util.ZQView;
import com.bm.sy.filmstudio.view.BMRadioButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 创建人: 李俊男
 * 类名称：JournalFragment
 * 类描述： 日志页面
 * 创建时间：2017/1/23 13:30
 */
@AFI(contentViewId = R.layout.fg_journal_ll)
public class JournalFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "【JournalFragment】";
    @BindView(R.id.tv_title_name)
    TextView tvTitleName;
    @BindView(R.id.iv_timeshare)
    ImageView ivTimeshare;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.menu_tab_submit)
    BMRadioButton menuTabSubmit;//未提交
    @BindView(R.id.menu_tab_box)
    BMRadioButton menuTabBox;//草稿箱
    @BindView(R.id.btn_tab_wait)
    BMRadioButton btnTabWait;//待审核
    @BindView(R.id.btn_tab_back)
    BMRadioButton btnTabBack;//退回
    @BindView(R.id.btn_tab_pass)
    BMRadioButton btnTabPass;//通过
    @BindView(R.id.rg_tab_menu)
    RadioGroup rgTabMenu;//单选按钮组
    @BindView(R.id.lv_ex_view)
    ZQExpandableListView lvExView;
    @BindView(R.id.tv_build)
    TextView tvBuild;
    SlideAdapter mAdapter;
    private boolean isSlideCanRoll = true;//是否添加左滑删除

    private List<String> groups;
    private List<List<WorkListBean>> child;
    private int CHOOSE_KEY = 0;//当前选择tab标签

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = createFragmentView(inflater, container, R.layout.fg_journal_ll);
        if (null == rootView) {
            LogUtil.d(TAG, "fragment view is not created！");
        }
        ButterKnife.bind(this, rootView);
        return this.rootView;
    }
    @Override
    protected void initLogic() {
        groups = new ArrayList<String>();
        child = new ArrayList<List<WorkListBean>>();
        initData();//添加测试数据
        tvTitleName.setVisibility(View.VISIBLE);
        ivTimeshare.setVisibility(View.VISIBLE);
        tvTitleName.setText("日志");
        rgTabMenu.setOnCheckedChangeListener(this);
        mAdapter = new SlideAdapter(rootView.getContext(),groups,child);
        lvExView.setAdapter(mAdapter);


        int groupCount = lvExView.getCount();
        for (int i = 0; i < groupCount; i++) {
            lvExView.expandGroup(i);
        }
        lvExView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                switch (CHOOSE_KEY){
                    case 0://未提交
                        break;
                    case 1://草稿箱
                        break;
                    case 2://审核
                        JournalExamineActivity.start((Activity) rootView.getContext());

                        break;
                    case 3://退回
                        JournalPassActivity.start((Activity) rootView.getContext(),0);
                        break;
                    case 4://通过
                        JournalPassActivity.start((Activity) rootView.getContext(),1);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        lvExView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    @OnClick(R.id.tv_build)
    public void onClick() {

    }

    @OnClick({R.id.iv_timeshare, R.id.tv_build})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_timeshare:
                break;
            case R.id.tv_build:
                NewLogActivity.start((Activity) rootView.getContext());
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.menu_tab_submit:
                CHOOSE_KEY = 0;
                isSlideCanRoll = true;
                menuTabSubmit.setTextColor(getResources().getColor(R.color.white));
                menuTabBox.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabWait.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabBack.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabPass.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                initData();
                break;
            case R.id.menu_tab_box:
                CHOOSE_KEY = 1;
                isSlideCanRoll = true;
                menuTabBox.setTextColor(getResources().getColor(R.color.white));
                menuTabSubmit.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabWait.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabBack.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabPass.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                initCao();
                break;
            case R.id.btn_tab_wait:
                CHOOSE_KEY = 2;
                isSlideCanRoll = false;
                btnTabWait.setTextColor(getResources().getColor(R.color.white));
                menuTabBox.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                menuTabSubmit.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabBack.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabPass.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                initShen();
                break;
            case R.id.btn_tab_back:
                CHOOSE_KEY = 3;
                isSlideCanRoll = false;
                btnTabBack.setTextColor(getResources().getColor(R.color.white));
                menuTabBox.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabWait.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                menuTabSubmit.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabPass.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                initBack();
                break;
            case R.id.btn_tab_pass:
                CHOOSE_KEY = 4;
                isSlideCanRoll = false;
                btnTabPass.setTextColor(getResources().getColor(R.color.white));
                menuTabBox.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabWait.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                btnTabBack.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                menuTabSubmit.setTextColor(getResources().getColor(R.color.menu_sing_un_select));
                initPass();
                break;
            default:
                break;
        }
        mAdapter.notifyDataSetChanged();

//        int groupCount = lvExView.getCount();
        for (int i = 0; i < groups.size(); i++) {
            lvExView.expandGroup(i);
        }
    }


    private class SlideAdapter extends BaseExpandableListAdapter {

        private Context context;
        private List<String> group;
        private List<List<WorkListBean>> child;

        public SlideAdapter(Context context,List<String> group, List<List<WorkListBean>> child) {
            this.context =context;
            this.group = group;
            this.child = child;
        }
        @Override
        public int getGroupCount() {
            // TODO Auto-generated method stub
            return group.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            // TODO Auto-generated method stub
            return child.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            // TODO Auto-generated method stub
            return group.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return child.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            // TODO Auto-generated method stub
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            TextView t = null;
            convertView = LayoutInflater.from(context).inflate(R.layout.group_item, null);

            t = (TextView) convertView.findViewById(R.id.textView1);
            convertView.setTag(t);
            t = (TextView) convertView.getTag();
            t.setText(group.get(groupPosition));
            return convertView;
        }


        @Override
        public View getChildView(final int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            ViewHolder holder;
            ZQView slideView = (ZQView) convertView;
            View itemView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            if(isSlideCanRoll){
                slideView = new ZQView(getActivity(),false);
            }else{
                slideView = new ZQView(getActivity(),true);
            }
            slideView.setContentView(itemView);
            holder = new ViewHolder(slideView);
//            slideView.setTag(holder);
//            holder = (ViewHolder) slideView.getTag();

            slideView.shrink();
            holder.title.setText(child.get(groupPosition).get(childPosition).getFilmName());
            holder.content.setText(child.get(groupPosition).get(childPosition).getTime() + "，" + child.get(groupPosition).get(childPosition).getNotice());
            if (childPosition == (child.get(groupPosition).size() - 1)) {
                holder.v_line.setVisibility(View.VISIBLE);
            } else {
                holder.v_line.setVisibility(View.INVISIBLE);

            }

            holder.deleteHolder.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

//                      child.get(gr)child.remove(childPosition);
                    child.get(groupPosition).remove(childPosition);
                    if (0 == child.get(groupPosition).size()) {
                        child.remove(groupPosition);
                        group.remove(groupPosition);
                    }
                    notifyDataSetChanged();
                }
            });

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    JournalPassActivity.start((Activity) rootView.getContext());
//                    Toast.makeText( rootView.getContext(), "11111", Toast.LENGTH_SHORT).show();
//                }
//            });

            return slideView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            // TODO Auto-generated method stub
            return true;
        }

    }

    private static class ViewHolder {
        public TextView title;//标题
        public TextView content;//详情
        public View v_line;//线
        public ViewGroup deleteHolder;

        ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.tv_name);
            content = (TextView) view.findViewById(R.id.tv_content);
            v_line = view.findViewById(R.id.line_v);
            deleteHolder = (ViewGroup) view.findViewById(R.id.holder);
        }
    }

    //以下为假数据  未提交
    private void initData() {
        child.clear();
        groups.clear();
        for (int i = 1; i < 4; i++) {
            List<WorkListBean> bean = new ArrayList<>();
            for (int j = 0; j < 1; j++) {
                WorkListBean beanTmp = new WorkListBean();
                beanTmp.setFilmName("秦放放 (盗墓笔记)");
                beanTmp.setTime("20:20提交");
                beanTmp.setNotice("共两条记录");
                bean.add(beanTmp);
            }
            child.add(bean);
            groups.add("2016年9月" + i + "号周三");
        }
//        addInfo("2016年9月8号周三", new String[]{"朝阳", "海淀", "东城区"});
//        addInfo("2016年9月9号周三", new String[]{"邯郸", "石家庄", "邢台"});
//        addInfo("2016年9月10号周三", new String[]{"广州", "深圳", "珠海"});
    }

    //草稿箱子
    private void initCao() {
        child.clear();
        groups.clear();
        for (int i = 1; i < 4; i++) {
            List<WorkListBean> bean = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                WorkListBean beanTmp = new WorkListBean();
                beanTmp.setFilmName("秦放放 (盗墓笔记)");
                beanTmp.setTime("20:20提交");
                beanTmp.setNotice("共两条记录");
                bean.add(beanTmp);
            }
            child.add(bean);
            groups.add("2016年9月" + i + "号周三");
        }
//        addInfo("2016年9月8号周三", new String[]{"朝阳", "海淀", "东城区"});
//        addInfo("2016年9月9号周三", new String[]{"邯郸", "石家庄", "邢台"});
//        addInfo("2016年9月10号周三", new String[]{"广州", "深圳", "珠海"});
    }

    //审核
    private void initShen() {
        child.clear();
        groups.clear();
        for (int i = 1; i < 2; i++) {
            List<WorkListBean> bean = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                WorkListBean beanTmp = new WorkListBean();
                beanTmp.setFilmName("秦放放 (盗墓笔记)");
                beanTmp.setTime("20:20提交");
                beanTmp.setNotice("共两条记录");
                bean.add(beanTmp);
            }
            child.add(bean);
            groups.add("2016年9月" + i + "号周三");
        }
//        addInfo("2016年9月8号周三", new String[]{"朝阳", "海淀", "东城区"});
//        addInfo("2016年9月9号周三", new String[]{"邯郸", "石家庄", "邢台"});
//        addInfo("2016年9月10号周三", new String[]{"广州", "深圳", "珠海"});
    }

    //退回
    private void initBack() {
        child.clear();
        groups.clear();
        for (int i = 1; i < 3; i++) {
            List<WorkListBean> bean = new ArrayList<>();
            for (int j = 0; j < 1; j++) {
                WorkListBean beanTmp = new WorkListBean();
                beanTmp.setFilmName("秦放放 (盗墓笔记)");
                beanTmp.setTime("20:20提交");
                beanTmp.setNotice("共两条记录");
                bean.add(beanTmp);
            }
            child.add(bean);
            groups.add("2016年9月" + i + "号周三");
        }
//        addInfo("2016年9月8号周三", new String[]{"朝阳", "海淀", "东城区"});
//        addInfo("2016年9月9号周三", new String[]{"邯郸", "石家庄", "邢台"});
//        addInfo("2016年9月10号周三", new String[]{"广州", "深圳", "珠海"});
    }

    //通过
    private void initPass() {
        child.clear();
        groups.clear();
        for (int i = 1; i < 4; i++) {
            List<WorkListBean> bean = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                WorkListBean beanTmp = new WorkListBean();
                beanTmp.setFilmName("秦放放 (盗墓笔记)");
                beanTmp.setTime("20:20提交");
                beanTmp.setNotice("共两条记录");
                bean.add(beanTmp);
            }
            child.add(bean);
            groups.add("2016年9月" + i + "号周三");
        }
//        addInfo("2016年9月8号周三", new String[]{"朝阳", "海淀", "东城区"});
//        addInfo("2016年9月9号周三", new String[]{"邯郸", "石家庄", "邢台"});
//        addInfo("2016年9月10号周三", new String[]{"广州", "深圳", "珠海"});
    }


    /**
     * 添加数据信息
     *
     * @param g
     * @param c
     */
    private void addInfo(String g, String[] c) {
//        group.add(g);
//        List<WorkListBean> list = new ArrayList<>();
//        for (int i = 0; i < c.length; i++) {
//            list.add(c[i]);
//        }
//        child.add(list);
    }


}
