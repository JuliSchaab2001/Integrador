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
@JsonTypeName("basketbolista")
@EqualsAndHashCode(callSuper = true)
public class Basketbolista extends Deportista{

    private Integer points;
    private Integer rebounds;

}
