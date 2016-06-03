package annotator.model.statistics.domain;

import annotator.model.vote.Vote;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

@XStreamAlias("WordStatistics")
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
