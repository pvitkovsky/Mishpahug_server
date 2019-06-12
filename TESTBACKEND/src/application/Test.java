import dto.EventDTO;
import dto.LoginDTO;
import dto.LoginResponse;
import dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
public class Test {

	public static final HttpMethod GET = HttpMethod.GET;
	public static final HttpMethod POST = HttpMethod.POST;
	public static final HttpMethod DELETE = HttpMethod.DELETE;

	public static final String url = "http://localhost:8080";
	public static final RestTemplate restTemplate = new RestTemplate();
	public static RequestEntity<?> request;
	public static HttpHeaders headers = new HttpHeaders();
	static {
		headers.set("Content-Type", "application/json");
	}
	public static String[] data = {"giapico0","rbulcock1",	"pcatterill2", 	"pdecarlo3", 	"bshillum4",	"efelderer5",
	"ndiver6",	"cshoobridge7",	"bcotmore8",	"mdesaur9",	"giapico04",	"rbulcock11",	"pcatterill21",	"pdecarlo33",
	"bshillum42",	"efeld4erer54",	"ndive4r6",	"cshoobri4dge7",	"bcot1more8",	"mdes32aur9",	"qgiapico0",	"qrbulcock1",
	"qpcatterill2",	"qpdecarlo3",	"qbshillum4",	"qefelderer5",	"qndiver6",	"qcshoobridge7",	"qbcotmore8",	"qmdesaur9",
	"qgiapico04",	"qrbulcock11",	"qpcatterill21",	"qpdecarlo33",	"qbshillum42",	"qefeld4erer54",	"qndive4r6",	"qcshoobri4dge7",
	"qbcot1more8",	"qmdes32aur9",	"agiapico0",	"arbulcock1",	"apcatterill2",	"apdecarlo3",	"abshillum4",	"aefelderer5",
	"andiver6",	"acshoobridge7",	"abcotmore8",	"amdesaur9",	"agiapico04",	"arbulcock11",	"apcatterill21",	"apdecarlo33",
	"abshillum42",	"aefeld4erer54",	"andive4r6",	"acshoobri4dge7",	"abcot1more8",	"amdes32aur9",	"wgiapico0",	"wrbulcock1",
	"wpcatterill2",	"wpdecarlo3",	"wbshillum4",	"wefelderer5",	"wndiver6",	"wcshoobridge7",	"wbcotmore8",	"wmdesaur9",
	"wgiapico04",	"wrbulcock11",	"wpcatterill21",	"wpdecarlo33",	"wbshillum42",	"wefeld4erer54",	"wndive4r6",	"wcshoobri4dge7",
	"wbcot1more8",	"wmdes32aur9",	"sgiapico0",	"srbulcock1",	"spcatterill2",	"spdecarlo3",	"sbshillum4",	"sefelderer5",	"sndiver6",	"scshoobridge7",	"sbcotmore8",	"smdesaur9",
	"sgiapico04",	"srbulcock11",	"spcatterill21",	"spdecarlo33",	"sbshillum42",	"sefeld4erer54",	"sndive4r6",	"scshoobri4dge7",
	"sbcot1more8",	"smdes32aur9",	"egiapico0",	"erbulcock1",	"epcatterill2",	"epdecarlo3",	"ebshillum4",	"eefelderer5",
	"endiver6",	"ecshoobridge7",	"ebcotmore8",	"emdesaur9",	"egiapico04",	"erbulcock11",	"epcatterill21",	"epdecarlo33",
	"ebshillum42",	"eefeld4erer54",	"endive4r6",	"ecshoobri4dge7",	"ebcot1more8",	"emdes32aur9",	"dgiapico0",	"drbulcock1",
	"dpcatterill2",	"dpdecarlo3",	"dbshillum4",	"defelderer5",	"dndiver6",	"dcshoobridge7",	"dbcotmore8",	"dmdesaur9",
	"dgiapico04",	"drbulcock11",	"dpcatterill21",	"dpdecarlo33",	"dbshillum42",	"defeld4erer54",	"dndive4r6",	"dcshoobri4dge7",
	"fbshillum4",	"fefelderer5",	"fndiver6",	"fcshoobridge7",	"fbcotmore8",	"tefeld4erer54",	"tndive4r6",	"tcshoobri4dge7",
	"tbcot1more8",	"tmdes32aur9",	"ggiapico0",	"grbulcock1",	"gpcatterill2",	"gpdecarlo3",	"gbshillum4",	"gefelderer5",
	"gndiver6",	"gcshoobridge7",	"gbcotmore8",	"gmdesaur9",	"ggiapico04",	"grbulcock11", "gpcatterill21",	"gpdecarlo33",
	"gbshillum42",	"gefeld4erer54", "gndive4r6",	"gcshoobri4dge7",	"gbcot1more8",	"gmdes32aur9",	"ygiapico0",	"yrbulcock1",
	"ypcatterill2",	"ypdecarlo3",	"ybshillum4",	"yefelderer5",	"yndiver6",	"ycshoobridge7",	"ybcotmore8",	"ymdesaur9",
	"ygiapico04",	"yrbulcock11",	"ypcatterill21",	"ypdecarlo33",	"ybshillum42",	"yefeld4erer54",	"yndive4r6",	"ycshoobri4dge7",
	"ybcot1more8",	"ymdes32aur9",	"hgiapico0",	"hrbulcock1",	"hpcatterill2",	"hpdecarlo3",	"hbshillum4",	"hefelderer5",
	"hndiver6",	"hcshoobridge7",	"hbcotmore8",	"hmdesaur9",	"hgiapico04",	"hrbulcock11",	"hpcatterill21",	"hpdecarlo33",
	"hbshillum42",	"hefeld4erer54",	"hndive4r6",	"hcshoobri4dge7",	"hbcot1more8",	"hmdes32aur9",	"ugiapico0",	"urbulcock1",
	"upcatterill2",	"updecarlo3",	"ubshillum4",	"uefelderer5",	"undiver6",	"ucshoobridge7",	"ubcotmore8",	"umdesaur9",
	"ugiapico04",	"urbulcock11",	"upcatterill21",	"updecarlo33",	"ubshillum42",	"uefeld4erer54",	"undive4r6",	"ucshoobri4dge7",
	"ubcot1more8",	"umdes32aur9"};
	private static String urn = "";
	private static ResponseEntity<String> responseS;
	private static ResponseEntity<UserDTO> responseU;
	private static ResponseEntity<EventDTO> responseE;
	private static ResponseEntity<LoginResponse> responseL;
	private static String[] resFromServer = new String[data.length];

	//составить все варианты правильных запросов
	// после проверки работоспособности кода
	// создать запросы вызываемые эксепшены
	
	public static void main(String[] args) throws URISyntaxException {
		for (int i = 0; i < data.length; i++) {
			urn = "/user/login";
			LoginDTO loginDTO = new LoginDTO();
			loginDTO.setUsername(data[i]);
			loginDTO.setPassword(data[i]);
			request = new RequestEntity(loginDTO, null, POST, new URI(url+urn));
			responseL = restTemplate.exchange(request, LoginResponse.class);
			log.info("token -> " + responseL.getBody().getToken());
			resFromServer[i] = responseL.getBody().getToken();
		}
		for (int j = 0; j < resFromServer.length; j++) {
			HttpHeaders headers = new HttpHeaders();
			urn = "/user/3";
			headers.add("Authorization", resFromServer[j]);
			request = new RequestEntity(null, headers, GET, new URI(url+urn));
			responseU = restTemplate.exchange(request, UserDTO.class);
			log.info("user [3] -> " + responseU.getBody().toString());
		}
		for (int j = 0; j < resFromServer.length; j++) {
			HttpHeaders headers = new HttpHeaders();
			urn = "/event/3";
			headers.add("Authorization", resFromServer[j]);
			request = new RequestEntity(null, headers, GET, new URI(url+urn));
			responseE = restTemplate.exchange(request, EventDTO.class);
			log.info("event [3] -> " + responseU.getBody().toString());
		}
						/*urn = "/devices/remove?iddev=";
						request = new RequestEntity(null, POST, new URI(url+urn));
						responseS = restTemplate.exchange(request, String.class);

                        urn = "/devices/save";
                        request = new RequestEntity(null, POST, new URI(url+urn));
                        responseS = restTemplate.exchange(request, String.class);*/
	}


}
