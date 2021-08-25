package com.internship.pillarglobal.NewsfeedMapperAPI.services;
import com.internship.pillarglobal.NewsfeedMapperAPI.repositories.NewsfeedMapperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


@Service
public class NewsFeedMapperService {
    private NewsfeedMapperRepository newsfeedMapperRepository;

    @Autowired
    public NewsFeedMapperService(NewsfeedMapperRepository newsfeedMapperRepository) {
        this.newsfeedMapperRepository = newsfeedMapperRepository;
    }

    public String processYahooUK() throws IOException, InterruptedException {

//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://yahoo-uk-feed.platforms-prod-gcp.telegraph.co.uk/feed.xml"))
//                .build();
//
//        HttpResponse<String> response =
//                client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        System.out.println(response.body());
//        return response.body();
        URL yahoo = new URL("https://yahoo-uk-feed.platforms-prod-gcp.telegraph.co.uk/feed.xml");
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;
        String data= new String("");
        while ((inputLine = in.readLine()) != null)
            data=data.concat(inputLine);
        in.close();
        return  data;

    }
}
