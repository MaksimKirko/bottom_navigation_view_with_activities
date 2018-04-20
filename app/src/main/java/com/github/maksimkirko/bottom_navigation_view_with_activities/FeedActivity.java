package com.github.maksimkirko.bottom_navigation_view_with_activities;

public class FeedActivity extends HomeScreenActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    protected int getBottomNavigationMenuItemId() {
        return R.id.action_feed;
    }
}