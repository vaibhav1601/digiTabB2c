package z_aksys.solutions.digiappequitybb.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import z_aksys.solutions.digiappequitybb.model.Feedback;

@Dao
public interface FeedbackModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertFeedback(Feedback feedback);


    @Query("SELECT * from feedback")
    List<Feedback> getAllFeedback();


    @Query("DELETE FROM feedback")
    int deleteFeedbackStatus();


}
