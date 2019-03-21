package com.songcw.basecore.ui;

import android.os.Vibrator;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.github.florent37.viewanimator.ViewAnimator;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.songcw.basecore.R;
import com.songcw.basecore.grobal.Config;
import com.songcw.basecore.mvp.BaseSection;
import com.songcw.basecore.mvp.IController;
import com.songcw.basecore.sp.PermanentInfoSP;
import com.songcw.basecore.widget.nicetoast.Toasty;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * Created by Sprout on 2018/8/27
 */
public class ConfigSection extends BaseSection {

    private RadioGroup rgConfig;
    private EditText etConfigPwd;

    private String baseUrl = "";
    private final String VERIFY = "sc";
    private Disposable disposable;

    public ConfigSection(Object source) {
        super(source);
    }

    @Override
    public IController.IPresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        rgConfig = (RadioGroup) getDecorView().findViewById(R.id.rg_config);
        etConfigPwd = (EditText) getDecorView().findViewById(R.id.et_config_pwd);
    }

    @Override
    protected void init() {
        registerRadioGroupListener();
        registerPwdListener();

    }

    private void registerRadioGroupListener() {
        rgConfig.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rg_dev) { //开发环境
                    baseUrl = Config.Http.Url_Dev;
                } else if (checkedId == R.id.rg_sit) { //测试环境
                    baseUrl = Config.Http.Url_Sit;
                } else if (checkedId == R.id.rg_pro) { //生产环境
                    baseUrl = Config.Http.Url_Pro;
                }
            }
        });
    }

    private void registerPwdListener() {
        disposable = RxTextView.textChanges(etConfigPwd)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence sequence) throws Exception {
                        if (verify(sequence)) saveUrl();
                        else warning(sequence);
                    }
                });
    }

    private boolean verify(CharSequence sequence) {
        return VERIFY.equals(sequence.toString()) && !TextUtils.isEmpty(baseUrl);
    }

    /**
     * 保存BaseURL
     */
    private void saveUrl() {
        PermanentInfoSP.baseUrl.setValue(baseUrl);
        finish();
    }

    /**
     * 提醒
     */
    private void warning(CharSequence sequence) {
        if (TextUtils.isEmpty(baseUrl)) {
            Toasty.info(getContext(), "请选择服务器环境");
        }
        if (sequence != null && sequence.length() == 8) {
            ViewAnimator.animate(etConfigPwd).duration(370).shake().start();
            Vibrator vibrator = (Vibrator) getContext().getSystemService(VIBRATOR_SERVICE);
            long[] patter = {0, 100, 80, 80, 50, 60};
            vibrator.vibrate(patter, -1);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) disposable.dispose();
    }
}
