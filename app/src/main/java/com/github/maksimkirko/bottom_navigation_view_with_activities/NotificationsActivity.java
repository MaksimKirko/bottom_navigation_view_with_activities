package com.github.maksimkirko.bottom_navigation_view_with_activities;

public class NotificationsActivity extends HomeScreenActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_notifications;
    }

    @Override
    protected int getBottomNavigationMenuItemId() {
        return R.id.action_notifications;
    }
}
