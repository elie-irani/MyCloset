package lcts.inc.mycloset;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class TopFragment extends Fragment {

    public TopFragment() {
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
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_top, container, false);

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
            Toast.makeText(getContext(), "Insert top!", Toast.LENGTH_SHORT).show();
        }
            

        return recyclerView;
    }
}