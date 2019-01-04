package z_aksys.solutions.digiappequitybb.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import z_aksys.solutions.digiappequitybb.model.QuestionStatus;
import z_aksys.solutions.digiappequitybb.model.QuestionStatusModel;

@Dao
public interface QuestionModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertQuestion(QuestionStatusModel questionStatusModel);

    @Query("SELECT * from questionstatusmodel where topic_id = :topicId and lesson_id=:lessonId")
    List<QuestionStatus> getQuestionStates(String topicId, String lessonId);


    @Query("SELECT * from questionstatusmodel")
    List<QuestionStatus> getAllQuestionStates();



    @Query("SELECT COUNT(score) FROM questionstatusmodel WHERE topic_id = :topicId and lesson_id=:lessonId")
    int getNumberOfRows(String topicId, String lessonId);


    @Query("DELETE FROM questionstatusmodel")
    int deleteQuestionStatus();


}
