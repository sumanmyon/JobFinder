package www.sumanmyon.com.jobfinder.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import www.sumanmyon.com.jobfinder.ErrorAndExceptionHandler.ToastShow;
import www.sumanmyon.com.jobfinder.Fragments.Description;
import www.sumanmyon.com.jobfinder.Fragments.HowToApply;
import www.sumanmyon.com.jobfinder.R;

public class JobDetailActivity extends AppCompatActivity {
    TabLayout tabLayout;
    FragmentPagerAdapter mPagerAdapter;
    ViewPager viewPager;

    TextView jobTitleTextView, comapnyNameTextView, locationTextView;
    ImageView logoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        tabLayout= (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.pager);

        jobTitleTextView = (TextView)findViewById(R.id.job_detail_job_textView);
        comapnyNameTextView = (TextView)findViewById(R.id.job_detail_company_textView);
        locationTextView = (TextView)findViewById(R.id.job_detail_location_textView);

        logoImageView = (ImageView)findViewById(R.id.job_detail_logo_imageView);

        showingDataInFields();
        tabToolBar();
    }

    private void showingDataInFields() {
        Bundle getDataFromProvider = getIntent().getExtras();
        String title = getDataFromProvider.getString("title");
        String companyName = getDataFromProvider.getString("company");
        String location = getDataFromProvider.getString("location");
        String logo = getDataFromProvider.getString("company_logo");

        jobTitleTextView.setText(title);
        comapnyNameTextView.setText(companyName);
        locationTextView.setText(location);

        //for logo
        if(logo!=null){
            Picasso.get().load(logo).into(logoImageView);
        }else {
            Picasso.get().load(R.mipmap.ic_launcher).into(logoImageView);
        }

    }

    private void tabToolBar() {
        mPagerAdapter= new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[]  fragments= new Fragment[]{new Description(),new HowToApply()};
            private  final String title[]= new String[]{"Description","HowToApply"};
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        };
        viewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
