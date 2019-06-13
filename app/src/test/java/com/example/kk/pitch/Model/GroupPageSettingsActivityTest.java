package com.example.kk.pitch.Model;


import android.content.Context;
import android.support.design.internal.NavigationMenu;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.junit.Test;

import static org.junit.Assert.*;

public class GroupPageSettingsActivityTest
{
    private static GroupPageSettingsActivity activity = new GroupPageSettingsActivity();
    private static View view = new View();
    private static Context context = new ContextThemeWrapper(view);
    private static Menu menu = new NavigationMenu(context);

    @Test
    public static void testonCreateOptionsMenu()
    {
        assertTrue(activity.onCreateOptionsMenu(menu));
    }

    @Test
    public static void testonOptionsItemsSelected()
    {
        MenuItem item = new MenuItem(view);
        assertTrue(activity.onNavigationItemSelected(item));
    }

    @Test
    public static void testOnNavigationItemsSelected()
    {
        MenuItem item = new MenuItem(view);
        assertTrue(activity.onNavigationItemSelected(item));
    }


}