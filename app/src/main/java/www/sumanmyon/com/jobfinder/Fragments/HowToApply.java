package www.sumanmyon.com.jobfinder.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import www.sumanmyon.com.jobfinder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HowToApply extends Fragment {


    public HowToApply() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_how_to_apply, container, false);
    }

}
