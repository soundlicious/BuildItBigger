package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import udacity.com.gotham.ArkhamAsylumActivity;


public class MainActivity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<String> {
    public static final int JOKER_VENOM_LOADER = 100;
    private String jokerVenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportLoaderManager().initLoader(JOKER_VENOM_LOADER, null, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getSupportLoaderManager().destroyLoader(JOKER_VENOM_LOADER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(JOKER_VENOM_LOADER, null, this);
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

    public void tellJoke(View view) {
        if (!TextUtils.isEmpty(jokerVenom)) {
            Intent intent = new Intent(MainActivity.this, ArkhamAsylumActivity.class);
            intent.putExtra(ArkhamAsylumActivity.JOKER_VENOM, jokerVenom);
            startActivity(intent);
        } else {
            Toast.makeText(this, getString(R.string.no_joke_found), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new JokerVenomAsync(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        jokerVenom = data;
    }


    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public static class JokerVenomAsync extends android.support.v4.content.AsyncTaskLoader<String> {
        private String jokerVenom;

        public JokerVenomAsync(Context context) {
            super(context);
        }

        @Override
        protected void onStartLoading() {
            if (jokerVenom == null)
                forceLoad();
            else
                deliverResult(jokerVenom);
        }

        @Override
        public String loadInBackground() {
            MyApi gothamService = com.udacity.gradle.builditbigger.GothamService.getInstance();
            try {
                return gothamService.sayHi().execute().getData();
            } catch (IOException e) {
                return "";
            }
        }

        @Override
        public void deliverResult(String data) {
            jokerVenom = data;
            super.deliverResult(data);
        }
    }


}


