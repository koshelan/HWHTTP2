import com.fasterxml.jackson.annotation.JsonProperty;

public class NASAResponse {
    private String copyright,
            date,
            explanation,
            hdurl,
            mediaType,
            serviceVersion,
            title,
            url;

    public NASAResponse(@JsonProperty("copyright") String copyright,
                        @JsonProperty("date") String date,
                        @JsonProperty("explanation") String explanation,
                        @JsonProperty("hdurl") String hdurl,
                        @JsonProperty("media_type") String mediaType,
                        @JsonProperty("service_version") String serviceVersion,
                        @JsonProperty("title") String title,
                        @JsonProperty("url") String url) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "NASAResponse{\n" +
                "copyright =" + copyright + '\n' +
                "date = " + date + '\n' +
                "explanation = " + explanation + '\n' +
                "hdurl = " + hdurl + '\n' +
                "mediaType = " + mediaType + '\n' +
                "serviceVersion = " + serviceVersion + '\n' +
                "title = " + title + '\n' +
                "url= " + url + '\n' +
                '}';
    }
}
