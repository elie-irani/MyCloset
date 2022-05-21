package lcts.inc.mycloset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permission, PERMISSION_CODE);
            }
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        ClosetFragment closetFragment = new ClosetFragment();
        fm.beginTransaction().add(R.id.host_framelayout, closetFragment).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                drawerLayout.closeDrawers();
                FrameLayout frameLayout = findViewById(R.id.host_framelayout);
                frameLayout.removeAllViews();

                if (item.toString().equalsIgnoreCase("Closet")) {

                    navigationView.getMenu().getItem(0).setChecked(true);
                    navigationView.getMenu().getItem(1).setChecked(false);
                    navigationView.getMenu().getItem(2).setChecked(false);
                    navigationView.getMenu().getItem(3).setChecked(false);

                    ClosetFragment closetFragment = new ClosetFragment();
                    fm.beginTransaction().add(R.id.host_framelayout, closetFragment).commit();

                } else if (item.toString().equalsIgnoreCase("Fit")) {

                    navigationView.getMenu().getItem(0).setChecked(false);
                    navigationView.getMenu().getItem(1).setChecked(true);
                    navigationView.getMenu().getItem(2).setChecked(false);
                    navigationView.getMenu().getItem(3).setChecked(false);

                    FitFragment fitFragment = new FitFragment();
                     fm.beginTransaction().add(R.id.host_framelayout, fitFragment).commit();


                } else if (item.toString().equalsIgnoreCase("Recommend")) {

                    navigationView.getMenu().getItem(0).setChecked(false);
                    navigationView.getMenu().getItem(1).setChecked(false);
                    navigationView.getMenu().getItem(2).setChecked(true);
                    navigationView.getMenu().getItem(3).setChecked(false);

                     RecommendFragment recommendationFragment = new RecommendFragment();
                    fm.beginTransaction().replace(R.id.host_framelayout, recommendationFragment).commit();

                } else {

                    navigationView.getMenu().getItem(0).setChecked(false);
                    navigationView.getMenu().getItem(1).setChecked(false);
                    navigationView.getMenu().getItem(2).setChecked(false);
                    navigationView.getMenu().getItem(3).setChecked(true);

                    ContactFragment contactFragment = new ContactFragment();
                    fm.beginTransaction().replace(R.id.host_framelayout, contactFragment).commit();
                }
                return false;
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void PickImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imageView.setImageURI(data.getData());
        }
    }
}