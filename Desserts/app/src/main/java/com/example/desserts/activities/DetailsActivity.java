package com.example.desserts.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import com.example.desserts.R;
import com.example.desserts.activities.adaptors.ShoppingCartAdaptor;
import com.example.desserts.activities.fragments.DetailsFragment;
import com.example.desserts.cart.ShoppingCart;
import com.example.desserts.databinding.ActivityDetailsBinding;
import com.example.desserts.helper.Helpers;
import com.example.desserts.structures.Dessert;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DetailsActivity is the Activity used to show the details of a selected Dessert.
 * @author Amy Lyu
 * @author Osama Kashif
 */
public class DetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;
    private List<Dessert> allDesserts = new ArrayList<>();
    private List<Dessert> searchResults = new ArrayList<>();
    private String selectedCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Set up the custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Dynamically load the fragment
        Bundle extras = getIntent().getExtras();
        String category = extras.getString("category");
        Intent intent = getIntent();
        allDesserts = (List<Dessert>) intent.getSerializableExtra("allDesserts");
        if (category != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            DetailsFragment cF = new DetailsFragment();
            ft.replace(R.id.details_fragment_placeholder, cF);
            ft.commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");
        mSearchView.setMaxWidth(750);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(DetailsActivity.this, "query submit", Toast.LENGTH_SHORT).show();
                String searchQuery = mSearchView.getQuery().toString();
                searchResults = Helpers.search(allDesserts,searchQuery);
                selectedCategory = "searchResults";
                switchToListActivity();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        switch (item.getItemId()) {
            case R.id.action_cart:
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                } else {
                    ShoppingCartAdaptor sA = new ShoppingCartAdaptor(this, R.layout.template_image_gallery);
                    ListView lV = findViewById(R.id.shopping_cart);
                    lV.setAdapter(sA);
                    TextView cost = findViewById(R.id.total_cost);
                    new Thread() {
                        public void run() {
                            while (true) {
                                try {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            cost.setText("Total cost: $"+ ShoppingCart.getInstance().getTotalCost()+"0");
                                        }
                                    });
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                    Button confirm = findViewById(R.id.confirm_order);
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ShoppingCart.getInstance().confirmOrder();
                            sA.notifyDataSetChanged();
                            Toast.makeText(DetailsActivity.this, "Your order has been confirmed!", Toast.LENGTH_LONG).show();
                        }
                    });
                    NavigationView navigation = findViewById(R.id.nav_view);
                    navigation.bringToFront();
                    drawer.openDrawer(GravityCompat.END);
                }
                return true;
            case R.id.action_search:
                Toast.makeText(DetailsActivity.this, "searching", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void switchToListActivity() {
        Intent switchActivityIntent = new Intent(this, ListActivity.class);
        switchActivityIntent.putExtra("category", selectedCategory);
        switchActivityIntent.putExtra("searchResults", (Serializable) searchResults);
        switchActivityIntent.putExtra("allDesserts", (Serializable) allDesserts);
        startActivity(switchActivityIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}