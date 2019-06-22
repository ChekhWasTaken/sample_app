package news.challenge.com.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents a media item
 */
public class MediaEntity implements Parcelable {
    @SerializedName("url")
    private String url;
    @SerializedName("format")
    private String format;
    @SerializedName("height")
    private int height;
    @SerializedName("width")
    private int width;
    @SerializedName("type")
    private String type;
    @SerializedName("subType")
    private String subType;
    @SerializedName("capton")
    private String caption;
    @SerializedName("copyright")
    private String copyright;

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getCaption() {
        return caption;
    }

    public String getCopyright() {
        return copyright;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.format);
        dest.writeInt(this.height);
        dest.writeInt(this.width);
        dest.writeString(this.type);
        dest.writeString(this.subType);
        dest.writeString(this.caption);
        dest.writeString(this.copyright);
    }

    public MediaEntity() {
    }

    protected MediaEntity(Parcel in) {
        this.url = in.readString();
        this.format = in.readString();
        this.height = in.readInt();
        this.width = in.readInt();
        this.type = in.readString();
        this.subType = in.readString();
        this.caption = in.readString();
        this.copyright = in.readString();
    }

    public static final Creator<MediaEntity> CREATOR = new Creator<MediaEntity>() {
        @Override
        public MediaEntity createFromParcel(Parcel source) {
            return new MediaEntity(source);
        }

        @Override
        public MediaEntity[] newArray(int size) {
            return new MediaEntity[size];
        }
    };
}
