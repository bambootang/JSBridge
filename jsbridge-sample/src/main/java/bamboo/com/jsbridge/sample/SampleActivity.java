package bamboo.com.jsbridge.sample;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bamboo.jsbridge.JSBridgeClient;
import com.bamboo.jsbridge.JsCallback;
import com.bamboo.jsbridge.TaskExecutor;

import bamboo.com.jsbridge.R;
import bamboo.com.jsbridge.sample.imp.ActivityWebLogicApi;


public class SampleActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
        JSBridgeClient.registerJSBridge(webView, new BridgeTest(), "__JSBridgeNative");
        JSBridgeClient.registerJSBridge(webView, new BridgeTest2(this, webView), "__JSBridgeNative2");
        webView.loadUrl("file:///android_asset/test1.0.html");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    public void onClick(View view) {
        JSBridgeClient.invokeJsMethod(webView, "showConsole", "text", "text01");
    }

    public class BridgeTest2 extends ActivityWebLogicApi {

        public BridgeTest2(FragmentActivity context, WebView webView) {
            super(context, webView);
        }

        @JavascriptInterface
        public void showToast() {
            Toast.makeText(SampleActivity.this, "BridgeTest2", Toast.LENGTH_LONG).show();
        }
    }

    public class BridgeTest {

        @JavascriptInterface
        public void showToast(final String text, final JsCallback jsCallback) {
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        public void run() {
                            try {
                                jsCallback.apply(text + text);
                            } catch (JsCallback.JsCallbackException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }.start();
        }

        @JavascriptInterface
        public void showToast(String text) {
            Toast.makeText(SampleActivity.this, text, Toast.LENGTH_SHORT).show();
        }
    }

}
