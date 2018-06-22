package com.example.user.rems;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import static com.example.user.rems.R.id.container;

/**
 * Created by user on 14-May-18.
 */

public class add extends Fragment {
    private static final String TAG = "Home";
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home,container,false);

        View rootView = inflater.inflate(R.layout.search, container, false);
        WebView webView=(WebView) rootView.findViewById(R.id.webviewsearch);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://rentalsolution.000webhostapp.com/Rentalhouse/Login/Login_v2/home1.php");

        return rootView;
    }
}
