package annotator.model.statistics.collector;

import annotator.model.statistics.domain.PackageStatistics;
import annotator.model.statistics.domain.Statistics;
import annotator.model.vote.VoteRepository;
import annotator.model.word.WordRepository;

public class PackageStatisticsCollector {

    private final VoteRepository voteRepository;
    private final WordRepository wordRepository;

    public PackageStatisticsCollector(VoteRepository voteRepository, WordRepository wordRepository) {
        this.voteRepository = voteRepository;
        this.wordRepository = wordRepository;
    }

    public Statistics getPackageStatistics(String packageId) {
        return new PackageStatistics(
                voteRepository.getVotesByPackageId(packageId),
                packageId
        );
    }

}
