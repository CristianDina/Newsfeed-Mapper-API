package com.internship.pillarglobal.NewsfeedMapperAPI.services;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.NewsfeedMapperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

@Service
public class NewsFeedMapperService {
    private NewsfeedMapperRepository newsfeedMapperRepository;
    @Autowired
    public NewsFeedMapperService(NewsfeedMapperRepository newsfeedMapperRepository){
        this.newsfeedMapperRepository=newsfeedMapperRepository;
    }
    public void processYahooUK() throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://yahoo-uk-feed.platforms-prod-gcp.telegraph.co.uk/feed.xml"))
//                .build();
//        HttpClient client = HttpClient.newBuilder()
//                .version(HttpClient.Version.HTTP_1_1)
//                .followRedirects(HttpClient.Redirect.NORMAL)
//                .connectTimeout(Duration.ofSeconds(20))
//                //.proxy(ProxySelector.of(new InetSocketAddress("https://yahoo-uk-feed.platforms-prod-gcp.telegraph.co.uk/feed.xml", 80)))
//                .authenticator(Authenticator.getDefault())
//                .build();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.statusCode());
//        System.out.println(response.body());
        // Create a neat value object to hold the URL
        URL url = new URL("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");

// Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

// Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("accept", "application/json");

// This line makes the request
        InputStream responseStream = connection.getInputStream();

// Manually converting the response body InputStream to APOD using Jackson
//        ObjectMapper mapper = new ObjectMapper();
//        APOD apod = mapper.readValue(responseStream, APOD.class);

// Finally we have the response
        System.out.println(responseStream);

    }
}
