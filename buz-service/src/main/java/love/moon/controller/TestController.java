package love.moon.controller;

import love.moon.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther lovemooner
 * @date 2019/10/31 14:56
 * @describe
 */
@RestController
@RequestMapping("/{appName}")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String handleGetRequest(@PathVariable("appName") String appName, HttpServletRequest request) {
        System.out.println(appName);
        return "test";
    }

    @RequestMapping(value = "/**", method = {RequestMethod.POST, RequestMethod.PUT})
    public String handlePostRequest(@PathVariable("appName") String appName, @RequestBody String req) {
        System.out.println(req);
        return "test";
    }


//    @RequestMapping("/")
//    public String hello() {
//        return testService.hello();
//    }

}
