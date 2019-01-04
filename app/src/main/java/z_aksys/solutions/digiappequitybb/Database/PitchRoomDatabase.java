package z_aksys.solutions.digiappequitybb.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import z_aksys.solutions.digiappequitybb.Dao.FeedbackModelDao;
import z_aksys.solutions.digiappequitybb.Dao.LearnDao;
import z_aksys.solutions.digiappequitybb.Dao.NewsDao;
import z_aksys.solutions.digiappequitybb.Dao.QuestionDao;
import z_aksys.solutions.digiappequitybb.Dao.QuestionModelDao;
import z_aksys.solutions.digiappequitybb.Dao.ShareDao;
import z_aksys.solutions.digiappequitybb.Response.LearnResponse;
import z_aksys.solutions.digiappequitybb.Response.NewsResponse;
import z_aksys.solutions.digiappequitybb.model.Feedback;
import z_aksys.solutions.digiappequitybb.model.QuestionStatus;
import z_aksys.solutions.digiappequitybb.model.QuestionStatusModel;
import z_aksys.solutions.digiappequitybb.model.Share;

@Database(entities = {NewsResponse.News.class, LearnResponse.topics.class, LearnResponse.lessons.class, LearnResponse.questions.class, QuestionStatus.class, QuestionStatusModel.class, Feedback.class, Share.class}, version = 3, exportSchema = true)
public abstract class PitchRoomDatabase extends RoomDatabase {

    private static volatile PitchRoomDatabase INSTANCE;

    public static PitchRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PitchRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PitchRoomDatabase.class, "pitch_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract NewsDao newsDao();

    public abstract LearnDao learnDao();

    public abstract QuestionDao questionDao();

    public abstract QuestionModelDao questionModelDao();

    public abstract FeedbackModelDao feedbackModelDao();

    public abstract ShareDao shareDao();

}
