package com.github.maksimkirko.bottom_navigation_view_with_activities;

public class ProfileActivity extends HomeScreenActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_profile;
    }

    @Override
    protected int getBottomNavigationMenuItemId() {
        return R.id.action_profile;
    }
}
