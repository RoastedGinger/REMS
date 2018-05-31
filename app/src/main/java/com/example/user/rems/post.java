package com.example.user.rems;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import static com.example.user.rems.R.id.container;

/**
 * Created by user on 14-May-18.
 */

public class post extends Fragment implements  View.OnClickListener{
    private static final String TAG = "post";
    Button button1,button2,button3;
    Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.post, container, false);
     /*   WebView  webView=(WebView) rootView.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://rentalsolution.000webhostapp.com/Rentalhouse/Login/Login_v2/post.html");*/

        /*Button button = (Button)getActivity().findViewById(R.id.button2);

*/

        return rootView;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button1 = (Button)getActivity().findViewById(R.id.button1);
        button2 = (Button)getActivity().findViewById(R.id.button2);
        button3 = (Button)getActivity().findViewById(R.id.button3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button1 :  intent = new Intent(getActivity(),Post_Activity.class);
                                 startActivity(intent);
                                 break;
            case R.id.button2 :   intent = new Intent(getActivity(),Post_Activity.class);
                                 startActivity(intent);
                                 break;
            case R.id.button3 :  intent = new Intent(getActivity(),Post_Activity.class);
                                 startActivity(intent);
                                 break;

        }
    }
}