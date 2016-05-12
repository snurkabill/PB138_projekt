package annotator.statistics;

import java.util.List;

public class StatisticsCalculator {

    private final Integer totalPopulation;

    private final Integer predictedTrueClassesCount;
    private final Integer predictedFalseClassesCount;

    private final Integer trueClassesCount;
    private final Integer falseClassesCount;

    private final Integer truePositive;
    private final Integer falsePositive;

    private final Integer trueNegative;
    private final Integer falseNegative;

    public StatisticsCalculator(List<Boolean> predicted, List<Boolean> correct) {
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
    }


}
