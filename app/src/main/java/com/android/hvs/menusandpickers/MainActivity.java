package com.android.hvs.menusandpickers;

import android.app.DialogFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        switch(item.getItemId()) {

            case R.id.action_favorites:
                isFavorite = true;
                //item.setIcon(R.drawable.ic_action_fav_selected);
                Toast.makeText(this, R.string.action_favorites, Toast.LENGTH_SHORT).show();
                invalidateOptionsMenu();
                break;

            case R.id.action_remove_favorites:
                isFavorite = false;
                Toast.makeText(this, R.string.action_remove_fav, Toast.LENGTH_SHORT).show();
                invalidateOptionsMenu();
                break;

            default:

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (isFavorite) {
            menu.findItem(R.id.action_favorites).setVisible(false);
            menu.findItem(R.id.action_remove_favorites).setVisible(true);
        }

        else {
            menu.findItem(R.id.action_favorites).setVisible(true);
            menu.findItem(R.id.action_remove_favorites).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void showDatePicker(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (day_string + "/" + month_string + "/" + year_string);

        Toast.makeText(this, dateMessage, Toast.LENGTH_SHORT).show();
    }
}