package seek;

import java.util.Date;
import java.util.LinkedList;


public class C_Group {

    private String m_streams;
    private Long m_memberCount;
    private String m_name;
    private String m_twitter;
    private String m_facebook;
    private String m_website;
    private String m_youtube;
    private LinkedList<String> m_regions;
    private String m_descriptionHtml;
    private Date m_createdAt;
    private Long m_eventRegistrationCount;
    
    public C_Group( 
            String streams, 
            Long memberCount,
            String name,
            String twitter,
            String facebook,
            String website,
            String youtube,
            LinkedList<String> regions,
            String descriptionHtml,
            Date createdAt,
            Long eventRegistrationCount
    ) {
        m_streams = streams;
        m_memberCount = memberCount;
        m_name = name;
        m_twitter = twitter;
        m_facebook = facebook;
        m_website = website;
        m_youtube = youtube;
        m_regions = regions;
        m_descriptionHtml = descriptionHtml;
        m_createdAt = createdAt;
        m_eventRegistrationCount = eventRegistrationCount;
    }

    public String getStreams() {
        return m_streams;
    }

    public Long getMemberCount() {
        return m_memberCount;
    }

    public String getName() {
        return m_name;
    }

    public String getTwitter() {
        return m_twitter;
    }

    public String getFacebook() {
        return m_facebook;
    }

    public String getWebsite() {
        return m_website;
    }

    public String getYoutube() {
        return m_youtube;
    }

    public LinkedList<String> getRegions() {
        return m_regions;
    }

    public String getDescriptionHtml() {
        return m_descriptionHtml;
    }

    public Date getCreatedAt() {
        return m_createdAt;
    }

    public Long getEventRegistrationCount() {
        return m_eventRegistrationCount;
    }
}
