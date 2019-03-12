package www.sumanmyon.com.jobfinder.StandardDataStorage;

public class DSFromDiffProvider {

    //Creating attribute for different provider
    private String contentProvider;

    //Creating attribute for job positions
    private String id;
    private String position;
    private String url;

    //Creating attr for time interval
    private String startDate;
    private String endDate;

    //Creating attr for company info
    private String company;
    private String companyURL;
    private String companyLogo;

    private String title;
    private String description;
    private String howToApply;

    //Creating attr for company location and others
    private String location;

    private String rateIntervalCode;

    private String minimum;
    private String maximum;

    //Constructor for GitHub
    public DSFromDiffProvider(String contentProvider,
                              String id, String type, String url,
                              String createdAt, String company, String companyURL,
                              String location, String title, String description,
                              String howToApply, String companyLogo){
        this.contentProvider = contentProvider;
        this.id = id;
        this.position = type;
        this.url = url;
        this.startDate = createdAt;
        this.company = company;
        this.companyURL = companyURL;
        this.location = location;
        this.title = title;
        this.description = description;
        this.howToApply = howToApply;
        this.companyLogo = companyLogo;
    }

    //Constructor for search.gov
    public DSFromDiffProvider(String contentProvider,
                              String id, String positionTitle, String organization,
                              String rateIntervalCode, String minimum, String maximum,
                              String startDate, String endDate, String location,
                              String url){
        this.contentProvider = contentProvider;
        this.id = id;
        this.position = positionTitle;
        this.company = organization;
        this.rateIntervalCode = rateIntervalCode;
        this.minimum = minimum;
        this.maximum = maximum;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.url = url;
    }

    public String getContentProvider() {
        return contentProvider;
    }

    public String getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getUrl() {
        return url;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCompany() {
        return company;
    }

    public String getCompanyURL() {
        return companyURL;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHowToApply() {
        return howToApply;
    }

    public String getLocation() {
        return location;
    }

    public String getRateIntervalCode() {
        return rateIntervalCode;
    }

    public String getMinimum() {
        return minimum;
    }

    public String getMaximum() {
        return maximum;
    }
}
