package com.github.maksimkirko.bottom_navigation_view_with_activities;


public class NewPostActivity extends HomeScreenActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_newpost;
    }

    @Override
    protected int getBottomNavigationMenuItemId() {
        return R.id.action_new_post;
    }
}
