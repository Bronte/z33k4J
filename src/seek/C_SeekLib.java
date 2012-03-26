package seek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * Basic lib-class for group-tournament access
 * 
 * @author Bronte
 *
 */
public class C_SeekLib {
    
    private JSONParser m_parser;
    private ContainerFactory m_containerFactory;
    
    private long m_lastRefresh = 0;
    private String m_groupUrl;
    
    private C_Tournaments m_tournaments;
    
    private LinkedList<Map<Object, Object>> m_pastTournaments;
    private LinkedList<Map<Object, Object>> m_upcomingTournaments;
    private LinkedList<Map<Object, Object>> m_runningTournaments;

    public C_SeekLib( String groupUrl ) {
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
          m_tournaments = new C_Tournaments( this );
    }
    
    public ArrayList<C_Tournament> getUpcomingTournaments() throws ParseException, IOException {
        refreshData();
        return m_tournaments.generateTournamentList( m_upcomingTournaments );
    }
    
    public ArrayList<C_Tournament> getRunningTournaments() throws ParseException, IOException {
        refreshData();
        return m_tournaments.generateTournamentList( m_runningTournaments );
    }
    
    public ArrayList<C_Tournament> getPastTournaments() throws ParseException, IOException {
        refreshData();
        return m_tournaments.generateTournamentList( m_pastTournaments );
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
        if( System.currentTimeMillis() - m_lastRefresh > 300000 ) {
            Map<Object, Object> json = (Map<Object, Object>) m_parser.parse( getData( m_groupUrl ), m_containerFactory );

            Map<Object, Object> group = (Map<Object, Object>) json.get( "group" );
            m_pastTournaments = (LinkedList<Map<Object, Object>>) group.get( "past_tournaments" );
            m_upcomingTournaments = (LinkedList<Map<Object, Object>>) group.get( "upcoming_tournaments" );
            m_runningTournaments = (LinkedList<Map<Object, Object>>) group.get( "live_tournaments" );
            m_lastRefresh = System.currentTimeMillis();
        }
    }
    
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
