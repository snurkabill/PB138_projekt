package annotator.model.statistics;

import annotator.model.vote.Vote;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;
import java.util.stream.Collectors;

@XStreamAlias("UserStatistics")
public class UserStatistics {

    private final Double trueRatio;
    private final Double averageDuration;
    private final Double averageDurationOnNoisyData;
    private final ConfusionMatrix confusionMatrix;

    public UserStatistics(List<Vote> noisyVoteList, List<Boolean> noisyRealLabels,  List<Vote> allVoteList) {
        this.trueRatio = ((Long) allVoteList.stream()
                .filter(Vote::getBelongsToType)
                .count())
                .doubleValue() / allVoteList.size();
        this.averageDuration = allVoteList.stream().mapToLong(Vote::getDuration).average().getAsDouble();
        this.averageDurationOnNoisyData = noisyVoteList.stream().mapToLong(Vote::getDuration).average().getAsDouble();
        confusionMatrix = new ConfusionMatrix(
                noisyVoteList.stream().map(Vote::getBelongsToType).collect(Collectors.toList()),
                noisyRealLabels);
    }

    public Double getTrueRatio() {
        return trueRatio;
    }

    public Double getAverageDuration() {
        return averageDuration;
    }

    public Double getAverageDurationOnNoisyData() {
        return averageDurationOnNoisyData;
    }

    public ConfusionMatrix getConfusionMatrix() {
        return confusionMatrix;
    }
}
