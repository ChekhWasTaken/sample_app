package news.challenge.com.sample.action;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.navigation.Navigation;

import news.challenge.com.sample.component.newslist.NewsListFragmentDirections;
import news.challenge.com.sample.model.NewsEntity;

public class NewsActionListener {
    public void onNewsItemClick(View v, NewsEntity entity) {
        Navigation.findNavController(v).navigate(
                NewsListFragmentDirections
                        .actionNewsListFragmentToNewsDetailFragment(entity)
        );

    }

    public void onNewsItemLinkClick(View v, NewsEntity entity) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(entity.getArticleUrl()));
        v.getContext().startActivity(intent);
    }
}
