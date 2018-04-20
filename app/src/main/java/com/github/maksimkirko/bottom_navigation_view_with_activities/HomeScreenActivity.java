package com.github.maksimkirko.bottom_navigation_view_with_activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class HomeScreenActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_navigation_view_activity_home)
    protected BottomNavigationViewEx bottomNavigationView;

    protected View notificationBadge;
    protected Menu bottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        Log.e("onCreate", this.getClass().getSimpleName());

        initBottomNavigationView();
    }

    private void initBottomNavigationView() {
        bottomNavigationView.setTextVisibility(false);
        bottomNavigationView.enableAnimation(false);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.enableAnimation(false);

        bottomMenu = bottomNavigationView.getMenu();
    }

    protected abstract int getContentViewId();

    protected abstract int getBottomNavigationMenuItemId();

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("onStart", this.getClass().getSimpleName());
        bottomMenu.findItem(getBottomNavigationMenuItemId()).setChecked(true);
        showNotificationBadge(!(this instanceof NotificationsActivity));
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("onPause", this.getClass().getSimpleName());
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("onStop", this.getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy", this.getClass().getSimpleName());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        bottomNavigationView.postDelayed(() -> {
            switch (item.getItemId()) {
                case R.id.action_feed:
                    showFeedScreen();
                    selectBottomMenuItem(R.id.action_feed);
                    break;
                case R.id.action_search:
                    showSearchScreen();
                    selectBottomMenuItem(R.id.action_search);
                    break;
                case R.id.action_new_post:
                    showNewPostScreen();
                    selectBottomMenuItem(R.id.action_new_post);
                    break;
                case R.id.action_notifications:
                    showNotificationsScreen();
                    selectBottomMenuItem(R.id.action_notifications);
                    break;
                case R.id.action_profile:
                    showLocalProfileScreen();
                    selectBottomMenuItem(R.id.action_profile);
                    break;
            }
        }, 150);
        return true;
    }

    public void selectBottomMenuItem(int id) {

    }

    public void showFeedScreen() {
        startActivity(getIntentForScreen(FeedActivity.class));
    }

    public void showSearchScreen() {
        startActivity(getIntentForScreen(SearchActivity.class));
    }

    public void showNewPostScreen() {
        startActivity(getIntentForScreen(NewPostActivity.class));
    }

    public void showNotificationsScreen() {
        startActivity(getIntentForScreen(NotificationsActivity.class));
    }

    public void showLocalProfileScreen() {
        startActivity(getIntentForScreen(ProfileActivity.class));
    }

    public void showNotificationBadge(boolean enabled) {
        if (enabled) {
            if (notificationBadge == null) {
                notificationBadge = getLayoutInflater()
                        .inflate(R.layout.layout_notification_badge, bottomNavigationView, false);

                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(notificationBadge.getLayoutParams());
                layoutParams.gravity = Gravity.END;
                layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.design_bottom_navigation_margin);

                layoutParams.rightMargin = getScreenSize(this).x / 5 / 3;

                bottomNavigationView.getBottomNavigationItemView(3)
                        .addView(notificationBadge, layoutParams);
            } else {
                notificationBadge.setVisibility(View.VISIBLE);
            }
        } else {
            if (notificationBadge != null) {
                notificationBadge.setVisibility(View.GONE);
            }
        }
    }

    private Intent getIntentForScreen(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        return intent;
    }

    @NonNull
    public Point getScreenSize(@NonNull Context context) {
        WindowManager windowManager = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
        Point size = new Point();
        if (windowManager != null) {
            Display display = windowManager.getDefaultDisplay();
            display.getSize(size);
        }
        return size;
    }
}