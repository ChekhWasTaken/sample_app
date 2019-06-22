package news.challenge.com.sample;

import android.text.TextUtils;

import org.junit.Test;

import java.util.Observable;
import java.util.Observer;

import news.challenge.com.sample.api.NewsApiService;
import news.challenge.com.sample.api.Response;
import news.challenge.com.sample.model.ArticlesResponseEntity;
import news.challenge.com.sample.model.MediaEntity;
import news.challenge.com.sample.model.NewsEntity;
import news.challenge.com.sample.util.State;

public class APIAvailabilityTest {

    @Test
    public void testAvailability() throws Exception {
        NewsApiService.getInstance().getArticles().addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                @SuppressWarnings("unchecked") Response<ArticlesResponseEntity> response = (Response<ArticlesResponseEntity>) o;

                assert response.getState() != State.ERROR;
                assert "OK".equalsIgnoreCase(response.getData().getStatus());
                assert !response.getData().getNews().isEmpty();

                for (NewsEntity e : response.getData().getNews()) {
                    assert !TextUtils.isEmpty(e.getArticleUrl());
                    assert !TextUtils.isEmpty(e.getTitle());
                    assert !TextUtils.isEmpty(e.getSummary());

                    if (e.getMediaEntities().isEmpty()) return;

                    for (MediaEntity m : e.getMediaEntities()) {
                        assert !TextUtils.isEmpty(m.getUrl());
                    }
                }
            }
        });
    }
}
