package annotator.statistics;

public class WordStatistics {

    private final Double trueRatio;
    private final Long averageDuration;

    public WordStatistics(Double trueRatio, Long averageDuration) {
        this.trueRatio = trueRatio;
        this.averageDuration = averageDuration;
    }

    public Double getTrueRatio() {
        return trueRatio;
    }

    public Long getAverageDuration() {
        return averageDuration;
    }
}
