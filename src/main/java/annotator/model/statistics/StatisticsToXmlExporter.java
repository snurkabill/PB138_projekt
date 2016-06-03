package annotator.model.statistics;

import annotator.model.statistics.domain.Statistics;
import com.thoughtworks.xstream.XStream;

public class StatisticsToXmlExporter {

    private final String inXml;

    public StatisticsToXmlExporter(Statistics statistics) {
        this.inXml = new XStream().toXML(statistics);
    }

    public String getInXml() {
        return inXml;
    }

}
