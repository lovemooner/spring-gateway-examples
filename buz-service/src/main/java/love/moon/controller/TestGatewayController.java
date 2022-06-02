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

    @GetMapping("/filter1")
    public String testGatewayFilter1(HttpServletRequest request,String username,String password) {
        String param1 = request.getHeader("param1");
        log.info("param1:{}",param1);

        //AddRequestParameter
        log.info("username:{},password:{}" , username,password);
        return "filter1 response";
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

    @GetMapping("/prefix-path")
    public String testPrefixPath() {
        return "prefix-path";
    }

    @GetMapping("/strip-path")
    public String testStripPrefix() {
        return "strip-path";
    }

}
