package ferrarib.com.app.crawler;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import ferrarib.com.app.crawler.adapter.CardRecyclerViewAdapter;
import ferrarib.com.app.crawler.service.ApiService;
import ferrarib.com.app.crawler.model.DataVO;
import ferrarib.com.app.crawler.util.Utils;

public class MainListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private Toolbar mToolbar;

    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private ApiService apiService = new ApiService(this);
    private Utils utils;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUiComponents();
        setSupportActionBar(mToolbar);

        setUpToolbar();

        addDrawerItems();
        setupDrawer();
        drawerItemsHandler();
        mDrawerToggle.syncState();

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        ((StaggeredGridLayoutManager) mLayoutManager)
                .setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int viewWidth = mRecyclerView.getMeasuredWidth();
                        float cardViewWidth = getResources().getDimension(R.dimen.card_view_land);
                        int newSpanCount = (int) Math.floor(viewWidth/cardViewWidth);
                        ((StaggeredGridLayoutManager)mLayoutManager).setSpanCount(newSpanCount);
                        mLayoutManager.requestLayout();
                    }
                });

        mRecyclerView.setLayoutManager(mLayoutManager);

        List<DataVO> dataList = fillCards();

        mAdapter = new CardRecyclerViewAdapter(dataList, MainListActivity.this);
        mRecyclerView.setAdapter(mAdapter);

        utils = new Utils(this);
        mGoogleApiClient = utils.getGoogleApiClient();
//        try {
//            apiService.queryByPerformanceScore(8, "obama");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private void setUpToolbar() {
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    private void initializeUiComponents() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerList = (ListView) findViewById(R.id.drawer_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.news_list_recycler_view);
    }

    private List<DataVO> fillCards() {
        List<DataVO> result = new ArrayList<>();
        result.add(new DataVO("Title goes here",
                getResources().getString(R.string.mock_text),
                "yahoo.com",
                Calendar.getInstance(),
                R.drawable.image_mock));

        result.add(new DataVO("What black America won't miss about Obama",
                getResources().getString(R.string.mock_text),
                "cnn.com",
                Calendar.getInstance(),
                R.drawable.image_obama));

        result.add(new DataVO("The (Overcrowded) Sidewalks of New York",
                getResources().getString(R.string.mock_text),
                "nytimes.com",
                Calendar.getInstance(),
                R.drawable.image_sidewalk));

        return result;
    }

    private void addDrawerItems() {
        String[] array = {"Home", "Pinned", "About"};
        ArrayAdapter<String> adapter
                = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        mDrawerList.setAdapter(adapter);
    }

    private void drawerItemsHandler() {
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Snackbar.make(view, adapterView.getAdapter().getItem(i).toString() + " Clicked!",
                        Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                supportInvalidateOptionsMenu();
            }
        };
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search)
            return true;

        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
