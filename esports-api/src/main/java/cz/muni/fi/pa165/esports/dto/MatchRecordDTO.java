package cz.muni.fi.pa165.esports.dto;

import javax.persistence.ManyToOne;
import java.util.Objects;

public class MatchRecordDTO {
    private Long id;

    private int score;
    private long matchNumber;

    private TeamDTO team;
    private PlayerDTO player;
    private CompetitionDTO competition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(long matchNumber) {
        this.matchNumber = matchNumber;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public CompetitionDTO getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionDTO competition) {
        this.competition = competition;
    }

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
