package annotator.model.statistics;

import annotator.model.statistics.domain.UserStatistics;
import annotator.model.vote.Vote;
import annotator.model.vote.VoteRepository;

import java.util.List;

public class UserStatisticsCollector {

    private final VoteRepository voteRepository;

    public UserStatisticsCollector(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public UserStatistics getUserStatistics(String userId) {
        List<Vote> allVotesList = voteRepository.getAllVotesByUserId(userId);
        List<Vote> noisyVotesList = voteRepository.getNoisyVotesByUserId(userId);
        return new UserStatistics(noisyVotesList, allVotesList);
    }
}
