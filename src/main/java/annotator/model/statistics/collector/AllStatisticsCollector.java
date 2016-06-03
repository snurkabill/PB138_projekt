package annotator.model.statistics.collector;

import annotator.model.statistics.domain.*;
import annotator.model.vote.Vote;
import annotator.model.vote.VoteRepository;
import annotator.model.word.WordRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllStatisticsCollector {

    private final VoteRepository voteRepository;
    private final WordRepository wordRepository;

    public AllStatisticsCollector(VoteRepository voteRepository, WordRepository wordRepository) {
        this.voteRepository = voteRepository;
        this.wordRepository = wordRepository;
    }

    public Statistics getAllStatistics() {
        UserStatisticsCollector userStatisticsCollector = new UserStatisticsCollector(this.voteRepository);
        WordStatisticsCollector wordStatisticsCollector = new WordStatisticsCollector(this.voteRepository);
        PackageStatisticsCollector packageStatisticsCollector = new PackageStatisticsCollector(
                this.voteRepository,
                this.wordRepository
        );

        List<Vote> allVotesList = voteRepository.getAllVotes();
        Set<String> userIdSet = new HashSet<>();
        Set<String> wordIdSet = new HashSet<>();
        Set<String> packageIdSet = new HashSet<>();
        for (Vote vote : allVotesList) {
            userIdSet.add(vote.getUserId());
            wordIdSet.add(vote.getWordId());
            packageIdSet.add(vote.getPackageId());
        }

        List<UserStatistics> userStatisticsList = new ArrayList<>();
        for (String userId : userIdSet) {
            userStatisticsList.add((UserStatistics) userStatisticsCollector.getUserStatistics(userId));
        }

        List<WordStatistics> wordStatisticsList = new ArrayList<>();
        for (String wordId : wordIdSet) {
            wordStatisticsList.add((WordStatistics) wordStatisticsCollector.getWordStatistics(wordId));
        }

        List<PackageStatistics> packageStatisticsList = new ArrayList<>();
        for (String packageId : packageIdSet) {
            packageStatisticsList.add((PackageStatistics) packageStatisticsCollector.getPackageStatistics(packageId));
        }

        return new AllStatistics(
                userStatisticsList,
                wordStatisticsList,
                packageStatisticsList,
                userStatisticsCollector.getAllUserStatistics(),
                wordStatisticsCollector.getAllWordStatistics()
        );
    }

}
