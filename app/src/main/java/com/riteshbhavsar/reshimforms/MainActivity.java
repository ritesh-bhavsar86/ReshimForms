package com.riteshbhavsar.reshimforms;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.radialtimepicker.RadialTimePickerDialogFragment;
import com.riteshbhavsar.reshimforms.model.Candidate;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static android.R.attr.bitmap;

public class MainActivity extends AppCompatActivity implements
        CalendarDatePickerDialogFragment.OnDateSetListener
    , RadialTimePickerDialogFragment.OnTimeSetListener
{
    Realm realmInstance;

    SharedPreferences sharedpreferences; //= getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    public static final String PrefName = "nameKey";


    @BindView(R.id.iv_profile)
    ImageView iv_profile;
    @BindView(R.id.tv_profile_edit)
    TextView tv_profile_edit;

    @BindView(R.id.edt_fname_wrapper)
    TextInputLayout edt_fname_wrapper;
    @BindView(R.id.edt_fname)
    TextInputEditText edt_fname;

    @BindView(R.id.edt_fathername_wrapper)
    TextInputLayout edt_fathername_wrapper;
    @BindView(R.id.edt_fathername)
    TextInputEditText edt_fathername;

    @BindView(R.id.edt_fatheroccu_wrapper)
    TextInputLayout edt_fatheroccu_wrapper;
    @BindView(R.id.edt_fatheroccu)
    TextInputEditText edt_fatheroccu;

    @BindView(R.id.edt_fcolor_wrapper)
    TextInputLayout edt_fcolor_wrapper;
    @BindView(R.id.edt_fcolor)
    TextInputEditText edt_fcolor;


//    @BindView(R.id.edt_surname_wrapper)
//    TextInputLayout edt_surname_wrapper;
//    @BindView(R.id.edt_surname)
//    TextInputEditText edt_surname;

    @BindView(R.id.edt_mothername_wrapper)
    TextInputLayout edt_mothername_wrapper;
    @BindView(R.id.edt_mothername)
    TextInputEditText edt_mothername;

    @BindView(R.id.edt_kul_wrapper)
    TextInputLayout edt_kul_wrapper;
    @BindView(R.id.edt_kul)
    TextInputEditText edt_kul;

    @BindView(R.id.edt_mamkul_wrapper)
    TextInputLayout edt_mamkul_wrapper;
    @BindView(R.id.edt_mamkul)
    TextInputEditText edt_mamkul;

    @BindView(R.id.edt_height_wrapper)
    TextInputLayout edt_height_wrapper;
    @BindView(R.id.edt_height)
    TextInputEditText edt_height;

    @BindView(R.id.edt_weight_wrapper)
    TextInputLayout edt_weight_wrapper;
    @BindView(R.id.edt_weight)
    TextInputEditText edt_weight;

    @BindView(R.id.edt_bdate_wrapper)
    TextInputLayout edt_bdate_wrapper;
    @BindView(R.id.edt_bdate)
    TextInputEditText edt_bdate;

    @BindView(R.id.edt_btime_wrapper)
    TextInputLayout edt_btime_wrapper;
    @BindView(R.id.edt_btime)
    TextInputEditText edt_btime;

    @BindView(R.id.edt_bplace_wrapper)
    TextInputLayout edt_bplace_wrapper;
    @BindView(R.id.edt_bplace)
    TextInputEditText edt_bplace;

    @BindView(R.id.edt_blood_wrapper)
    TextInputLayout edt_blood_wrapper;
    @BindView(R.id.edt_blood)
    TextInputEditText edt_blood;

    @BindView(R.id.edt_edu_wrapper)
    TextInputLayout edt_edu_wrapper;
    @BindView(R.id.edt_edu)
    TextInputEditText edt_edu;

    @BindView(R.id.edt_occupation_wrapper)
    TextInputLayout edt_occupation_wrapper;
    @BindView(R.id.edt_occupation)
    TextInputEditText edt_occupation;

    @BindView(R.id.edt_salary_wrapper)
    TextInputLayout edt_salary_wrapper;
    @BindView(R.id.edt_salary)
    TextInputEditText edt_salary;

    @BindView(R.id.edt_siblings_wrapper)
    TextInputLayout edt_siblings_wrapper;
    @BindView(R.id.edt_siblings)
    TextInputEditText edt_siblings;

    @BindView(R.id.edt_addr_wrapper)
    TextInputLayout edt_addr_wrapper;
    @BindView(R.id.edt_addr)
    TextInputEditText edt_addr;

    @BindView(R.id.edt_contact_wrapper)
    TextInputLayout edt_contact_wrapper;
    @BindView(R.id.edt_contact)
    TextInputEditText edt_contact;

    @BindView(R.id.edt_raas_wrapper)
    TextInputLayout edt_raas_wrapper;
    @BindView(R.id.edt_raas)
    TextInputEditText edt_raas;


    @BindView(R.id.edt_nakshatra_wrapper)
    TextInputLayout edt_nakshatra_wrapper;
    @BindView(R.id.edt_nakshatra)
    TextInputEditText edt_nakshatra;

    @BindView(R.id.edt_mamafullname_wrapper)
    TextInputLayout edt_mamafullname_wrapper;
    @BindView(R.id.edt_mamafullname)
    TextInputEditText edt_mamafullname;

    @BindView(R.id.edt_expectation_wrapper)
    TextInputLayout edt_expectation_wrapper;
    @BindView(R.id.edt_expectation)
    TextInputEditText edt_expectation;

    @BindView(R.id.rg_gender)
    RadioGroup rg_gender;
    @BindView(R.id.rbtn_male)
    RadioButton rbtn_male;
    @BindView(R.id.rbtn_female)
    RadioButton rbtn_female;
    @BindView(R.id.rbtn_trans)
    RadioButton rbtn_trans;

    @BindView(R.id.btn_save)
    TextView btn_save;
    @BindView(R.id.btn_cancel)
    TextView btn_cancel;

    @BindView(R.id.edt_status_wrapper)
    TextInputLayout edt_status_wrapper;
    @BindView(R.id.edt_status)
    TextInputEditText edt_status;


    private static int RESULT_LOAD_IMAGE = 1;
    Bitmap bmp_profile_logo = null;
    private int actionBarHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Calculate ActionBar height
        TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }

        // remove title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
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
        sharedpreferences = getSharedPreferences("ReshimGathi", Context.MODE_PRIVATE);

        edt_bdate.setInputType(0);
        edt_btime.setInputType(0);

//        edt_bdate.setShowSoftInputOnFocus(false);
//        edt_btime.setShowSoftInputOnFocus(false);
        btn_save.setHeight(actionBarHeight);
        btn_cancel.setHeight(actionBarHeight);

        tv_profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //validate form

                //save to db
                addToDB();
                //goto list activity
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bitmap bmp = intent.getExtras().get("data");
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                byte[] byteArray = stream.toByteArray();
                onBackPressed();
            }
        });
        edt_bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment()
                        .setOnDateSetListener(MainActivity.this)
                        .setFirstDayOfWeek(Calendar.SUNDAY)
//                        .setPreselectedDate(towDaysAgo.getYear(), towDaysAgo.getMonthOfYear() - 1, towDaysAgo.getDayOfMonth())
//                        .setDateRange(minDate, null)
                        .setDoneText("Done")
                        .setCancelText("Cancel");
//                        .setThemeDark();
                cdp.show(getSupportFragmentManager(), "fragment_date_picker_name");
            }
        });
        edt_bdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
        edt_btime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadialTimePickerDialogFragment rtpd = new RadialTimePickerDialogFragment()
                        .setOnTimeSetListener(MainActivity.this);
                rtpd.show(getSupportFragmentManager(), "timePickerDialogFragment");
            }
        });
        edt_btime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });
//        addToDB();
//        RealmResults<Candidate> data = readFromDB();
//        for(Candidate model : data){
//            Toast.makeText(this, model.getFirstName(), Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();

            //with glide
//            Glide.with(iv_profile.getContext())
//                    .load(selectedImage)
////                    .asBitmap()
////                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
////                    .fitCenter()
//                    .into(iv_profile);

            //manual orientation
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();


            Bitmap loadedBitmap = BitmapFactory.decodeFile(picturePath);

            Matrix matrix = new Matrix();
            Bitmap scaledBitmap;
            if (loadedBitmap.getWidth() >= loadedBitmap.getHeight()){
                matrix.setRectToRect(new RectF(0, 0, loadedBitmap.getWidth(), loadedBitmap.getHeight()), new RectF(0, 0, 400, 300), Matrix.ScaleToFit.CENTER);
                scaledBitmap = Bitmap.createBitmap(loadedBitmap, 0, 0, loadedBitmap.getWidth(), loadedBitmap.getHeight(), matrix, true);
            } else{
                matrix.setRectToRect(new RectF(0, 0, loadedBitmap.getWidth(), loadedBitmap.getHeight()), new RectF(0, 0, 300, 400), Matrix.ScaleToFit.CENTER);
                scaledBitmap = Bitmap.createBitmap(loadedBitmap, 0, 0, loadedBitmap.getWidth(), loadedBitmap.getHeight(), matrix, true);
            }

            File file = new File(getExternalCacheDir(), "image.jpg");
            try {
                FileOutputStream out = new FileOutputStream(file);
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
                out.flush();
                out.close();
                bmp_profile_logo = scaledBitmap;
            } catch (Exception e) {
//                Log.e("Image", "Convert");
                e.printStackTrace();
            }


//            ImageView imageView = (ImageView) findViewById(R.id.imgView);

            //old working
//            try {
//                bmp_profile_logo = getBitmapFromUri(selectedImage);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            imageView.setImageBitmap(bmp);
            iv_profile.setImageBitmap(bmp_profile_logo);
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        ExifInterface ei = null;
        try {
            ei = new ExifInterface(uri.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        Bitmap rotatedBitmap = null;
        switch(orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                rotatedBitmap = rotateImage(image, 90);
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                rotatedBitmap = rotateImage(image, 180);
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                rotatedBitmap = rotateImage(image, 270);
                break;

            case ExifInterface.ORIENTATION_NORMAL:
                rotatedBitmap = image;
            default:
                break;
        }
        return rotatedBitmap;
    }
    public Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }
    public static Bitmap modifyOrientation(Bitmap bitmap, String image_absolute_path) throws IOException {
        ExifInterface ei = new ExifInterface(image_absolute_path);
        int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotate(bitmap, 90);

            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotate(bitmap, 180);

            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotate(bitmap, 270);

            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                return flip(bitmap, true, false);

            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                return flip(bitmap, false, true);

            default:
                return bitmap;
        }
    }

    public static Bitmap rotate(Bitmap bitmap, float degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
        Matrix matrix = new Matrix();
        matrix.preScale(horizontal ? -1 : 1, vertical ? -1 : 1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public void addToDB(){
        final long id = sharedpreferences.getLong(PrefName, 0);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
                realmInstance.executeTransaction(new Realm.Transaction() {

                    @Override
                    public void execute(Realm realm) {
                        if (id == 0) {
                            RealmResults<Candidate> results = realmInstance.where(Candidate.class).findAll();
                            if (results != null) {
                                if (results.size() > 0) {
                                    return;
                                }
                            }
                        }
                        Candidate data = realmInstance.createObject(Candidate.class, id + 1);
                        data.setFullname(edt_fname.getText().toString().trim());
                        data.setFathersFullName(edt_fathername.getText().toString().trim());
                        data.setFathersOccupation(edt_fatheroccu.getText().toString().trim());
                        data.setFaceColor(edt_fcolor.getText().toString().trim());
//                        data.setMiddleName(edt_fathername.getText().toString().trim());
//                        data.setLastName(edt_surname.getText().toString().trim());
                        data.setMothersName(edt_mothername.getText().toString().trim());
                        data.setDobStr(edt_bdate.getText().toString().trim());
                        data.setDotStr(edt_btime.getText().toString().trim());
                        data.setBirthPlace(edt_bplace.getText().toString().trim());

                        if (rg_gender.getCheckedRadioButtonId() == rbtn_male.getId()) {
                            data.setGender("M");
                            data.setCandidateId("RGM-" + (id + 1));
                        } else if (rg_gender.getCheckedRadioButtonId() == rbtn_female.getId()) {
                            data.setGender("F");
                            data.setCandidateId("RGF-" + (id + 1));
                        } else {
                            data.setGender("T");
                            data.setCandidateId("RGT-" + (id + 1));
                        }
                        try {
                            data.setHeight(Float.valueOf(edt_height.getText().toString().trim()));
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setWeight(Float.valueOf(edt_weight.getText().toString().trim()));
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        data.setBloodGrp(edt_blood.getText().toString().trim());
                        data.setEducation(edt_edu.getText().toString().trim());
                        data.setOccupation(edt_occupation.getText().toString().trim());
                        try {
                            data.setSalary(Long.valueOf(edt_salary.getText().toString().trim()));
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        data.setAddress(edt_addr.getText().toString().trim());
                        data.setContactNo(edt_contact.getText().toString().trim());
                        data.setKul(edt_kul.getText().toString().trim());
                        data.setMameKul(edt_mamkul.getText().toString().trim());
                        data.setFullNameOfMama(edt_mamafullname.getText().toString().trim());
                        data.setStatus(edt_status.getText().toString().trim());
                        try {
                            data.setNoOfSiblings(Integer.valueOf(edt_siblings.getText().toString().trim()));
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        data.setExpectation(edt_expectation.getText().toString().trim());
                        data.setRaas(edt_raas.getText().toString().trim());
                        data.setNakshatra(edt_nakshatra.getText().toString().trim());

                        saveImage(bmp_profile_logo, data.getCandidateId(), data);
//                        try {
//                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                            bmp_profile_logo.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                            byte[] byteArray = stream.toByteArray();
//                            data.setProfileLogoByte(byteArray);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }

//                                                 data.setId(100);
//                                                 data.setFirstName("Ritesh");
//                                                 data.setMiddleName("Rajesh");
//                                                 data.setLastName("Bhavsar");
//                                                 data.setMothersName("Lalita");
//                                                 data.setDobStr("03/03/1986");
//                                                 data.setDotStr("5:30 AM");
//                                                 data.setAddress("Nashik");
//                                                 data.setContactNo("8087265182");
//                                                 data.setBloodGrp("b+");
//                                                 data.setEducation("MCA");
//                                                 data.setOccupation("Sr Software Engg");
//                                                 data.setSalary(50000);
//                                                 data.setKul("jadhav");
//                                                 data.setMameKul("jadhav");
//                                                 data.setHeight(5.4f);
//                                                 data.setWeight(60.10f);

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putLong(PrefName, id + 1);//data.getId()
                        editor.commit();
                        onBackPressed();
                    }
//                }, new Realm.Transaction.OnSuccess() {
//                    @Override
//                    public void onSuccess() {
//                        SharedPreferences.Editor editor = sharedpreferences.edit();
//                        editor.putLong(PrefName, id + 1);//data.getId()
//                        editor.commit();
                        //goto list activity
//                Intent intent = new Intent(MainActivity.this, )
//                finish();
//                        onBackPressed();
//
//                    }
//                }, new Realm.Transaction.OnError() {
//                    @Override
//                    public void onError(Throwable error) {
//                        onBackPressed();
//
//                    }
                });
//            }
//        });
    }
    public RealmResults<Candidate> readFromDB(){
        RealmResults<Candidate> results = realmInstance.where(Candidate.class).findAll();
        return results;
    }
    public void updateToDB(){

    }
    public void deleteFromDB(){

    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
        edt_bdate.setText(getString(R.string.calendar_date_picker_result_values, year, monthOfYear+1, dayOfMonth));
    }

    @Override
    public void onTimeSet(RadialTimePickerDialogFragment dialog, int hourOfDay, int minute) {
        edt_btime.setText(getString(R.string.radial_time_picker_result_value, hourOfDay, minute));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.nothing, R.anim.slide_out);
    }

    private void saveImage(Bitmap finalBitmap, String image_name, Candidate data) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root);
        myDir.mkdirs();

        File imagedir = new File(myDir, "/ReshimImages/");
        if(!imagedir.exists()){
            imagedir.mkdir();
        }
        imagedir = new File(imagedir, "profileLogos/");
        if(!imagedir.exists()){
            imagedir.mkdir();
        }

//        File imageFile = new File(imagedir, candidateId + ".jpg");
        String fname = "Image-" + image_name+ ".jpg";
        File file = new File(imagedir, fname);
        if (file.exists()) file.delete();
//        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
            out.flush();
            out.close();
            //data saved
            data.setProfileLogo(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //    private void basicCRUD(Realm realm) {
//        // All writes must be wrapped in a transaction to facilitate safe multi threading
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                // Add a person
//                Person person = realm.createObject(Person.class);
//                person.setId(1);
//                person.setName("Young Person");
//                person.setAge(14);
//
//            }
//        });
//
//        // Find the first person (no query conditions) and read a field
//        final Person person = realm.where(Person.class).findFirst();
//        showStatus(person.getName() + ":" + person.getAge());
//
//        // Update person in a transaction
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                person.setName("Senior Person");
//                person.setAge(99);
//                showStatus(person.getName() + " got older: " + person.getAge());
//            }
//        });
//
//        // Delete all persons
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.delete(Person.class);
//            }
//        });
//    }
}