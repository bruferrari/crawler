package com.ferrarib.crawler.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Thread {

    private String uuid;
    private String url;
    private String siteFull;
    private String site;
    private String siteSection;
    private String sectionTitle;
    private String title;
    private String titleFull;
    private String published;
    private Integer repliesCount;
    private Integer participantsCount;
    private String siteType;
    private String country;
    private Double spamScore;
    private String mainImage;
    private Integer performanceScore;
    private Integer domainRank;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     * The siteFull
     */
    public String getSiteFull() {
        return siteFull;
    }

    /**
     *
     * @param siteFull
     * The site_full
     */
    public void setSiteFull(String siteFull) {
        this.siteFull = siteFull;
    }

    /**
     *
     * @return
     * The site
     */
    public String getSite() {
        return site;
    }

    /**
     *
     * @param site
     * The site
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     *
     * @return
     * The siteSection
     */
    public String getSiteSection() {
        return siteSection;
    }

    /**
     *
     * @param siteSection
     * The site_section
     */
    public void setSiteSection(String siteSection) {
        this.siteSection = siteSection;
    }

    /**
     *
     * @return
     * The sectionTitle
     */
    public String getSectionTitle() {
        return sectionTitle;
    }

    /**
     *
     * @param sectionTitle
     * The section_title
     */
    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
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
     * The titleFull
     */
    public String getTitleFull() {
        return titleFull;
    }

    /**
     *
     * @param titleFull
     * The title_full
     */
    public void setTitleFull(String titleFull) {
        this.titleFull = titleFull;
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
     * The repliesCount
     */
    public Integer getRepliesCount() {
        return repliesCount;
    }

    /**
     *
     * @param repliesCount
     * The replies_count
     */
    public void setRepliesCount(Integer repliesCount) {
        this.repliesCount = repliesCount;
    }

    /**
     *
     * @return
     * The participantsCount
     */
    public Integer getParticipantsCount() {
        return participantsCount;
    }

    /**
     *
     * @param participantsCount
     * The participants_count
     */
    public void setParticipantsCount(Integer participantsCount) {
        this.participantsCount = participantsCount;
    }

    /**
     *
     * @return
     * The siteType
     */
    public String getSiteType() {
        return siteType;
    }

    /**
     *
     * @param siteType
     * The site_type
     */
    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The spamScore
     */
    public Double getSpamScore() {
        return spamScore;
    }

    /**
     *
     * @param spamScore
     * The spam_score
     */
    public void setSpamScore(Double spamScore) {
        this.spamScore = spamScore;
    }

    /**
     *
     * @return
     * The mainImage
     */
    public String getMainImage() {
        return mainImage;
    }

    /**
     *
     * @param mainImage
     * The main_image
     */
    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    /**
     *
     * @return
     * The performanceScore
     */
    public Integer getPerformanceScore() {
        return performanceScore;
    }

    /**
     *
     * @param performanceScore
     * The performance_score
     */
    public void setPerformanceScore(Integer performanceScore) {
        this.performanceScore = performanceScore;
    }

    /**
     *
     * @return
     * The domainRank
     */
    public Integer getDomainRank() {
        return domainRank;
    }

    /**
     *
     * @param domainRank
     * The domain_rank
     */
    public void setDomainRank(Integer domainRank) {
        this.domainRank = domainRank;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}