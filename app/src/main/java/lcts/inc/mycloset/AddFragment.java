package lcts.inc.mycloset;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import static android.app.Activity.RESULT_OK;


public class AddFragment extends Fragment {

    ImageView imageView;
    Button button;
    Spinner type;
    EditText name;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        Button add = v.findViewById(R.id.save_button);
        type = (Spinner) v.findViewById(R.id.spinner);
        name = v.findViewById(R.id.name_edit_text);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //gets name, type and image and stores them in DB
                try {
                    BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    byte[] data = getBitmapAsByteArray(bitmap);

                    String clothetype = type.getSelectedItem().toString();
                    String clothename = name.getText().toString();

                    BitmapFactory.decodeByteArray(data, 0, data.length);
                    try {
                        ClosetSQLiteOpenHelper scoreSQLiteOpenHelper = new ClosetSQLiteOpenHelper(getContext());
                        SQLiteDatabase db = scoreSQLiteOpenHelper.getWritableDatabase();
                        scoreSQLiteOpenHelper.insertC(db, clothetype, clothename, data);
                        db.close();
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "DB error", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getContext(), "Fill all fields", Toast.LENGTH_SHORT).show();
                }

                //returns the user to closet fragment and refreshes the list so that the new item appears in card view
                ClosetFragment closetFragment = new ClosetFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.host_framelayout, closetFragment);
                fragmentTransaction.commit();

            }
        });


        //insert button
        imageView = v.findViewById(R.id.image_preview);
        button = v.findViewById(R.id.insert_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageFromGallery();
            }
        });

        return v;
    }


    private void PickImageFromGallery() {
        //opens the gallery to select an image

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageView.setImageURI(data.getData());
        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        //converts a Bitmap into byte[] for the images

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}