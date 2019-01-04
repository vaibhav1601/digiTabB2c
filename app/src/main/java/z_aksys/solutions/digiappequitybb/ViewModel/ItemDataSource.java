package z_aksys.solutions.digiappequitybb.ViewModel;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import z_aksys.solutions.digiappequitybb.API.RetrofitClient;
import z_aksys.solutions.digiappequitybb.App;
import z_aksys.solutions.digiappequitybb.Response.NewsResponse;

public class ItemDataSource extends PageKeyedDataSource<Integer, NewsResponse.News> {

    public static final int PAGE_SIZE = 2;
    private static final int FIRST_PAGE = 0;
    private static final String SITE_NAME = "stackoverflow";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, NewsResponse.News> callback) {
        RetrofitClient.getInstance()
                .getApi().getNews("application/json", "80w4k8ogow8k4g4ks80sg08o4kcsc04scg48kks4", "42d34bd99093094714e0257e391a810c", "test", FIRST_PAGE, PAGE_SIZE)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if (response.body() != null) {
                            // int fist=Integer.parseInt(FIRST_PAGE);
                            callback.onResult(response.body().getNews(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {

                        Toast.makeText(App.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, NewsResponse.News> callback) {

        String paramKey = String.valueOf(params.key);
        RetrofitClient.getInstance()
                .getApi().getNews("application/json", "42d34bd99093094714e0257e391a810c", "42d34bd99093094714e0257e391a810c", "test", params.key, PAGE_SIZE)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {
                            callback.onResult(response.body().getNews(), adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                        Toast.makeText(App.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();


                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, NewsResponse.News> callback) {

        //String paramKey=String.valueOf(params.key);

        RetrofitClient.getInstance()
                .getApi()
                .getNews("application/json", "42d34bd99093094714e0257e391a810c", "42d34bd99093094714e0257e391a810c", "test", params.key, PAGE_SIZE)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if (response.body() != null) {

                            //  int pageid= Integer.parseInt(response.body().getHasNext());

                           /* if(pageid<0)
                            {
                                callback.onResult(response.body().getNews(), params.key + 1);

                            }*/


                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {


                        Toast.makeText(App.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();


                    }
                });
    }
}
