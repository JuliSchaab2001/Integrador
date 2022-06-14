package com.UTN.Integrador.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LiveMatch {

    @JsonAlias("Away Score")
    private Integer Away_Score;
    @JsonAlias("Away Team")
    private String Away_Team;
    @JsonAlias("Home Score")
    private Integer Home_Score;
    @JsonAlias("Home Team")
    private String Home_Team;
    @JsonAlias("Initial Away Odd")
    private Double Initial_Away_Odd;
    @JsonAlias("Initial Home Odd")
    private Double Initial_Home_Odd;
    @JsonAlias("Initial Draw Odd")
    private Double Initial_Draw_Odd;
    @JsonAlias("League")
    private String League;
    @JsonAlias("League ID")
    private Integer League_ID;
    @JsonAlias("Odd")
    private Double Odd;
    @JsonAlias("Live Home Odd")
    private Double Live_Home_Odd;
    @JsonAlias("Live Away Odd")
    private Double Live_Away_Odd;
    @JsonAlias("Live Draw Odd")
    private Double Live_Draw_Odd;
    @JsonAlias("Match ID")
    private Integer Match_ID;
    @JsonAlias("Period 1 Away")
    private Integer Period_1_Away;
    @JsonAlias("Period 1 Home")
    private Integer Period_1_Home;
    @JsonAlias("Period 2 Away")
    private Integer Period_2_Away;
    @JsonAlias("Period 2 Home")
    private Integer  Period_2_Home;
    @JsonAlias("Period 3 Away")
    private Integer  Period_3_Away;
    @JsonAlias("Period 3 Home")
    private Integer  Period_3_Home;
    @JsonAlias("Status")
    private String Status;



}
