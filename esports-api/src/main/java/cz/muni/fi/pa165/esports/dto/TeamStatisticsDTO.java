package cz.muni.fi.pa165.esports.dto;

import cz.muni.fi.pa165.esports.enums.StatisticType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamStatisticsDTO {
    private Long teamId;
    private Double value;
    private StatisticType type;
}
