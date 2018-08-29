package com.catatanasad.navigationdrawer.fragment;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.catatanasad.navigationdrawer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalFragment extends Fragment {


    public JadwalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal, container, false);
    }

    // todo panggil method onViewCreated
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // todo id webviewnya
        WebView webView = view.findViewById(R.id.web_view);

        // todo set url
        String url = "http://idn.id/jadwal/";

        // todo load url
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);

        final ProgressDialog dialog = ProgressDialog.show(getContext(), "", "Loading");

        webView.setWebViewClient(new WebViewClient(){

            // todo ketika url error
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(getContext(), "URL Error", Toast.LENGTH_SHORT).show();
            }

            // todo ketika halaman web loading
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog.show();
            }

            // todo ketika halaman web finish loading
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
            }
        });

    }

}
