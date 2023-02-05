package sia.tacocloud.takoBase.web;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {   //лучше чем просто HomeController
        registry.addViewController("/").setViewName("home");
    }

}
