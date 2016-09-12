package com.pactsafe.api.activity.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * created by Michael Welling on 8/22/16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterStore implements Serializable {

    private static final String API_VERSION = "1";
    private static final String LIBRARY_VERSION = "ps1";

    private String apiVersion = API_VERSION;
    private String libraryVersion = LIBRARY_VERSION;
    private String name;
    private String uuid;
    private String siteId;
    private EventType eventType;
    private String signerId;
    private List<String> revisions;
    private String pageTitle;
    private String pageUrl;
    private String pageDomain;
    private String pagePath;
    private String pageEncoding;
    private String referrer;
    private String browserTimezone;
    private Boolean javaEnabled;
    private String flashVersion;
    private String operatingSystem;
    private String environment;
    private String screenColorDepth;
    private String screenResolution;
    private String screenDimentions;
    private String request;
    private String external;
    private String externalUrl;
    private String customData;
    private String userAgent;
    private Boolean testMode;
    private Boolean dynamic;
    private String certification_token;

    // Group Fields
    private String key;
    private String type;
    private Integer group;
    private String containerSelector;
    private String signerIdSelector;
    private String formSelector;
    private Boolean blockFormSubmission;
    private String alertMessage;
    private Boolean confirmationEmail;
    private Boolean triggered;
    private List<Integer> contracts;
    private List<String> versions;
    private String renderId;
    private Map<String, Object> renderData;
    private Boolean autoRun;
    private Boolean displayAll;
    private String contractHtml;
    private String locale;
    private String remoteAddress;
    private Boolean silentError = false;

    // Action Fields
    private String sentAt;
    private String timeStamp;
    private String actionId;

    public ParameterStore() {}

    public ParameterStore(ParameterStore existing) {
        this.apiVersion = existing.getApiVersion();
        this.libraryVersion = existing.getLibraryVersion();
        this.name = existing.getName();
        this.uuid = existing.getUuid();
        this.siteId = existing.getSiteId();
        this.eventType = existing.getEventType();
        this.signerId = existing.getSignerId();
        this.revisions = existing.getRevisions();
        this.pageTitle = existing.getPageTitle();
        this.pageUrl = existing.getPageUrl();
        this.pageDomain = existing.getPageDomain();
        this.pagePath = existing.getPagePath();
        this.pageEncoding = existing.getPageEncoding();
        this.referrer = existing.getReferrer();
        this.browserTimezone = existing.getBrowserTimezone();
        this.javaEnabled = existing.getJavaEnabled();
        this.flashVersion = existing.getFlashVersion();
        this.operatingSystem = existing.getOperatingSystem();
        this.environment = existing.getEnvironment();
        this.screenColorDepth = existing.getScreenColorDepth();
        this.screenResolution = existing.getScreenResolution();
        this.screenDimentions = existing.getScreenDimentions();
        this.request = existing.getRequest();
        this.external = existing.getExternal();
        this.externalUrl = existing.getExternalUrl();
        this.customData = existing.getCustomData();
        this.userAgent = existing.getUserAgent();
        this.testMode = existing.getTestMode();
        this.dynamic = existing.getDynamic();
        this.certification_token = existing.getCertification_token();
        this.key = existing.getKey();
        this.type = existing.getType();
        this.group = existing.getGroup();
        this.containerSelector = existing.getContainerSelector();
        this.signerIdSelector = existing.getSignerIdSelector();
        this.formSelector = existing.getFormSelector();
        this.blockFormSubmission = existing.getBlockFormSubmission();
        this.alertMessage = existing.getAlertMessage();
        this.confirmationEmail = existing.getConfirmationEmail();
        this.triggered = existing.getTriggered();
        this.contracts = existing.getContracts();
        this.versions = existing.getVersions();
        this.renderId = existing.getRenderId();
        this.renderData = existing.getRenderData();
        this.autoRun = existing.getAutoRun();
        this.displayAll = existing.getDisplayAll();
        this.contractHtml = existing.getContractHtml();
        this.locale = existing.getLocale();
        this.remoteAddress = existing.getRemoteAddress();
        this.sentAt = existing.getSentAt();
        this.timeStamp = existing.getTimeStamp();
        this.actionId = existing.getActionId();
        this.silentError = existing.getSilentError();
    }

    public ParameterStore(ParameterStore s, ParameterStore o) {
        this.apiVersion = StringUtils.isBlank(o.getApiVersion()) ? s.getApiVersion() : o.getApiVersion();
        this.libraryVersion = StringUtils.isBlank(o.getLibraryVersion()) ? s.getLibraryVersion() : o.getLibraryVersion();
        this.name = StringUtils.isBlank(o.getName()) ? s.getName() : o.getName();
        this.uuid = StringUtils.isBlank(o.getUuid()) ? s.getUuid() : o.getUuid();
        this.siteId = StringUtils.isBlank(o.getSiteId()) ? s.getSiteId() : o.getSiteId();
        this.eventType = o.getEventType() == null ? s.getEventType() : o.getEventType();
        this.signerId = StringUtils.isBlank(o.getSignerId()) ? s.getSignerId() : o.getSignerId();
        this.revisions = o.getRevisions() == null ? s.getRevisions() : o.getRevisions();
        this.versions = o.getVersions() == null ? s.getVersions() : o.getVersions();
        this.contracts = o.getContracts() == null ? s.getContracts() : o.getContracts();
        this.group = o.getGroup() == null ? s.getGroup() : o.getGroup();
        this.confirmationEmail = o.getConfirmationEmail() == null ? s.getConfirmationEmail() : o.getConfirmationEmail();
        this.pageTitle = StringUtils.isBlank(o.getPageTitle()) ? s.getPageTitle() : o.getPageTitle();
        this.pageUrl = StringUtils.isBlank(o.getPageUrl()) ? s.getPageUrl() : o.getPageUrl();
        this.pageDomain = StringUtils.isBlank(o.getPageDomain()) ? s.getPageDomain() : o.getPageDomain();
        this.pagePath = StringUtils.isBlank(o.getPagePath()) ? s.getPagePath() : o.getPagePath();
        this.pageEncoding = StringUtils.isBlank(o.getPageEncoding()) ? s.getPageEncoding() : o.getPageEncoding();
        this.referrer = StringUtils.isBlank(o.getReferrer()) ? s.getReferrer() : o.getReferrer();
        this.browserTimezone = StringUtils.isBlank(o.getBrowserTimezone()) ? s.getBrowserTimezone() : o.getBrowserTimezone();
        this.javaEnabled = o.getJavaEnabled() == null ? s.getJavaEnabled() : o.getJavaEnabled();
        this.flashVersion = StringUtils.isBlank(o.getFlashVersion()) ? s.getFlashVersion() : o.getFlashVersion();
        this.operatingSystem = StringUtils.isBlank(o.getOperatingSystem()) ? s.getOperatingSystem() : o.getOperatingSystem();
        this.environment = StringUtils.isBlank(o.getEnvironment()) ? s.getEnvironment() : o.getEnvironment();
        this.screenColorDepth = StringUtils.isBlank(o.getScreenColorDepth()) ? s.getScreenColorDepth() : o.getScreenColorDepth();
        this.screenResolution = StringUtils.isBlank(o.getScreenResolution()) ? s.getScreenResolution() : o.getScreenResolution();
        this.screenDimentions = StringUtils.isBlank(o.getScreenDimentions()) ? s.getScreenDimentions() : o.getScreenDimentions();
        this.key = StringUtils.isBlank(o.getKey()) ? s.getKey() : o.getKey();
        this.type = StringUtils.isBlank(o.getType()) ? s.getType() : o.getType();
        this.containerSelector = StringUtils.isBlank(o.getContainerSelector()) ? s.getContainerSelector() : o.getContainerSelector();
        this.signerIdSelector = StringUtils.isBlank(o.getSignerIdSelector()) ? s.getSignerIdSelector() : o.getSignerIdSelector();
        this.formSelector = StringUtils.isBlank(o.getFormSelector()) ? s.getFormSelector() : o.getFormSelector();
        this.blockFormSubmission = o.getBlockFormSubmission() == null ? s.getBlockFormSubmission() : o.getBlockFormSubmission();
        this.alertMessage = StringUtils.isBlank(o.getAlertMessage()) ? s.getAlertMessage() : o.getAlertMessage();
        this.triggered = o.getTriggered() == null ? s.getTriggered() : o.getTriggered();
        this.renderId = StringUtils.isBlank(o.getRenderId()) ? s.getRenderId() : o.getRenderId();
        this.renderData = o.getRenderData() == null ? s.getRenderData() : o.getRenderData();
        this.autoRun = o.getAutoRun() == null ? s.getAutoRun() : o.getAutoRun();
        this.displayAll = o.getDisplayAll() == null ? s.getDisplayAll() : o.getDisplayAll();
        this.contractHtml = StringUtils.isBlank(o.getContractHtml()) ? s.getContractHtml() : o.getContractHtml();
        this.locale = StringUtils.isBlank(o.getLocale()) ? s.getLocale() : o.getLocale();
        this.remoteAddress = StringUtils.isBlank(o.getRemoteAddress()) ? s.getRemoteAddress() : o.getRemoteAddress();
        this.timeStamp = StringUtils.isBlank(o.getTimeStamp()) ? s.getTimeStamp() : o.getTimeStamp();
        this.sentAt = StringUtils.isBlank(o.getSentAt()) ? s.getSentAt() : o.getSentAt();
        this.actionId = StringUtils.isBlank(o.getActionId()) ? s.getActionId() : o.getActionId();
        this.silentError = o.getSilentError() == null ? s.getSilentError() : o.getSilentError();
    }

    public String getApiVersion() {
        return apiVersion;
    }

    @JsonProperty("api_version")
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getLibraryVersion() {
        return libraryVersion;
    }

    @JsonProperty("library_version")
    public void setLibraryVersion(String libraryVersion) {
        this.libraryVersion = libraryVersion;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSiteId() {
        return siteId;
    }

    @JsonProperty("site_id")
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getSignerId() {
        return signerId;
    }

    @JsonProperty("signer_id")
    public void setSignerId(String signerId) {
        this.signerId = signerId;
    }

    public List<String> getRevisions() {
        return revisions;
    }

    @JsonProperty("revisions")
    public void setRevisions(List<String> revisions) {
        this.revisions = revisions;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    @JsonProperty("page_title")
    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    @JsonProperty("page_url")
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageDomain() {
        return pageDomain;
    }

    @JsonProperty("page_domain")
    public void setPageDomain(String pageDomain) {
        this.pageDomain = pageDomain;
    }

    public String getPagePath() {
        return pagePath;
    }

    @JsonProperty("page_path")
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getPageEncoding() {
        return pageEncoding;
    }

    @JsonProperty("page_encoding")
    public void setPageEncoding(String pageEncoding) {
        this.pageEncoding = pageEncoding;
    }

    public String getReferrer() {
        return referrer;
    }

    @JsonProperty("referrer")
    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getBrowserTimezone() {
        return browserTimezone;
    }

    @JsonProperty("browser_timezone")
    public void setBrowserTimezone(String browserTimezone) {
        this.browserTimezone = browserTimezone;
    }

    public Boolean getJavaEnabled() {
        return javaEnabled;
    }

    public void setJavaEnabled(Boolean javaEnabled) {
        this.javaEnabled = javaEnabled;
    }

    public String getFlashVersion() {
        return flashVersion;
    }

    public void setFlashVersion(String flashVersion) {
        this.flashVersion = flashVersion;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getScreenColorDepth() {
        return screenColorDepth;
    }

    public void setScreenColorDepth(String screenColorDepth) {
        this.screenColorDepth = screenColorDepth;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getScreenDimentions() {
        return screenDimentions;
    }

    public void setScreenDimentions(String screenDimentions) {
        this.screenDimentions = screenDimentions;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Boolean getTestMode() {
        return testMode;
    }

    public void setTestMode(Boolean testMode) {
        this.testMode = testMode;
    }

    public Boolean getDynamic() {
        return dynamic;
    }

    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }

    public String getCertification_token() {
        return certification_token;
    }

    public void setCertification_token(String certification_token) {
        this.certification_token = certification_token;
    }

    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Integer getGroup() {
        return group;
    }

    @JsonProperty("group")
    public void setGroup(Integer group) {
        this.group = group;
    }

    public String getContainerSelector() {
        return containerSelector;
    }

    @JsonProperty("container_selector")
    public void setContainerSelector(String containerSelector) {
        this.containerSelector = containerSelector;
    }

    public String getSignerIdSelector() {
        return signerIdSelector;
    }

    @JsonProperty("signer_id_selector")
    public void setSignerIdSelector(String signerIdSelector) {
        this.signerIdSelector = signerIdSelector;
    }

    public String getFormSelector() {
        return formSelector;
    }

    @JsonProperty("form_selector")
    public void setFormSelector(String formSelector) {
        this.formSelector = formSelector;
    }

    public Boolean getBlockFormSubmission() {
        return blockFormSubmission;
    }

    @JsonProperty("block_form_submission")
    public void setBlockFormSubmission(Boolean blockFormSubmission) {
        this.blockFormSubmission = blockFormSubmission;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    @JsonProperty("alert_message")
    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public Boolean getConfirmationEmail() {
        return confirmationEmail;
    }

    @JsonProperty("confirmation_email")
    public void setConfirmationEmail(Boolean confirmationEmail) {
        this.confirmationEmail = confirmationEmail;
    }

    public Boolean getTriggered() {
        return triggered;
    }

    @JsonProperty("triggered")
    public void setTriggered(Boolean triggered) {
        this.triggered = triggered;
    }

    public List<Integer> getContracts() {
        return contracts;
    }

    @JsonProperty("contracts")
    public void setContracts(List<Integer> contracts) {
        this.contracts = contracts;
    }

    public List<String> getVersions() {
        return versions;
    }

    @JsonProperty("versions")
    public void setVersions(List<String> versions) {
        this.versions = versions;
    }

    public String getRenderId() {
        return renderId;
    }

    @JsonProperty("render_id")
    public void setRenderId(String renderId) {
        this.renderId = renderId;
    }

    public Map<String, Object> getRenderData() {
        return renderData;
    }

    @JsonProperty("render_data")
    public void setRenderData(Map<String, Object> renderData) {
        this.renderData = renderData;
    }

    public Boolean getAutoRun() {
        return autoRun;
    }

    @JsonProperty("auto_run")
    public void setAutoRun(Boolean autoRun) {
        this.autoRun = autoRun;
    }

    public Boolean getDisplayAll() {
        return displayAll;
    }

    @JsonProperty("display_all")
    public void setDisplayAll(Boolean displayAll) {
        this.displayAll = displayAll;
    }

    public String getContractHtml() {
        return contractHtml;
    }

    @JsonProperty("contract_html")
    public void setContractHtml(String contractHtml) {
        this.contractHtml = contractHtml;
    }

    public String getLocale() {
        return locale;
    }

    @JsonProperty("locale")
    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    @JsonProperty("remote_address")
    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public Boolean getSilentError() {
        return silentError;
    }

    @JsonProperty("silent_error")
    public void setSilentError(Boolean silentError) {
        this.silentError = silentError;
    }
}

