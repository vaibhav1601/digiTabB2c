package z_aksys.solutions.digiappequitybb.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import z_aksys.solutions.digiappequitybb.Database.PitchRoomDatabase;
import z_aksys.solutions.digiappequitybb.Response.NewsResponse;

public class NewsListViewModel extends AndroidViewModel {

    private LiveData<List<NewsResponse.News>> newsLiveData;

    private List<NewsResponse.News> newsResponseList;

    private PitchRoomDatabase pitchRoomDatabase;

    public NewsListViewModel(Application application, List<NewsResponse.News> newsResponseList) {

        super(application);

        this.newsResponseList = newsResponseList;

        pitchRoomDatabase = PitchRoomDatabase.getDatabase(this.getApplication());

        new InsertTask(pitchRoomDatabase, newsResponseList).execute();

        //newsLiveData = pitchRoomDatabase.newsModelDao().getAllNews();
    }

    public LiveData<List<NewsResponse.News>> getNewsLiveData() {
        return newsLiveData;
    }

    public void setNewsLiveData(LiveData<List<NewsResponse.News>> newsLiveData) {
        this.newsLiveData = newsLiveData;
    }

    public List<NewsResponse.News> getNewsResponseList() {
        return newsResponseList;
    }

    public void setNewsResponseList(List<NewsResponse.News> newsResponseList) {
        this.newsResponseList = newsResponseList;
    }

    public class InsertTask extends AsyncTask<String, String, Boolean> {

        List<NewsResponse.News> newsResponseList = new ArrayList<>();

        PitchRoomDatabase pitchRoomDatabase;


        public InsertTask(PitchRoomDatabase pitchRoomDatabase, List<NewsResponse.News> newsResponseList) {

            this.newsResponseList = newsResponseList;
            this.pitchRoomDatabase = pitchRoomDatabase;


        }

        @Override
        protected Boolean doInBackground(String... params) {


            for (int i = 0; i < newsResponseList.size(); i++) {
                //Long id = pitchRoomDatabase.newsModelDao().addNews(newsResponseList.get(i));
                //Log.d("inserid", "inserid>>" + id);

            }


            return true;

        }


        @Override
        protected void onPostExecute(Boolean result) {

            new GetNewsTask(pitchRoomDatabase).execute();

        }


        @Override
        protected void onPreExecute() {


        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }


    private class GetNewsTask extends AsyncTask<String, String, Boolean> {


        PitchRoomDatabase pitchRoomDatabase;


        public GetNewsTask(PitchRoomDatabase pitchRoomDatabase) {
            this.pitchRoomDatabase = pitchRoomDatabase;


        }

        @Override
        protected Boolean doInBackground(String... params) {

            //newsLiveData = pitchRoomDatabase.newsModelDao().getAllNews();

            System.out.println("list>>>" + newsResponseList.size());

            // Toast.makeText(getContext(), "doInBackground"+newsResponseList.size(), Toast.LENGTH_LONG).show();


            return true;

        }


        @Override
        protected void onPostExecute(Boolean result) {

            super.onPostExecute(result);

            //callAdapter();


        }


        @Override
        protected void onPreExecute() {

        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }

}
