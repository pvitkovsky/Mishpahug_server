package Application.repo.custom;

import Application.entities.HoliDayEntity;
import Application.entities.values.responsefromserver.HolidayResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class HoliDayRepositoryImpl implements HoliDayRepositoryCustom {
    private static ResponseEntity<HolidayResponse> responsData;
    public static RequestEntity<?> request;
    public static final RestTemplate restTemplate = new RestTemplate();
    @Override
    public List<HoliDayEntity> loadFromServer() {
        String urn = "https://calendarific.com/api/v2/holidays?api_key=e3d29a00c8e79f50211cfd05e47f92c7c5735a5e&country=IL&year=2019";
        try {
            request = new RequestEntity(null, HttpMethod.POST, new URI(urn));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        responsData = restTemplate.exchange(request, HolidayResponse.class);
        return null;
    }
}
