package com.bijakpay.member.features.profile;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bijakpay.member.BijakApps;
import com.bijakpay.member.R;
import com.bijakpay.member.base.BaseActivity;
import com.bijakpay.member.base.BaseFragment;
import com.bijakpay.member.features.DateDialog;
import com.bijakpay.member.model.SysMembership;
import com.bijakpay.member.utils.CallbackInterface;
import com.bijakpay.member.utils.Consts;
import com.bijakpay.member.utils.Helper;
import com.bijakpay.member.utils.Utility;
import com.google.gson.JsonObject;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.FileNotFoundException;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by agustinaindah on 28/04/2017.
 */

public class ProfileFragment extends BaseFragment implements ProfilePresenter.View,
        DateDialog.OnDateDialog {

    private static final int GALLERY_REQUEST = 693;

    @BindView(R.id.imgProfile)
    CircularImageView imgProfile;
    @BindView(R.id.imgBtnChangePhoto)
    ImageButton imgBtnChangePhoto;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.edtNoPonsel)
    EditText edtNoPonsel;
    @BindView(R.id.rgGender)
    RadioGroup rgGender;
    @BindView(R.id.rbGenderMale)
    RadioButton rbGenderMale;
    @BindView(R.id.rbGenderFemale)
    RadioButton rbGenderFemale;
    @BindView(R.id.txtBirthDate)
    TextView txtBirthDate;
    @BindView(R.id.edtJob)
    EditText edtJob;
    @BindView(R.id.edtHobby)
    EditText edtHobby;
    @BindView(R.id.btnProfileSave)
    Button btnProfileSave;

    @BindString(R.string.msg_not_connect)
    String strInfoNotConnect;
    @BindString(R.string.loading)
    String strLoading;
    @BindString(R.string.profile)
    String strProfile;
    @BindString(R.string.not_fill)
    String strNotFill;

    private ProgressDialog progressDialog;
    private ProfilePresenter mPresenter;
    private String gender;
    private String mBirthDate;
    private GalleryPhoto mGalleryPhoto;
    private String newPhoto = null;

    private String oldPhoto;
    private String mImgProfileEncoded;

    public static ProfileFragment newInstance() {
        Bundle args = new Bundle();
        ProfileFragment fragment = new ProfileFragment();
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
        mGalleryPhoto = new GalleryPhoto(getActivity());

        int permission = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i("Tes", "Permission to record denied");
            makeRequest();
        }
    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                GALLERY_REQUEST);
    }

    private void initLoading() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(strLoading);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(strProfile);
        try {
            SharedPreferences mPref = BijakApps.getInstance().Prefs();

            Helper.displayImage(getActivity(), mPref.getString(Consts.PHOTO, ""),
                    imgProfile, true);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter = null;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_profile;
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

    @OnClick(R.id.imgBtnChangePhoto)
    public void clickChangePhoto(){
//        startActivityForResult(mGalleryPhoto.openGalleryIntent(), Consts.CODE_REQUEST_GALLERY);
        boolean result = Utility.checkPermission(getActivity());
        if (result)
            galleryIntent();
    }

    private void galleryIntent() {
        /*startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);*/
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, GALLERY_REQUEST);
        }
    }

    @OnClick(R.id.btnProfileSave)
    public void updateProfile(){
        mPresenter.updateProfile(getInput());
    }

    @OnClick(R.id.txtBirthDate)
    public void changeDate(){
        DialogFragment dateDialog = DateDialog.newInstance(mBirthDate, this);
        dateDialog.show(getFragmentManager(), DateDialog.TAG);
    }

//    private String getImageEncodedWithPrefix() {
//        String prefix = "data:image/jpeg;base64,";
//        return (mImgProfileEncoded == null) ? "" : prefix + mImgProfileEncoded;
//    }

    private JsonObject getInput(){
         JsonObject jsonInput = new JsonObject();
        try {
            gender = (rgGender.getCheckedRadioButtonId() == R.id.rbGenderMale) ? Consts.MALE
                    : Consts.FEMALE;
            /*jInput.addProperty(prefix + "profile_pic", getImageEncodedWithPrefix());*/
            //jsonInput.addProperty("member_photo",getImageEncodedWithPrefix());
            jsonInput.addProperty("member_name",edtName.getText().toString());
            jsonInput.addProperty("member_phone_number", edtNoPonsel.getText().toString());
            jsonInput.addProperty("member_birthdate", mBirthDate);
            jsonInput.addProperty("member_gender",gender);
            jsonInput.addProperty("member_job", edtJob.getText().toString());
            jsonInput.addProperty("member_hobby", edtHobby.getText().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonInput;
    }

    @Override
    public void showProfile(JsonObject results) {
        try {

//            Helper.displayImage(getActivity(), results.get("member_photo").getAsString(),imgProfile,true);
            oldPhoto = results.get("member_photo").getAsString();
            mBirthDate   = results.get("member_birthdate").getAsString();
            gender      = results.get("member_gender").getAsString();
            edtName.setText(results.get("member_name").getAsString());
            edtHobby.setText(results.get("member_hobby").getAsString());
            edtJob.setText(results.get("member_job").getAsString());
            edtNoPonsel.setText("0" + results.get("member_mobile_phone_number").getAsLong());

            rgGender.check((gender.equals(Consts.MALE) ? R.id.rbGenderMale : R.id.rbGenderFemale));

            txtBirthDate.setText(
                    (mBirthDate.equals("")) ? strNotFill
                            : Helper.parseToDateString(mBirthDate, Consts.TYPE_DATE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void successListSys(List<SysMembership> sysMemberships) {

    }

    @Override
    public void successUpdateProfile() {
        Helper.createAlert(getActivity(), Consts.STR_INFO, "Data Successfully saved", false,
                new CallbackInterface() {
                    @Override
                    public void callback() {
                        getActivity().onBackPressed();
                    }
                });

    }

    @Override
    public void successUpdateEwallet() {

    }

    @Override
    public void successUpdateToken() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*if (resultCode == RESULT_OK) {
            if (requestCode == Consts.CODE_REQUEST_GALLERY) {
                mGalleryPhoto.setPhotoUri(data.getData());
                String photoPath = mGalleryPhoto.getPath();
                try {
                    Bitmap bitmap = ImageLoader
                            .init()
                            .from(photoPath)
                            .requestSize(256, 256)
                            .getBitmap();

                    mImgProfileEncoded = ImageBase64.encode(bitmap);

                    imgProfile.setImageDrawable(null);
                    imgProfile.setImageDrawable(
                            ImageLoader
                                    .init()
                                    .from(photoPath)
                                    .requestSize(256, 256)
                                    .getImageDrawable()
                    );

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }*/
        /*if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST) {
                mGalleryPhoto.setPhotoUri(data.getData());
                String photoPath = mGalleryPhoto.getPath();

                try {
                    Bitmap bitmap = ImageLoader
                            .init()
                            .from(photoPath)
                            .requestSize(512, 512)
                            .getBitmap();

                    imgProfile.setImageBitmap(bitmap);

                    newPhoto = photoPath;

                    if (newPhoto != null) {
                        try {

                            ImageLoader imgLoader = ImageLoader
                                    .init()
                                    .from(newPhoto);

                            Bitmap newPhoto = imgLoader.requestSize(1024, 1024).getBitmap();
                            Bitmap newPhotoThumbnail = imgLoader.requestSize(130, 130).getBitmap();

                            String newPhotoEncoded = ImageBase64.encode(newPhoto);
                            String newPhotoThumbnailEncoded = ImageBase64.encode(newPhotoThumbnail);

                            JsonObject jsonPhoto = new JsonObject();

                            jsonPhoto.addProperty("member_old_photo", oldPhoto);
                            jsonPhoto.addProperty("member_photo", newPhotoEncoded);
                            *//*jsonPhoto.addProperty("member_photo_thumbnail", newPhotoThumbnailEncoded);*//*
                            mPresenter.updatePhotoProfile(jsonPhoto);

                        } catch (FileNotFoundException e) {

                        }

                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }*/
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST)
                onSelectFromGalleryResult(data);
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        mGalleryPhoto.setPhotoUri(data.getData());
        String photoPath = mGalleryPhoto.getPath();

        try {
            Bitmap bitmap = ImageLoader
                    .init()
                    .from(photoPath)
                    .requestSize(512, 512)
                    .getBitmap();

            imgProfile.setImageBitmap(bitmap);

            newPhoto = photoPath;

            if (newPhoto != null) {
                try {

                    ImageLoader imgLoader = ImageLoader
                            .init()
                            .from(newPhoto);

                    Bitmap newPhoto = imgLoader.requestSize(1024, 1024).getBitmap();
                    Bitmap newPhotoThumbnail = imgLoader.requestSize(130, 130).getBitmap();

                    String newPhotoEncoded = ImageBase64.encode(newPhoto);
                    String newPhotoThumbnailEncoded = ImageBase64.encode(newPhotoThumbnail);

                    JsonObject jsonPhoto = new JsonObject();

                    jsonPhoto.addProperty("member_old_photo", oldPhoto);
                    jsonPhoto.addProperty("member_photo", newPhotoEncoded);
                    /*jsonPhoto.addProperty("member_photo_thumbnail", newPhotoThumbnailEncoded);*/
                    mPresenter.updatePhotoProfile(jsonPhoto);

                } catch (FileNotFoundException e) {

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        galleryIntent();
                } else {
                    //code for deny
                    Log.i("Tes", "Permission has been granted by user");
                }
                break;
        }
    }

    /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST) {
                mGalleryPhoto.setPhotoUri(data.getData());
                String photoPath = mGalleryPhoto.getPath();

                try {
                    Bitmap bitmap = ImageLoader
                            .init()
                            .from(photoPath)
                            .requestSize(512, 512)
                            .getBitmap();

                    imgProfile.setImageBitmap(bitmap);

                    newPhoto = photoPath;

                    if (newPhoto != null) {
                        try {

                            ImageLoader imgLoader = ImageLoader
                                    .init()
                                    .from(newPhoto);

                            Bitmap newPhoto = imgLoader.requestSize(1024, 1024).getBitmap();
                            Bitmap newPhotoThumbnail = imgLoader.requestSize(130, 130).getBitmap();

                            String newPhotoEncoded = ImageBase64.encode(newPhoto);
                            String newPhotoThumbnailEncoded = ImageBase64.encode(newPhotoThumbnail);

                            JsonObject jsonPhoto = new JsonObject();

                            jsonPhoto.addProperty("member_old_photo", oldPhoto);
                            jsonPhoto.addProperty("member_photo", newPhotoEncoded);
                            *//*jsonPhoto.addProperty("member_photo_thumbnail", newPhotoThumbnailEncoded);*//*
                            mPresenter.updatePhotoProfile(jsonPhoto);

                        } catch (FileNotFoundException e) {

                        }

                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    @Override
    public void onSelectedDate(String selectedDate) {
        mBirthDate = selectedDate;
        txtBirthDate.setText(Helper.parseToDateString(mBirthDate, Consts.TYPE_DATE));
    }
}
