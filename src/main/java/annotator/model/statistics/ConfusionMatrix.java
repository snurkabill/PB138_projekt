package annotator.model.statistics;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

@XStreamAlias("ConfusionMatrix")
public class ConfusionMatrix {

    private final Integer totalPopulation;

    private final Integer predictedTrueClassesCount;
    private final Integer predictedFalseClassesCount;

    private final Integer trueClassesCount;
    private final Integer falseClassesCount;

    private final Integer truePositive;
    private final Integer falsePositive;

    private final Integer trueNegative;
    private final Integer falseNegative;

    private final Double trueAccuracy;
    private final Double falseAccuracy;

    private final Double predictedTrueAccuracy;
    private final Double predictedFalseAccuracy;
    private final Double overallAccuracy;
    private final Double averageAccuracy;
    private final Double meanClassificationAccuracy;
    private final Double randomAgreementProbability;
    private final Double kappa;

    public ConfusionMatrix(List<Boolean> predicted, List<Boolean> correct) {
        this.totalPopulation = predicted.size();

        Integer trueClassesCount = 0;
        Integer falseClassesCount = 0;

        Integer predictedTrueClassesCount = 0;
        Integer predictedFalseClassesCount = 0;

        Integer truePositive = 0;
        Integer falsePositive = 0;

        Integer trueNegative = 0;
        Integer falseNegative = 0;

        for (int i = 0; i < totalPopulation; i++) {
            if (predicted.get(i) && correct.get(i)) {
                truePositive++;
                trueClassesCount++;
                predictedTrueClassesCount++;
            } else if (!predicted.get(i) && !correct.get(i)) {
                trueNegative++;
                falseClassesCount++;
                predictedFalseClassesCount++;
            } else if (predicted.get(i) && !correct.get(i)) {
                falsePositive++;
                falseClassesCount++;
                predictedTrueClassesCount++;
            } else {
                falseNegative++;
                trueClassesCount++;
                predictedFalseClassesCount++;
            }
        }
        this.trueClassesCount = trueClassesCount;
        this.falseClassesCount = falseClassesCount;
        this.predictedTrueClassesCount = predictedTrueClassesCount;
        this.predictedFalseClassesCount = predictedFalseClassesCount;

        this.truePositive = truePositive;
        this.trueNegative = trueNegative;
        this.falsePositive = falsePositive;
        this.falseNegative = falseNegative;

        this.trueAccuracy = ((double) this.truePositive / this.trueClassesCount);
        this.falseAccuracy = ((double) this.trueNegative / this.falseClassesCount);

        this.predictedTrueAccuracy = ((double) this.truePositive / this.predictedTrueClassesCount);
        this.predictedFalseAccuracy = ((double) this.trueNegative / this.predictedFalseClassesCount);

        this.overallAccuracy = (this.truePositive + this.trueNegative) / (double) this.totalPopulation;
        this.averageAccuracy = (this.predictedTrueAccuracy + predictedFalseAccuracy) / 2.0;

        this.meanClassificationAccuracy = (this.overallAccuracy + this.averageAccuracy) / 2.0;

        this.randomAgreementProbability = (
                this.trueClassesCount * this.predictedTrueClassesCount +
                this.falseClassesCount * this.predictedFalseClassesCount)
                / Math.pow(totalPopulation, 2.0);

        this.kappa = (this.overallAccuracy - randomAgreementProbability) / (1.0 - this.randomAgreementProbability);
    }

    public Integer getTotalPopulation() {
        return totalPopulation;
    }

    public Integer getPredictedTrueClassesCount() {
        return predictedTrueClassesCount;
    }

    public Integer getPredictedFalseClassesCount() {
        return predictedFalseClassesCount;
    }

    public Integer getTrueClassesCount() {
        return trueClassesCount;
    }

    public Integer getFalseClassesCount() {
        return falseClassesCount;
    }

    public Integer getTruePositive() {
        return truePositive;
    }

    public Integer getFalsePositive() {
        return falsePositive;
    }

    public Integer getTrueNegative() {
        return trueNegative;
    }

    public Integer getFalseNegative() {
        return falseNegative;
    }

    public Double getTrueAccuracy() {
        return trueAccuracy;
    }

    public Double getFalseAccuracy() {
        return falseAccuracy;
    }

    public Double getPredictedTrueAccuracy() {
        return predictedTrueAccuracy;
    }

    public Double getPredictedFalseAccuracy() {
        return predictedFalseAccuracy;
    }

    public Double getOverallAccuracy() {
        return overallAccuracy;
    }

    public Double getAverageAccuracy() {
        return averageAccuracy;
    }

    public Double getMeanClassificationAccuracy() {
        return meanClassificationAccuracy;
    }

    public Double getRandomAgreementProbability() {
        return randomAgreementProbability;
    }

    public Double getKappa() {
        return kappa;
    }
}
