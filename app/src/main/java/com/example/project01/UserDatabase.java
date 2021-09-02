package com.example.project01;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1, exportSchema = false)

public abstract class UserDatabase extends RoomDatabase {


    public abstract UserDao userDao();


    private static final String dbName = "user";

    private static UserDatabase userDatabase;

    public static synchronized  UserDatabase getUserDatabase(Context context){
        if(userDatabase ==  null){
            userDatabase = Room.databaseBuilder(context, UserDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return userDatabase;
    }

}
