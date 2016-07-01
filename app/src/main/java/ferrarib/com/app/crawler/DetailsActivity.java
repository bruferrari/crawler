package ferrarib.com.app.crawler;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;


/**
 * Created by bruno on 6/30/16.
 */
public class DetailsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);
        mToolbar = (Toolbar) findViewById(R.id.details_toolbar);
        setSupportActionBar(mToolbar);
        setUpToolbar();

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_layout);
//        setPaletteColor();
    }

    private void setPaletteColor() {
        ImageView image = (ImageView) findViewById(R.id.details_img);
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        if (bitmap != null && !bitmap.isRecycled()) {
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    Palette.Swatch vibrant = palette.getVibrantSwatch();
                    int mutedColor = palette.getMutedColor(ContextCompat
                            .getColor(getApplicationContext(),
                            R.color.colorPrimary));
                    int mutedDarkColor = palette.getDarkMutedColor(ContextCompat
                            .getColor(getApplicationContext(),
                                    R.color.colorPrimaryDark));
                    CollapsingToolbarLayout collapsing = (CollapsingToolbarLayout) findViewById(R.id.collapsing_layout);
                    collapsing.setStatusBarScrimColor(mutedDarkColor);
                }
            });
        }
    }

    private void setUpToolbar() {

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

}
