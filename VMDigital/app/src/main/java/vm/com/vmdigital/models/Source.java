package vm.com.vmdigital.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("urlsToLogos")
    @Expose
    private UrlsToLogos urlsToLogos;
    @SerializedName("sortBysAvailable")
    @Expose
    private List<SortType> sortBysAvailable = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Source withCategory(String category) {
        this.category = category;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Source withLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Source withCountry(String country) {
        this.country = country;
        return this;
    }

    public UrlsToLogos getUrlsToLogos() {
        return urlsToLogos;
    }

    public void setUrlsToLogos(UrlsToLogos urlsToLogos) {
        this.urlsToLogos = urlsToLogos;
    }

    public List<SortType> getSortBysAvailable() {
        return sortBysAvailable;
    }

    public void setSortBysAvailable(List<SortType> sortBysAvailable) {
        this.sortBysAvailable = sortBysAvailable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.url);
        dest.writeString(this.category);
        dest.writeString(this.language);
        dest.writeString(this.country);
        dest.writeParcelable(this.urlsToLogos, flags);
        dest.writeList(this.sortBysAvailable);
    }

    public Source() {
    }

    protected Source(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.url = in.readString();
        this.category = in.readString();
        this.language = in.readString();
        this.country = in.readString();
        this.urlsToLogos = in.readParcelable(UrlsToLogos.class.getClassLoader());
        this.sortBysAvailable = new ArrayList<SortType>();
        in.readList(this.sortBysAvailable, SortType.class.getClassLoader());
    }

    public static final Parcelable.Creator<Source> CREATOR = new Parcelable.Creator<Source>() {
        @Override
        public Source createFromParcel(Parcel source) {
            return new Source(source);
        }

        @Override
        public Source[] newArray(int size) {
            return new Source[size];
        }
    };


    public static class UrlsToLogos implements Parcelable {

        @SerializedName("small")
        @Expose
        private String small;
        @SerializedName("medium")
        @Expose
        private String medium;
        @SerializedName("large")
        @Expose
        private String large;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public UrlsToLogos withSmall(String small) {
            this.small = small;
            return this;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public UrlsToLogos withMedium(String medium) {
            this.medium = medium;
            return this;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public UrlsToLogos withLarge(String large) {
            this.large = large;
            return this;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.small);
            dest.writeString(this.medium);
            dest.writeString(this.large);
        }

        public UrlsToLogos() {
        }

        protected UrlsToLogos(Parcel in) {
            this.small = in.readString();
            this.medium = in.readString();
            this.large = in.readString();
        }

        public static final Creator<UrlsToLogos> CREATOR = new Creator<UrlsToLogos>() {
            @Override
            public UrlsToLogos createFromParcel(Parcel source) {
                return new UrlsToLogos(source);
            }

            @Override
            public UrlsToLogos[] newArray(int size) {
                return new UrlsToLogos[size];
            }
        };
    }

    public enum SortType {
        @SerializedName("top")
        @Expose
        TOP(0),

        @SerializedName("latest")
        @Expose
        LATEST(1);

        private final int value;

        public int getValue() {
            return value;
        }

        SortType(final int pValue) {
            value = pValue;
        }
    }
}
