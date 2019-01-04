package z_aksys.solutions.digiappequitybb.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import z_aksys.solutions.digiappequitybb.model.QuestionStatus;

@Dao
public interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertQuestion(QuestionStatus questionStatus);

    @Query("SELECT * from questionstatus where topic_id = :topicId and lesson_id=:lessonId")
    List<QuestionStatus> getQuestionStates(String topicId, String lessonId);


    @Query("SELECT COUNT(score) FROM questionstatus WHERE topic_id = :topicId and lesson_id=:lessonId")
    int getNumberOfRows(String topicId, String lessonId);


    @Query("DELETE FROM questionstatus WHERE topic_id = :topicId and lesson_id=:lessonId ")
    int deleteQuestionStatus(String topicId, String lessonId);


}
