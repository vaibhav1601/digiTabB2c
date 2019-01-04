package z_aksys.solutions.digiappequitybb.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import z_aksys.solutions.digiappequitybb.Response.NewsResponse;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(NewsResponse.News news);

    @Query("DELETE FROM news")
    int deleteAll();

    @Query("SELECT * from news ORDER BY news_id ASC")
    List<NewsResponse.News> getAllnews();


}
