package annotator.model.statistics;

public class UserStatistics {

    private final Double kappa;
    private final Double overallAccuracy;
    private final Double randomAgreementProbability;

    public UserStatistics(Double kappa, Double overallAccuracy, Double randomAgreementProbability) {
        this.kappa = kappa;
        this.overallAccuracy = overallAccuracy;
        this.randomAgreementProbability = randomAgreementProbability;
    }

    public Double getKappa() {
        return kappa;
    }

    public Double getOverallAccuracy() {
        return overallAccuracy;
    }

    public Double getRandomAgreementProbability() {
        return randomAgreementProbability;
    }
}
