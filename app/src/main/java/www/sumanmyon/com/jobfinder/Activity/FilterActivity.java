package www.sumanmyon.com.jobfinder.Activity;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import www.sumanmyon.com.jobfinder.ErrorAndExceptionHandler.ToastShow;
import www.sumanmyon.com.jobfinder.MainActivity;
import www.sumanmyon.com.jobfinder.R;

public class FilterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] provider = { "GitHub", "searchGov" };
    Spinner spin;
    EditText positionEditText;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        spin = (Spinner) findViewById(R.id.spinner1);
        positionEditText = (EditText)findViewById(R.id.filter_job_position_editText);
        searchButton = (Button)findViewById(R.id.filter_job_search_button);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, provider);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

    }

    private void search() {
        String item = (String) spin.getSelectedItem();
        String term = positionEditText.getText().toString();

        Intent filterIntent = new Intent(FilterActivity.this, ShowDataUsingFilter.class);
        filterIntent.putExtra("provider",item);
        filterIntent.putExtra("query", term);
        startActivity(filterIntent);
        //new ToastShow(this,item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String item = provider[position];
        //Toast.makeText(getApplicationContext(), "Selected User: "+item , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
