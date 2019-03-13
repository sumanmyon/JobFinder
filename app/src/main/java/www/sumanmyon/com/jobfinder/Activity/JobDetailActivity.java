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

    TextView jobTitleTextView, comapnyNameTextView, locationTextView, startDateTextView;
    ImageView logoImageView;

    Bundle getDataFromProvider;

    String descriptionData;
    String howToApplyData, minimumData, maximumData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        tabLayout= (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.pager);

        jobTitleTextView = (TextView)findViewById(R.id.job_detail_job_textView);
        comapnyNameTextView = (TextView)findViewById(R.id.job_detail_company_textView);
        locationTextView = (TextView)findViewById(R.id.job_detail_location_textView);
        startDateTextView = (TextView)findViewById(R.id.job_detail_start_textView);

        logoImageView = (ImageView)findViewById(R.id.job_detail_logo_imageView);

        getDataFromProvider = getIntent().getExtras();

        showingDataInFields();
        tabToolBar();
    }

    private void showingDataInFields() {
        String title = getDataFromProvider.getString("title");
        String companyName = getDataFromProvider.getString("company");
        String location = getDataFromProvider.getString("location");
        String logo = getDataFromProvider.getString("company_logo");
        String date = getDataFromProvider.getString("start_date");

        jobTitleTextView.setText(title);
        comapnyNameTextView.setText(companyName);
        locationTextView.setText(location);
        startDateTextView.setText(date);

        //for logo
        if(logo!=null){
            Picasso.get().load(logo).into(logoImageView);
        }else {
            Picasso.get().load(R.mipmap.ic_launcher).into(logoImageView);
        }

    }

    private void tabToolBar() {

        //for description
        descriptionData = getDataFromProvider.getString("description");
        final Description description = new Description();
        description.data(descriptionData);

        //for How to apply and others
        howToApplyData = getDataFromProvider.getString("how_to_apply");
        minimumData = getDataFromProvider.getString("minimum");
        maximumData = getDataFromProvider.getString("maximum");
        final HowToApply howToApply= new HowToApply();
        howToApply.data(howToApplyData,minimumData,maximumData);

        mPagerAdapter= new FragmentPagerAdapter(getSupportFragmentManager()) {
            private final Fragment[]  fragments= new Fragment[]{description,howToApply};
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
