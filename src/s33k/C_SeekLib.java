package s33k;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * Basic lib-class for group and tournament access
 * 
 * @author Bronte www.starcade-gaming.org
 *
 */
public class C_SeekLib {
    
    private JSONParser m_parser;
    private ContainerFactory m_containerFactory;
    
    private long m_lastRefresh = 0;
    private long m_cashingTime;
    private String m_groupUrl;
    
    private LinkedList<Map<Object, Object>> m_pastTournaments;
    private LinkedList<Map<Object, Object>> m_upcomingTournaments;
    private LinkedList<Map<Object, Object>> m_runningTournaments;
    
    private Map<Object, Object> m_group;

    public C_SeekLib( String groupUrl, long cashingTime ) {
        m_parser=new JSONParser();
        
        m_containerFactory = new ContainerFactory(){
            @Override
            public List<Object> creatArrayContainer() {
              return new LinkedList<Object>();
            }

            @Override
            public Map<Object, Object> createObjectContainer() {
              return new LinkedHashMap<Object, Object>();
            }
                                
          };
          m_groupUrl = groupUrl;
          m_cashingTime = cashingTime;
    }
    
    public ArrayList<C_Tournament> getUpcomingTournaments() throws ParseException, IOException {
        refreshData();
        return C_TournamentParser.generateTournamentList( m_upcomingTournaments );
    }
    
    public ArrayList<C_Tournament> getRunningTournaments() throws ParseException, IOException {
        refreshData();
        return C_TournamentParser.generateTournamentList( m_runningTournaments );
    }
    
    public ArrayList<C_Tournament> getPastTournaments() throws ParseException, IOException {
        refreshData();
        return C_TournamentParser.generateTournamentList( m_pastTournaments );
    }
    
    @SuppressWarnings( "unchecked" )
    public C_Group getGroup() throws ParseException, IOException {
        refreshData();
        return new C_Group(
                    (String ) m_group.get( "streams" ),//String streams, 
                    (Long ) m_group.get( "member_count" ),//Long memberCount,
                    (String ) m_group.get( "name" ),//String name,
                    (String ) m_group.get( "twitter" ),//String twitter,
                    (String ) m_group.get( "facebook" ),//String facebook,
                    (String ) m_group.get( "website" ),//String website,
                    (String ) m_group.get( "youtube" ),//String youtube,
                    (LinkedList<String> ) m_group.get( "regions" ),//ArrayList<String> regions,
                    (String ) m_group.get( "description_html" ),//String descriptionHtml,
                    new Date( ( (Long ) m_group.get( "created_at" ) )  * 1000 ),//Date createdAt,
                    (Long ) m_group.get( "event_registration_count" )//Long eventRegistrationCount
                );
    }

    /**
     * Refreshes all tournament types
     * gets called on each tournament request, refresh is done if current data is older than 5 minutes
     * 
     * @throws ParseException
     * @throws IOException
     */
    @SuppressWarnings( "unchecked" )
    public void refreshData() throws ParseException, IOException {
        if( System.currentTimeMillis() - m_lastRefresh > m_cashingTime ) {
            Map<Object, Object> json = (Map<Object, Object>) m_parser.parse( getData( m_groupUrl ), m_containerFactory );

            m_group = (Map<Object, Object>) json.get( "group" );
            m_pastTournaments = (LinkedList<Map<Object, Object>>) m_group.get( "past_tournaments" );
            m_upcomingTournaments = (LinkedList<Map<Object, Object>>) m_group.get( "upcoming_tournaments" );
            m_runningTournaments = (LinkedList<Map<Object, Object>>) m_group.get( "live_tournaments" );
            m_lastRefresh = System.currentTimeMillis();
        }
    }
    
    /**
     * general-purpose method to get the http response
     * 
     * @param urlString
     * @return
     * @throws IOException
     */
    public String getData( String urlString ) throws IOException {
        URL url = new URL( urlString );
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader( new InputStreamReader( yc.getInputStream() ) );
        StringBuilder inputLine = new StringBuilder();
        String line;
        
        while ( true ) {
            if( ( line = in.readLine() ) != null ) {
                inputLine.append( line );
            }
            else {
                break;
            }
        }
        in.close();
        return inputLine.toString();
    }
}
