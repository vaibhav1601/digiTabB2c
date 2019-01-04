package z_aksys.solutions.digiappequitybb.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import z_aksys.solutions.digiappequitybb.Adapter.NewsAdapter;
import z_aksys.solutions.digiappequitybb.R;
import z_aksys.solutions.digiappequitybb.model.Model;
import z_aksys.solutions.digiappequitybb.model.News;
import z_aksys.solutions.digiappequitybb.model.YoutubeVideoModel;

public class TwoFragment extends Fragment {


    public Model youtubeVideoModel;
    public ArrayList<Model> youtubeVideoModelArrayList;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> albumList;
    private List<YoutubeVideoModel> youtubeVideoModelList;


    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.demo, container, false);
    }

}