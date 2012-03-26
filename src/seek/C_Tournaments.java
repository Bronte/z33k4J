package seek;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

import org.json.simple.parser.ParseException;

class C_Tournaments {

    private C_SeekLib m_lib;
    
    public C_Tournaments( C_SeekLib lib ) {
        m_lib = lib;
    }
    
    @SuppressWarnings( "unchecked" ) ArrayList<C_Tournament> generateTournamentList( LinkedList<Map<Object,Object>> tournaments ) {
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
                        (Boolean )map.get( "z33k_run" )//boolean zeekRun
                    ) 
            );
        }
        return tournamentList;
    }
    
    public ArrayList<C_Tournament> getUpcomingTournaments( LinkedList<Map<Object, Object>> upcomingTournaments ) throws IOException, ParseException {
        m_lib.refreshData();
        return generateTournamentList( upcomingTournaments );
    }
    
    public ArrayList<C_Tournament> getRunningTournaments( LinkedList<Map<Object, Object>> runningTournaments ) throws IOException, ParseException {
        m_lib.refreshData();
        return generateTournamentList( runningTournaments );
    }
    
    public ArrayList<C_Tournament> getPastTournaments( LinkedList<Map<Object, Object>> pastTournaments ) throws IOException, ParseException {
        m_lib.refreshData();
        return generateTournamentList( pastTournaments );
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
