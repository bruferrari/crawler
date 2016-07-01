package ferrarib.com.app.crawler;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ferrarib.com.app.crawler.model.DataVO;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private Toolbar mToolbar;

    private ListView mDrawerList;
    private ArrayAdapter<String> adapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mDrawerList = (ListView) findViewById(R.id.drawer_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        addDrawerItems();
        setupDrawer();
        drawerItemsHandler();
        mDrawerToggle.syncState();

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mRecyclerView = (RecyclerView) findViewById(R.id.news_list_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<DataVO> dataList = fillCards();

        mAdapter = new RecyclerViewAdapter(dataList, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);

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

        return result;
    }

    private void addDrawerItems() {
        String[] array = {"Home", "Pinned", "About"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
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

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        List<DataVO> dataVOList = new ArrayList<>();
        Context ctx;

        public RecyclerViewAdapter(List<DataVO> dataVOList, Context ctx) {
            this.dataVOList = dataVOList;
            this.ctx = ctx;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            CardView cv;
            TextView title;
            TextView description;
            TextView source;
            TextView publishingDate;
            ImageView image;

            public ViewHolder(View itemView) {
                super(itemView);
                cv = (CardView) itemView.findViewById(R.id.card_view);
                title = (TextView) itemView.findViewById(R.id.card_title);
                description = (TextView) itemView.findViewById(R.id.card_description);
                source = (TextView) itemView.findViewById(R.id.card_content_source);
                publishingDate = (TextView) itemView.findViewById(R.id.card_content_publishing_date);
                image = (ImageView) itemView.findViewById(R.id.card_image);
            }
        }

        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
            View view = getLayoutInflater().inflate(R.layout.card_content_view, parent, false);
            final Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
            final ViewHolder vh = new ViewHolder(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("TAG", String.valueOf(vh.getAdapterPosition()));
                    startActivity(intent);
                }
            });
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM, d yyyy", Locale.getDefault());

            holder.title.setText(dataVOList.get(position).getTitle());
            holder.description.setText(dataVOList.get(position).getDescription());
            holder.source.setText(String.format("@ %s", dataVOList.get(position).getSource()));
            holder.publishingDate.setText(String.format("Published in %s",
                    sdf.format(dataVOList.get(position).getPublishingDate().getTime())));
            holder.image.setImageResource(dataVOList.get(position).getImageId());
        }

        @Override
        public int getItemCount() {
            return dataVOList.size();
        }
    }

}
