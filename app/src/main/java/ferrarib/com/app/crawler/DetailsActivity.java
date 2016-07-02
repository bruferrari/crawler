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
import com.squareup.picasso.Picasso;
import ferrarib.com.app.crawler.ui.DynamicHeightImageView;


/**
 * Created by bruno on 6/30/16.
 */
public class DetailsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private DynamicHeightImageView dynamicHeightImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_view);
        initializeUiComponents();
        setSupportActionBar(mToolbar);
        setUpToolbar();
        setImage();
    }

    private void initializeUiComponents() {
        mToolbar = (Toolbar) findViewById(R.id.details_toolbar);
        dynamicHeightImageView = (DynamicHeightImageView) findViewById(R.id.details_img);
    }

    private void setImage() {
        Picasso.with(this)
                .load(R.drawable.image_mock)
                .fit().centerCrop()
                .into(dynamicHeightImageView);
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
