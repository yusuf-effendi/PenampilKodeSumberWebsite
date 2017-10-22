package com.example.effendi.AppPenampilkodesumberwebsite;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    TextView tx;
    Spinner Spinn;
    Button tombolgt;
    EditText input;
    public String url=null;
    ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tx=(TextView)findViewById(R.id.textvieww);
        Spinn=(Spinner) findViewById(R.id.spinnerr);
        tombolgt=(Button)findViewById(R.id.tombol);
        input=(EditText)findViewById(R.id.textt);
        loading=(ProgressBar)findViewById(R.id.pload);

        loading.setVisibility(View.GONE);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Spinner, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        Spinn.setAdapter(adapter);

        tombolgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = Spinn.getSelectedItem() + input.getText().toString();
                boolean valid = Patterns.WEB_URL.matcher(url).matches();

                if (valid){
                    getSupportLoaderManager().restartLoader(0, null, MainActivity.this);
                    loading.setVisibility(View.VISIBLE);
                    tx.setVisibility(View.GONE);
                } else {
                    Loader loader = getSupportLoaderManager().getLoader(0);
                    if (loader != null) {
                        loader.cancelLoad();
                    }
                    loading.setVisibility(View.GONE);
                    tx.setVisibility(View.VISIBLE);
                    tx.setText("This Website is not Valid");
                }
            }
        });


        //tv.setText(getWebsite("http://radefffactory.free.bg/")
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new adapter(this, url);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        loading.setVisibility(View.GONE);
        tx.setVisibility(View.VISIBLE);
        tx.setText(data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
