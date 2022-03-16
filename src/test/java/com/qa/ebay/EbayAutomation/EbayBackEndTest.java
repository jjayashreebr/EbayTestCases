package com.qa.ebay.EbayAutomation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class EbayBackEndTest {
	private String responseBody;
	public String responseBodyPOST;
	private static Logger LOGGER = (Logger) LogManager.getLogger(EbayBackEndTest.class);
	
	private RestTemplate restTemplate;
	private ResponseEntity<String> response;

	@Test
	public void testcase1() {
		String getURI = "http://www.ebay.com";
		LOGGER.info("Get URL :" + getURI);
		this.restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		String cookie ="ak_bmsc=E4C5A66952519F649A208401D12BD787~000000000000000000000000000000~YAAQPHtBF4auGlJ/AQAAWLJneg+nO7Fzwgwwz0D1Bgw7ytTUUXkuoB4m+qE7k8Lux+D5QUwcTddAKdfPSVcrc224JAfsodBGTXAIN+Gc+zZzJt57mJAOrd3PNgO3npXK6UtEYKON6SNpWNYyUGiAEzX6ifZ8J6dB4Rqs4kv3ectBLT4HJfyYbgu9caGEwMw4PFFMVRhEFwONulqr5wkhrz/2nE/OTLXLEu0hZ+sMDc2G3ShPmRBwcWPEM2jHnm6/Tz9cWCf6yJtcJ1sH7QwbZUyTajWrOoftBcR4aoFVWK7ChqTSeNElKnaX/n9Qn5lF4PJu/Uzc0Uiq7OoKaBubiKZKirICyFczsaUOvNb+v1eXECIq+uUb/KEptA==";
		headers.add("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
		headers.add("Accept-Encoding", "gzip, deflate, br");
		//headers.add("Accept-Language", "en-US,en;q=0.9");
		//headers.add("Cache-Control", "max-age=0");
		headers.add("Connection", "keep-alive");
        headers.add("Cookie",cookie);
		/*headers.add("sec-ch-ua-mobile", "?0");
		headers.add("sec-ch-ua-full-version", "\"99.0.4844.51\"");
		headers.add("sec-ch-ua", "\"Not A;Brand\";v=\"99\", \"Chromium\";v=\"99\", \"Google Chrome\";v=\"99\"");
		headers.add("sec-ch-ua-model", "");
		headers.add("sec-ch-ua-platform", "Windows");
		headers.add("sec-ch-ua-platform-version", "14.0.0");
		headers.add("Sec-Fetch-Dest", "document");
		headers.add("Sec-Fetch-Mode", "navigate");
		headers.add("Sec-Fetch-Site", "none");
		headers.add("Sec-Fetch-User", "?1");
		headers.add("Upgrade-Insecure-Requests", "1");*/
		headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.51 Safari/537.36");

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		response = this.restTemplate.exchange(getURI, HttpMethod.GET, entity, String.class);
		// response = restTemplate.getForEntity(getURI,String.class);

		responseBody = response.getBody();

		LOGGER.info("GET Response Body :" + responseBody);
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
		LOGGER.info("Response code " + response.getStatusCode());

	}

}
