## 一.描述

本架构在传统的MVP模式上引入Section(切片)概念, 进一步解耦Activity和Fragment, 极大的降低了Activity和Fragment的冗余. 在传统MVP模式中, 通常代码是这样的:

```java
public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }
    
    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ...        
        mPresenter.getBannerList();	//获取Banner列表
        ...
        mPresent.getHotBrand();		//获取热门品牌
        ...
    }
    
    public void onBannerSucc(List<Banner> list) {
        //更新Banner相关界面
    }
    
    public void onHotBrandSucc(List<Brand> list) {
        //更新热门品牌相关界面
    }
    
    public void onFail(String errorMsg) {
        //Toast
    }
}

```

而在本架构中, 您的代码将是这样:

```java
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("首页");
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void addSections() {
        addSection(new BannerSection(this));   //Banner切片
        addSection(new HotBrandSection(this)); //热门品牌切片
        ...
        addSection(new XXXSection(this));      //xxx切片
    }
}

```

更清爽, 更解耦, 专治代码强迫症<轻砖\*_\*>

## 二.技术栈

Rxjava+RxAndroid+Retrofit+OkHttp+Rxbus+Rxpermissions+Rxlifecycle+Rxbinding+Glide+Logger+Multidex

## 三.架构原理

![MVPS](https://github.com/Sprout4188/MVPS/blob/master/images/MVPS.png)

a.UI层持有Section池的引用, 将业务切片委托给相应Section去处理

b.Section持有Presenter的引用, 将具体的业务实现委托给Presenter处理

c.Presenter将处理结果回调给View, 因Section实现了View接口, 故结果实质是回调给了Section

d.Section拿到回调结果后, 去更新UI或者返回给组件调用方

![相关生命周期先后调用关系](https://github.com/Sprout4188/MVPS/blob/master/images/Lifecycle.png)

## 四.使用示例

 ### 1.mvps使用

a.Activity或Fragment中添加Section

```java
public class LoginActivity extends BaseActivity {
    @Override
    protected void addSections() {
        addSection(new LoginSection(this));
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_login;
    }
}
```

b.Section中绑定Presenter, 实现View

```java
public class LoginSection extends BaseSection<LoginPresenter> implements LoginView {

    private String ccCallId;
    private View btNet;

    public LoginSection(Object source) {
        super(source);
    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = ((BaseActivity) getContext()).getIntent();
        ccCallId = intent.getStringExtra("ccCallId");
    }

    @Override
    protected void initViews() {
        btNet = findView(R.id.bt_test);
    }

    @Override
    protected void initEvents() {
        btNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
                Map<String, Object> map = new HashMap<>();
                map.put("memberNo", "12345678");
                mPresenter.login(map);
            }
        });
    }

    @Override
    public void onLoginSucc(UserInfoEntity userInfoEntity) {
        hideLoading();
        //将本组件处理的结果返回给调用方
	CC.sendCCResult(ccCallId, CCResult.success("loginSucc", userInfoEntity));
    }

    @Override
    public void onLoginFail(String error) {
        hideLoading();
	CC.sendCCResult(ccCallId, CCResult.error("loginFail", error));
    }
}
```

c.Presenter中处理业务逻辑(如请求接口), 并将处理结果回调给View

```java
public class LoginPresenter extends BasePresenter<LoginView> {
    public LoginPresenter(LoginView view) {
        super(view);
    }

    public void login(Map<String, Object> map) {
        addDisposable(RetrofitUtil.create(User.class).captchaLogin(map), new ICallBack<UserInfoEntity>() {
            @Override
            public void onSuccess(UserInfoEntity entity) {
                if (isViewAttach()) mView.onLoginSucc(entity);
            }

            @Override
            public void onFail(String errorMsg) {
                if (isViewAttach()) mView.onLoginFail(errorMsg);
            }
        });
    }
}
```

d.在View中定义回调接口

```java
public interface LoginView extends IController.IView{
    
    void onLoginSucc(UserInfoEntity userInfoEntity);

    void onLoginFail(String error);
}
```
