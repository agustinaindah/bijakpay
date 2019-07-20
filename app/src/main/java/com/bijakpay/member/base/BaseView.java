package com.bijakpay.member.base;

/**
 * Created by agustinaindah on 26/04/2017.
 */

public interface BaseView {

    void showProgress();

    void hideProgress();

    void showMessage(String msg);

    void notConnect(String msg);
}
