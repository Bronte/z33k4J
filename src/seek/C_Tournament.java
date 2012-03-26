package seek;

import java.util.Date;

public class C_Tournament {
    
    private long m_id; 
    private String m_name; 
    private String m_prize;

    private String m_leagues;
    private String m_gameSize;
    private Date m_start;
    
    private long m_playersActiveRegistered;
    private long m_maxSize;
    
    private String m_status;
    private String m_regions;
    private String m_location;
    private String m_gameName;
    private String m_gameNameShort;
    private String m_jsEmbed;
    private String m_iframeEmbed;
    private boolean m_zeekRun;
    
    public C_Tournament( 
            long id, 
            String name, 
            String prize,
            String status,
            String regions,
            String leagues,
            String location,
            String gameName,
            String gameNameShort,
            String gameSize,
            Date start,
            long players_active_registered,
            long maxSize,
            String jsEmbed,
            String iframeEmbed,
            boolean zeekRun
    ) {
        m_id = id;
        m_name = name;
        m_prize = prize;
        m_leagues = leagues;
        m_gameSize = gameSize;
        m_start = start;
        m_playersActiveRegistered = players_active_registered;
        m_maxSize = maxSize;
        m_status = status;
        m_regions = regions;
        m_location = location;
        m_gameName = gameName;
        m_gameNameShort = gameNameShort;
        m_jsEmbed = jsEmbed;
        m_iframeEmbed = iframeEmbed;
        m_zeekRun = zeekRun;
    }
    
    public long getPlayersActiveRegistered() {
        return m_playersActiveRegistered;
    }


    public long getMaxSize() {
        return m_maxSize;
    }

    public long getId() {
        return m_id;
    }

    public String getName() {
        return m_name;
    }

    public String getPrize() {
        return m_prize;
    }

    public String getLeagues() {
        return m_leagues;
    }

    public String getGameSize() {
        return m_gameSize;
    }

    public Date getStart() {
        return m_start;
    }
    
    public String getSingleUrl() {
        String url;
        int end;
        url = m_iframeEmbed.substring( 13 );
        end = url.lastIndexOf( "width" );
        url = url.substring( 0, end - 7 );
        return url;
    }
}
