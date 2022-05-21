package lcts.inc.mycloset;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;


public class ClothesFragment extends Fragment {


    public ClothesFragment() {
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
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_clothes, container, false);

        Bundle bundle = getArguments();
        String message = bundle.getString("message");

        //converts DB to array
        Convert convert = new Convert();
        convert.convertDB(recyclerView);


        if (message.equalsIgnoreCase("all")) {

            if (Clothes.clothes != null) {

                String[] clothesNames = new String[Clothes.clothes.length];
                for (int i = 0; i < clothesNames.length; i++) {
                    clothesNames[i] = Clothes.clothes[i].getName();
                }

                byte[][] images = new byte[Clothes.clothes.length][];
                for (int i = 0; i < images.length; i++) {
                    images[i] = Clothes.clothes[i].getImageResourceId();
                }

                CaptionedImagesAdapter captionedImagesAdapter = new CaptionedImagesAdapter(clothesNames, images);
                recyclerView.setAdapter(captionedImagesAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);

            }

        } else if (message.equalsIgnoreCase("top")) {

            try {

                Clothes[] filter = Convert.filterTop(Clothes.clothes);
                String[] clothesNames = new String[filter.length];

                for (int i = 0; i < clothesNames.length; i++) {
                    clothesNames[i] = filter[i].getName();
                }
                byte[][] images = new byte[filter.length][];
                for (int i = 0; i < images.length; i++) {
                    images[i] = filter[i].getImageResourceId();
                }


                CaptionedImagesAdapter captionedImagesAdapter = new CaptionedImagesAdapter(clothesNames, images);
                recyclerView.setAdapter(captionedImagesAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
            } catch (Exception e) {
                Toast.makeText(getContext(), "No clothing!", Toast.LENGTH_SHORT).show();
            }

        } else {

            try {
                Clothes[] filter = Convert.filterBottom(Clothes.clothes);
                String[] clothesNames = new String[filter.length];

                for (int i = 0; i < clothesNames.length; i++) {
                    clothesNames[i] = filter[i].getName();
                }
                byte[][] images = new byte[filter.length][];
                for (int i = 0; i < images.length; i++) {
                    images[i] = filter[i].getImageResourceId();
                }


                CaptionedImagesAdapter captionedImagesAdapter = new CaptionedImagesAdapter(clothesNames, images);
                recyclerView.setAdapter(captionedImagesAdapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
            } catch (Exception e) {
                Toast.makeText(getContext(), "No clothing!", Toast.LENGTH_SHORT).show();
            }
        }

        return recyclerView;
    }
}