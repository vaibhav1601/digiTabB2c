package z_aksys.solutions.digiappequitybb.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import z_aksys.solutions.digiappequitybb.Request.ShareRequest;
import z_aksys.solutions.digiappequitybb.model.Share;

@Dao
public interface ShareDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertShareData(Share share);

    @Query("SELECT * from share ")
    List<ShareRequest> getAllShareDetails();


    @Query("DELETE FROM share")
    int deleteAllShareDetails();


}
