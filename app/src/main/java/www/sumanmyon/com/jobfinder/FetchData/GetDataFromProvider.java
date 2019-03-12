package www.sumanmyon.com.jobfinder.FetchData;

import android.app.Activity;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import www.sumanmyon.com.jobfinder.Adapter.DataListAdapter;
import www.sumanmyon.com.jobfinder.ErrorAndExceptionHandler.ToastShow;
import www.sumanmyon.com.jobfinder.StandardDataStorage.CreatingStandardDataFromDifferentProviderAPIs;

import static www.sumanmyon.com.jobfinder.URLs.ProviderURLs.GitHubURL;

public class GetDataFromProvider {
    Activity activity;
    ListView listView;
    ArrayList<CreatingStandardDataFromDifferentProviderAPIs> dsFromDiffProviders;
    DataListAdapter listAdapter;
    CreatingStandardDataFromDifferentProviderAPIs ds;
    JsonArrayRequest request;

    public GetDataFromProvider(final Activity activity, ListView listView) {
        this.activity = activity;
        this.listView = listView;

        listAdapter = new DataListAdapter(activity);
        dsFromDiffProviders = new ArrayList<>();
    }

    public void getData(String URL, final String provider) {
        request = new JsonArrayRequest(Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        storeData(provider, response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                new ToastShow(activity,error.getMessage());

            }
        });

        //calling volley interface to get data
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(request);
    }

    private void storeData(String provider, JSONArray data) {
        try{
            //new ToastShow(activity,data.toString());

            for(int i=0; i<data.length(); i++){

                ds = new CreatingStandardDataFromDifferentProviderAPIs();

                JSONObject object = data.getJSONObject(i);

                if(provider.equals("GitHub")){
                    String id = object.getString("id");
                    String position = object.getString("type");
                    String url = object.getString("url");
                    String start_date = object.getString("created_at");
                    String company = object.getString("company");
                    String company_url = object.getString("company_url");
                    String location = object.getString("location");
                    String title = object.getString("title");
                    String description = object.getString("description");
                    String how_to_apply = object.getString("how_to_apply");
                    String company_logo = object.getString("company_logo");

                    ds.storeDataFromProvider(provider,
                            id,position,url,
                            start_date,company,company_url,
                            location,title,description,
                            how_to_apply,company_logo);

                    dsFromDiffProviders.add(ds);

                }else if(provider.equals("searchGov")){
                    String id = object.getString("id");
                    String position = object.getString("position_title");
                    String company = object.getString("organization_name");
                    String rate_interval_code = object.getString("organization_name");
                    String minimum = object.getString("minimum");
                    String maximum = object.getString("maximum");
                    String start_date = object.getString("start_date");
                    String end_date = object.getString("end_date");

                    //JSONArray array = object.getJSONArray("location");
                    String location = "";
//                    for(int ii=0;ii<array.length();i++){
//                        location = location+  array.get(ii).toString() + " ";
//                    }
                    String url = object.getString("url");

                    ds.storeDataFromProvider(provider,
                            id,position,company,
                            rate_interval_code,minimum,maximum,
                            start_date,end_date,location,
                            url);

                    dsFromDiffProviders.add(ds);
                }
            }
            listAdapter.store(dsFromDiffProviders);
            listView.setAdapter(listAdapter);
        }catch (Exception e){
            new ToastShow(activity,e.getMessage());
        }
    }
}