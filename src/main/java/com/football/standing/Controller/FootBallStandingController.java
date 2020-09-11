package com.football.standing.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.football.standing.service.FootBallStandingService;

@RestController
public class FootBallStandingController {
	FootBallStandingService footBallStandingService;

	@Autowired
	public FootBallStandingController(FootBallStandingService footBallStandingService) {
		this.footBallStandingService=footBallStandingService;
	}
    @GetMapping("/api/team/standing")
	public String getFBStandings(@RequestParam(name="countryName") String countryName,@RequestParam(name="leagueName") String leagueName,@RequestParam(name="teamName") String teamName) {
		 return footBallStandingService.getFBStandings(countryName,leagueName,teamName);
		
	}
}
