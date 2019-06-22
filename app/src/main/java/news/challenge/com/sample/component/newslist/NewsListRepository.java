package news.challenge.com.sample.component.newslist;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import news.challenge.com.sample.api.NewsApiService;
import news.challenge.com.sample.api.Response;
import news.challenge.com.sample.model.ArticlesResponseEntity;
import news.challenge.com.sample.model.NewsEntity;
import news.challenge.com.sample.util.Data;
import news.challenge.com.sample.util.State;

public class NewsListRepository {
    public LiveData<Data<List<NewsEntity>>> getArticles() {
        final MutableLiveData<Data<List<NewsEntity>>> data = new MutableLiveData<>();
        data.postValue(new Data<List<NewsEntity>>(State.LOADING, null));

        NewsApiService.getInstance().getArticles().addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                @SuppressWarnings("unchecked") Response<ArticlesResponseEntity> response = (Response<ArticlesResponseEntity>) o;
                List<NewsEntity> list = response.getData() == null ? null : response.getData().getNews();

                data.postValue(new Data<>(response.getState(), list));
            }
        });

        return data;
    }

}
