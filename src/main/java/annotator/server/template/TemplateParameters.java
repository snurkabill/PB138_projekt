package annotator.server.template;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TemplateParameters {

    private Map<String, Object> parameters;

    public TemplateParameters() {
        this.parameters = new HashMap<>();
    }

    public Object get(String name) {
        return this.parameters.get(name);
    }

    public String getString(String name) {
        return (String) this.get(name);
    }

    public Set<Map.Entry<String, Object>> getParameters() {
        return this.parameters.entrySet();
    }

    public TemplateParameters set(String parameterName, Object value) {
        this.parameters.put(parameterName, value);
        return this;
    }

}
