package com.riteshbhavsar.reshimforms;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.riteshbhavsar.reshimforms.model.Candidate;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by ritesh.bhavsar on 07-07-2017.
 */

public class ProfileActivity extends AppCompatActivity {

    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private boolean sentToSettings = false;

    Realm realmInstance;
    Candidate cdata;

    @BindView(R.id.scroll)
    RelativeLayout scroll;
    @BindView(R.id.linear)
    LinearLayout linear;

    @BindView(R.id.iv_candidate_pic)
    ImageView iv_candidate_pic;

    @BindView(R.id.txt_c_cid)
    TextView txt_c_cid;

    @BindView(R.id.txt_cname)
    TextView txt_cname;

    @BindView(R.id.txt_c_bdate)
    TextView txt_c_bdate;

    @BindView(R.id.txt_c_btime)
    TextView txt_c_btime;

    @BindView(R.id.txt_c_edu)
    TextView txt_c_edu;

    @BindView(R.id.txt_c_occu)
    TextView txt_c_occu;

    @BindView(R.id.txt_c_salary)
    TextView txt_c_salary;

    @BindView(R.id.txt_c_kul)
    TextView txt_c_kul;

    @BindView(R.id.txt_c_mamkul)
    TextView txt_c_mamkul;

    @BindView(R.id.txt_c_addr)
    TextView txt_c_addr;

    @BindView(R.id.txt_c_contact)
    TextView txt_c_contact;

    @BindView(R.id.txt_c_blood)
    TextView txt_c_blood;

    @BindView(R.id.txt_c_height)
    TextView txt_c_height;

    @BindView(R.id.txt_c_weight)
    TextView txt_c_weight;

    @BindView(R.id.txt_c_fathername)
    TextView txt_c_fathername;

    @BindView(R.id.txt_c_fatheroccu)
    TextView txt_c_fatheroccu;

    @BindView(R.id.txt_c_mamaname)
    TextView txt_c_mamaname;

    @BindView(R.id.txt_c_expectation)
    TextView txt_c_expectation;

    @BindView(R.id.txt_c_raas)
    TextView txt_c_raas;

    @BindView(R.id.txt_c_nakshatra)
    TextView txt_c_nakshatra;

    @BindView(R.id.txt_c_bplace)
    TextView txt_c_bplace;
//
    @BindView(R.id.txt_c_fcolor)
    TextView txt_c_fcolor;

    @BindView(R.id.txt_c_mothername)
    TextView txt_c_mother;

    @BindView(R.id.txt_status)
    TextView txt_c_status;

    @BindView(R.id.txt_c_noofsibling)
    TextView txt_c_noofsibling;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capturescreen);

        ButterKnife.bind(this);
//        GalleryModule.get(this).setMemoryCategory(MemoryCategory.HIGH);


        try {
            realmInstance = Realm.getDefaultInstance();
        } catch (Exception e) {
            e.printStackTrace();
            // Get a Realm instance for this thread
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            realmInstance = Realm.getInstance(config);
        }



        int cid = getIntent().getIntExtra("id", 0);
        if(cid != 0){
            cdata = realmInstance.where(Candidate.class).equalTo("id", cid).findFirst();
        }

//        sv = (ScrollView) findViewById(R.id.scroll);
//        scroll.setScreenshotHeightPx((int) (getResources().getDisplayMetrics().density * 100));

        iv_candidate_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ProfileActivity.this, MyListActivity.class);
//                startActivity(intent);
//                finish();
//                onBackPressed();
//                //1st
                View v1 = linear.getRootView();
                v1.setDrawingCacheEnabled(true);
                Bitmap bm = getBitmapFromView(v1);
//                @SuppressWarnings("deprecation")
//                BitmapDrawable bitmapDrawable = new BitmapDrawable(bm);
//                ImageView view2 = (ImageView) findViewById(R.id.imgView);
//                imageView.setBackgroundDrawable(bitmapDrawable);
//
//                //3rd
////                Bitmap bitmap = Bitmap.createBitmap(
////                        sv.getChildAt(0).getWidth(),
////                        sv.getChildAt(0).getHeight(),
////                        Bitmap.Config.ARGB_8888);
////                Canvas c = new Canvas(bitmap);
//////                sv.getChildAt(0).draw(c);
////                sv.draw(c);
//                //2nd
////                Bitmap bitmap = getScreenBitmap(); // Get the bitmap

                if(cdata!=null){
                    saveTheBitmap(bm, cdata.getCandidateId());
                }

            }
        });

        //check permission
        if (ActivityCompat.checkSelfPermission(ProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Show Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app needs storage permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
//            } else if (permissionStatus.getBoolean(Manifest.permission.WRITE_EXTERNAL_STORAGE,false)) {
//                //Previously Permission Request was cancelled with 'Dont Ask Again',
//                // Redirect to Settings after showing Information about why you need the permission
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Need Storage Permission");
//                builder.setMessage("This app needs storage permission.");
//                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                        sentToSettings = true;
//                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                        Uri uri = Uri.fromParts("package", getPackageName(), null);
//                        intent.setData(uri);
//                        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
//                        Toast.makeText(getBaseContext(), "Go to Permissions to Grant Storage", Toast.LENGTH_LONG).show();
//                    }
//                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                builder.show();
            } else {
                //just request the permission
                ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
            }
        }else {
            //You already have the permission, just go ahead.
//            proceedAfterPermission();
        }

        try {
            if(cdata!=null){

                if(cdata.getGender().equalsIgnoreCase("M")) {
                    txt_c_cid.setTextColor(getResources().getColor(R.color.bpDarker_red));
                } else if(cdata.getGender().equalsIgnoreCase("F")) {
                    txt_c_cid.setTextColor(getResources().getColor(R.color.bpDarker_blue));
                } else {
                    txt_c_cid.setTextColor(getResources().getColor(R.color.bpWhite));
                }
                txt_c_cid.setText(cdata.getCandidateId());
                txt_cname.setText( cdata.getFullname());
                txt_c_bdate.setText(cdata.getDobStr());
                txt_c_btime.setText(cdata.getDotStr());
                txt_c_bplace.setText( cdata.getBirthPlace());
                txt_c_fathername.setText(cdata.getFathersFullName());
                txt_c_fatheroccu.setText(cdata.getFathersOccupation());
                txt_c_edu.setText( cdata.getEducation());
                txt_c_occu.setText(cdata.getOccupation());
                txt_c_salary.setText(String.valueOf( cdata.getSalary()));
                txt_c_kul.setText( cdata.getKul());
                txt_c_mamaname.setText(cdata.getFullNameOfMama());
                txt_c_mamkul.setText(cdata.getMameKul());
                txt_c_addr.setText(cdata.getAddress());
                txt_c_contact.setText( cdata.getContactNo());
                txt_c_blood.setText(": "+ cdata.getBloodGrp());

                txt_c_mother.setText( cdata.getMothersName());
                txt_c_status.setText(cdata.getStatus());

                try {
                    if(String.valueOf(cdata.getHeight()).contains(".")) {
                        txt_c_height.setText(": "+String.valueOf(cdata.getHeight()).substring(0,String.valueOf(cdata.getHeight()).indexOf(".")) + " feet "
                                + String.valueOf(cdata.getHeight()).substring(String.valueOf(cdata.getHeight()).indexOf(".")+1) + " inch");
//                        String[] arr = String.valueOf(cdata.getHeight()).split(".");
//                        txt_c_height.setText(arr[0] + " feet" + arr[1] + " inch");
//                        txt_c_height.setText(String.valueOf(cdata.getHeight()).split(".")[0] + " feet" + String.valueOf(cdata.getHeight()).split(".")[1] + " inch");
                    }else{
                        txt_c_height.setText(": "+String.valueOf(cdata.getHeight()) + " feet");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    txt_c_weight.setText(": "+String.valueOf(cdata.getWeight())+ " Kg");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                txt_c_raas.setText(": "+cdata.getRaas());
                txt_c_nakshatra.setText(": "+cdata.getNakshatra());
                txt_c_expectation.setText(cdata.getExpectation());
                txt_c_fcolor.setText(": "+cdata.getFaceColor());

                try {
                    txt_c_noofsibling.setText(getResources().getString(R.string.no_of_sibling_txt,
                            String.valueOf(cdata.getNoOfSiblings()),
                            String.valueOf(cdata.getNoOfSiblings_bro())));
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
//txt_c_noofsibling.setText(": "+cdata.getNoOfSiblings()+ " sister"
//                + cdata.getNoOfSiblings_bro()+ " brother");


                try {
//                Bitmap bmp = BitmapFactory.decodeByteArray(cdata.getProfileLogoByte(), 0, cdata.getProfileLogoByte().length);
//                iv_candidate_pic.setImageBitmap(bmp);
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.placeholder(R.mipmap.ic_launcher_round);
                    requestOptions.error(R.mipmap.ic_launcher_round);

                    Glide.with(ProfileActivity.this)
                            .setDefaultRequestOptions(requestOptions)
                            .load(cdata.getProfileLogo())
                            .thumbnail(0.5f)
//                        .crossFade()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(iv_candidate_pic);
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (OutOfMemoryError e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

//        scroll.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);

    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);


    }
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            scroll.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.nothing, R.anim.slide_out);
    }

    private void saveTheBitmap(Bitmap bitmap, String candidateId) {
//        Date now = new Date();
//        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
//            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
//            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + candidateId +".jpg";

            // create bitmap screen capture
//            View v1 = getWindow().getDecorView().getRootView();
//            v1.setDrawingCacheEnabled(true);
//            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
//            v1.setDrawingCacheEnabled(false);
            File imagedir = new File(Environment.getExternalStorageDirectory(), "/ReshimImages/");
            if(!imagedir.exists()){
                imagedir.mkdir();
            }
            imagedir = new File(imagedir, "profiles/");
            if(!imagedir.exists()){
                imagedir.mkdir();
            }

            File imageFile = new File(imagedir, candidateId + ".jpg");

//            try{
//                imageFile.mkdirs();
//            }catch (Exception e){
//
//            }

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            shareintent(imageFile);
//            openScreenshot(imageFile);
            Toast.makeText(this, "Image created successfully", Toast.LENGTH_SHORT).show();
        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }
    /**
     * Show share dialog BOTH image and text
     * @param pictureFile
     */
    public void shareintent(File pictureFile) {
        Uri imageUri = Uri.parse(pictureFile.getAbsolutePath());
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        //Target whatsapp:
//        shareIntent.setPackage("com.whatsapp");
        //Add text and then Image URI
        shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.header_head));
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/jpeg");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        try {
            startActivity(shareIntent);
        } catch (android.content.ActivityNotFoundException ex) {
//            ToastHelper.MakeShortText("Whatsapp have not been installed.");
        }
    }
    private void openScreenshot(File imageFile) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

    public Bitmap getScreenBitmap() {
        View v= linear.getRootView();
        v.setDrawingCacheEnabled(true);
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());

        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false); // clear drawing cache
        return b;
    }

    private Bitmap getBitmapFromView(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }



}
