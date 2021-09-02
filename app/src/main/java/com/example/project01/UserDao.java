package com.example.project01;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * from users where username=(:username) and password=(:password)")
    UserEntity login(String username, String password);
}
