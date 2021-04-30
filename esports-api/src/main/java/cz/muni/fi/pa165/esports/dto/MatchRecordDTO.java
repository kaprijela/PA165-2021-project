package cz.muni.fi.pa165.esports.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class MatchRecordDTO {
    private Long id;

    private int score;
    private long matchNumber;

    private String team;
    private Long player;
    private String competition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchRecordDTO)) return false;
        MatchRecordDTO that = (MatchRecordDTO) o;
        return getMatchNumber() == that.getMatchNumber() && Objects.equals(getTeam(), that.getTeam()) && Objects.equals(getPlayer(), that.getPlayer()) && Objects.equals(getCompetition(), that.getCompetition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatchNumber(), getTeam(), getPlayer(), getCompetition());
    }
}
