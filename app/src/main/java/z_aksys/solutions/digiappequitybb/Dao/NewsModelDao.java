package z_aksys.solutions.digiappequitybb.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import z_aksys.solutions.digiappequitybb.Response.NewsResponse;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface NewsModelDao {

    @Query("SELECT * from news ORDER BY news_id ASC")
    LiveData<List<NewsResponse.News>> getAllNews();

    @Insert(onConflict = REPLACE)
    Long addNews(NewsResponse.News news);

    @Delete
    void deleteBorrow(NewsResponse.News news);

}
