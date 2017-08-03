package com.riteshbhavsar.reshimforms;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.riteshbhavsar.reshimforms.model.Candidate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by ritesh.bhavsar on 07-07-2017.
 */

public class MyListActivity extends AppCompatActivity {
    @BindView(R.id.mainText)
    TextView text;
    List<Candidate> cities;
    @BindView(R.id.rcv_list)
    RecyclerView rcv_list;

    //    private CustomAdapter mAdapter;
    private CustomRecyclerAdapter mAdapter;
    Realm realmInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);
        try {
            realmInstance = Realm.getDefaultInstance();
        } catch (Exception e) {
            e.printStackTrace();
            // Get a Realm instance for this thread
            try {
                RealmConfiguration config = new RealmConfiguration.Builder()
                        //                    .deleteRealmIfMigrationNeeded()
                        .schemaVersion(1)
                        .migration(new Migration())
                        .build();
                realmInstance = Realm.getInstance(config);
            } catch (Exception e1) {
                e1.printStackTrace();
                RealmConfiguration config = new RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
//                        .schemaVersion(1)
//                        .migration(new Migration())
                        .build();
                realmInstance = Realm.getInstance(config);
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyListActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.nothing);

            }
        });


        rcv_list.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext()
                , rcv_list, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
//        String selectedItem = (String) getListView().getItemAtPosition(position);
                String selectedItem = cities.get(position).getFullname();
                int cid = cities.get(position).getId();
                //exception
//        int i = 100/0;
                text.setText("You clicked " + cid + " name " + selectedItem + " at position " + position);
//        Candidate cdata = realmInstance.where(Candidate.class).equalTo("id", cid).findFirst();
//        Toast.makeText(this, "Done" + cdata.getFirstName()+" "+ cdata.getContactNo(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyListActivity.this, ProfileActivity.class);

                // Get the transition name from the string
                String transitionName = getString(R.string.transition_string);
                String transitionName1 = getString(R.string.transition_string1);
                String transitionName2 = getString(R.string.transition_string2);
                String transitionName3 = getString(R.string.transition_string3);

                // Define the view that the animation will start from
//                    View viewStart = findViewById(R.id.card_view);
                View viewStart = view.findViewById(R.id.iv_candidate_profile);
                View viewStart1 = view.findViewById(R.id.txt_can_id);
                View viewStart2 = view.findViewById(R.id.txt_candidate_name);
                View viewStart3 = view.findViewById(R.id.txt_candidate_dob);


                Pair<View, String> pair1 = Pair.create(viewStart, transitionName);
                Pair<View, String> pair2 = Pair.create(viewStart1, transitionName1);
                Pair<View, String> pair3 = Pair.create(viewStart2, transitionName2);
                Pair<View, String> pair4 = Pair.create(viewStart3, transitionName3);

                ActivityOptionsCompat options =

                        ActivityOptionsCompat.makeSceneTransitionAnimation(MyListActivity.this,
                                pair1,
                                pair2,
                                pair3,
                                pair4
                        );
//                            ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,
//                                    viewStart,   // Starting view
//                                    transitionName    // The String
//                            );
                intent.putExtra("id", cid);
                startActivity(intent, options.toBundle());
//                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
//        try {
//            if (mAdapter == null) {
//                List<Candidate> cities = readFromDB();
//
////                mAdapter = new CustomAdapter(this);
//                mAdapter = new CustomRecyclerAdapter(this);
//                mAdapter.setData(cities);
////                setListAdapter(mAdapter);
//                rcv_list.setAdapter(mAdapter);
//            } else {
//                List<Candidate> cities = readFromDB();
//                mAdapter.setData(cities);
//                mAdapter.notifyDataSetChanged();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    // when an item of the list is clicked
//    @Override
//    protected void onListItemClick(ListView list, View view, int position, long id) {
//        super.onListItemClick(list, view, position, id);
////        String selectedItem = (String) getListView().getItemAtPosition(position);
//        String selectedItem = ((Candidate)getListAdapter().getItem(position)).getFullname();
//        int cid = ((Candidate)getListAdapter().getItem(position)).getId();
//
//        //exception
////        int i = 100/0;
//        text.setText("You clicked " + cid + " name "+ selectedItem + " at position " + position);
//
////        Candidate cdata = realmInstance.where(Candidate.class).equalTo("id", cid).findFirst();
////        Toast.makeText(this, "Done" + cdata.getFirstName()+" "+ cdata.getContactNo(), Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(MyListActivity.this, ProfileActivity.class);
//        intent.putExtra("id", cid);
//        startActivity(intent);
//    }

    public RealmResults<Candidate> readFromDB() {
        RealmResults<Candidate> results = realmInstance.where(Candidate.class).findAll();
        return results;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Load from file "cities.json" first time
        try {
            if (mAdapter == null) {
                cities = readFromDB();

//                mAdapter = new CustomAdapter(this);
                mAdapter = new CustomRecyclerAdapter(this);
                mAdapter.setData(cities);
//                setListAdapter(mAdapter);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                rcv_list.setLayoutManager(mLayoutManager);
                rcv_list.setItemAnimator(new DefaultItemAnimator());
                rcv_list.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                rcv_list.setAdapter(mAdapter);
            } else {
                cities = readFromDB();
                mAdapter.setData(cities);
                mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
