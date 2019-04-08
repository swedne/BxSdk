package com.zorasun.fangchanzhichuang.section.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.loopj.android.http.RequestParams;
import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseActivityNoSwipe;
import com.zorasun.fangchanzhichuang.general.base.BaseApi.RequestCallBack;
import com.zorasun.fangchanzhichuang.general.common.SystemConstant;
import com.zorasun.fangchanzhichuang.general.commonadapter.CommonAdapter;
import com.zorasun.fangchanzhichuang.general.commonadapter.ViewHolder;
import com.zorasun.fangchanzhichuang.general.util.AsyncImageLoader;
import com.zorasun.fangchanzhichuang.general.util.PopupWindowUtil;
import com.zorasun.fangchanzhichuang.general.util.StringUtils;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.CustomView;
import com.zorasun.fangchanzhichuang.general.widget.CustomView.OnLoadStateLinstener;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.Mode;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshBase.OnRefreshListener2;
import com.zorasun.fangchanzhichuang.general.widget.pulltorefresh.PullToRefreshListView;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerInfoEntity.SpecialSkillList;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerListEntity;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerListEntity.AllSkillList;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerListEntity.AreaList;
import com.zorasun.fangchanzhichuang.section.index.entity.BrokerListEntity.BrokerList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class JingjiRenActivity extends BaseActivityNoSwipe
        implements OnItemClickListener, OnClickListener, OnRefreshListener2<ListView>, OnLoadStateLinstener {

    private RelativeLayout rl_quyu;
    private int poptab;
    private TextView tvQuyu;
    private TextView tvLeixing;
    private TextView tvPaixu;
    private ArrayList<String> popList1 = new ArrayList<>();
    private ArrayList<String> popList2 = new ArrayList<>();
    private ArrayList<String> popList3 = new ArrayList<>();
    private PopAdapter3 adapter3;
    private PopAdapter2 adapter2;
    private int select1 = -1;
    private int select2 = -1;
    private int select3 = -1;
    private PopAdapter1 adapter1;

    private List<BrokerList> brokerList = new ArrayList<>();
    private MyAdapter myAdapter;
    private ListView mListView;
    private PullToRefreshListView ptrListView;
    private RequestParams params;
    private int page = 1;
    private boolean isRefresh;
    private int pageCount;
    private CustomView customview;
    private List<AllSkillList> allSkillList = new ArrayList<>();
    protected int flag;
    private List<AreaList> areaList = new ArrayList<>();
    private HashMap<Integer, Integer> hasMap = new HashMap<>();
    private HashMap<String, Integer> hasMapAreaId = new HashMap<>();
    private ArrayList<Integer> intList = new ArrayList<>();
    protected int indexFlag;
    protected int businessListId = -1;
    protected int type = -1;
    private LocationClient locationClient;
    private double latitude = -1;
    private double longitude = -1;
    private PopupWindow popupWindow;
    protected int houseTypeId = -1;
    private int popIndex = -1;
    private String orderByName = new String();
    protected boolean isFirstPaiXu;
    protected int orderBy = -1;
    private String likeSelect;
    private View searchEmpty;
    private String houseTypeName;
    private View delete;
    private EditText etSearch;
    private Drawable rightDrawableSelect;
    private Drawable rightDrawableNormal;
    private Drawable rightDrawableUp;
    private int areaId = -1;
    private HashMap<Integer, Integer> hasRecord = new HashMap<>();
    private boolean searchBusiness = true;
    private int height;// ListView2的高度
    private int sec = -1;
    private int first = -2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jingjiren);
        hasRecord.put(0, -1);
        hasRecord.put(1, -1);
        hasRecord.put(2, -1);
        hasRecord.put(sec, -1);
        hasRecord.put(first, -1);
        initView();
        initData();
    }

    private void initData() {
        searchEmpty.setVisibility(View.GONE);
        selectParams();
        IndexApi.getInstance().requestBrokerList(this, params, new RequestCallBack() {

            @Override
            public void onSuccess(int code, String msg, Object object) {
                ptrListView.onRefreshComplete();
                BrokerListEntity brokerListEntity = (BrokerListEntity) object;
                List<BrokerList> brokerList2 = brokerListEntity.getContent().getBrokerList();
                brokerListEntity.getContent().getAreaList();
                if (isRefresh) {
                    brokerList.clear();
                    areaList.clear();
                    allSkillList.clear();
                }
                allSkillList.clear();
                areaList.addAll(brokerListEntity.getContent().getAreaList());
                allSkillList.addAll(brokerListEntity.getContent().getAllSkillList());
                pageCount = brokerListEntity.getContent().getPageCount();
                if (pageCount <= page) {
                    ptrListView.setMode(Mode.PULL_FROM_START);
                } else {
                    ptrListView.setMode(Mode.BOTH);
                }
                brokerList.addAll(brokerList2);

                if (brokerList.isEmpty()) {
                    customview.showLoadStateView(CustomView.STATE_EMPTY);
                    ptrListView.setMode(Mode.DISABLED);
                    ToastUtil.toastShow(JingjiRenActivity.this, "无满足条件的经纪人");
                } else {
                    customview.showLoadStateView(CustomView.STATE_NONE);
                }
                // if (brokerList.isEmpty() && ((!TextUtils.isEmpty(likeSelect))
                // || !TextUtils.isEmpty(houseTypeName))) {
                // // searchEmpty.setVisibility(View.VISIBLE);
                // ToastUtil.toastShow(JingjiRenActivity.this, "无满足条件的经纪人");
                // } else {
                // searchEmpty.setVisibility(View.GONE);
                // }
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }

                for (int i = 0; i < areaList.size(); i++) {
                    if (!hasMapAreaId.containsKey(areaList.get(i).getAreaName())) {
                        hasMapAreaId.put(areaList.get(i).getAreaName(), areaList.get(i).getAreaId());
                    }
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNetworkError() {
                ToastUtil.toastShow(JingjiRenActivity.this, getResources().getString(R.string.net_error));
                customview.showLoadStateView(CustomView.STATE_ERROR);
                ptrListView.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        ptrListView.onRefreshComplete();
                    }
                }, 1000);
                ptrListView.setMode(Mode.DISABLED);
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }

            }

            @Override
            public void onFailure(int code, String msg, Object object) {
                ToastUtil.toastShow(JingjiRenActivity.this, getResources().getString(R.string.net_error));
                ptrListView.onRefreshComplete();
                customview.showLoadStateView(CustomView.STATE_EMPTY);
                ptrListView.setMode(Mode.DISABLED);
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });

    }

    @SuppressWarnings("deprecation")
    private void initView() {
        searchEmpty = findViewById(R.id.search_empty);
        customview = (CustomView) findViewById(R.id.data_error);
        customview.setLoadStateLinstener(this);
        customview.showLoadStateView(CustomView.STATE_EMPTY);
        findViewById(R.id.title_right_iv).setVisibility(View.GONE);
        ptrListView = (PullToRefreshListView) findViewById(R.id.ptr_listView);
        ptrListView.setMode(Mode.BOTH);
        ptrListView.setOnRefreshListener(this);
        mListView = ptrListView.getRefreshableView();
        myAdapter = new MyAdapter(this, brokerList, R.layout.jingjiren_item);
        mListView.setAdapter(myAdapter);
        TextView tvRightTitle = (TextView) findViewById(R.id.title_right_tv);
        tvRightTitle.setVisibility(View.GONE);
        mListView.setOnItemClickListener(this);
        etSearch = (EditText) findViewById(R.id.et_title_Search);
        etSearch.setOnClickListener(this);
        etSearch.setHint("请输入关键词");
        rl_quyu = (RelativeLayout) findViewById(R.id.rl_quyu);
        rl_quyu.setOnClickListener(this);
        findViewById(R.id.rl_leixing).setOnClickListener(this);
        findViewById(R.id.rl_paixu).setOnClickListener(this);
        tvQuyu = (TextView) findViewById(R.id.tv_quyu);
        tvLeixing = (TextView) findViewById(R.id.tv_leixing);
        tvPaixu = (TextView) findViewById(R.id.tv_paixu);
        findViewById(R.id.tv_tudigong).setOnClickListener(this);
        delete = findViewById(R.id.img_delete);
        delete.setOnClickListener(this);

        rightDrawableSelect = getResources().getDrawable(R.drawable.sanjiao_p);
        rightDrawableSelect.setBounds(0, 0, rightDrawableSelect.getMinimumWidth(),
                rightDrawableSelect.getMinimumHeight());
        rightDrawableNormal = getResources().getDrawable(R.drawable.sanjiao_n);
        rightDrawableNormal.setBounds(0, 0, rightDrawableNormal.getMinimumWidth(),
                rightDrawableNormal.getMinimumHeight());
        rightDrawableUp = getResources().getDrawable(R.drawable.sanjiao_p_up);
        rightDrawableUp.setBounds(0, 0, rightDrawableNormal.getMinimumWidth(), rightDrawableUp.getMinimumHeight());
    }

    class MyAdapter extends CommonAdapter<BrokerList> {

        public MyAdapter(Context context, List<BrokerList> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @SuppressLint("InflateParams")
        @Override
        public void convert(ViewHolder helper, BrokerList item, int position) {
            ImageView imgLevel = (ImageView) helper.getView(R.id.img_leavel);
            imgLevel.setVisibility(View.GONE);
            if (orderByName != null) {
                if (orderByName.equals("房源数") || orderByName.equals("成交量") || orderByName.equals("好评率")) {
                    imgLevel.setVisibility(View.GONE);
                } else {
                    imgLevel.setVisibility(View.VISIBLE);
                    switch (position) {
                        case 0:
                            imgLevel.setImageResource(R.drawable.jin);
                            break;
                        case 1:
                            imgLevel.setImageResource(R.drawable.yin);
                            break;
                        case 2:
                            imgLevel.setImageResource(R.drawable.tong);
                            break;
                        default:
                            imgLevel.setVisibility(View.GONE);
                            break;
                    }
                }
            } else {
                imgLevel.setVisibility(View.VISIBLE);
                switch (position) {
                    case 0:
                        imgLevel.setImageResource(R.drawable.jin);
                        break;
                    case 1:
                        imgLevel.setImageResource(R.drawable.yin);
                        break;
                    case 2:
                        imgLevel.setImageResource(R.drawable.tong);
                        break;
                    default:
                        imgLevel.setVisibility(View.GONE);
                        break;
                }
            }
            TextView tvBrokerName = (TextView) helper.getView(R.id.tv_brokerName);
            TextView tvRealName = (TextView) helper.getView(R.id.tv_realName);
            TextView tvIsExpert = (TextView) helper.getView(R.id.tv_isExpert);
            TextView tvHarkBackHouse = (TextView) helper.getView(R.id.tv_harkBackHouse);
            TextView tvBusinessName = (TextView) helper.getView(R.id.tv_businessname);
            View line = helper.getView(R.id.line);
            LinearLayout llSkill = (LinearLayout) helper.getView(R.id.ll_biaoqian);
            tvBrokerName.setText(item.getBrokerName());
            tvRealName.setText(item.getRealName());
            tvHarkBackHouse.setText(item.getHarkBackHouse());
            tvBusinessName.setText(item.getBusinessName());
            ImageView imgBroker = (ImageView) helper.getView(R.id.img_broker);
            if (!TextUtils.isEmpty(item.getHeadUrl())) {
                // Bitmap image = AsyncImageLoader.getImage(item.getHeadUrl(),
                // JingjiRenActivity.this);
                // int degree = readPictureDegree(item.getHeadUrl());
                // imgBroker.setImageBitmap(rotaingImageView(degree, image));
                AsyncImageLoader.setAsynImagesNoExif(imgBroker, item.getHeadUrl());
            } else {
                imgBroker.setImageResource(R.drawable.wutu);
            }
            if (item.getIsExpert() == 1) {
                tvIsExpert.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
            } else {
                tvIsExpert.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
            }
            String specialSkillList = item.getSpecialSkillList();
            llSkill.removeAllViews();
            if (!StringUtils.isEmpty(specialSkillList) && specialSkillList.length() > 0) {
                View childLayout = getLayoutInflater().inflate(R.layout.childview_jingjiren_item, null);
                TextView tvSkill = (TextView) childLayout.findViewById(R.id.tv_skill);
                tvSkill.setText(specialSkillList);
                llSkill.addView(childLayout);
            } else {
                View childLayout = getLayoutInflater().inflate(R.layout.childview_jingjiren_item, null);
                TextView tvSkill = (TextView) childLayout.findViewById(R.id.tv_skill);
                tvSkill.setText("暂无");
                llSkill.addView(childLayout);
            }
        }
    }

    private static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    private static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        position -= mListView.getHeaderViewsCount();
        int brokerId = brokerList.get(position).getBrokerId();
        Intent intent = new Intent(this, JingjirenXqActivity.class);
        intent.putExtra("brokerId", brokerId);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            // 搜索界面
            case R.id.et_title_Search:
                Intent jingjirenintent = new Intent(this, IndexSerachActivity.class);
                jingjirenintent.putExtra("stageNum", SystemConstant.STATE_PAGE_JINGJIREN);
                startActivityForResult(jingjirenintent, SystemConstant.STATE_PAGE_JINGJIREN);
                break;
            case R.id.rl_quyu:
                poptab = 0;
                showPopWindow();
                break;
            case R.id.rl_leixing:
                poptab = 1;
                showPopWindow();
                break;
            case R.id.rl_paixu:
                poptab = 2;
                showPopWindow();
                break;
            // 周边土地公
            case R.id.tv_tudigong:
                Intent intent = new Intent(this, TuDiGongActivity.class);
                startActivity(intent);
                break;
            case R.id.img_delete:
                likeSelect = null;
                houseTypeName = null;
                page = 1;
                mListView.setSelection(1);
                etSearch.setText("");
                delete.setVisibility(View.GONE);
                brokerList.clear();
                areaList.clear();
                myAdapter.notifyDataSetChanged();
                initData();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int arg0, int resultCode, Intent data) {
        super.onActivityResult(arg0, resultCode, data);
        if (data != null) {
            cancleParams();
            if (resultCode == 0) {
                likeSelect = data.getStringExtra("likeSelect");
                // params = new RequestParams();
                if (!TextUtils.isEmpty(likeSelect)) {
                    // params.put("likeSelect", likeSelect);
                    page = 1;
                    mListView.setSelection(1);
                    popIndex = 3;
                    delete.setVisibility(View.VISIBLE);
                    etSearch.setText(likeSelect);
                } else {
                    etSearch.setText("");
                    delete.setVisibility(View.GONE);
                }
                popIndex = 5;
            } else {
                // params = new RequestParams();
                houseTypeName = data.getStringExtra("houseTypeName");
                // params.put("houseTypeName", houseTypeName);
                page = 1;
                mListView.setSelection(1);
                delete.setVisibility(View.VISIBLE);
                etSearch.setText(houseTypeName);
                popIndex = 4;
            }
            brokerList.clear();
            areaList.clear();
            mListView.setSelection(1);
            myAdapter.notifyDataSetChanged();
            page = 1;
            initData();
        }
    }

    // 区域、类型、排序popWindow
    private void showPopWindow() {
        // params = new RequestParams();
        // select1 = -1;
        // select2 = -1;
        // select3 = -1;

        View view = getLayoutInflater().inflate(R.layout.item_jingjiren_tab, null);
        final LinearLayout rlPopWindow = (LinearLayout) view.findViewById(R.id.relativeLayout);
        ListView poplv1 = (ListView) view.findViewById(R.id.poplist1);
        final ListView poplv2 = (ListView) view.findViewById(R.id.poplist2);
        final ListView poplv3 = (ListView) view.findViewById(R.id.poplist3);

        final View line1 = view.findViewById(R.id.line1);

        LinearLayout ll_popleft = (LinearLayout) view.findViewById(R.id.ll_popleft);
        final LinearLayout ll_popmiddle = (LinearLayout) view.findViewById(R.id.ll_popmiddle);

        popupWindow = PopupWindowUtil.getPopupWindow(this, view);
        popupWindow.showAsDropDown(rl_quyu);
        TextView tv_bottom = (TextView) view.findViewById(R.id.tv_bottom);

        popList1.clear();
        popList2.clear();
        popList3.clear();
        select1 = hasRecord.get(first);
        select2 = hasRecord.get(sec);
        select3 = hasRecord.get(poptab);
        if (poptab == 0) {
            if (!searchBusiness) {
                String[] strings3 = getResources().getStringArray(R.array.pop_fujin);
                for (int i = 0; i < strings3.length; i++) {
                    popList3.add(strings3[i]);
                }
                ll_popmiddle.setVisibility(View.GONE);
                ll_popleft.setVisibility(View.VISIBLE);
                line1.setVisibility(View.GONE);
            } else {
                ll_popmiddle.setVisibility(View.VISIBLE);
                line1.setVisibility(View.VISIBLE);
                popList2.add("不限");
                for (int i = 0; i < areaList.size(); i++) {
                    String areaName = areaList.get(i).getAreaName();
                    if (!popList2.contains(areaName)) {
                        popList2.add(areaName);
                    }
                }
                if (select2 != 0 && select2 != -1) {
                    popList3.add("不限");
                    switch (select2) {
                        case 0:
                            break;
                        case 1:
                            for (int i = 0; i < areaList.size(); i++) {
                                if (areaList.get(i).getAreaName().equals("思明区")) {
                                    popList3.add(areaList.get(i).getBusinessName());
                                }
                            }
                            break;
                        case 2:
                            for (int i = 0; i < areaList.size(); i++) {
                                if (areaList.get(i).getAreaName().equals("海沧区")) {
                                    popList3.add(areaList.get(i).getBusinessName());
                                }
                            }

                            break;
                        case 3:
                            for (int i = 0; i < areaList.size(); i++) {
                                if (areaList.get(i).getAreaName().equals("湖里区")) {
                                    popList3.add(areaList.get(i).getBusinessName());
                                }
                            }
                            break;
                        case 4:
                            for (int i = 0; i < areaList.size(); i++) {
                                if (areaList.get(i).getAreaName().equals("集美区")) {
                                    popList3.add(areaList.get(i).getBusinessName());
                                }
                            }
                            break;
                        case 5:
                            for (int i = 0; i < areaList.size(); i++) {
                                if (areaList.get(i).getAreaName().equals("同安区")) {
                                    popList3.add(areaList.get(i).getBusinessName());
                                }
                            }
                            break;
                        case 6:
                            for (int i = 0; i < areaList.size(); i++) {
                                if (areaList.get(i).getAreaName().equals("翔安区")) {
                                    popList3.add(areaList.get(i).getBusinessName());
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
                if (height != 0) {
                    LayoutParams layoutParams = rlPopWindow.getLayoutParams();
                    layoutParams.height = height;
                }
            }
            tvQuyu.setTextColor(Color.parseColor("#1693FE"));
            tvQuyu.setCompoundDrawables(null, null, rightDrawableUp, null);
        }
        if (poptab == 1) {
            String[] strings = getResources().getStringArray(R.array.pop_broker_housetype);
            for (int i = 0; i < strings.length; i++) {
                popList3.add(strings[i]);
            }
            line1.setVisibility(View.GONE);
            ll_popmiddle.setVisibility(View.GONE);
            ll_popleft.setVisibility(View.GONE);
            tvLeixing.setTextColor(Color.parseColor("#1693FE"));
            tvLeixing.setCompoundDrawables(null, null, rightDrawableUp, null);

        }
        if (poptab == 2) {
            String[] strings = getResources().getStringArray(R.array.pop_paixu);
            for (int i = 0; i < strings.length; i++) {
                popList3.add(strings[i]);
            }
            line1.setVisibility(View.GONE);
            ll_popmiddle.setVisibility(View.GONE);
            ll_popleft.setVisibility(View.GONE);
            tvPaixu.setTextColor(Color.parseColor("#1693FE"));
            tvPaixu.setCompoundDrawables(null, null, rightDrawableUp, null);
        }
        adapter1 = new PopAdapter1();
        poplv1.setAdapter(adapter1);
        adapter2 = new PopAdapter2();
        poplv2.setAdapter(adapter2);
        adapter3 = new PopAdapter3();
        poplv3.setAdapter(adapter3);
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
        adapter3.notifyDataSetChanged();

        tv_bottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                // 默认字体的颜色和图片
                if (tvQuyu.getText().toString().equals("区域")) {
                    tvQuyu.setTextColor(Color.parseColor("#919191"));
                    tvQuyu.setCompoundDrawables(null, null, rightDrawableNormal, null);
                } else {
                    tvQuyu.setCompoundDrawables(null, null, rightDrawableSelect, null);
                }
                if (tvLeixing.getText().toString().equals("类型")) {
                    tvLeixing.setTextColor(Color.parseColor("#919191"));
                    tvLeixing.setCompoundDrawables(null, null, rightDrawableNormal, null);
                } else {
                    tvLeixing.setCompoundDrawables(null, null, rightDrawableSelect, null);
                }
                if (tvPaixu.getText().toString().equals("排序")) {
                    tvPaixu.setTextColor(Color.parseColor("#919191"));
                    tvPaixu.setCompoundDrawables(null, null, rightDrawableNormal, null);
                } else {
                    tvPaixu.setCompoundDrawables(null, null, rightDrawableSelect, null);
                }
            }
        });
        poplv1.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                select1 = position;
                // 点击区域或者附近的时候清空后面两个
                select2 = -1;
                select3 = -1;
                if (position == 1) {
                    ll_popmiddle.setVisibility(View.GONE);
                    line1.setVisibility(View.GONE);
                    String[] strings = getResources().getStringArray(R.array.pop_fujin);
                    popList3.clear();
                    for (int i = 0; i < strings.length; i++) {
                        popList3.add(strings[i]);
                    }
                } else {
                    ll_popmiddle.setVisibility(View.VISIBLE);
                    line1.setVisibility(View.VISIBLE);
                    popList2.clear();
                    popList2.add("不限");
                    for (int i = 0; i < areaList.size(); i++) {
                        String areaName = areaList.get(i).getAreaName();
                        if (!popList2.contains(areaName)) {
                            popList2.add(areaName);
                        }
                    }
                    adapter2.notifyDataSetChanged();
                    popList3.clear();

                }
                mListView.setSelection(0);
                adapter3.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();
            }

        });
        poplv2.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                select2 = position;
                popList3.clear();
                hasMap.clear();
                intList.clear();
                // areaList.get(position).getBusinessName();
                if (poptab == 0) {
                    LayoutParams layoutParams = rlPopWindow.getLayoutParams();
                    height = poplv2.getHeight();
                    if (position == 0) {
                        hasRecord.put(first, 0);
                        hasRecord.put(sec, select2);
                        searchBusiness = true;
                        type = -1;
                        latitude = -1;
                        longitude = -1;
                        areaId = -1;
                        businessListId = -1;
                        tvQuyu.setText("区域");
                        adapter3.notifyDataSetChanged();
                        brokerList.clear();
                        areaList.clear();
                        myAdapter.notifyDataSetChanged();
                        page = 1;
                        initData();

                    } else {
                        select3 = -1;
                        layoutParams.height = height;// 设置同listView2同样的高度
                        popList3.add("不限");
                        switch (position) {
                            case 0:
                                break;
                            case 1:
                                for (int i = 0; i < areaList.size(); i++) {
                                    if (areaList.get(i).getAreaName().equals("思明区")) {
                                        popList3.add(areaList.get(i).getBusinessName());
                                        intList.add(areaList.get(i).getBusinessListId());
                                    }

                                }
                                for (int j = 0; j < intList.size(); j++) {
                                    hasMap.put(j, intList.get(j));
                                }
                                break;
                            case 2:
                                for (int i = 0; i < areaList.size(); i++) {
                                    if (areaList.get(i).getAreaName().equals("海沧区")) {
                                        popList3.add(areaList.get(i).getBusinessName());
                                        intList.add(areaList.get(i).getBusinessListId());
                                    }
                                }
                                for (int j = 0; j < intList.size(); j++) {
                                    hasMap.put(j, intList.get(j));
                                }

                                break;
                            case 3:
                                for (int i = 0; i < areaList.size(); i++) {
                                    if (areaList.get(i).getAreaName().equals("湖里区")) {
                                        popList3.add(areaList.get(i).getBusinessName());
                                        intList.add(areaList.get(i).getBusinessListId());
                                    }
                                }
                                for (int j = 0; j < intList.size(); j++) {
                                    hasMap.put(j, intList.get(j));
                                }
                                break;
                            case 4:
                                for (int i = 0; i < areaList.size(); i++) {
                                    if (areaList.get(i).getAreaName().equals("集美区")) {
                                        popList3.add(areaList.get(i).getBusinessName());
                                        intList.add(areaList.get(i).getBusinessListId());
                                    }
                                }
                                for (int j = 0; j < intList.size(); j++) {
                                    hasMap.put(j, intList.get(j));
                                }
                                break;
                            case 5:
                                for (int i = 0; i < areaList.size(); i++) {
                                    if (areaList.get(i).getAreaName().equals("同安区")) {
                                        popList3.add(areaList.get(i).getBusinessName());
                                        intList.add(areaList.get(i).getBusinessListId());
                                    }
                                }
                                for (int j = 0; j < intList.size(); j++) {
                                    hasMap.put(j, intList.get(j));
                                }
                                break;
                            case 6:
                                for (int i = 0; i < areaList.size(); i++) {
                                    if (areaList.get(i).getAreaName().equals("翔安区")) {
                                        popList3.add(areaList.get(i).getBusinessName());
                                        intList.add(areaList.get(i).getBusinessListId());
                                    }
                                }
                                for (int j = 0; j < intList.size(); j++) {
                                    hasMap.put(j, intList.get(j));
                                }
                                break;
                            default:
                                break;
                        }
                    }

                    adapter3.notifyDataSetChanged();
                    poplv3.setSelection(0);
                    adapter2.notifyDataSetChanged();
                    return;
                }

                adapter2.notifyDataSetChanged();
            }
        });
        poplv3.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                select3 = position;
                hasRecord.put(poptab, position);
                if (poptab == 0) {
                    popIndex = 0;
                    if (ll_popmiddle.getVisibility() == View.VISIBLE) {
                        hasRecord.put(first, 0);
                        hasRecord.put(sec, select2);
                        searchBusiness = true;
                        indexFlag = 1;
                        type = -1;
                        latitude = -1;
                        longitude = -1;
                        if (position == 0) {
                            businessListId = -1;
                            if (select2 == 0) {
                                // TODO
                                // areaId = -1;
                                // tvQuyu.setText("区域");
                                // adapter3.notifyDataSetChanged();
                                // brokerList.clear();
                                // areaList.clear();
                                // initData();
                                return;
                            } else {
                                tvQuyu.setText(popList2.get(select2));
                                tvQuyu.setTextColor(Color.parseColor("#1693FE"));
                                tvQuyu.setCompoundDrawables(null, null, rightDrawableSelect, null);
                                adapter3.notifyDataSetChanged();
                                areaId = hasMapAreaId.get(popList2.get(select2));
                                brokerList.clear();
                                areaList.clear();
                                myAdapter.notifyDataSetChanged();
                                page = 1;
                                initData();
                                return;
                            }
                        } else {
                            businessListId = hasMap.get(position - 1);
                        }
                        // params.put("businessListId", businessListId);
                    } else {
                        hasRecord.put(first, 1);
                        searchBusiness = false;
                        businessListId = -1;
                        areaId = -1;
                        type = position + 1;
                        initLocation(position + 1);
                        if (!locationClient.isStarted()) {
                            locationClient.start();
                            adapter3.notifyDataSetChanged();
                        }
                        tvQuyu.setText(popList3.get(position));
                        tvQuyu.setTextColor(Color.parseColor("#1693FE"));
                        tvQuyu.setCompoundDrawables(null, null, rightDrawableSelect, null);
                        select2 = -1;
                        return;
                    }
                    // TODO
                    // flag = 0;
                    tvQuyu.setText(popList3.get(position));
                    tvQuyu.setTextColor(Color.parseColor("#1693FE"));
                    tvQuyu.setCompoundDrawables(null, null, rightDrawableSelect, null);
                } else if (poptab == 1) {
                    popIndex = 1;
                    houseTypeId = position;
                    if (position == 0) {
                        tvLeixing.setText("类型");
                    } else {
                        tvLeixing.setText(popList3.get(position));
                        tvLeixing.setTextColor(Color.parseColor("#1693FE"));
                        tvLeixing.setCompoundDrawables(null, null, rightDrawableSelect, null);
                    }
                    // params.put("houseTypeId", houseTypeId);
                } else if (poptab == 2) {
                    popIndex = 2;
                    isFirstPaiXu = !isFirstPaiXu;
                    // if (isFirstPaiXu) {
                    // } else {
                    // }
                    if (position == 0 || position == 1 || position == 3 || position == 5) {
                        orderBy = 0;
                    } else {
                        orderBy = 1;
                    }
                    // orderByName = popList3.get(position);
                    if (position == 0) {
                        orderByName = "积分排序";
                    } else if (position == 1 || position == 2) {
                        orderByName = "房源数";
                    } else if (position == 3 || position == 4) {
                        orderByName = "成交量";
                    } else if (position == 5 || position == 6) {
                        orderByName = "好评率";
                    }
                    params.put("orderByName", orderByName);
                    if (position == 0) {
                        orderBy = 0;
                    }
                    // params.put("orderBy", orderBy);
                    tvPaixu.setTextColor(Color.parseColor("#1693FE"));
                    tvPaixu.setCompoundDrawables(null, null, rightDrawableSelect, null);
                    tvPaixu.setText(popList3.get(position));
                }

                page = 1;
                brokerList.clear();
                areaList.clear();
                myAdapter.notifyDataSetChanged();
                initData();
                mListView.setSelection(1);
                adapter3.notifyDataSetChanged();
            }
        });

    }

    private void initLocation(final int type) {
        locationClient = new LocationClient(getApplicationContext());
        // 设置定位条件
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 是否打开GPS
        option.setCoorType("bd09ll"); // 设置返回值的坐标类型。
        option.setPriority(LocationClientOption.NetWorkFirst); // 设置定位优先级
        // option.setProdName("LocationDemo"); //
        // 设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
        // option.setScanSpan(UPDATE_TIME);// 设置定时定位的时间间隔。单位毫秒
        option.setScanSpan(1);
        locationClient.setLocOption(option);

        // 注册位置监听器
        locationClient.registerLocationListener(new BDLocationListener() {

            @Override
            public void onReceiveLocation(BDLocation location) {
                if (location == null) {
                    return;
                }
                brokerList.clear();
                areaList.clear();
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                // params.put("latitude", latitude);
                // params.put("longitude", longitude);
                // params.put("type", type);
                page = 1;
                mListView.setSelection(1);
                myAdapter.notifyDataSetChanged();
                initData();

            }
        });

    }

    class PopAdapter1 extends BaseAdapter {

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate = getLayoutInflater().inflate(R.layout.searchkey_item, null);
            TextView tv = (TextView) inflate.findViewById(R.id.tvSearchKey);
            if (position == 0) {
                tv.setText("区域");
            } else {
                tv.setText("附近");
            }
            if (select1 == position) {
                tv.setTextColor(Color.parseColor("#1693FE"));
            }
            return inflate;
        }

    }

    class PopAdapter2 extends BaseAdapter {

        @Override
        public int getCount() {
            return popList2.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate = getLayoutInflater().inflate(R.layout.searchkey_item, null);
            TextView tv = (TextView) inflate.findViewById(R.id.tvSearchKey);
            tv.setText(popList2.get(position));
            if (select2 == position) {
                tv.setTextColor(Color.parseColor("#1693FE"));
            }
            return inflate;
        }
    }

    class PopAdapter3 extends BaseAdapter {

        @Override
        public int getCount() {
            return popList3.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate = getLayoutInflater().inflate(R.layout.searchkey_item, null);
            TextView tv = (TextView) inflate.findViewById(R.id.tvSearchKey);
            tv.setText(popList3.get(position));
            if (select3 == position) {
                tv.setTextColor(Color.parseColor("#1693FE"));
            }
            return inflate;
        }
    }

    public void selectParams() {
        params = new RequestParams();
        if (params != null) {
            params.put("pageNum", page);
            if (businessListId != -1) {
                params.put("businessListId", businessListId);
            }
            if (latitude != -1) {
                params.put("latitude", latitude);
            }
            if (longitude != -1) {
                params.put("longitude", longitude);
            }
            if (type != -1) {
                params.put("type", type);
            }
            if (houseTypeId != -1) {
                params.put("houseTypeId", houseTypeId);
            }
            if (!TextUtils.isEmpty(orderByName)) {
                params.put("orderByName", orderByName);
            }
            if (orderBy != -1) {
                params.put("orderBy", orderBy);
            }
            if (!TextUtils.isEmpty(likeSelect)) {
                params.put("likeSelect", likeSelect);
            }
            if (!TextUtils.isEmpty(houseTypeName)) {
                params.put("houseTypeName", houseTypeName);
            }
            if (areaId != -1) {
                params.put("areaId", areaId);
            }
        }
    }

    public void cancleParams() {
        searchBusiness = true;
        hasRecord.put(0, -1);
        hasRecord.put(1, -1);
        hasRecord.put(2, -1);
        hasRecord.put(sec, -1);
        hasRecord.put(first, -1);
        areaId = -1;
        businessListId = -1;
        latitude = -1;
        longitude = -1;
        type = -1;
        houseTypeId = -1;
        orderBy = -1;
        orderByName = null;
        houseTypeName = null;
        page = 1;
        likeSelect = null;
        setNormalState();
    }

    public void setNormalState() {
        // 默认字体的颜色和图片
        tvQuyu.setTextColor(Color.parseColor("#919191"));
        tvLeixing.setTextColor(Color.parseColor("#919191"));
        tvPaixu.setTextColor(Color.parseColor("#919191"));

        tvQuyu.setCompoundDrawables(null, null, rightDrawableNormal, null);
        tvLeixing.setCompoundDrawables(null, null, rightDrawableNormal, null);
        tvPaixu.setCompoundDrawables(null, null, rightDrawableNormal, null);
        tvQuyu.setText("区域");
        tvLeixing.setText("类型");
        tvPaixu.setText("排序");
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
        // params = new RequestParams();
        page = 1;
        isRefresh = true;
        initData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (locationClient != null) {
            locationClient.stop();
        }

    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
        page++;
        isRefresh = false;
        initData();
    }

    @Override
    public void onLoadData() {
        initData();
    }

}
