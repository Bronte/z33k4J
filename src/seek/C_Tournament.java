package seek;

import java.util.Date;
import java.util.LinkedList;

/**
 * 
 * Tournament Class
 * 
 * @author Bronte
 *
 */

public class C_Tournament {
    
    private long m_id; 
    private String m_name; 
    private String m_prize;

    private LinkedList<String> m_leagues;
    private String m_gameSize;
    private Date m_start;
    
    private long m_playersActiveRegistered;
    private long m_maxSize;
    
    private String m_status;
    private LinkedList<String> m_regions;
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
            LinkedList<String> regions,
            LinkedList<String> leagues,
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
    
    public String getStatus() {
        return m_status;
    }

    public LinkedList<String> getRegions() {
        return m_regions;
    }
    
    public String getRegionsAsSingleString() {
        return parseLinkedList( m_regions );
    }

    public String getLcation() {
        return m_location;
    }

    public String getGameName() {
        return m_gameName;
    }

    public String getGameNameShort() {
        return m_gameNameShort;
    }

    public String getJsEmbed() {
        return m_jsEmbed;
    }

    public String getIframeEmbed() {
        return m_iframeEmbed;
    }

    public boolean isZeekRun() {
        return m_zeekRun;
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

    public LinkedList<String> getLeagues() {
        return m_leagues;
    }
    
    public String getLeaguesAsSingleString() {
        return parseLinkedList( m_leagues );
    }

    public String getGameSize() {
        return m_gameSize;
    }

    public Date getStart() {
        return m_start;
    }
    
    /**
     * Parses the iframe code and extracts the tournament url
     * 
     * @return tournament url
     */
    public String getSingleUrl() {
        String url;
        int end;
        url = m_iframeEmbed.substring( 13 );
        end = url.lastIndexOf( "width" );
        url = url.substring( 0, end - 7 );
        return url;
    }
    
    public static String parseLinkedList( LinkedList<String> list ) {
        StringBuilder res = new StringBuilder();
        
        for( int i = 0; i < list.size(); i++ ) {
            res.append( list.get( i ).substring( 0, 1 ) );
            res.append( "/" );
        }
        
        return res.toString();
    }
}
