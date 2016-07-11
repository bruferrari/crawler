package ferrarib.com.app.crawler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Entities {

    private List<Person> persons = new ArrayList<Person>();
    private List<Organization> organizations = new ArrayList<Organization>();
    private List<Location> locations = new ArrayList<Location>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The persons
     */
    public List<Person> getPersons() {
        return persons;
    }

    /**
     *
     * @param persons
     * The persons
     */
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    /**
     *
     * @return
     * The organizations
     */
    public List<Organization> getOrganizations() {
        return organizations;
    }

    /**
     *
     * @param organizations
     * The organizations
     */
    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    /**
     *
     * @return
     * The locations
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     *
     * @param locations
     * The locations
     */
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}