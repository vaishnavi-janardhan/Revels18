package mitrev.in.mitrev18.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import mitrev.in.mitrev18.R;

public class AboutUsActivity extends AppCompatActivity {
    private String TAG = "AboutUsActivity";
    String URL_SNAPCHAT  = "https://www.snapchat.com/add/revelsmit";
    String URL_TWITTER  = "http://www.twitter.com/revelsmit";
    String URL_FB  = "http://www.facebook.com/mitrevels";
    String URL_INSTA  = "http://www.instagram.com/revelsmit";
    ImageView instaIV, fbIV, snapchatIV, twitterIV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                AppBarLayout appBar=(AppBarLayout)findViewById(R.id.app_bar);
                appBar.setElevation((4 * getResources().getDisplayMetrics().density + 0.5f));
                Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle(R.string.drawer_about_us);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setElevation((4 * getResources().getDisplayMetrics().density + 0.5f));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        instaIV = (ImageView) findViewById(R.id.insta_image);
        fbIV = (ImageView)findViewById(R.id.fb_image);
        twitterIV = (ImageView)findViewById(R.id.twitter_image);
        snapchatIV = (ImageView)findViewById(R.id.snapchat_image);
        instaIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchURL(URL_INSTA);
            }
        });
        fbIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchURL(URL_FB);
            }
        });
        snapchatIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchURL(URL_SNAPCHAT);
            }
        });
        twitterIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchURL(URL_TWITTER);
            }
        });

    }
    public void launchURL(String URL){
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
            startActivity(browserIntent);
        }catch(ActivityNotFoundException e2){
            Log.e(TAG, e2.getMessage()+"\n Perhaps user does not have a Browser installed ");
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:{
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
