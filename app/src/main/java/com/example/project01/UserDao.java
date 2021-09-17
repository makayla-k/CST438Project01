package com.example.project01;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {


    @Insert
    void registerUser(UserEntity userEntity);
    @Update
    void update(UserEntity userEntity);
    @Delete
    void delete(UserEntity userEntity);


    @Query("SELECT * from users where username=(:username) and password=(:password)")
    UserEntity login(String username, String password);
}
