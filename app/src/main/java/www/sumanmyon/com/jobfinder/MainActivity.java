package www.sumanmyon.com.jobfinder;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import www.sumanmyon.com.jobfinder.Activity.FilterActivity;
import www.sumanmyon.com.jobfinder.CheckNetwork.NetworkConnection;
import www.sumanmyon.com.jobfinder.ErrorAndExceptionHandler.ToastShow;
import www.sumanmyon.com.jobfinder.FetchData.GetDataFromProvider;

import static www.sumanmyon.com.jobfinder.URLs.ProviderURLs.GitHubURL;
import static www.sumanmyon.com.jobfinder.URLs.ProviderURLs.searchGovURL;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    FloatingActionButton fab;

    NetworkConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.main_activity_listView);
        fab = (FloatingActionButton)findViewById(R.id.fab);

        connection = new NetworkConnection(getApplicationContext());
        if(connection.isNetworkConnection()){
            //get data from provider
            GetDataFromProvider getDataFromProvider = new GetDataFromProvider(this,listView);
            getDataFromProvider.getData(GitHubURL,"GitHub");
            getDataFromProvider.getData(searchGovURL,"searchGov");
        }else {
            new ToastShow(this,"Please check your network.");
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterIntent = new Intent(MainActivity.this,FilterActivity.class);
                startActivity(filterIntent);
            }
        });
    }
}
