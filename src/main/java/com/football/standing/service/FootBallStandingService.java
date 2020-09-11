package com.football.standing.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.football.standing.dto.Country;
import com.football.standing.dto.FootBallStanding;
import com.football.standing.dto.League;

@Service
public class FootBallStandingService {
	@Autowired
	private RestTemplate rest;
	@Value("${api.countries}")
	private String countriesUrl;
	@Value("${api.leagues}")
	private String leagueUrl;
	@Value("${api.standings}")
	private String standingUrl;

	@Autowired
	public FootBallStandingService(RestTemplate rest) {
		this.rest = rest;
	}

	public String getFBStandings(String countryName, String leagueName, String teamName) {

		List<Country> countries = getCountries();
		Country country = countries.stream().filter(c -> countryName.equalsIgnoreCase(c.getCountry_name())).findFirst()
				.orElse(null);
		List<League> leagues = getLeagues(country);
		League league = leagues.stream().filter(c -> leagueName.equalsIgnoreCase(c.getLeague_name())).findFirst()
				.orElse(null);
		List<FootBallStanding> footBallStandings = getStandings(league);
		FootBallStanding footBallStanding = footBallStandings.stream()
				.filter(c -> teamName.equalsIgnoreCase(c.getTeam_name())).findFirst().orElse(null);
		footBallStanding.setCountry_id(country.getCountry_id());
		return prepareStandingResponse(footBallStanding);

	}

	private List<League> getLeagues(Country country) {
		ResponseEntity<List<League>> leagueRes = rest.exchange(
				leagueUrl.replace("<country_id>", String.valueOf(country.getCountry_id())), HttpMethod.GET,
				getHeaders(), new ParameterizedTypeReference<List<League>>() {
				});
		return leagueRes.getBody();
	}

	private List<Country> getCountries() {
		ResponseEntity<List<Country>> res = rest.exchange(countriesUrl.replace("<action>", "get_countries"),
				HttpMethod.GET, getHeaders(), new ParameterizedTypeReference<List<Country>>() {
				});
		return res.getBody();
	}

	private HttpEntity<String> getHeaders() {
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(header);
		return entity;

	}

	private String prepareStandingResponse(FootBallStanding footBallStanding) {
		StringBuffer sb = new StringBuffer();
		sb.append("Country ID & Name: " + footBallStanding.getCountry_id() + "-" + footBallStanding.getCountry_name()
				+ "<br>");
		sb.append("League ID & Name: " + footBallStanding.getLeague_id() + "-" + footBallStanding.getLeague_name()
				+ "<br>");
		sb.append("Team ID & Name: " + footBallStanding.getTeam_id() + "-" + footBallStanding.getTeam_name() + "<br>");
		sb.append("Overall League Position: " + footBallStanding.getOverall_league_position());
		return sb.toString();
	}

	private List<FootBallStanding> getStandings(League league) {
		ResponseEntity<List<FootBallStanding>> standingRes = rest.exchange(
				standingUrl.replace("<league_id>", String.valueOf(league.getLeague_id())), HttpMethod.GET, getHeaders(),
				new ParameterizedTypeReference<List<FootBallStanding>>() {
				});
		return standingRes.getBody();
	}
}
