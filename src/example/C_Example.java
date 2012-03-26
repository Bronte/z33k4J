package example;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import seek.C_SeekLib;
import seek.C_Tournament;

/**
 * 
 * Basic example which prints some past tournaments
 * 
 * @author Bronte
 *
 */
public class C_Example {
    
    public static void main( String[] args ) throws IOException, ParseException {
        // SimpleDate for formatting
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        
        // init the lib
        C_SeekLib lib = new C_SeekLib( "http://api.z33k.com/v1/groups/starcade.json" );
        // get all past tournaments
        ArrayList<C_Tournament> res = lib.getPastTournaments();
        
        //iterate over all tournaments and print some of the data
        for( int i = 0; i < res.size(); i++ ) {
            C_Tournament tournament = res.get( i );
            System.out.println( tournament.getName() );
            System.out.println( dateFormat.format( tournament.getStart() ) + " CET" );
            System.out.println( tournament.getLeagues() );
            System.out.println( tournament.getGameSize() );
            System.out.println( tournament.getPlayersActiveRegistered() );
            System.out.println( tournament.getMaxSize() );
            System.out.println( tournament.getSingleUrl() );
        }
    }
}
