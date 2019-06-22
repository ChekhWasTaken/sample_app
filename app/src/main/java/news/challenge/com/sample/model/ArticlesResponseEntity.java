package news.challenge.com.sample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesResponseEntity {
    @SerializedName("status")
    private String status;
    @SerializedName("copyright")
    private String copyright;
    @SerializedName("section")
    private String section;
    @SerializedName("last_updated")
    private String lastUpdated;
    @SerializedName("num_results")
    private int newsCount;
    @SerializedName("results")
    private List<NewsEntity> news;

    public String getStatus() {
        return status;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getSection() {
        return section;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public int getNewsCount() {
        return newsCount;
    }

    public List<NewsEntity> getNews() {
        return news;
    }
}
