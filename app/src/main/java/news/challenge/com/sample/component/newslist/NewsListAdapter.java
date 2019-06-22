package news.challenge.com.sample.component.newslist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import news.challenge.com.sample.action.NewsActionListener;
import news.challenge.com.sample.databinding.ListItemNewsEntryBinding;
import news.challenge.com.sample.model.NewsEntity;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListViewHolder> {
    private final List<NewsEntity> data = new ArrayList<>();
    private final NewsActionListener listener;

    public NewsListAdapter(NewsActionListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ListItemNewsEntryBinding binding = ListItemNewsEntryBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);

        return new NewsListViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListViewHolder newsListViewHolder, int position) {
        NewsEntity entity = data.get(position);

        newsListViewHolder.bind(entity);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void submit(List<NewsEntity> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void set(List<NewsEntity> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
