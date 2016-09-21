package com.psyberia.fragmentsexample.fragments;

/**
 * Created by combo on 21.09.2016.
 */

import android.net.Uri;

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
public interface IOnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
    void showPDialog();
    void hidePDialog();
    void onSectionAttached(String title);
}

/*
* // Container Activity must implement this interface
    public interface IChildFragmentListener {

    }
* */
