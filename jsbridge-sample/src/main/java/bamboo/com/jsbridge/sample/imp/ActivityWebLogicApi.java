package bamboo.com.jsbridge.sample.imp;

import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.bamboo.jsbridge.JsCallback;
import com.bamboo.jsbridge.TaskExecutor;


/**
 * Created by tangshuai on 16/11/11.
 */
public abstract class ActivityWebLogicApi extends BaseWebLogicApi {

    FragmentActivity context;

    public ActivityWebLogicApi(FragmentActivity context, WebView webView) {
        super(webView);
        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String message, int time) {
        Toast.makeText(context, message, time).show();
    }

    @JavascriptInterface
    public void alert(String title, String content, String buttons, final JsCallback jsCallback) {
        Toast.makeText(context, title + "," + buttons, Toast.LENGTH_SHORT).show();
        final String[] btTexts = buttons.split(",");
        if (btTexts.length == 0) {
            showPromptDialog(title, content, "确定", jsCallback);
        } else if (btTexts.length == 1) {
            showPromptDialog(title, content, btTexts[0], jsCallback);
        } else if (btTexts.length == 2) {
            showConfirmDialog(title, content, btTexts, jsCallback);
        } else {
            showConfirmWithNeutralDialog(title, content, btTexts, jsCallback);
        }
    }

    private void showConfirmWithNeutralDialog(String title, String content, String[] btTexts, JsCallback jsCallback) {

    }

    private void showConfirmDialog(String title, String content, final String[] btTexts, final JsCallback jsCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getWebView().getContext());
        if (title != null) builder.setTitle(title);
        builder.setMessage(content);
        builder.setPositiveButton(btTexts[1], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TaskExecutor.runTaskOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            jsCallback.apply(btTexts[1]);
                        } catch (JsCallback.JsCallbackException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        builder.setNegativeButton(btTexts[0], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TaskExecutor.runTaskOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            jsCallback.apply(btTexts[0]);
                        } catch (JsCallback.JsCallbackException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        builder.show();
    }

    private void showPromptDialog(String title, String content, final String btTexts, final JsCallback jsCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getWebView().getContext());
        if (title != null) builder.setTitle(title);
        builder.setMessage(content);
        builder.setPositiveButton(btTexts, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TaskExecutor.runTaskOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            jsCallback.apply(btTexts);
                        } catch (JsCallback.JsCallbackException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        builder.show();
    }

    @JavascriptInterface
    public void showLoading(String message) {
    }
}
