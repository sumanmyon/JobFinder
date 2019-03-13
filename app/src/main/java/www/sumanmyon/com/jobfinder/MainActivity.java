package www.sumanmyon.com.jobfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import www.sumanmyon.com.jobfinder.FetchData.GetDataFromProvider;

import static www.sumanmyon.com.jobfinder.URLs.ProviderURLs.GitHubURL;
import static www.sumanmyon.com.jobfinder.URLs.ProviderURLs.searchGovURL;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.main_activity_listView);

        //get data from provider
        GetDataFromProvider getDataFromProvider = new GetDataFromProvider(this,listView);
        getDataFromProvider.getData(GitHubURL,"GitHub");
        getDataFromProvider.getData(searchGovURL,"searchGov");
    }
}
