package z_aksys.solutions.digiappequitybb.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import z_aksys.solutions.digiappequitybb.Response.NewsResponse;

import static z_aksys.solutions.digiappequitybb.ViewModel.ItemDataSource.PAGE_SIZE;

public class ItemViewModel extends ViewModel {

    public LiveData<PagedList<NewsResponse.News>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, NewsResponse.News>> liveDataSource;

    public ItemViewModel() {
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        //int first= Integer.parseInt(PAGE_SIZE);

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(PAGE_SIZE).build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)).build();
    }
}
