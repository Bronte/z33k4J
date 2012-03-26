package seek;

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

public class C_SeekLib {
    
    private JSONParser m_parser;
    private ContainerFactory m_containerFactory;
    
    LinkedList<Map<Object, Object>> m_pastTournaments;
    LinkedList<Map<Object, Object>> m_upcomingTournaments;
    LinkedList<Map<Object, Object>> m_runningTournaments;
    
    private long m_lastRefresh;

    public C_SeekLib() {
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
          m_lastRefresh = 1000;
          //refreshData();
    }
    
    public ArrayList<C_Tournament> getUpcomingTournaments() throws IOException, ParseException {
        refreshData();
        return generateList( m_upcomingTournaments );
    }
    
    public ArrayList<C_Tournament> getRunningTournaments() throws IOException, ParseException {
        refreshData();
        return generateList( m_runningTournaments );
    }
    
    public ArrayList<C_Tournament> getOldTournaments() throws IOException, ParseException {
        refreshData();
        return generateList( m_pastTournaments );
    }

    @SuppressWarnings( "unchecked" )
    private void refreshData() throws ParseException, IOException {
        if( System.currentTimeMillis() - m_lastRefresh > 120000 ) {
            Map<Object, Object> json = (Map<Object, Object>) m_parser.parse( getData( "http://api.z33k.com/v1/groups/starcade.json" ), m_containerFactory );

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
    
    public static void main( String[] args ) throws ParseException, IOException {
        C_SeekLib lib = new C_SeekLib();
        ArrayList<C_Tournament> res = lib.getOldTournaments();
        
        for( int i = 0; i < res.size(); i++ ) {
            C_Tournament t = res.get( i );
            System.out.println( t.getLeagues() );
        }
    }
    

    @SuppressWarnings( "unchecked" )
    private ArrayList<C_Tournament> generateList( LinkedList<Map<Object,Object>> tournaments ) {
        ArrayList<C_Tournament> tournamentList = new ArrayList<C_Tournament>();
        Map<Object,Object> map;
        
        for( int i = 0; i < tournaments.size(); i++ ) {
            map = tournaments.get( i );
            tournamentList.add( 
                    new C_Tournament( 
                        (Long )map.get( "id" ),
                        (String )map.get( "name" ),
                        (String )map.get( "prize" ),
                        (String )map.get( "status" ),//status
                        parseLinkedList(( LinkedList<String> )map.get( "regions" ) ),//regions
                        parseLinkedList(( LinkedList<String> )map.get( "leagues" ) ),
                        (String )map.get( "location" ),//String location,
                        (String )map.get( "game_name" ),//String gameName,
                        (String )map.get( "game_name_short" ),//String gameNameShort,
                        (String )map.get( "game_size" ),
                        new Date( (Long )map.get( "start_at" ) * 1000 ),
                        (Long )map.get( "players_active_registered" ),
                        (Long )map.get( "size" ),
                        (String )map.get( "embed_js_html" ),//String jsEmbed,
                        (String )map.get( "embed_iframe_html" ),//String iframeEmbed,
                        Boolean.valueOf( (String )map.get( "z33k_run" ) )//boolean zeekRun
                    ) 
            );
        }
        return tournamentList;
    }
    
    private String parseLinkedList( LinkedList<String> list ) {
        StringBuilder res = new StringBuilder();
        
        for( int i = 0; i < list.size(); i++ ) {
            res.append( list.get( i ).substring( 0, 1 ) );
            res.append( "/" );
        }
        
        return res.toString();
    }
}
