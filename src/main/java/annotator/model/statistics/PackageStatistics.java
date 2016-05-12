package annotator.model.statistics;

public class PackageStatistics {

    private final Long averagePackageDuration;
    private final Long averageWordDurationInPackage;
    private final Double trueRatio;

    public PackageStatistics(Long averagePackageDuration, Long averageWordDurationInPackage, Double trueRatio) {
        this.averagePackageDuration = averagePackageDuration;
        this.averageWordDurationInPackage = averageWordDurationInPackage;
        this.trueRatio = trueRatio;
    }

    public Long getAveragePackageDuration() {
        return averagePackageDuration;
    }

    public Long getAverageWordDurationInPackage() {
        return averageWordDurationInPackage;
    }

    public Double getTrueRatio() {
        return trueRatio;
    }
}
