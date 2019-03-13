package www.sumanmyon.com.jobfinder.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import www.sumanmyon.com.jobfinder.R;
import www.sumanmyon.com.jobfinder.StandardDataStorage.CreatingStandardDataFromDifferentProviderAPIs;

public class DataListAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<CreatingStandardDataFromDifferentProviderAPIs> dsFromDiffProviders;
    

    LayoutInflater layoutInflater;

    public DataListAdapter(Activity activity) {
        this.activity = activity;
        layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public void store(ArrayList<CreatingStandardDataFromDifferentProviderAPIs> dsFromDiffProviders) {
        this.dsFromDiffProviders = dsFromDiffProviders;
    }

    @Override
    public int getCount() {
        return dsFromDiffProviders.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null)
            view = layoutInflater.inflate(R.layout.list_item_layout,null);

        Holder holder = new Holder(view);
        if(dsFromDiffProviders.get(position).getCompanyLogo()!=null) {
            Picasso.get().load(dsFromDiffProviders.get(position).getCompanyLogo()).into(holder.logoImageView);
        }else {
            Picasso.get().load(R.drawable.defaultimg).into(holder.logoImageView);

        }
        holder.jobTitleTextView.setText(dsFromDiffProviders.get(position).getTitle());
        holder.companyNameTextView.setText(dsFromDiffProviders.get(position).getCompany());
        holder.locationTextView.setText(dsFromDiffProviders.get(position).getLocation());
        holder.postDateTextView.setText(dsFromDiffProviders.get(position).getStartDate());
        return view;
    }


    private class Holder{
        ImageView logoImageView;
        Button applyButton;
        TextView jobTitleTextView, companyNameTextView, locationTextView, postDateTextView;
        public Holder(View view){
            logoImageView = (ImageView)view.findViewById(R.id.main_list_imageView);
            //applyButton = (Button) view.findViewById(R.id.main_list_apply_button);
            jobTitleTextView = (TextView) view.findViewById(R.id.main_list_job_textView);
            companyNameTextView = (TextView) view.findViewById(R.id.main_list_company_textView);
            locationTextView = (TextView) view.findViewById(R.id.main_list_location_textView);
            postDateTextView = (TextView) view.findViewById(R.id.main_list_postDate_textView);
        }
    }
}
