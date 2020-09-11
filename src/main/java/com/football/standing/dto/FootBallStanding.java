package com.football.standing.dto;


public class FootBallStanding {
private int country_id;
private String country_name;
private int league_id;
@Override
public String toString() {
	return "FootBallStanding [country_id=" + country_id + ", country_name=" + country_name + ", league_id=" + league_id
			+ ", league_name=" + league_name + ", team_id=" + team_id + ", team_name=" + team_name
			+ ", overall_league_position=" + overall_league_position + "]";
}
public int getCountry_id() {
	return country_id;
}
public void setCountry_id(int country_id) {
	this.country_id = country_id;
}
public String getCountry_name() {
	return country_name;
}
public void setCountry_name(String country_name) {
	this.country_name = country_name;
}
public int getLeague_id() {
	return league_id;
}
public void setLeague_id(int league_id) {
	this.league_id = league_id;
}
public String getLeague_name() {
	return league_name;
}
public void setLeague_name(String league_name) {
	this.league_name = league_name;
}
public int getTeam_id() {
	return team_id;
}
public void setTeam_id(int team_id) {
	this.team_id = team_id;
}
public String getTeam_name() {
	return team_name;
}
public void setTeam_name(String team_name) {
	this.team_name = team_name;
}
public int getOverall_league_position() {
	return overall_league_position;
}
public void setOverall_league_position(int overall_league_position) {
	this.overall_league_position = overall_league_position;
}
private String league_name;
private int team_id;
private String team_name;
private int overall_league_position;

}
