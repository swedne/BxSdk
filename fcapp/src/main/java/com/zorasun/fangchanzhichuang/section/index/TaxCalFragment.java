package com.zorasun.fangchanzhichuang.section.index;

import com.zorasun.fangchanzhichuang.R;
import com.zorasun.fangchanzhichuang.general.base.BaseFragment;
import com.zorasun.fangchanzhichuang.general.helper.log.AppLog;
import com.zorasun.fangchanzhichuang.general.util.ToastUtil;
import com.zorasun.fangchanzhichuang.general.widget.MyRadioGroup;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * 税费计算
 */
public class TaxCalFragment extends BaseFragment implements OnClickListener {
    private View view;
    private EditText et_tax_total;
    private EditText et_tax_ori;
    private EditText et_tax_square;
    private TextView tv_tax_year;
    private TextView tv_tax_option;
    private TextView tv_tax_type;
    private Button btn_tax_startcal;
    private LinearLayout linear_tax_isonly;
    private TextView tv_tax_category;
    // private RadioButton rbtn_isonly_yes;
    // private RadioButton rbtn_isonly_no;
    private RadioGroup rg_isonly;
    private RadioGroup rg_loan;
    // private RadioButton rbtn_loan_yes;
    // private RadioButton rbtn_loan_no;

    private int type = -1;
    // 是否买方家庭唯一住房
    private boolean isOnly = true;
    // 是否贷款
    private boolean isLoan = true;

    private String houseType = "商品房";
    private String year = "年限<2";
    private String option = "首套";

    public static final TaxCalFragment newInstance(int pid, String message) {
        TaxCalFragment fragment = new TaxCalFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pid", pid);
        bundle.putString("message", message);
        fragment.setArguments(bundle);
        return fragment;
    }


    TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String temp = s.toString();
            int posDot = temp.indexOf(".");
            if (posDot <= 0)
                return;
            if (temp.length() - posDot - 1 > 2) {
                s.delete(posDot + 3, posDot + 4);
            }
        }
    };

    @SuppressLint("ValidFragment")
    public TaxCalFragment(int type) {
        // 0:个人住宅1：非个人住宅
        this.type = type;
    }

    public TaxCalFragment() {
        // 0:个人住宅1：非个人住宅
    }

    @Override
    public void onResume() {
        AppLog.redLog("233", "onResume" + type);
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_tax_cal_update, container, false);
            initUI();
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void clearData() {
        et_tax_total.setText("");
        et_tax_ori.setText("");
        et_tax_square.setText("");
    }

    private void initUI() {
        // 房屋总价
        et_tax_total = (EditText) view.findViewById(R.id.et_tax_total);
        et_tax_total.addTextChangedListener(textWatcher);
        // 房屋原价
        et_tax_ori = (EditText) view.findViewById(R.id.et_tax_ori);
        et_tax_ori.addTextChangedListener(textWatcher);
        // 房屋面积
        et_tax_square = (EditText) view.findViewById(R.id.et_tax_square);
        et_tax_square.addTextChangedListener(textWatcher);
        // 房产证年限
        tv_tax_year = (TextView) view.findViewById(R.id.tv_tax_year);
        // 买方家庭购房选项
        tv_tax_option = (TextView) view.findViewById(R.id.tv_tax_option);
        // 住宅类型
        tv_tax_type = (TextView) view.findViewById(R.id.tv_tax_type);
        // 开始计算
        btn_tax_startcal = (Button) view.findViewById(R.id.btn_tax_startcal);
        btn_tax_startcal.setOnClickListener(this);

        // view.findViewById(R.id.linear_tax_year).setOnClickListener(this);
        // view.findViewById(R.id.linear_tax_option).setOnClickListener(this);
        // view.findViewById(R.id.linear_tax_type).setOnClickListener(this);

        RadioButton rbtn_commercial = (RadioButton) view.findViewById(R.id.rbtn_commercial);
        RadioButton rbtn_private = (RadioButton) view.findViewById(R.id.rbtn_private);
        RadioButton rbtn_remove = (RadioButton) view.findViewById(R.id.rbtn_remove);
        RadioButton rbtn_public = (RadioButton) view.findViewById(R.id.rbtn_public);

        if (type == 1) {
            houseType = "写字楼";
            rbtn_commercial.setText("写字楼");
            rbtn_private.setText("商铺");
            rbtn_remove.setText("车库    ");
            rbtn_public.setText("车位");
        } else if (type == 0) {
            houseType = "商品房";
            // rbtn_commercial.setText("商品房");
            // rbtn_private.setText("私宅");
            // rbtn_remove.setText("拆迁安置房");
            // rbtn_public.setText("公房");
        }

        linear_tax_isonly = (LinearLayout) view.findViewById(R.id.linear_tax_isonly);
        tv_tax_category = (TextView) view.findViewById(R.id.tv_tax_category);

        MyRadioGroup rg_type = (MyRadioGroup) view.findViewById(R.id.rg_type);
        rg_type.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                if (type == 0) {
                    switch (checkedId) {
                        case R.id.rbtn_commercial:
                            houseType = "商品房";
                            break;
                        case R.id.rbtn_private:
                            houseType = "私宅";
                            break;
                        case R.id.rbtn_remove:
                            houseType = "拆迁安置房";
                            break;
                        case R.id.rbtn_public:
                            houseType = "公房";
                            break;
                    }
                    // ToastUtil.toastShow(getActivity(), houseType);
                } else if (type == 1) {
                    switch (checkedId) {
                        case R.id.rbtn_commercial:
                            houseType = "写字楼";
                            break;
                        case R.id.rbtn_private:
                            houseType = "商铺";
                            break;
                        case R.id.rbtn_remove:
                            houseType = "车库";
                            break;
                        case R.id.rbtn_public:
                            houseType = "车位";
                            break;
                    }
                    // ToastUtil.toastShow(getActivity(), houseType);
                }
            }
        });

        MyRadioGroup rg_year = (MyRadioGroup) view.findViewById(R.id.rg_year);
        rg_year.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_less2:
                        year = "年限<2";
                        break;
                    case R.id.rbtn_2_3:
                        year = "2≤年限<5";
                        break;
                    // case R.id.rbtn_3_4:
                    // year = "年限≥5";
                    // break;
                    case R.id.rbtn_longer4:
                        year = "年限≥5";
                        break;
                }
                // ToastUtil.toastShow(getActivity(), year);
            }
        });

        MyRadioGroup rg_count = (MyRadioGroup) view.findViewById(R.id.rg_count);
        rg_count.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_first:
                        option = "首套";
                        break;
                    case R.id.rbtn_second:
                        option = "第二套";
                        break;
                    case R.id.rbtn_third:
                        option = "第三套以上";
                        break;
                }
                // ToastUtil.toastShow(getActivity(), option);
            }
        });

        // 是否卖方家庭唯一住房
        rg_isonly = (RadioGroup) view.findViewById(R.id.rg_isonly);
        rg_isonly.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_isonly_yes:
                        isOnly = true;
                        break;
                    case R.id.rbtn_isonly_no:
                        isOnly = false;
                        break;
                    default:
                        break;
                }
            }
        });

        // 是否贷款
        rg_loan = (RadioGroup) view.findViewById(R.id.rg_loan);
        rg_loan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_loan_yes:
                        isLoan = true;
                        break;
                    case R.id.rbtn_loan_no:
                        isLoan = false;
                        break;
                    default:
                        break;
                }
            }
        });

        if (type == 1) {
            view.findViewById(R.id.line_tax_1).setVisibility(View.GONE);
            view.findViewById(R.id.line_tax_2).setVisibility(View.GONE);
            view.findViewById(R.id.linear_tax_option).setVisibility(View.GONE);
            linear_tax_isonly.setVisibility(View.GONE);
            tv_tax_category.setText("非住宅类型");
            tv_tax_type.setText("写字楼");
        } else if (type == 0) {
            tv_tax_category.setText("住宅类型");
            tv_tax_type.setText("商品房");
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.linear_tax_year:// 选择房产证年限
                intent = new Intent(getActivity(), AdjustActivity.class);
                intent.putExtra("type", 0);
                startActivityForResult(intent, 0);
                break;
            case R.id.linear_tax_option:// 选择买方家庭购房选项
                intent = new Intent(getActivity(), AdjustActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, 1);
                break;
            case R.id.linear_tax_type:// 选择住宅类型
                if (type == 0) {
                    // 个人住宅
                    intent = new Intent(getActivity(), AdjustActivity.class);
                    intent.putExtra("type", 2);
                    startActivityForResult(intent, 2);
                } else if (type == 1) {
                    // 非个人住宅
                    intent = new Intent(getActivity(), AdjustActivity.class);
                    intent.putExtra("type", 3);
                    startActivityForResult(intent, 3);
                }
                break;
            case R.id.btn_tax_startcal:// 开始计算
                calAndShow();
                break;
            default:
                break;
        }
    }

    private void calAndShow() {
        double total = 0.0;
        double ori = 0.0;
        double square = 0.0;

        if (TextUtils.isEmpty(et_tax_total.getText().toString().trim())) {
            ToastUtil.toastShow(getActivity(), "请输入成交价");
            return;
        } else {
            total = Double.parseDouble(et_tax_total.getText().toString().trim()) * 10000;
        }
        if (TextUtils.isEmpty(et_tax_ori.getText().toString().trim())) {
            ToastUtil.toastShow(getActivity(), "请输入原购价");
            return;
        } else {
            ori = Double.parseDouble(et_tax_ori.getText().toString().trim()) * 10000;
        }
        if (TextUtils.isEmpty(et_tax_square.getText().toString().trim())) {
            ToastUtil.toastShow(getActivity(), "请输入建筑面积");
            return;
        } else {
            square = Double.parseDouble(et_tax_square.getText().toString().trim());
        }

        // year = tv_tax_year.getText().toString();
        // houseType = tv_tax_type.getText().toString();

        // option = tv_tax_option.getText().toString();

        // 契税
        double deedTax = 0.0;
        // 个人所得税
        double indivTax = 0.0;
        // 增值税
        double valueTax = 0.0;
        // 土地出让金
        double landMoney = 0.0;
        // 交易手续费
        double charge = 0.0;
        // 权属登记费
        double regisFee = 0.0;
        // 评估费
        double assessFee = 0.0;

        if (type == 1) {
            // 非个人住宅
            deedTax = total * 0.03;
        } else {
            // 个人住宅
            // 契税 面积小于90
            if (square <= 90.0) {
                switch (option) {
                    case "首套":
                        deedTax = total * 0.01;
                        break;
                    case "第二套":
                        deedTax = total * 0.01;
                        break;
                    case "第三套以上":
                        deedTax = total * 0.03;
                        break;
                }
            } else {
                switch (option) {
                    case "首套":
                        deedTax = total * 0.015;
                        break;
                    case "第二套":
                        deedTax = total * 0.02;
                        break;
                    case "第三套以上":
                        deedTax = total * 0.03;
                        break;
                    default:
                        break;
                }
            }
        }

        // 个人所得税
        // 总价大于原价才征收
        if (total > ori) {
            if (type == 1) {
                // 非个人住宅
                indivTax = total * 0.015;
            } else {
                // 个人住宅
                if (isOnly) {
                    // 卖方是以家庭为单位不满五年的唯一住宅
                    switch (year) {
                        case "年限<2":
                        case "2≤年限<5":
                            indivTax = total * 0.015;
                            break;
                        case "年限≥5":
                            // 满五年免征
                            break;
                    }
                } else {
                    // 不是唯一住宅，则不论年限都要征收
                    indivTax = total * 0.015;
                }
            }
        }

        // 个人住宅和非个人住宅一样
        // 增值税
        if (type == 1) {
            // 个人非住宅
            // 有增值才征收
            // 公式：增值部分 * 5.6%
            if (total > ori) {
                valueTax = (total - ori) * 0.056;
            }
        } else {
            // 个人住宅
            if ("年限<2".equals(year)) {
                // valueTax = total / (1 + 0.05) * 0.05 + total * 0.006;
                valueTax = total * 0.056;
            }
        }

        // 土地出让金
        if (type == 1) {
            // 非个人住宅
            landMoney = total * 0.05;
        } else {
            // 个人住宅
            switch (houseType) {
                case "商品房":
                    // 免征
                    break;
                case "私宅":
                    landMoney = total * 0.08;
                    break;
                case "拆迁安置房":
                    landMoney = total * 0.04;
                    break;
                case "公房":
                    landMoney = total * 0.01;
                    break;
            }
        }

        // 交易手续费
        if (type == 1) {
            // 非个人住宅
            switch (houseType) {
                case "写字楼":
                case "商铺":
                case "车库":
                    charge = square * 13.5;
                    break;
                case "车位":
                    charge = 300.0;
                    break;
            }
        } else {
            // 个人住宅
            charge = square * 4;
        }

        // 权属变更登记费
        if (type == 1) {
            // 非个人住宅
            switch (houseType) {
                case "写字楼":
                case "商铺":
                case "车库":
                    regisFee = 1250.0;
                    break;
                case "车位":
                    regisFee = 800.0;
                    break;
            }
        } else {
            // 个人住宅
            regisFee = 200.0;
        }

        // 评估费
        // 有贷款才有评估费
        if (isLoan) {

            if (total * 0.8 < 1000000.0) {
                // 100万以内
                assessFee = (total * 0.8) * 0.004 * 0.6;
            } else {
                // 100万到1000万或者1000万以上
                assessFee += 1000000 * 0.004 * 0.6 + (total * 0.8 - 1000000) * 0.002 * 0.6;
            }
        }

        // deedTax + indivTax + valueTax + landMoney + charge + regisFee +
        // assessFee;

        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putDouble("deedTax", deedTax);
        bundle.putDouble("indivTax", indivTax);
        bundle.putDouble("valueTax", valueTax);
        bundle.putDouble("landMoney", landMoney);
        bundle.putDouble("charge", charge);
        bundle.putDouble("regisFee", regisFee);
        bundle.putDouble("assessFee", assessFee);

        Intent intent = new Intent(getActivity(), TaxResultActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {

                String string = data.getStringExtra("adjust");

                switch (requestCode) {
                    case 0:
                        tv_tax_year.setText(string);
                        break;
                    case 1:
                        tv_tax_option.setText(string);
                        break;
                    case 2:
                    case 3:
                        tv_tax_type.setText(string);
                        break;

                    default:
                        break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initView() {
        // TODO Auto-generated method stub

    }

}