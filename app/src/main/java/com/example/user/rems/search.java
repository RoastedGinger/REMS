package com.example.user.rems;

import android.support.v4.app.Fragment;

import android.os.Bundle;
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

public class search extends Fragment {
    private static final String TAG = "search";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search, container, false);
        WebView webView=(WebView) rootView.findViewById(R.id.webviewsearch);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://rentalsolution.000webhostapp.com/Rentalhouse/Login/Login_v2/search.php");

        return rootView;


    }
}