package z_aksys.solutions.digiappequitybb.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;
import android.util.Log;

import z_aksys.solutions.digiappequitybb.Response.NewsResponse;

import static android.support.constraint.Constraints.TAG;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, NewsResponse.News>> itemLiveDataSource = new MutableLiveData<>();

    public ItemDataSourceFactory() {
        itemLiveDataSource = new MutableLiveData<>();
    }

    @Override
    public DataSource<Integer, NewsResponse.News> create() {

        Log.d(TAG, "create: ");
        ItemDataSource itemDataSource = new ItemDataSource();
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, NewsResponse.News>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
