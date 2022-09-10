import org.junit.Test;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.*;

public class Mytest {

    @Test
    public void tester() {
        List<String> listofc = List.of("2s", "2C", "2D");
        assertTrue(Game.isTrio(listofc));
    }


    @Test
    public void test1() {

        List<String> listofc = List.of("3s", "2C", "2D");
        assertTrue(Game.pair(listofc));

    }

    @Test
    public void test2(){

        List <String> listofc = List.of("2s", "3s","4s" );
        assertTrue(Game.sameSuit(listofc));

    }


    @Test
    public void test3(){

        List<String> listofc = List.of("2s", "4s","3s" );
        assertTrue(Game.sequence(listofc));
    }

    @Test
    public void comp(){
        List<String> listofc = List.of("3H", "KS","JH" );
        List<String> c = List.of("2S", "QD","QS" );
        assertEquals("CPU wins", Game.compare(listofc, c));

    }


}
