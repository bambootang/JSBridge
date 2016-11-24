package com.bamboo.jsbridge;

import android.webkit.WebView;

/**
 * Created by tangshuai on 16/11/23.
 */
public class JSBridgeClient {


    /**
     * @param webView     webview
     * @param webLogicApi js接口对象，会将接口对象的所有有JavaScriptInterface注解的方法注入到js中
     * @param bridgeName  桥名称，也就是js里面调用的时候的对象名称
     */
    public static void registerJSBridge(WebView webView, Object webLogicApi, String bridgeName) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JSBridge(webView, webLogicApi, bridgeName), bridgeName);
    }


    /**
     * 本地调用js方法
     *
     * @param webView    webview
     * @param methodName 方法名
     * @param args       方法需要的参数
     */
    public static void invokeJsMethod(WebView webView, String methodName, Object... args) {
        StringBuffer sb = new StringBuffer();
        for (Object s : args) {
            sb.append("\"" + s + "\"");
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        webView.loadUrl("javascript:" + methodName + "(" + sb.toString() + ");");
    }
}
