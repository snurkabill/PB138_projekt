package annotator.model.statistics.collector;

import annotator.model.statistics.domain.Statistics;
import annotator.model.statistics.domain.WordStatistics;
import annotator.model.vote.VoteRepository;

public class WordStatisticsCollector {

    private final VoteRepository voteRepository;

    public WordStatisticsCollector(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Statistics getWordStatistics(String wordId) {
        return new WordStatistics(voteRepository.getVotesByWordId(wordId), wordId);
    }

    public WordStatistics getAllWordStatistics() {
        return new WordStatistics(voteRepository.getAllVotes(), "allUsersCombined");
    }
}
