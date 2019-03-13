package www.sumanmyon.com.jobfinder.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import www.sumanmyon.com.jobfinder.ErrorAndExceptionHandler.ToastShow;
import www.sumanmyon.com.jobfinder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HowToApply extends Fragment {
    String howToApplyData;
    String minimumData;
    String maximumData;

    TextView howToApplyTextView, minimumTextView, maximumTextView;
    Button applyButton;

    public HowToApply() {
        // Required empty public constructor
    }

    public void data(String howToApplyData, String minimumData, String maximumData) {
        this.howToApplyData = howToApplyData;
        this.minimumData = minimumData;
        this.maximumData = maximumData;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_how_to_apply, container, false);

        //casting
        howToApplyTextView = (TextView)view.findViewById(R.id.fragment_howtoapply_2_textView);
        minimumTextView = (TextView)view.findViewById(R.id.fragment_howtoapply_minimum_2_textView);
        maximumTextView = (TextView)view.findViewById(R.id.fragment_howtoapply_maximum_2_textView);

        applyButton = (Button) view.findViewById(R.id.fragment_howtoapply_button);

        //showing data in textviews'
        if(howToApplyData!=null)
            howToApplyTextView.setText(Html.fromHtml(howToApplyData));
        if(minimumData!=null)
            minimumTextView.setText(minimumData);
        if(maximumData!=null)
            maximumTextView.setText(maximumData);

        //Apply
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apply();
            }
        });
        return view;
    }

    private void apply() {
        new ToastShow(getActivity(),"Apply Successful");
    }
}
