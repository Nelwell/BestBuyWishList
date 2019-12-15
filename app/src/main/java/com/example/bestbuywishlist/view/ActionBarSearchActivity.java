//package com.example.bestbuywishlist.view;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.widget.ArrayAdapter;
//import android.widget.SearchView;
//
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.view.MenuItemCompat;
//
//import com.example.bestbuywishlist.R;
//
//public class ActionBarSearchActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//
//        // Get ActionBar
//        ActionBar actionBar = getSupportActionBar();
//        // Set below attributes to add logo in ActionBar.
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setDisplayUseLogoEnabled(true);
//        actionBar.setLogo(R.drawable.ic_search);
//
//        actionBar.setTitle("dev2qa.com - Search Example");
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        // Inflate the search menu action bar.
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.action_bar_search, menu);
//
//        // Get the search menu.
//        MenuItem searchMenu = menu.findItem(R.id.app_bar_menu_search);
//
//        // Get SearchView object.
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenu);
//
//        // Get SearchView autocomplete object.
//        final SearchView searchAutoComplete = searchView.findViewById(R.id.search_src_text);
//        searchAutoComplete.setBackgroundColor(Color.BLUE);
//        searchAutoComplete.setTextColor(Color.GREEN);
//        searchAutoComplete.setDropDownBackgroundResource(android.R.color.holo_blue_light);
//
//        // Create a new ArrayAdapter and add data to search auto complete object.
//        String dataArr[] = {"Apple" , "Amazon" , "Amd", "Microsoft", "Microwave", "MicroNews", "Intel", "Intelligence"};
//        ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, dataArr);
//        searchAutoComplete.setAdapter(newsAdapter);
//
//        // Listen to search view item on click event.
//        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id) {
//                String queryString=(String)adapterView.getItemAtPosition(itemIndex);
//                searchAutoComplete.setText("" + queryString);
//                Toast.makeText(ActionBarSearchActivity.this, "you clicked " + queryString, Toast.LENGTH_LONG).show();
//            }
//        });
//
//        // Below event is triggered when submit search query.
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                AlertDialog alertDialog = new AlertDialog.Builder(ActionBarSearchActivity.this).create();
//                alertDialog.setMessage("Search keyword is " + query);
//                alertDialog.show();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
////
////
////        // Get the share menu item.
////        MenuItem shareMenuItem = menu.findItem(R.id.app_bar_menu_share);
////        // Because it's actionProviderClass is ShareActionProvider, so after below settings
////        // when click this menu item A sharable applications list will popup.
////        // User can choose one application to share.
////        ShareActionProvider shareActionProvider = (ShareActionProvider)MenuItemCompat.getActionProvider(shareMenuItem);
////        Intent shareIntent = new Intent(Intent.ACTION_SEND);
////        shareIntent.setType("image/*");
////        shareActionProvider.setShareIntent(shareIntent);
////
////        return super.onCreateOptionsMenu(menu);
////    }
//}
