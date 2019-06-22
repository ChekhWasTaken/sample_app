package news.challenge.com.sample.component.newsdetail;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.security.InvalidParameterException;
import java.util.List;

import news.challenge.com.sample.R;
import news.challenge.com.sample.model.MediaEntity;

import static news.challenge.com.sample.util.BindingAdapters.downloadImageFromUrl;

public class NewsDetailPagerAdapter extends PagerAdapter {
    private final List<MediaEntity> mediaEntities;

    public NewsDetailPagerAdapter(@NonNull List<MediaEntity> mediaEntities) {
        if (mediaEntities.isEmpty())
            throw new InvalidParameterException("Should contain at least one image");

        this.mediaEntities = mediaEntities;
    }

    @Override
    public int getCount() {
        return mediaEntities.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return o.equals(view.getTag(R.id.media_entity));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        MediaEntity entity = mediaEntities.get(position);

        ImageView imageView = new ImageView(container.getContext());
        imageView.setTag(R.id.media_entity, entity.getUrl());

        downloadImageFromUrl(imageView, entity.getUrl(), false, true);

        container.addView(imageView);

        return entity.getUrl();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        int childIndex = -1;

        for (int i = 0; i < container.getChildCount(); i++) {
            View child = container.getChildAt(i);

            if (child.getTag(R.id.media_entity).equals(object)) {
                childIndex = i;
                break;
            }
        }

        if (childIndex == -1) return;

        container.removeViewAt(childIndex);
    }
}
