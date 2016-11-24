package bamboo.com.jsbridge.sample;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.bamboo.jsbridge.JSBridgeClient;

import bamboo.com.jsbridge.R;
import bamboo.com.jsbridge.sample.imp.ActivityWebLogicApi;



public class SampleActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);
        JSBridgeClient.registerJSBridge(webView, new BridgeTest(this,webView), "__JSBridgeNative");
        JSBridgeClient.registerJSBridge(webView, new BridgeTest2(), "__JSBridgeNative2");
        webView.loadUrl("file:///android_asset/test1.0.html");
    }

    public void onClick(View view) {
        JSBridgeClient.invokeJsMethod(webView, "showConsole", "text","text01");
    }

    public class BridgeTest extends ActivityWebLogicApi{

        public BridgeTest(FragmentActivity context, WebView webView) {
            super(context, webView);
        }

        @JavascriptInterface
        public void showToast(String text, int time) {
            Toast.makeText(SampleActivity.this, text, time).show();
        }
    }

    public class BridgeTest2 {

        @JavascriptInterface
        public void showToast() {
            Toast.makeText(SampleActivity.this, "BridgeTest2", Toast.LENGTH_SHORT).show();
        }
    }
}
