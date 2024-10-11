package com.stewartlavenia.tally3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class ThymeLeafConfig implements ApplicationContextAware {

//ACA represents the Spring IoC container that holds all the beans created by the application
    
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@Autowired ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("classpath:/templates-1/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(1);
        templateResolver.setCheckExistence(true); /* FYI: necessary to chain TemplateResolvers */
        templateResolver.setCacheable(false);     /* FYI: during development -> false, so that we can see changes we make */
        return templateResolver;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver2() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("classpath:/templates-2/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(2);
        templateResolver.setCheckExistence(true); /* FYI: necessary to chain TemplateResolvers */
        templateResolver.setCacheable(false);     /* FYI: during development -> false, so that we can see changes we make */
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        /* SpringTemplateEngine automatically applies SpringStandardDialect and
           enables Spring's own MessageSource message resolution mechanisms. */
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        templateEngine.addTemplateResolver(this.templateResolver());
        templateEngine.addTemplateResolver(this.templateResolver2());

        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(this.templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setCache(false);   /* FYI: during development -> false */
        viewResolver.setOrder(1);
        return viewResolver;
    }
}
