package ferrarib.com.app.crawler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Post {

    private Thread thread;
    private String uuid;
    private String url;
    private Integer ordInThread;
    private String author;
    private String published;
    private String title;
    private String text;
    private String highlightText;
    private String highlightTitle;
    private String language;
    private List<Object> externalLinks = new ArrayList<Object>();
    private List<String> persons = new ArrayList<String>();
    private List<String> locations = new ArrayList<String>();
    private List<String> organizations = new ArrayList<String>();
    private Entities entities;
    private String crawled;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The thread
     */
    public Thread getThread() {
        return thread;
    }

    /**
     *
     * @param thread
     * The thread
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    /**
     *
     * @return
     * The uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     *
     * @param uuid
     * The uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The ordInThread
     */
    public Integer getOrdInThread() {
        return ordInThread;
    }

    /**
     *
     * @param ordInThread
     * The ord_in_thread
     */
    public void setOrdInThread(Integer ordInThread) {
        this.ordInThread = ordInThread;
    }

    /**
     *
     * @return
     * The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     * The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return
     * The published
     */
    public String getPublished() {
        return published;
    }

    /**
     *
     * @param published
     * The published
     */
    public void setPublished(String published) {
        this.published = published;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The text
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @param text
     * The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return
     * The highlightText
     */
    public String getHighlightText() {
        return highlightText;
    }

    /**
     *
     * @param highlightText
     * The highlightText
     */
    public void setHighlightText(String highlightText) {
        this.highlightText = highlightText;
    }

    /**
     *
     * @return
     * The highlightTitle
     */
    public String getHighlightTitle() {
        return highlightTitle;
    }

    /**
     *
     * @param highlightTitle
     * The highlightTitle
     */
    public void setHighlightTitle(String highlightTitle) {
        this.highlightTitle = highlightTitle;
    }

    /**
     *
     * @return
     * The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     * The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     *
     * @return
     * The externalLinks
     */
    public List<Object> getExternalLinks() {
        return externalLinks;
    }

    /**
     *
     * @param externalLinks
     * The external_links
     */
    public void setExternalLinks(List<Object> externalLinks) {
        this.externalLinks = externalLinks;
    }

    /**
     *
     * @return
     * The persons
     */
    public List<String> getPersons() {
        return persons;
    }

    /**
     *
     * @param persons
     * The persons
     */
    public void setPersons(List<String> persons) {
        this.persons = persons;
    }

    /**
     *
     * @return
     * The locations
     */
    public List<String> getLocations() {
        return locations;
    }

    /**
     *
     * @param locations
     * The locations
     */
    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    /**
     *
     * @return
     * The organizations
     */
    public List<String> getOrganizations() {
        return organizations;
    }

    /**
     *
     * @param organizations
     * The organizations
     */
    public void setOrganizations(List<String> organizations) {
        this.organizations = organizations;
    }

    /**
     *
     * @return
     * The entities
     */
    public Entities getEntities() {
        return entities;
    }

    /**
     *
     * @param entities
     * The entities
     */
    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    /**
     *
     * @return
     * The crawled
     */
    public String getCrawled() {
        return crawled;
    }

    /**
     *
     * @param crawled
     * The crawled
     */
    public void setCrawled(String crawled) {
        this.crawled = crawled;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}