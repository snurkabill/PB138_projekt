package annotator.model.statistics.domain;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("AllStatistics")
public class AllStatistics extends Statistics {

    private final UserStatistics allUsers;
    private final WordStatistics allWords;
    @XStreamImplicit(itemFieldName = "userStatistics")
    private final List<UserStatistics> userStatisticsList;
    @XStreamImplicit(itemFieldName = "wordStatistics")
    private final List<WordStatistics> wordStatisticsList;
    @XStreamImplicit(itemFieldName = "packageStatistics")
    private final List<PackageStatistics> packageStatisticsList;

    public AllStatistics(
            List<UserStatistics> userStatisticsList,
            List<WordStatistics> wordStatisticsList,
            List<PackageStatistics> packageStatisticsList,
            UserStatistics allUsers,
            WordStatistics allWords
    ) {
        this.userStatisticsList = userStatisticsList;
        this.wordStatisticsList = wordStatisticsList;
        this.packageStatisticsList = packageStatisticsList;
        this.allUsers = allUsers;
        this.allWords = allWords;
    }

    public UserStatistics getAllUsers() {
        return allUsers;
    }

    public WordStatistics getAllWords() {
        return allWords;
    }

    public List<UserStatistics> getUserStatisticsList() {
        return userStatisticsList;
    }

    public List<WordStatistics> getWordStatisticsList() {
        return wordStatisticsList;
    }

    public List<PackageStatistics> getPackageStatisticsList() {
        return packageStatisticsList;
    }
}
