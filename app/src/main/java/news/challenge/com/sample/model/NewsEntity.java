package news.challenge.com.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * This represents a news item
 */
public class NewsEntity implements Parcelable {
    @SerializedName("title")
    private String title;
    @SerializedName("abstract")
    private String summary;
    @SerializedName("url")
    private String articleUrl;
    @SerializedName("byline")
    private String byline;
    @SerializedName("published_date")
    private String publishedDate;
    @SerializedName("multimedia")
    private List<MediaEntity> mediaEntityList;

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public String getByline() {
        return byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<MediaEntity> getMediaEntities() {
        return mediaEntityList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.summary);
        dest.writeString(this.articleUrl);
        dest.writeString(this.byline);
        dest.writeString(this.publishedDate);
        dest.writeList(this.mediaEntityList);
    }

    public NewsEntity() {
    }

    protected NewsEntity(Parcel in) {
        this.title = in.readString();
        this.summary = in.readString();
        this.articleUrl = in.readString();
        this.byline = in.readString();
        this.publishedDate = in.readString();
        this.mediaEntityList = new ArrayList<>();
        in.readList(this.mediaEntityList, MediaEntity.class.getClassLoader());
    }

    public static final Creator<NewsEntity> CREATOR = new Creator<NewsEntity>() {
        @Override
        public NewsEntity createFromParcel(Parcel source) {
            return new NewsEntity(source);
        }

        @Override
        public NewsEntity[] newArray(int size) {
            return new NewsEntity[size];
        }
    };

    public static final class NewsEntityTypeAdapter implements JsonDeserializer<NewsEntity> {
        @Override
        public NewsEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            NewsEntity entity = new NewsEntity();

            JsonObject jsonObject = json.getAsJsonObject();

            entity.title = jsonObject.get("title").getAsString();
            entity.summary = jsonObject.get("abstract").getAsString();
            entity.articleUrl = jsonObject.get("url").getAsString();
            entity.byline = jsonObject.get("byline").getAsString();
            entity.publishedDate = jsonObject.get("published_date").getAsString();

            JsonElement multimedia = jsonObject.get("multimedia");

            entity.mediaEntityList = new ArrayList<>();

            if (multimedia.isJsonArray()) {
                Type listType = new TypeToken<List<MediaEntity>>() {
                }.getType();

                entity.mediaEntityList = context.deserialize(multimedia, listType);
            }

            return entity;
        }
    }

}



















