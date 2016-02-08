package com.calcul.diabetif.commun.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.calcul.diabetif.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Customize WebView to be able to get content.
 *
 * @author saravuth
 */
public class ContentApproachableWebView extends WebView {

    private static final String TAG = ContentApproachableWebView.class.getSimpleName();
    private static final String INTERFACE_NAME = "HTMLOUT";
    private String javascriptCode;
    private String url;
    private ResponseHandler handler;
    private ProgressDialog progressDialog;
    private Context context;

    public ContentApproachableWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initiateWebView();
    }

    public ContentApproachableWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initiateWebView();
    }

    public ContentApproachableWebView(Context context) {
        super(context);
        this.context = context;
        initiateWebView();
    }

    public void initiateWebView() {
        /* This code will be injected into the page which just finished loading. */
        StringBuilder strbld = new StringBuilder("");
        strbld.append("javascript:");
        strbld.append("window.").append(INTERFACE_NAME)
                .append(".processHTML(''+document.getElementsByTagName('html')[0].innerHTML);");
        javascriptCode = strbld.toString();
        /* JavaScript must be enabled if you want it to work, obviously */
        getSettings().setJavaScriptEnabled(true);

        /* Register a new JavaScript interface called HTMLOUT */
        addJavascriptInterface(new JavaScriptInterface(), INTERFACE_NAME);

        /* WebViewClient must be set BEFORE calling loadUrl! */
        setWebViewClient(new InternalWebViewClient());

    }

    public void setHandler(ResponseHandler handler) {
        this.handler = handler;
    }

    protected void showProgressDialog() {
        Log.v(TAG, "showProgressDialog()");
        if (progressDialog != null) {
            return;
        }
        CharSequence loadingMessage = context.getResources().getText(
                R.string.ratio_soir);
        progressDialog = ProgressDialog.show(context, "", loadingMessage, true);
    }

    protected void hideProgressDialog() {
        Log.v(TAG, "hideProgressDialog()");
        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                // nothing
            }
            progressDialog = null;
        }
    }

    public interface ResponseHandler {
        void onFinished(String url, String html);
    }

    /* An object of this class will be registered as a JavaScript interface */
    private class JavaScriptInterface {
        @SuppressWarnings("unused")
        public void processHTML(String innerHtml) {
            Log.v(TAG, "processHTML: " + innerHtml);
            if (handler != null) {
                // Try to find result from payment gateway website.
                String patternStr = "(\\d+)";
                Pattern pattern = Pattern.compile(patternStr);
                Matcher matcher = pattern.matcher(innerHtml);
                String resultHtml = innerHtml;
                if (matcher.find()) {
                    resultHtml = matcher.group();
                }
                // if there's no number we're looking for, just return the
                // original innerHtml
                // content.
                Log.v(TAG, "resultHtml: " + resultHtml);
                handler.onFinished(url, resultHtml);
            }
        }
    }

    /* inner class */
    private class InternalWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.v(TAG, "onPageFinished() " + url);
            Log.v(TAG, "loading for: " + view.getTitle());
            Log.v(TAG, "loadUrl(javascriptCode) " + javascriptCode);
            hideProgressDialog();
            loadUrl(javascriptCode);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.v(TAG, "onPageStarted() " + url);
            showProgressDialog();
            ContentApproachableWebView.this.url = url;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description,
                                    String failingUrl) {
            Log.e(TAG, "onReceivedError()" + errorCode + ": " + description);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        /*
         * During development/integration phase; the server had the untrusted
         * SSL certificate. So we need to ignore the untrusted certificate.
         */
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            int errorCode = error.getPrimaryError();
            Log.e(TAG, "onReceivedSslError(): " + errorCode);

            if (SslError.SSL_UNTRUSTED == errorCode) {
                Log.v(TAG, "proceed SSL_UNTRUSTED");
                handler.proceed();
            } else {
                // TODO howto handle these SSL error?
                /*
                 * Field descriptor #8 I SSL_NOTYETVALID = 0; SSL_EXPIRED = 1;
                 * SSL_IDMISMATCH = 2; SSL_UNTRUSTED = 3; SSL_MAX_ERROR = 4;
                 */
                Log.v(TAG, "proceed SSL error no: " + errorCode);
                handler.proceed();
            }
        }

    }
}