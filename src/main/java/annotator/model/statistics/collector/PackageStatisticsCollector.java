package annotator.model.statistics.collector;

import annotator.model.statistics.domain.PackageStatistics;
import annotator.model.statistics.domain.Statistics;
import annotator.model.vote.VoteRepository;

public class PackageStatisticsCollector {

    private final VoteRepository voteRepository;

    public PackageStatisticsCollector(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Statistics getPackageStatistics(String packageId) {
        return new PackageStatistics(
                voteRepository.getVotesByPackageId(packageId),
                packageId
        );
    }

}
