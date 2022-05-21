package lcts.inc.mycloset;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ClosetFragment extends Fragment {


    public ClosetFragment() {
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
        View v = inflater.inflate(R.layout.fragment_closet, container, false);

        List<String> flowers = new ArrayList<>();
        flowers.add("All");
        flowers.add("Top");
        flowers.add("Bottom");
        Spinner mSpinner = v.findViewById(R.id.filter_spinner);
        ArrayAdapter mAdapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, flowers);
        mAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItemText = (String) adapterView.getItemAtPosition(i);
                ClothesFragment clothesFragment = new ClothesFragment();

                Bundle b = new Bundle();
                b.putString("message", selectedItemText);
                clothesFragment.setArguments(b);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.item_list, clothesFragment);
                fragmentTransaction.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getContext(), "No selection", Toast.LENGTH_LONG).show();
            }
        });

        FloatingActionButton add = v.findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFragment addFragment = new AddFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.host_framelayout, addFragment);
                fragmentTransaction.commit();
            }
        });


        return v;
    }
}