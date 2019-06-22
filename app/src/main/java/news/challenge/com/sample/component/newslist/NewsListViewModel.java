package news.challenge.com.sample.component.newslist;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import news.challenge.com.sample.model.NewsEntity;
import news.challenge.com.sample.util.Data;

public class NewsListViewModel extends ViewModel {
    private final NewsListRepository repository;

    private LiveData<Data<List<NewsEntity>>> newsListData;

    public NewsListViewModel() {
        repository = new NewsListRepository();
    }

    public LiveData<Data<List<NewsEntity>>> fetchNewsList() {
        return fetchNewsList(false);
    }

    public LiveData<Data<List<NewsEntity>>> fetchNewsList(boolean forceRefresh) {
        if (forceRefresh || newsListData == null) {
            newsListData = repository.getArticles();
        }

        return newsListData;
    }

    public LiveData<Data<List<NewsEntity>>> getData() {
        return newsListData;
    }
}
