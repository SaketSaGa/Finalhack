package com.ssn.skillindia.fragments.learner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.ssn.skillindia.R;
import com.ssn.skillindia.SkillIndiaApplication;
import com.ssn.skillindia.adapters.TrainingCenterAdapter;
import com.ssn.skillindia.model.TrainingCenter;
import com.ssn.skillindia.ui.LabelledSpinner;
import com.ssn.skillindia.utils.LogHelper;
import com.ssn.skillindia.utils.RealmHelper;
import com.ssn.skillindia.utils.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmResults;


public class SearchTrainingCourseFragment extends Fragment {


    private Unbinder unbinder;
    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_training_course, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

}
