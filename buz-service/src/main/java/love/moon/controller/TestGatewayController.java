package love.moon.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : lovemooner
 * date : 2022/5/27 14:36
 * desc :
 */
@Slf4j
@RestController
@RequestMapping("/gw")
public class TestGatewayController {

    @GetMapping("/addRequestHeader1")
    public String testAddRequestHeader1(HttpServletRequest request,String username) {
        String param1 = request.getHeader("param1");
        log.info("param1:{}",param1);

        //AddRequestParameter
        System.out.println("username:" + username);
        return username;
    }

    /**
     * UriVariable
     * @param request
     * @param userId
     * @return
     */
    @GetMapping("/addRequestHeader2/{userId}")
    public String testAddRequestHeader2(HttpServletRequest request, @PathVariable String userId) {
        String param2 = request.getHeader("param2");
        log.info("param2:{}", param2);
        return param2;
    }

}
