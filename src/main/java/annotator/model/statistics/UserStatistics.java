package annotator.model.statistics;

import annotator.model.vote.Vote;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserStatistics {

    private final Double trueRatio;
    private final Double averageDuration;
    private final Double averageDurationOnNoisyData;
    private final ConfusionMatrixStatistics confusionMatrix;

    public UserStatistics(List<Vote> noisyVoteList, List<Vote> allVoteList) {
        this.trueRatio = ((Long) allVoteList.stream()
                .filter(Vote::getBelongsToType)
                .count())
                .doubleValue() / allVoteList.size();
        this.averageDuration = allVoteList.stream().mapToLong(Vote::getDuration).average().getAsDouble();
        this.averageDurationOnNoisyData = noisyVoteList.stream().mapToLong(Vote::getDuration).average().getAsDouble();

        confusionMatrix = new ConfusionMatrixStatistics(
                noisyVoteList.stream().map(Vote::getBelongsToType).collect(Collectors.toList()),
                new ArrayList<>()); // new ArrayList<>() ~ all values are false.
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

    public ConfusionMatrixStatistics getConfusionMatrix() {
        return confusionMatrix;
    }
}
