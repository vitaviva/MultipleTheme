# MultipleTheme
支持Android动态切换夜间模式等多主题

<img src="/screenshot/01.gif">



## 使用方法

1. attrs.xml
```xml
<declare-styleable name="MultipleTheme">
        <attr name="color3" format="reference|color"/>
</declare-styleable>
```

2. colors.xml
```xml
<resources>
    <color name="color3">#666666</color>
    <color name="color3_night">#999999</color>
</resources>
```

3. styles.xml
```xml
<resources>
    <style name="DayModeTheme">
        <item name="color3">@color/color3</item>
    </style>
    <style name="NightModeTheme">
        <item name="color3">@color/color3_night</item>
    </style>
</resources>

```

4.layout.xml
```xml
    <com.vitaviva.multipletheme.widget.ThemedTextView
        android:id="@+id/tvName"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:ellipsize="end"
        android:singleLine="true"
        android:textSize="18sp"
        android:textColor="?attr/color3"
        tools:text="@string/app_name" />
```

5.Application.java
```java
public class MultiThemedApplication extends Application {
    public static final String MULTI_THEME_DAYNIGHT = "daynight";
    public static final String MULTI_THMEM_DAYMODE = "day";
    public static final String MULTI_THMEME_NIGHTMODE = "night";

    @Override
    public void onCreate() {
        super.onCreate();
        ThemeManager.init(this);
        ThemeManager.registerThemeRes(MULTI_THEME_DAYNIGHT, MULTI_THMEM_DAYMODE, R.style.DayModeTheme, true);
        ThemeManager.registerThemeRes(MULTI_THEME_DAYNIGHT, MULTI_THMEME_NIGHTMODE, R.style.NightModeTheme, false);
    }
}
```

6.Activity.java
```java
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.DayModeTheme);
        ThemeManager.registerMultipleTheme(this);
        setContentView(R.layout.activity_main);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ThemeManager.unregisterMultipleTheme(this);
    }
```

7.当需要切换主题时
```java
   ThemeManager.changeTheme(
                        MultiThemedApplication.MULTI_THEME_DAYNIGHT,
                        MultiThemedApplication.MULTI_THMEM_NIGHTMODE);
```


## Gradle Dependency

compile 'com.vitaviva:multip:1.0.0'
