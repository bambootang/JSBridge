package bamboo.com.jsbridge.sample.imp;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.bamboo.jsbridge.JsCallback;
import com.google.gson.Gson;
import bamboo.com.jsbridge.sample.config.JSConfig;

import org.json.JSONObject;


/**
 * Created by tangshuai on 16/11/11.
 */
public abstract class BaseWebLogicApi {

    private static final String TAG = "BaseWebLogicApi";
    private WebView webView;
    private JSConfig config = new JSConfig();

    public WebView getWebView() {
        return webView;
    }

    public JSConfig getConfig() {
        return config;
    }

    public BaseWebLogicApi(WebView webView) {
        this.webView = webView;
    }

    @JavascriptInterface
    public void setConfig(JSONObject pageConfig) {
        Log.d(TAG, pageConfig.toString());
        Gson gson = new Gson();
        try {
            config = gson.fromJson(pageConfig.toString(), JSConfig.class);
            Log.d(TAG, new Gson().toJson(config));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public String getSDKVersion() {
        return "1.0";
    }

    @JavascriptInterface
    public String getShellVersion() {
        return "1.1";
    }

    @JavascriptInterface
    public String getShellPlatform() {
        return "Android";
    }

    @JavascriptInterface
    public void requestApi(String requestName, String requestBody, JsCallback okCallback, JsCallback errorCallback) {
    }

}
