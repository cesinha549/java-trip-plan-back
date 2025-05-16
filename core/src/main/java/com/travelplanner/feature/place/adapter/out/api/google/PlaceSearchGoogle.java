package com.travelplanner.feature.place.adapter.out.api.google;

import com.travelplanner.feature.place.domain.model.PlaceModel;
import com.travelplanner.feature.place.adapter.out.api.google.dto.GooglePlaceResponseDTO;
import com.travelplanner.feature.place.adapter.out.api.google.dto.GoogleRequestDTO;
import com.travelplanner.feature.place.domain.port.out.PlaceSearchPort;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import java.util.List;
import java.util.Objects;


@Service
@Slf4j
public class PlaceSearchGoogle implements PlaceSearchPort {

    private final RestTemplate restTemplate;

    private final GoogleMapper googleMapper;

    private final String apiKey;
    private final String host;
    private final String apiPlaces;

    public PlaceSearchGoogle(GoogleMapper googleMapper, @Value("${google.api.key}") String apiKey, @Value("${google.api.host}") String host, @Value("${google.api.places}") String apiPlaces) {
        this.googleMapper = googleMapper;
        this.host = host;
        this.apiPlaces = apiPlaces;
        this.restTemplate = new RestTemplate();
        this.apiKey = apiKey;
    }

    @Override
    public List<PlaceModel> searchPlaces(Double lat, Double lng, Integer radius, String city, String state, String country, String category) {
        String url = host+apiPlaces+":searchText";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Goog-Api-Key", apiKey);
        headers.set("X-Goog-FieldMask", "places.id,places.displayName,places.primaryType,places.location,places.addressComponents,places.rating");

        GoogleRequestDTO googleRequestDTO = GoogleRequestDTO.builder().textQuery(city + " " +state + " " + category).build();

        HttpEntity<GoogleRequestDTO> entity = new HttpEntity<>(googleRequestDTO, headers);

        ResponseEntity<GooglePlaceResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity, GooglePlaceResponseDTO.class);

        response.getBody();

        String json = new Gson().toJson(response.getBody());
        log.info("<<RESPONSE GOOGLE PLACES>>{}",json);


        return googleMapper.fromGooglePlaceResponse(Objects.requireNonNull(response.getBody()));
    }
}
