
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import java.util.Iterator;
import org.antinori.life.gdx.Actor;
import org.antinori.life.gdx.ActorType;
import org.antinori.life.gdx.BaseScreen;
import org.antinori.life.gdx.LifeMap;
import org.antinori.life.gdx.LifeMap.ManhattanDistance;
import org.antinori.life.gdx.Location;
import org.antinori.life.gdx.NewGame;
import org.antinori.life.gdx.Player;
import static org.mockito.Mockito.mock;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IndexedAStarPathFinderTest {

    //@Test
    public void testLifeMap() {
        LifeMap map = new LifeMap();
        GraphPath<Location> outPath = new DefaultGraphPath<>();

        boolean searchResult1 = map.getPathfinder().searchNodePath(map.getNodes()[15][9], map.getNodes()[18][12], new ManhattanDistance(), outPath);

        Assert.assertTrue(searchResult1, "Unexpected search result");
        Assert.assertEquals(outPath.getCount(), 7, "Unexpected number of nodes in path");

        BaseScreen screen = mock(BaseScreen.class);
        NewGame game = new NewGame(screen);

        Actor a = new Actor(ActorType.PLAYER1);
        Actor b = new Actor(ActorType.PLAYER2);
        Actor c = new Actor(ActorType.PLAYER3);
        
        game.addPlayer("fred", a, true);
        game.addPlayer("tom", b, true);
        game.addPlayer("amy", c, true);

        game.initPlayers();

        while (!game.allPlayersRetired()) {

            System.out.println(game.currentPlayer().toShortString());
            System.out.println(game.currentPlayer().getLocation());

            game.play();

            System.out.println("*********");
        }
        
        Iterator<Player> players = game.getPlayers();
        while(players.hasNext()) {
            Player p = players.next();
            System.out.println("XXXXXXXXXX");
            System.out.println(p.toShortString());
            System.out.println(p.getLocation());
            System.out.println("XXXXXXXXXX");
        }
    }

}
