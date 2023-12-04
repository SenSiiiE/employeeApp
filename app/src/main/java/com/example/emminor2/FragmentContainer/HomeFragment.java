package com.example.emminor2.FragmentContainer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.emminor2.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    public HomeFragment() {
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
        getActivity().setTitle("Employee Turnover");

        View view= inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.emp6,"Employee Turnover", ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.emp1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.emp3,ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.emp4, ScaleTypes.FIT));

        imageList.add(new SlideModel(R.drawable.emp2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.emp5, ScaleTypes.FIT));



        ImageSlider imageSlider = view.findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);

        return view;
    }
}