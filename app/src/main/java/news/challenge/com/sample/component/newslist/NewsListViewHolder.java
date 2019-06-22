package news.challenge.com.sample.component.newslist;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import news.challenge.com.sample.action.NewsActionListener;
import news.challenge.com.sample.databinding.ListItemNewsEntryBinding;
import news.challenge.com.sample.model.NewsEntity;

public class NewsListViewHolder extends RecyclerView.ViewHolder {
    private final ListItemNewsEntryBinding binding;

    public NewsListViewHolder(@NonNull ListItemNewsEntryBinding binding, NewsActionListener listener) {
        super(binding.getRoot());

        this.binding = binding;
        this.binding.setListener(listener);
    }

    public void bind(NewsEntity entity) {
        binding.setNewsEntity(entity);
    }
}
