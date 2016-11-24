package com.bamboo.jsbridge;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;


/**
 * Created by tangshuai on 16/11/15.
 */
public class JSBridge {

    private JsCallJava jsCallJava;
    private WebView webView;


    public JsCallJava getJsCallJava() {
        return jsCallJava;
    }

    protected JSBridge(WebView webView, Object injectObject, String bridgeName) {
        this.webView = webView;
        this.jsCallJava = new JsCallJava(bridgeName, injectObject);
    }

    /**
     * 所有js调用本地方法的通道
     *
     * @param apiRequest 请求的方法对应的json映射
     * @return 返回执行结果
     */
    @JavascriptInterface
    public String invokeJavaMethod(String apiRequest) {
        return jsCallJava.call(webView, apiRequest);
    }

    /**
     * 注入本地js桥。
     */
    @JavascriptInterface
    public void linkBridge() {
        TaskExecutor.runTaskOnUiThread(new Runnable() {
            public void run() {
                webView.loadUrl(getJsCallJava().getPreloadInterfaceJS());
            }
        });
    }
}
