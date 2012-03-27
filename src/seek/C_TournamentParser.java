package seek;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

public class C_TournamentParser {

    @SuppressWarnings( "unchecked" ) 
    public static ArrayList<C_Tournament> generateTournamentList( LinkedList<Map<Object,Object>> tournaments ) {
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
                        (LinkedList<String> )map.get( "regions" ),//regions
                        (LinkedList<String> )map.get( "leagues" ),
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
}
