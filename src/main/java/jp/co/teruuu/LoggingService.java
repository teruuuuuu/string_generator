package jp.co.teruuu;

import jp.co.teruuu.stringgen.Opt;
import jp.co.teruuu.stringgen.RandomGen;
import jp.co.teruuu.stringgen.RandomNum;
import jp.co.teruuu.stringgen.SeqGen;
import jp.co.teruuu.stringgen.StaticString;
import jp.co.teruuu.stringgen.StrGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

@Service
public class LoggingService {
    private static final Logger log = LoggerFactory.getLogger(LoggingService.class);

    StrGen space = new StaticString(" ");
    StrGen colon = new StaticString(":");
    StrGen randomName = randomName();
    StrGen randomNum = new RandomNum(new BigDecimal(0), new BigDecimal(100), 3);

    public void execute(Long id, String name, Date reqDate) {
        StrGen strGen = new RandomGen(Arrays.asList(hellowWorld(), commandLog(), globalCommandLog(), moveCommandLog()));

        for (int i = 0; i < 1000; i++) {
            log.info(strGen.genStr());
        }
    }

    private StrGen randomName() {
        StaticString a = new StaticString("A");
        StaticString b = new StaticString("B");
        StaticString c = new StaticString("C");
        RandomGen r = new RandomGen(Arrays.asList(a, b, c));
        return new SeqGen(Arrays.asList(r, r, r));
    }

    private StrGen hellowWorld() {
        StaticString hello = new StaticString("hello");
        StaticString world = new StaticString("world");

        Opt exclamation = new Opt(new StaticString("!"), 0.5);
        RandomGen helloOrWorld = new RandomGen(Arrays.asList(hello, world));

        return new SeqGen(Arrays.asList(hello, space, helloOrWorld, space, randomName, exclamation));
    }



    private StrGen commandLog() {
        StaticString add = new StaticString("add");
        StaticString remove = new StaticString("remove");
        StaticString set = new StaticString("set");
        RandomGen r = new RandomGen(Arrays.asList(add, remove, set));

        return new SeqGen(Arrays.asList(r, space, randomName, colon, randomNum));
    }

    private StrGen globalCommandLog() {
        StaticString add = new StaticString("globalAdd");
        StaticString remove = new StaticString("globalRemove");
        RandomGen r = new RandomGen(Arrays.asList(add, remove));

        return new SeqGen(Arrays.asList(r, space, randomNum));
    }

    private StrGen moveCommandLog() {
        StaticString move = new StaticString("move");
        StaticString from = new StaticString("from");
        StaticString to = new StaticString("to");

        return new SeqGen(Arrays.asList(move, space, from, space, randomName, space, to, space, randomName, space, colon, space, randomNum));
    }


}
