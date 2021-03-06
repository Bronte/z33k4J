# What?

This is a simple java library to easy-access the www.z33k.com Api.
It currently can parse a group (json) and extract all group data, including upcoming/running/past tournaments.

http://www.starcade-gaming.org

<a href="http://flattr.com/thing/995646/Brontez33k4J-on-GitHub" target="_blank">
<img src="http://api.flattr.com/button/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0" /></a>

## Features:
 - easy access to all data
 - caching of http requests

## Requirements

json-simple V1.1.1: http://code.google.com/p/json-simple/downloads/list


# Quick Example (See also src/example/Example.java):

```java
// SimpleDate for formatting
SimpleDateFormat dateFormat = new SimpleDateFormat();
TimeZone tz = TimeZone.getTimeZone( "Europe/Berlin" );
dateFormat.setTimeZone( tz );

// init the lib with a group url and a caching time of 5 minutes (5 * 60 * 1000)
// the caching time specifies how long the lib will wait to refresh the data

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
```

# TODO:
 - implement the tournament/bracket api after they got replaced/updated by the z33k developers
 - auto-generate all access-classes (in case of json, mybe http://jsongen.byingtondesign.com/)