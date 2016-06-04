package annotator.model.statistics.domain;

import annotator.model.vote.Vote;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

@XStreamAlias("PackageStatistics")
public class PackageStatistics extends Statistics {

    private final String packageId;
    private final Double averagePackageDuration;
    private final Double trueRatio;

    public PackageStatistics(List<Vote> voteList, String packageId) {
        this.packageId = packageId;
        this.averagePackageDuration = voteList.stream().mapToLong(Vote::getDuration).average().getAsDouble();
        this.trueRatio = ((Long) voteList.stream()
            .filter(Vote::getVoteBelongsToType)
            .count())
            .doubleValue() / voteList.size();
    }

    public Double getAveragePackageDuration() {
        return averagePackageDuration;
    }

    public Double getTrueRatio() {
        return trueRatio;
    }
}
