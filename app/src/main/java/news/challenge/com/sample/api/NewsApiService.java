package news.challenge.com.sample.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import news.challenge.com.sample.model.ArticlesResponseEntity;
import news.challenge.com.sample.model.NewsEntity;
import news.challenge.com.sample.util.State;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsApiService {
    private static final NewsApiService ourInstance = new NewsApiService();

    public static NewsApiService getInstance() {
        return ourInstance;
    }

    private final NewsApi service;

    private NewsApiService() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(NewsEntity.class, new NewsEntity.NewsEntityTypeAdapter())
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        service = retrofit.create(NewsApi.class);
    }

    public Response<ArticlesResponseEntity> getArticles() {
        final Response<ArticlesResponseEntity> response = new Response<>();
        response.setState(State.LOADING);

        service.getArticles().enqueue(new Callback<ArticlesResponseEntity>() {
            @Override
            public void onResponse(Call<ArticlesResponseEntity> call, retrofit2.Response<ArticlesResponseEntity> httpResponse) {
                if (httpResponse.isSuccessful()) {
                    response.setData(httpResponse.body());
                    response.setState(State.LOADED);
                } else {
                    response.setState(State.ERROR);
                }
            }

            @Override
            public void onFailure(Call<ArticlesResponseEntity> call, Throwable t) {
                t.printStackTrace();
                response.setState(State.ERROR);
            }
        });

        return response;
    }
}



