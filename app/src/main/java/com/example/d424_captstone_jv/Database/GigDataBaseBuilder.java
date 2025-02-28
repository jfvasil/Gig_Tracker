package com.example.d424_captstone_jv.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.d424_captstone_jv.DAO.UserDao;
import com.example.d424_captstone_jv.DAO.GigDao;
import com.example.d424_captstone_jv.Entities.User;
import com.example.d424_captstone_jv.Entities.Gig;

@Database(entities = {User.class,
        Gig.class},
        version = 1,
        exportSchema = false)
public abstract class GigDataBaseBuilder extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract GigDao gigDao();

    private static volatile GigDataBaseBuilder INSTANCE;

    static GigDataBaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GigDataBaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                                    GigDataBaseBuilder.class,
                                    "gig_tracker_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
