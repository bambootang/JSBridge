package bamboo.com.jsbridge.sample.config;

/**
 * Created by tangshuai on 16/11/16.
 */
public class ConfigUI {

    public int fullscreen = 0;

    //    "share","like","favorite"
    public NaviButton[] naviButtons;

    public class NaviButton {
        //喜欢、收藏、分享、入口名称等对应的类型。
        String type = "";
        //对应类型对应的标题
        String title = "";
        //对应的按钮指定的执行方法
        String callback = "";
    }
}
