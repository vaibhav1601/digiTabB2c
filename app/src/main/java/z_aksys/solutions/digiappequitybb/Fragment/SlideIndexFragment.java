package z_aksys.solutions.digiappequitybb.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import z_aksys.solutions.digiappequitybb.Adapter.SlideCategoryAdapter;
import z_aksys.solutions.digiappequitybb.Adapter.SlideIndexAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.model.PitchSlide;

public class SlideIndexFragment extends Fragment {

    public ArrayList<PitchSlide> mSlides = new ArrayList<>();
    public HashMap<Integer, String> mSlideCategories = new HashMap<>();
    public TextView tvNumberOfSlides;
    private RecyclerView rvSlideNavigation;
    private RecyclerView rvSlideCategories;
    private SlideIndexAdapter mSlideIndexAdapter;
    private SlideCategoryAdapter mSlideCategoryAdapter;

    public SlideIndexFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_slide_index, container, false);

        rvSlideNavigation = view.findViewById(R.id.rv_slide_thumbnails);

        rvSlideNavigation.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rvSlideCategories = view.findViewById(R.id.rv_slide_categories);
        rvSlideCategories.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        mSlideIndexAdapter = new SlideIndexAdapter(getActivity(), mSlides, this, 0);
        mSlideCategoryAdapter = new SlideCategoryAdapter(getActivity(), mSlideCategories, mSlideIndexAdapter, 0);

        rvSlideNavigation.setAdapter(mSlideIndexAdapter);
        rvSlideCategories.setAdapter(mSlideCategoryAdapter);

        tvNumberOfSlides = view.findViewById(R.id.tv_number_of_slides);

        return view;
    }

    public void updateSlides(ArrayList<PitchSlide> slides) {
        mSlides = slides;
        mSlideIndexAdapter.updateSlides(mSlides);
    }

    public void updateCategories(HashMap<Integer, String> categories) {
        mSlideCategories = categories;
        mSlideCategoryAdapter.updateSlideCategories(mSlideCategories);
    }

    public void updateCurrentlySelectedLanguage(int selectedLanguage) {
        mSlideIndexAdapter.updateSelectedLanguage(selectedLanguage);
    }

    public void updateCurrentlySelectedCategory(int selectedCategory) {
        mSlideCategoryAdapter.updateCurrentlySelectedCategory(selectedCategory);
        mSlideIndexAdapter.updateCurrentlySelectedCategory(selectedCategory);
    }

    public void updateCurrentlySelectedSlide(int selectedSlideIndex) {
        mSlideIndexAdapter.updateCurrentlySelectedSlide(selectedSlideIndex);
    }
}
