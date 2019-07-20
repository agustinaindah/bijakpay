package com.bijakpay.member.features.profile;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.features.profile.address.AddressFragment;
import com.bijakpay.member.features.profile.change_password.ChangePasswordFragment;
import com.bijakpay.member.model.ItemProfile;
import com.bijakpay.member.model.SysMembership;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.google.gson.JsonObject;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by agustinaindah on 04/05/2017.
 */

public class MyProfileMemberFragment extends BaseFragment implements ProfilePresenter.View{

    @BindView(R.id.imgProfile)
    CircularImageView imgProfile;
    /*@BindView(R.id.txtProfileName)
    TextView txtProfileName;
    @BindView(R.id.txtProfileBirthday)
    TextView txtProfileBirthday;
    @BindView(R.id.txtProfileJob)
    TextView txtProfileJob;
    @BindView(R.id.txtProfileHobby)
    TextView txtProfileHobby;*/
    @BindView(R.id.imgEditProfile)
    ImageView imgEditProfile;
    @BindView(R.id.imgEditAddress)
    ImageView imgEditAddress;
    @BindView(R.id.imgEditPassword)
    ImageView imgEditPassword;

    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;
    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.not_fill)
    String strNotFill;
    @BindString(R.string.label_profile)
    String strTitleMyProfile;

    private ProgressDialog progressDialog;
    private ProfilePresenter mPresenter;
    private String mBirthDate;
    private ItemProfile itemProfile;

    public static MyProfileMemberFragment newInstance() {
        Bundle args = new Bundle();
        MyProfileMemberFragment fragment = new MyProfileMemberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoading();
        mPresenter = new ProfilePresenterImpl(this);
        if (savedInstanceState == null){
            mPresenter.getProfile();
        }
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @OnClick(R.id.imgEditProfile)
    public void editProfile(){
        Fragment fragment = ProfileFragment.newInstance();
        AccountSettingsActivity activity = ((AccountSettingsActivity) getActivity());
        activity.gotoFragment(activity.getSupportFragmentManager(), fragment, true);
    }

    @OnClick(R.id.imgEditAddress)
    public void editAddress(){
        Fragment fragment = AddressFragment.newInstance();
        AccountSettingsActivity activity = ((AccountSettingsActivity) getActivity());
        activity.gotoFragment(activity.getSupportFragmentManager(), fragment, true);
    }

    @OnClick(R.id.imgEditPassword)
    public void editPassword(){
        Fragment fragment = ChangePasswordFragment.newInstance();
        AccountSettingsActivity activity = ((AccountSettingsActivity) getActivity());
        activity.gotoFragment(activity.getSupportFragmentManager(), fragment, true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strTitleMyProfile);
        try {
            SharedPreferences mPref = BijakApps.getInstance().Prefs();

            Helper.displayImage(getActivity(), mPref.getString(Consts.PHOTO, ""),
                    imgProfile, true);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected int setView() {
        return R.layout.fragment_my_profile_member;
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        Helper.createAlert(getActivity(), Consts.STR_INFO, msg);
    }

    @Override
    public void notConnect(String msg) {
        Helper.createAlert(getActivity(), Consts.STR_INFO, strInfoNotConnect, false, new CallbackInterface() {
            @Override
            public void callback() {
                getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void showProfile(JsonObject results) {
        try {
            mBirthDate   = results.get("member_birthdate").getAsString();
           /* txtProfileName.setText(results.get("member_name").getAsString());
            txtProfileHobby.setText(results.get("member_hobby").getAsString());
            txtProfileJob.setText(results.get("member_job").getAsString());

            txtProfileBirthday.setText(
                    (mBirthDate.equals("")) ? strNotFill
                            : Helper.parseToDateString(mBirthDate, Consts.TYPE_DATE));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void displayProfile(ItemProfile itemProfile){
       /* txtProfileBirthday.setText((itemProfile == null) ? "" : itemProfile.getMemberBirthdate());
        txtProfileName.setText((itemProfile == null) ? "" : itemProfile.getMemberName());
        txtProfileHobby.setText((itemProfile == null) ? "" : itemProfile.getMemberHobby());
        txtProfileJob.setText((itemProfile == null) ? "" : itemProfile.getMemberJob());*/
    }

    @Override
    public void successListSys(List<SysMembership> sysMemberships) {

    }

    @Override
    public void successUpdateProfile() {

    }

    @Override
    public void successUpdateEwallet() {

    }

    @Override
    public void successUpdateToken() {

    }
}
