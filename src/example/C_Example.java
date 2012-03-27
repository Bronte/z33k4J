package example;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import org.json.simple.parser.ParseException;

import seek.C_Group;
import seek.C_SeekLib;
import seek.C_Tournament;

/**
 * 
 * Basic example which prints some past tournaments
 * 
 * @author Bronte www.starcade-gaming.org
 *
 */
public class C_Example {
    
    public static void main( String[] args ) throws IOException, ParseException {
        // SimpleDate for formatting
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        TimeZone tz = TimeZone.getTimeZone( "Europe/Berlin" );
        dateFormat.setTimeZone( tz );
        
        // init the lib with a group url and a cashing time of 5 minutes (5 * 60 * 1000)
        // the cashing time specifies how long the lib will wait to refresh the data
        C_SeekLib lib = new C_SeekLib( "http://api.z33k.com/v1/groups/starcade.json", 300000 );
        // get all past tournaments
        ArrayList<C_Tournament> res = lib.getPastTournaments();
        
        // iterate over all tournaments and print some of the data
        for( int i = 0; i < res.size(); i++ ) {
            C_Tournament tournament = res.get( i );
            System.out.println( "Name: " + tournament.getName() );
            System.out.println( "Date: " + dateFormat.format( tournament.getStart() ) + " CET" );
            System.out.println( "Leagues: " + tournament.getLeaguesAsSingleString() );
            System.out.println( "GameSize: " + tournament.getGameSize() );
            System.out.println( "Registred players: " + tournament.getPlayersActiveRegistered() );
            System.out.println( "MaximumSize: " + tournament.getMaxSize() );
            System.out.println( "URL: " + tournament.getSingleUrl() );
            System.out.println();
        }
        
        // print some group details, they should be cashed
        C_Group group = lib.getGroup();
        System.out.println( "GroupName: " + group.getName() );
        System.out.println( "Website: " + group.getWebsite() );
        System.out.println( "MemberCount: " + group.getMemberCount() );
        System.out.println( "EventRegistrationCount: " + group.getEventRegistrationCount() );
        System.out.println( "Created at: " + dateFormat.format( group.getCreatedAt() ) );
    }
}
