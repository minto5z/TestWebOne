package com.blogspot.larn4android.testwebone;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button alert;
    //flag for internet conection status
    Boolean isInternetPresent = false ;
    // Connection detector class
    com.blogspot.larn4android.testwebone.ConnectionDetector cd;
    Button toast;



    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            // creating connection detector class instance
            cd = new com.blogspot.larn4android.testwebone.ConnectionDetector(getApplicationContext());
            // get Internet status
            isInternetPresent = cd.isConnectingToInternet();


            // check for Internet status
            if (isInternetPresent) {
                // Internet Connection is Present
                // make HTTP requests
//                showAlertDialog(AndroidDetectInternetConnectionActivity.this, "Internet Connection",
//                        "You have internet connection", true);
//                new AlertDialog.Builder(this)
//                        .setTitle("Whoops")
//                        .setIcon(R.drawable.success)
//                        .setMessage("Internet conection is found!")
//                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dlg, int sumthin) {
//                                // do nothing – it will close on its own
//                            }
//                        })
//                        .show();
                webView = (WebView) findViewById(R.id.webView);

                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webView.loadUrl("http://basarkhoj.com");
                webView.setWebViewClient(new WebViewClient());
            } else {
                // Internet connection is not present
                // Ask user to connect to Internet
//                showAlertDialog(AndroidDetectInternetConnectionActivity.this, "No Internet Connection",
//                        "You don't have internet connection.", false);
                new AlertDialog.Builder(this)
                        .setTitle("Whoops")
                        .setIcon(R.drawable.fail)

                        .setMessage("Internet conection is not found!")
                        .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dlg, int sumthin) {
                                // do nothing – it will close on its own
                            }
                        })
                        .setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }






//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
