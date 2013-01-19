package org.alfanous.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//
public class MainActivity extends Activity {

    /* Declare Component Objects*/
    Button search;
    Button customerSearch;
    Button setting;
    Button about;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        search = (Button) findViewById(R.id.search_btn);
        customerSearch = (Button) findViewById(R.id.custome_search_add_btn);
        about = (Button)findViewById(R.id.about);
        setting=(Button)findViewById(R.id.settings);
        initializeUI();
    }

    @Override
    public void onResume() {

        initializeUI();
        super.onResume();
    }

    /**
     * Initializes some of the UI components and test
     */
    protected void initializeUI() {
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);

            }
        });
        
         about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);

            }
        });
    }
}
