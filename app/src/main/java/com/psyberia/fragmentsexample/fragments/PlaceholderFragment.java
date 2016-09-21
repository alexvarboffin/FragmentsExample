package com.psyberia.fragmentsexample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psyberia.fragmentsexample.R;

/**
 * Created by combo on 19.09.2016.
 */
public class PlaceholderFragment extends Fragment {

    IOnFragmentInteractionListener mCallback;

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static PlaceholderFragment newInstance(CharSequence sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, String.valueOf(sectionNumber));
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
        Log.e("tag", "000000000000000000");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        //((TextView) rootView.findViewById(R.id.section_label)).setText("#######");
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (IOnFragmentInteractionListener) context;
            mCallback.onSectionAttached(getArguments().getString(ARG_SECTION_NUMBER));
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }
}