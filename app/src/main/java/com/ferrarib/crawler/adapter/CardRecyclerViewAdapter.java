package com.ferrarib.crawler.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.ferrarib.crawler.DetailsActivity;
import com.ferrarib.crawler.R;
import com.ferrarib.crawler.model.DataVO;
import com.ferrarib.crawler.ui.DynamicHeightImageView;

/**
 * Created by bruno on 7/2/16.
 */
public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder> {

    List<DataVO> dataVOList = new ArrayList<>();
    Context mContext;
    DynamicHeightImageView mDynamicHeightImageView;

    public CardRecyclerViewAdapter(List<DataVO> dataVOList, Context mContext) {
        this.dataVOList = dataVOList;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements Target {

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

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            float ratio = (float) bitmap.getHeight() / bitmap.getWidth();
            mDynamicHeightImageView.setHeightRatio(ratio);
            mDynamicHeightImageView.setImageBitmap(bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) { }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) { }

    }

    @Override
    public CardRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        View view = ((Activity) mContext).getLayoutInflater().inflate(R.layout.card_content_view, parent, false);
        final Intent intent = new Intent(mContext, DetailsActivity.class);
        final ViewHolder vh = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(intent);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM, d yyyy", Locale.getDefault());
        holder.title.setText(dataVOList.get(position).getTitle());

        if (descriptionExists(holder))
            holder.description.setText(dataVOList.get(position).getDescription());

        if (sourceExists(holder))
            holder.source.setText(String.format("@ %s", dataVOList.get(position).getSource()));

        if (publishingDateExists(holder))
            holder.publishingDate.setText(String.format("Published in %s",
                    sdf.format(dataVOList.get(position).getPublishingDate().getTime())));

        Picasso.with(mContext)
                .load(dataVOList.get(position).getImageId())
                .resize(400, 0)
                .into(holder.image);
    }

    private boolean publishingDateExists(ViewHolder holder) {
        return holder.publishingDate != null;
    }

    private boolean sourceExists(ViewHolder holder) {
        return holder.source != null;
    }

    private boolean descriptionExists(ViewHolder holder) {
        return holder.description != null;
    }

    @Override
    public int getItemCount() {
        return dataVOList.size();
    }
}