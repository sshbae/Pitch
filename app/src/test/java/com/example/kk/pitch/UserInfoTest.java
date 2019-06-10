package com.example.kk.pitch;

import com.example.kk.pitch.Model.UserInfo;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UserInfoTest {

    @Test
    public void testName(){
        assertEquals(UserInfo.getInstance().getName(), null);
        UserInfo.getInstance().setName("mario");
        assertEquals(UserInfo.getInstance().getName(), "mario");
    }

    @Test
    public void testUserName(){
        assertEquals(UserInfo.getInstance().getUsername(), null);
        UserInfo.getInstance().setUsername("mario_64 <3 u");
        assertEquals(UserInfo.getInstance().getUsername(), "mario_64 <3 u");
    }
}
