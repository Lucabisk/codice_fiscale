package com.lucap.codicefiscale.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import static android.transition.Fade.IN;

@Dao
public interface Comuni_DAO {

    @Query("SELECT * FROM comuni")
    List<Comuni> getAll();

    @Query("SELECT CodiceCatastale FROM Comuni WHERE Comune IN (:com)")
    String  getCc(String com);

}
