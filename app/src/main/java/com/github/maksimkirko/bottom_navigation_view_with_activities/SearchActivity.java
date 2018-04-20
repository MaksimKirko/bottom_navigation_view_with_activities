package com.github.maksimkirko.bottom_navigation_view_with_activities;

public class SearchActivity extends HomeScreenActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected int getBottomNavigationMenuItemId() {
        return R.id.action_search;
    }
}
