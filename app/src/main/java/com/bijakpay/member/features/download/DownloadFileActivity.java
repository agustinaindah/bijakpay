package com.bijakpay.member.features.download;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 02/09/2017.
 */

public class DownloadFileActivity extends BaseActivity {

    // TODO: 02/09/2017 link web bijakpay.com

    @BindView(R.id.btn_download)
    Button btn_download;

    @BindString(R.string.loading)
    String strLoading;

    private ProgressDialog progressDialog;

    @BindString(R.string.download_materi)
    String strDownloadMateri;

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(strDownloadMateri);
        initLoading();
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(strLoading);
    }

    @Override
    protected int setView() {
        return R.layout.activity_download_file;
    }

    @OnClick(R.id.btn_download)
    public void link(View view){
        Uri uri = Uri.parse("http://bijakpay.com/materi");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
