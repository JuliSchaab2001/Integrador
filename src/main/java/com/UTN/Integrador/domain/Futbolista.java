package com.UTN.Integrador.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("futbolista")
@EqualsAndHashCode(callSuper = true)
public class Futbolista extends Deportista{

    private Integer totalGoals;
    private Integer freeKickGoals;
    private Integer headGoals;
    private Integer penaltyGoals;


}
