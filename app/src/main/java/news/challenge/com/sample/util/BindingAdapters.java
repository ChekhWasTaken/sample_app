package news.challenge.com.sample.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import news.challenge.com.sample.R;

public class BindingAdapters {
    @BindingAdapter(value = {"app:imageUrl", "app:roundedCorners", "app:centerCrop"}, requireAll = false)
    public static void downloadImageFromUrl(ImageView imageView, String imageUrl, boolean roundedCorners, boolean centerCrop) {
        if (TextUtils.isEmpty(imageUrl)) return;

        Context context = imageView.getContext();

        RequestOptions options = new RequestOptions().placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder);

        if (centerCrop) {
            options = options.transform(new CenterCrop());
        }

        if (roundedCorners) {
            options = options.transform(new RoundedCorners(32));
        }

        Glide.with(context).load(imageUrl)
                .apply(options)
                .into(imageView);
    }
}
