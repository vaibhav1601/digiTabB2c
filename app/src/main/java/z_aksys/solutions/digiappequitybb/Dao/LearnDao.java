package z_aksys.solutions.digiappequitybb.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import z_aksys.solutions.digiappequitybb.Response.LearnResponse;

@Dao
public interface LearnDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertTopic(LearnResponse.topics topics);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertLessons(LearnResponse.lessons lessons);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertQuestions(LearnResponse.questions lessons);


    @Query("SELECT * from topics ORDER BY topic_id ASC")
    List<LearnResponse.topics> getAllTopics();


    @Query("SELECT lessons.name from lessons where topic_id = :topicId")
    String lessionName(String topicId);


    @Query("SELECT * from lessons where topic_id = :topicId")
    List<LearnResponse.lessons> getlessons(String topicId);


    @Query("SELECT * from lessons where topic_id = :topicId  and lesson_id= :lessonId")
    List<LearnResponse.lessons> getlessonsWithId(String topicId, String lessonId);


    @Query("select * from lessons where lessons.lesson_id > :lessonId and lessons.topic_id= :topicId limit 1")
    List<LearnResponse.lessons> getNextlessons(String lessonId, String topicId);


    @Query("SELECT * FROM lessons WHERE lessons.lesson_id < :lessonId and lessons.topic_id=:topicId ORDER BY lessons.lesson_id  DESC LIMIT 1")
    List<LearnResponse.lessons> getPreviouslessons(String lessonId, String topicId);


    @Query("SELECT * from questions where lesson_id =:lessonId")
    List<LearnResponse.questions> getQuestionsWithId(String lessonId);

    @Query("select * from questions  where questions.lesson_id >:lessonId limit 1")
    List<LearnResponse.questions> getNextquestion(String lessonId);


    @Query("SELECT * FROM questions WHERE questions.lesson_id < :lessonId  ORDER BY questions.question_id ")
    List<LearnResponse.questions> getPreviousquestion(String lessonId);


    @Query("DELETE FROM topics")
    int deleteAllTopics();


    @Query("DELETE FROM lessons")
    int deleteAllLessons();


    @Query("DELETE FROM questions")
    int deleteAllQuestions();


}
