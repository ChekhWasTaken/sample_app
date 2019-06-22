package news.challenge.com.sample.api;

import news.challenge.com.sample.model.ArticlesResponseEntity;
import retrofit2.Call;
import retrofit2.http.GET;

interface NewsApi {
    @GET("bins/nl6jh")
    Call<ArticlesResponseEntity> getArticles();
}
