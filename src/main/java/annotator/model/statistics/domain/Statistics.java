package annotator.model.statistics.domain;


import com.thoughtworks.xstream.annotations.XStreamInclude;

@XStreamInclude({
        UserStatistics.class,
        PackageStatistics.class,
        WordStatistics.class
})
public abstract class Statistics {
}
