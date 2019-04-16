package com.jc.efire.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.jc.basecore.mvp.BaseActivity;
import com.jc.basecore.util.KeyboardUtil;
import com.jc.efire.R;

public class FragmentShellActivity extends BaseActivity {
    public static final String EXTRA_FRAGMENTNAME = "fragname";
    public static final String EXTRA_FRAGMENTARGS = "fragargs";

    private static FragmentShellActivity instance=null;
    private static String lastFragment;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_fragment_shell;
    }

    @Override
    protected void addSections() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_shell);
        Intent launchIntent = getIntent();
        final String fragclassname = launchIntent.getStringExtra(EXTRA_FRAGMENTNAME);
        final Bundle fragargs = launchIntent.getBundleExtra(EXTRA_FRAGMENTARGS);
        try {
            Class<?> fragmentClass = getClassLoader().loadClass(fragclassname);
            Fragment mBaseFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_shell_content);
            if (mBaseFragment == null) {
                mBaseFragment = (Fragment) fragmentClass.newInstance();
                mBaseFragment.setArguments(fragargs);
                FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
                tr.add(R.id.fragment_shell_content, mBaseFragment);
                tr.commit();
            }
        } catch (Exception e) {
            Log.e(FragmentShellActivity.class.getName(), "", e);
            finish();
        }
    }

    public void pushFragmentToBackStack(Fragment f) {
        pushFragmentToBackStack(f, true);
    }

    public void pushFragmentToBackStack(Fragment f, boolean isAddToBackStack) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.fragment_shell_content, f, f.getClass().getName());
        if (isAddToBackStack) tr.addToBackStack(f.getClass().getName());
        tr.commitAllowingStateLoss();
    }

    public static Intent createIntent(Context context,Class<?> fragmentClass, Bundle fragmentArgs){
        if (isWrongClick(fragmentClass.getName()) || context == null) return null;
        Intent retval = new Intent(context, FragmentShellActivity.class);
        retval.putExtra(EXTRA_FRAGMENTNAME, fragmentClass.getName());
        retval.putExtra(EXTRA_FRAGMENTARGS, fragmentArgs);
        return retval;
    }

    protected static boolean isWrongClick(String nowFragment) {
        if (nowFragment.equals(lastFragment)) {
            return !KeyboardUtil.isOverWhenPressAgain(600);
        }
        lastFragment = nowFragment;
        return false;
    }
}
