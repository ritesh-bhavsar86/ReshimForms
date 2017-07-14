package com.riteshbhavsar.reshimforms;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.riteshbhavsar.reshimforms.model.Candidate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ritesh.bhavsar on 07-07-2017.
 */

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    private List<Candidate> candidate = null;


    public CustomAdapter(Context context){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void setData(List<Candidate> details) {
        this.candidate = details;
    }
    @Override
    public int getCount() {
        if (candidate == null) {
            return 0;
        }
        return candidate.size();
    }

    @Override
    public Object getItem(int i) {
        if (candidate == null || candidate.get(i) == null) {
            return null;
        }
        return candidate.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.list_row_layout, parent, false);
        }

        Candidate city = candidate.get(position);

        if (city != null) {
            ((TextView) currentView.findViewById(R.id.txt_candidate_name)).setText(city.getFullname());
//                    city.getFirstName() + " "
//                    + city.getMiddleName() + " "
//                    + city.getLastName());
            ((TextView) currentView.findViewById(R.id.txt_candidate_dob)).setText("Dob: "+city.getDobStr());

            ((TextView) currentView.findViewById(R.id.txt_candidate_dot)).setText("Dot: "+city.getDotStr());

            ((TextView) currentView.findViewById(R.id.txt_can_id)).setText(city.getCandidateId());

            ((TextView) currentView.findViewById(R.id.txt_can_edu)).setText("Education: "+city.getEducation());

            ((TextView) currentView.findViewById(R.id.txt_can_occupa)).setText("Job: "+city.getOccupation());

            ((TextView) currentView.findViewById(R.id.txt_can_sal)).setText("Salary: "+String.valueOf(city.getSalary()));


            try {
//                Bitmap bmp = BitmapFactory.decodeByteArray(city.getProfileLogoByte(), 0, city.getProfileLogoByte().length);
//                ((ImageView) currentView.findViewById(R.id.iv_candidate_profile)).setImageBitmap(bmp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return currentView;
    }
}
