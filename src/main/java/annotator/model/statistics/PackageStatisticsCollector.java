package annotator.model.statistics;

import annotator.model.statistics.domain.PackageStatistics;
import annotator.model.vote.Vote;
import annotator.model.vote.VoteRepository;
import annotator.model.word.Word;
import annotator.model.word.WordRepository;

import java.util.ArrayList;
import java.util.List;

public class PackageStatisticsCollector {

    private final VoteRepository voteRepository;
    private final WordRepository wordRepository;

    public PackageStatisticsCollector(VoteRepository voteRepository, WordRepository wordRepository) {
        this.voteRepository = voteRepository;
        this.wordRepository = wordRepository;
    }

    public PackageStatistics getPackageStatistics(String packageId) {
        List<Word> wordList = wordRepository.getWordByPackageId(packageId);
        List<Vote> voteList = new ArrayList<>();
        for (Word aWordList : wordList) {
            voteList.addAll(voteRepository.getVotesByWordId(aWordList.getId()));
        }
        return new PackageStatistics(voteList);
    }

}
