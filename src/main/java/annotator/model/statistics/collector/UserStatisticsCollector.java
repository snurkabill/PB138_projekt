package annotator.model.statistics.collector;

import annotator.model.statistics.domain.Statistics;
import annotator.model.statistics.domain.UserStatistics;
import annotator.model.vote.Vote;
import annotator.model.vote.VoteRepository;

import java.util.List;

public class UserStatisticsCollector {

    private final VoteRepository voteRepository;

    public UserStatisticsCollector(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Statistics getUserStatistics(String userId) {
        List<Vote> allVotesList = voteRepository.getAllVotesByUserId(userId);
        List<Vote> noisyVotesList = voteRepository.getNoisyVotesByUserId(userId);
        return new UserStatistics(noisyVotesList, allVotesList);
    }

    public UserStatistics getAllUserStatistics() {
        List<Vote> allVotesList = voteRepository.getAllVotes();
        List<Vote> allNoisyVotesList = voteRepository.getAllNoisyVotes();
        return new UserStatistics(allNoisyVotesList, allVotesList);
    }

}
