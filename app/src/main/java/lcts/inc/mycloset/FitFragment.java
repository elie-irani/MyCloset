package lcts.inc.mycloset;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FitFragment extends Fragment {

    public FitFragment() {
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
        View v = inflater.inflate(R.layout.fragment_fit, container, false);

        FragmentManager fm = getFragmentManager();
        TopFragment topFragment = new TopFragment();
        fm.beginTransaction().add(R.id.top_framelayout, topFragment).commit();

        FragmentManager f = getFragmentManager();
        BottomFragment bottomFragment = new BottomFragment();
        f.beginTransaction().add(R.id.bottom_framelayout, bottomFragment).commit();

        return v;
    }
}