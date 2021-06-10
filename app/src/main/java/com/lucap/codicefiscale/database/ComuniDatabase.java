package com.lucap.codicefiscale.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Comuni.class}, version = 1, exportSchema = false)
public abstract class ComuniDatabase extends RoomDatabase {
    public abstract Comuni_DAO comuniDAO();
}
