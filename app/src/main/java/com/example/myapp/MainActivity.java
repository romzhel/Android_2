package com.example.myapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    View contextMenuView;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolBar();
        initContextMenus();
        initNavigationPane();
    }

    private void initNavigationPane() {
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initContextMenus() {
        registerForContextMenu(findViewById(R.id.textview1));
        registerForContextMenu(findViewById(R.id.textview2));
        registerForContextMenu(findViewById(R.id.textview3));
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        initSearchView(menu);

        return true;
    }

    private void initSearchView(Menu menu) {
        MenuItem search = menu.findItem(R.id.main_menu_search);
        SearchView searchView = (SearchView) search.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String comment = getResources().getString(R.string.search_query);
                Toast.makeText(MainActivity.this, comment.concat(query), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_item1:
                Snackbar.make(toolbar, R.string.selected_menu_item1, Snackbar.LENGTH_SHORT).show();
                item.setChecked(!item.isChecked());
                return true;
            case R.id.main_menu_item2:
                Snackbar.make(toolbar, R.string.selected_menu_item2, Snackbar.LENGTH_SHORT).show();
                return true;
            case R.id.main_menu_item3:
                Snackbar.make(toolbar, R.string.selected_menu_item3, Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
        contextMenuView = v;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String targetObjectName = ((TextView) contextMenuView).getText().toString();
        String article = getResources().getString(R.string.my_for);
        String menuItemName = null;

        switch (item.getItemId()) {
            case R.id.context_menu_item1:
                menuItemName = getResources().getString(R.string.selected_context_menu_item1);
                break;
            case R.id.context_menu_item2:
                menuItemName = getResources().getString(R.string.selected_context_menu_item2);
                break;
            case R.id.context_menu_item3:
                menuItemName = getResources().getString(R.string.selected_context_menu_item3);
        }

        if (menuItemName != null) {
            Snackbar.make(toolbar, menuItemName.concat(" ").concat(article).concat(" ").
                    concat(targetObjectName), Snackbar.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_about:

                break;
            case R.id.nav_feedback:

                break;
            case R.id.nav_item1:

                break;
            case R.id.nav_item2:

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
