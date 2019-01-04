package z_aksys.solutions.digiappequitybb.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.utils.CTEventLog;
import z_aksys.solutions.digiappequitybb.utils.ObjectUtils;

import static android.support.v4.app.ActivityCompat.invalidateOptionsMenu;

;

public class NewsViewerFragment extends Fragment {

    protected WebView webView;
    AppCompatImageView imageView;
    private String newsurl;

    public NewsViewerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.newsviewer, container, false);

        webView = (WebView) view.findViewById(R.id.webview);
        imageView = (AppCompatImageView) view.findViewById(R.id.img_close);
        setData();
        initWebView();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void setData() {


        Bundle bundle = getArguments();

        if (ObjectUtils.isNotNull(bundle)) {


            if (bundle.containsKey("newsUrl")) {
                //ObjectUtils.getIntFromString(id=bundle.getString("ID"));

                newsurl = bundle.getString("newsUrl");
                String newsId = bundle.getString("newsId");

                webView.loadUrl(newsurl);

                Log.d("url", newsurl);
                CTEventLog.getInstance(getActivity()).logNewsViewed(newsId, newsurl);
            }

        }
    }


    private void initWebView() {
        webView.setWebChromeClient(new MyWebChromeClient(getContext()));
        webView.clearCache(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                invalidateOptionsMenu(getActivity());
            }
        });
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
    }

    private class MyWebChromeClient extends WebChromeClient {
        Context context;

        public MyWebChromeClient(Context context) {
            super();
            this.context = context;
        }
    }

}
