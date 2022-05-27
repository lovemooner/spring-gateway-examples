package love.moon.service.impl;

import love.moon.service.TestService;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @auther lovemooner
 * @date 2020/3/20 18:07
 * @describe
 */
@Component
public class TestServiceImpl implements TestService {

    public String hello() {
        Random r= new Random();
        return "hi"+r.nextInt(100);
    }

}
