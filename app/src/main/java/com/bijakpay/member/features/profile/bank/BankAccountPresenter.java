package com.bijakpay.member.features.profile.bank;

import com.bijakpay.member.base.BaseView;
import com.bijakpay.member.model.Bank;

import java.util.List;

/**
 * Created by agustinaindah on 21/05/2017.
 */

public interface BankAccountPresenter {

    interface View extends BaseView{

        void successPrimary(List<Bank> banks);

        /*void showSucces();*/
    }

    void getBankAccount();

   /* void deleteBank(int id);*/
}
