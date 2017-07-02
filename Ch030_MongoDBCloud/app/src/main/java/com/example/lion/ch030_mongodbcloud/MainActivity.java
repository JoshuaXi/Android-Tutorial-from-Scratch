package com.example.lion.ch030_mongodbcloud;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.lion.ch030_mongodbcloud.Class.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lstView;
    Button btnAdd, btnEdit, btnDelete;

    EditText edtUser;
    User userSelected = null;
    List<User> users = new ArrayList<User>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstView = (ListView) findViewById(R.id.lstView);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        edtUser = (EditText) findViewById(R.id.edtUsername);

        //Load data when app opened
        new GetData().execute(Common.getAddressAPI());

        //Add event to button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PostData(edtUser.getText().toString()).execute(Common.getAddressAPI());
            }
        });

    }

    //Function process
    class GetData extends AsyncTask<String, Void, String>{

        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        //ProgressBar pd = new ProgressBar(MainActivity.this);



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Pre Process
            pd.setTitle("Please wait....");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            //Running process...
            String stream = null;
            String urlString = params[0];

            HTTPDataHandler http = new HTTPDataHandler();
            stream = http.GetHTTPData(urlString);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Done process

            //We will use GSon to parse Json to Class
            Gson gson = new Gson();
            Type listType =  new TypeToken<List<User>>(){}.getType();
            users = gson.fromJson(s, listType); // parse to List
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(), users); //Create adapter
            lstView.setAdapter(adapter); // set Adapter to ListView
            pd.dismiss();
        }
    }

    class PostData extends AsyncTask<String, String, String>{

        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        String userName;

        public PostData(String userName) {
            this.userName = userName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please wait...");
            pd.show();
        }



        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];

            HTTPDataHandler hh = new HTTPDataHandler();
            String json = "(\"user\":\""+userName+"\")";
            hh.PostHTTPData(urlString, json);
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //Refresh Data
            new GetData().execute(Common.getAddressAPI());

            pd.dismiss();
        }
    }
}
