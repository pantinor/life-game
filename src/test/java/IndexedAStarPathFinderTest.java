
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import org.antinori.life.gdx.LifeMap;
import org.antinori.life.gdx.LifeMap.ManhattanDistance;
import static org.antinori.life.gdx.LifeMap.START_COLLEGE_LOCATION;
import org.antinori.life.gdx.Location;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IndexedAStarPathFinderTest {

    @Test
    public void testLifeMap() {
        LifeMap map = new LifeMap();
        GraphPath<Location> outPath = new DefaultGraphPath<>();

        boolean searchResult1 = map.getPathfinder().searchNodePath(map.getNodes()[15][9], map.getNodes()[18][12], new ManhattanDistance(), outPath);

        Assert.assertTrue(searchResult1, "Unexpected search result");
        Assert.assertEquals(outPath.getCount(), 7, "Unexpected number of nodes in path");
        
        Location l = START_COLLEGE_LOCATION.getConnections().get(0).getToNode();
        System.out.println("start " + l);
        while (l != null) {
            l = l.getConnections().first().getToNode();
            System.out.println("to " + l);
        }
    }

}
