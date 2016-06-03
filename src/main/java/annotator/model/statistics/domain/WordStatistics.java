package annotator.model.statistics.domain;

import annotator.model.vote.Vote;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

@XStreamAlias("WordStatistics")
public class WordStatistics extends Statistics {

    private final String wordId;
    private final Double trueRatio;
    private final Double averageDuration;

    public WordStatistics(List<Vote> voteList, String wordId) {
        this.wordId = wordId;
        this.trueRatio = ((Long) voteList.stream()
            .filter(Vote::getVoteBelongsToType)
            .count())
            .doubleValue() / voteList.size();
        this.averageDuration = voteList.stream().mapToLong(Vote::getDuration).average().getAsDouble();
    }

    public String getWordId() {
        return wordId;
    }

    public Double getTrueRatio() {
        return trueRatio;
    }

    public Double getAverageDuration() {
        return averageDuration;
    }
}
