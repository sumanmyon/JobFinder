package www.sumanmyon.com.jobfinder.Activity;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import www.sumanmyon.com.jobfinder.ErrorAndExceptionHandler.ToastShow;
import www.sumanmyon.com.jobfinder.FetchData.GetDataFromProvider;
import www.sumanmyon.com.jobfinder.R;

import static www.sumanmyon.com.jobfinder.URLs.ProviderURLs.GitHubURL;
import static www.sumanmyon.com.jobfinder.URLs.ProviderURLs.searchGovURL;

public class ShowDataUsingFilter extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_using_filter);

        listView = (ListView)findViewById(R.id.show_data_using_filter_activity_listView);

        Bundle bundle = getIntent().getExtras();
        String provider = bundle.getString("provider");
        String words = bundle.getString("query");

        String getURLForSearch = null;
        if(provider.equals("GitHub")){
            getURLForSearch = GitHubURL;
        }
        else if(provider.equals("searchGov"))
            getURLForSearch = searchGovURL;

        if(!words.equals("")){
            if(provider.equals("GitHub")){
                String query = "?description="+convertingWordsToQuery(words);
                getURLForSearch = getURLForSearch + query;
            }
            else if(provider.equals("searchGov")) {
                String query = "?query="+convertingWordsToQuery(words);
                getURLForSearch = getURLForSearch + query;
            }

        }
        new ToastShow(this,getURLForSearch);
        
        //get data from provider
        GetDataFromProvider getDataFromProvider = new GetDataFromProvider(this,listView);
        getDataFromProvider.getData(getURLForSearch,provider);
    }

    private String convertingWordsToQuery(String words) {
        String[] convert = words.split(" ");
        String converted = "";

        if(convert.length == 1){
            converted = convert[0];
        }else {
            for(int i=0; i<convert.length; i++){
                if(i<convert.length-1)
                    converted = converted +convert[i]+"+";
                else
                    converted = converted +convert[i];
            }
        }


        return converted;
    }
}
