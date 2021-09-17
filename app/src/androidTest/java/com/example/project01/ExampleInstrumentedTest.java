package com.example.project01;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //UserDao test = Room.databaseBuilder(appContext,UserDatabase.class, UserDatabase.dbName)
         //       .allowMainThreadQueries()
           //     .build()
             //   .getUserDatabase();

        UserEntity user = new UserEntity("name", "pass");
   //     UserDatabase.userDao().delete(user);
     //   UserDatabase.userDao().registerUser(user);
       // UserEntity usertwo = UserDatabase.getUserDatabase(user.getUsername());
        //assertEquals(user,usertwo);
        //usertwo.setPassword("userDao is highlighted red");
        //UserDatabase.userDao().update(usertwo);
        //if you look at the vid real different the way things are called compared to here
       // UserEntity userthre = UserDatabase.getUserDatabase(user.getUsername());
       // assertNotEquals(user,userthre);
        //assertEquals(usertwo,userthre);
    assertEquals("com.example.project01", appContext.getPackageName());
    }
}