package com.example.kk.pitch;

import com.example.kk.pitch.Model.GroupObject;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GroupObjectTest {

    @Test
    public void testGroupName(){
        GroupObject groupObject = new GroupObject("group 1");
        assertEquals(groupObject.getName(), "group 1");
        groupObject.setName("not group 1");
        assertNotEquals(groupObject.getName(), "group 1");
    }

    @Test
    public void testGroupMembers(){
        GroupObject groupObject = new GroupObject("group 1");
        groupObject.addMembers("brian", "tsai");
        groupObject.addMembers("joe", "mama");
        assertTrue(groupObject.getMembers().containsKey("brian"));
        assertTrue(groupObject.getMembers().containsKey("joe"));
    }
}
