package annotator.model.statistics;

import annotator.model.statistics.domain.WordStatistics;
import annotator.model.vote.VoteRepository;

public class WordStatisticsCollector {

    private final VoteRepository voteRepository;

    public WordStatisticsCollector(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public WordStatistics getWordStatistics(String wordId) {
        return new WordStatistics(voteRepository.getVoteByWordId(wordId));
    }
}
