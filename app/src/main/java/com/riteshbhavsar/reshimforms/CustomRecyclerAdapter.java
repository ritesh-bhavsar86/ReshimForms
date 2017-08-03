package com.riteshbhavsar.reshimforms;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.riteshbhavsar.reshimforms.model.Candidate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ritesh.bhavsar on 11-07-2017.
 */

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    private List<Candidate> candidate = null;
    private LayoutInflater inflater;
    Context mContext;


    public CustomRecyclerAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
    }

    public void setData(List<Candidate> details) {
        this.candidate = details;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        RecyclerView.ViewHolder viewHolder;
//        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v1 = inflater.inflate(R.layout.list_row_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Candidate city = candidate.get(position);


        if (city != null) {

            if(city.getGender().equalsIgnoreCase("M")){
                holder.rl_view.setBackgroundResource(R.color.lst_bg_male);
                holder.txt_can_id.setTextColor(mContext.getResources().getColor(R.color.bpDarker_red));
                holder.txt_candidate_name.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_candidate_dob.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_candidate_dot.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_can_edu.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_can_occupa.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_can_sal.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
            }else if(city.getGender().equalsIgnoreCase("F")){
                holder.rl_view.setBackgroundResource(R.color.lst_bg_female);
                holder.txt_can_id.setTextColor(mContext.getResources().getColor(R.color.bpDarker_blue));
                holder.txt_candidate_name.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_candidate_dob.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_candidate_dot.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_can_edu.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_can_occupa.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_can_sal.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
            }else{
                holder.rl_view.setBackgroundResource(R.color.bpDarker_red);
                holder.txt_can_id.setTextColor(mContext.getResources().getColor(R.color.bpWhite));
                holder.txt_candidate_name.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_candidate_dob.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_candidate_dot.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_can_edu.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_can_occupa.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
                holder.txt_can_sal.setTextColor(mContext.getResources().getColor(R.color.bpDark_gray));
            }
            holder.txt_candidate_name.setText(city.getFullname());
//                    city.getFirstName() + " "
//                    + city.getMiddleName() + " "
//                    + city.getLastName());
            holder.txt_candidate_dob.setText("Dob: "+city.getDobStr());

            holder.txt_candidate_dot.setText("Dot: "+city.getDotStr());

            holder.txt_can_id.setText(city.getCandidateId());

            holder.getTxt_can_edu().setText("Education: "+city.getEducation());

            holder.txt_can_occupa.setText("Job: "+city.getOccupation());

            holder.getTxt_can_sal().setText("Salary: "+String.valueOf(city.getSalary()));
            try {
//                Bitmap bmp = BitmapFactory.decodeByteArray(city.getProfileLogoByte(), 0, city.getProfileLogoByte().length);
//                ((ImageView) currentView.findViewById(R.id.iv_candidate_profile)).setImageBitmap(bmp);
                Glide.with(mContext).load(city.getProfileLogo())
                        .thumbnail(0.5f)
//                        .crossFade()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.iv_candidate_profile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//        Candidate city = candidate.get(position);
//
//        if (city != null) {
//
//            ((ViewHolder)holder).getTxt_candidate_name().setText(city.getFullname());
////                    city.getFirstName() + " "
////                    + city.getMiddleName() + " "
////                    + city.getLastName());
//            ((ViewHolder)holder).getTxt_candidate_dob().setText("Dob: "+city.getDobStr());
//
//            ((ViewHolder)holder).getTxt_candidate_dot().setText("Dot: "+city.getDotStr());
//
//            ((ViewHolder)holder).getTxt_can_id().setText(city.getCandidateId());
//
//            ((ViewHolder)holder).getTxt_can_edu().setText("Education: "+city.getEducation());
//
//            ((ViewHolder)holder).getTxt_can_occupa().setText("Job: "+city.getOccupation());
//
//            ((ViewHolder)holder).getTxt_can_sal().setText("Salary: "+String.valueOf(city.getSalary()));
//            try {
////                Bitmap bmp = BitmapFactory.decodeByteArray(city.getProfileLogoByte(), 0, city.getProfileLogoByte().length);
////                ((ImageView) currentView.findViewById(R.id.iv_candidate_profile)).setImageBitmap(bmp);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

    @Override
    public int getItemCount() {
        if (candidate == null) {
            return 0;
        }
        return candidate.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_candidate_name)
public        TextView txt_candidate_name;
        @BindView(R.id.txt_candidate_dob)
        public        TextView txt_candidate_dob;
        @BindView(R.id.txt_candidate_dot)
        public TextView txt_candidate_dot;
        @BindView(R.id.txt_can_id)
        public TextView txt_can_id;
        @BindView(R.id.txt_can_edu)
        public TextView txt_can_edu;
        @BindView(R.id.txt_can_occupa)
        public TextView txt_can_occupa;
        @BindView(R.id.txt_can_sal)
        public TextView txt_can_sal;

        @BindView(R.id.rl_view)
        RelativeLayout rl_view;
        @BindView(R.id.iv_candidate_profile)
        ImageView iv_candidate_profile;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        public RelativeLayout getRl_view() {
            return rl_view;
        }

        public void setRl_view(RelativeLayout rl_view) {
            this.rl_view = rl_view;
        }

        public ImageView getIv_candidate_profile() {
            return iv_candidate_profile;
        }

        public void setIv_candidate_profile(ImageView iv_candidate_profile) {
            this.iv_candidate_profile = iv_candidate_profile;
        }

        public TextView getTxt_candidate_name() {
            return txt_candidate_name;
        }

        public void setTxt_candidate_name(TextView txt_candidate_name) {
            this.txt_candidate_name = txt_candidate_name;
        }

        public TextView getTxt_candidate_dob() {
            return txt_candidate_dob;
        }

        public void setTxt_candidate_dob(TextView txt_candidate_dob) {
            this.txt_candidate_dob = txt_candidate_dob;
        }

        public TextView getTxt_candidate_dot() {
            return txt_candidate_dot;
        }

        public void setTxt_candidate_dot(TextView txt_candidate_dot) {
            this.txt_candidate_dot = txt_candidate_dot;
        }

        public TextView getTxt_can_id() {
            return txt_can_id;
        }

        public void setTxt_can_id(TextView txt_can_id) {
            this.txt_can_id = txt_can_id;
        }

        public TextView getTxt_can_edu() {
            return txt_can_edu;
        }

        public void setTxt_can_edu(TextView txt_can_edu) {
            this.txt_can_edu = txt_can_edu;
        }

        public TextView getTxt_can_occupa() {
            return txt_can_occupa;
        }

        public void setTxt_can_occupa(TextView txt_can_occupa) {
            this.txt_can_occupa = txt_can_occupa;
        }

        public TextView getTxt_can_sal() {
            return txt_can_sal;
        }

        public void setTxt_can_sal(TextView txt_can_sal) {
            this.txt_can_sal = txt_can_sal;
        }
    }

}
