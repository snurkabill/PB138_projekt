package annotator.model.statistics;

import annotator.model.vote.Vote;

import java.util.List;

public class WordStatistics {

    private final Double trueRatio;
    private final Double averageDuration;

    public WordStatistics(List<Vote> voteList) {
        this.trueRatio = ((Long) voteList.stream()
                .filter(Vote::getBelongsToType)
                .count())
                .doubleValue() / voteList.size();
        this.averageDuration = voteList.stream().mapToLong(Vote::getDuration).average().getAsDouble();
    }

    public Double getTrueRatio() {
        return trueRatio;
    }

    public Double getAverageDuration() {
        return averageDuration;
    }
}
