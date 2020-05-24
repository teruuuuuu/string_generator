package jp.co.teruuu;

import jp.co.teruuu.stringgen.Opt;
import jp.co.teruuu.stringgen.RandomGen;
import jp.co.teruuu.stringgen.RandomNum;
import jp.co.teruuu.stringgen.SeqGen;
import jp.co.teruuu.stringgen.StaticString;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;
//import java.util.logging.Logger;

@SpringBootApplication
public class StringGenerator {
    private static final Logger log = LoggerFactory.getLogger(StringGenerator.class);
//    private static final Logger log = Logger.getLogger(jp.co.teruuu.StringGenerator);

    public static void main(String[] args) {
        SpringApplication.run(StringGenerator.class, args);


    }
}
