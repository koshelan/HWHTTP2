import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class Main {

    public static final String HTTP_TASK_2 = "https://api.nasa.gov/planetary/apod?api_key=";
    public static final String API_KEY = "b9Dhc5Ydo1qsaRs0dBawCzU8BburYuxqEUJyTGT8";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        final CloseableHttpClient httpClient = getBuild();

        HttpGet request = new HttpGet(HTTP_TASK_2 + API_KEY);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            NASAResponse nasaResponses = mapper.readValue(response.getEntity().getContent(),
                    mapper.getTypeFactory().constructType(NASAResponse.class));

            String filename = getFileName(nasaResponses.getUrl());

            saveFile(nasaResponses, filename);
        }
    }

    private static void saveFile(NASAResponse nasaResponses, String filename) throws IOException {
        URL website = new URL(nasaResponses.getUrl());
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(filename);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        System.out.println("файл сохранён");
    }

    private static CloseableHttpClient getBuild() {
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
    }

    private static String getFileName(String url){
        String[] split = url.split("/");
        return split[split.length-1];
    }


}
