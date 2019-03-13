package www.sumanmyon.com.jobfinder.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

import www.sumanmyon.com.jobfinder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Description extends Fragment {

    String description;

    TextView descriptionTextView;

    public Description() {
        // Required empty public constructor

    }
    public void data(String description) {
        this.description = description;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_description, container, false);
        descriptionTextView = (TextView)view.findViewById(R.id.fragment_description_textView);
        if(description == null){
            descriptionTextView.setText("There is no Description about company.");
        }else {
            descriptionTextView.setText(Html.fromHtml(description).toString());
        }

        return view;
    }


}
