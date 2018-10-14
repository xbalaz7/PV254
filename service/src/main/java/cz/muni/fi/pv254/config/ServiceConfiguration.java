package cz.muni.fi.pv254.config;


import cz.muni.fi.pv254.ApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Import(ApplicationContext.class)
@ComponentScan(basePackages = {"cz.muni.fi.pv254", "cz.muni.fi.pv254.facade"})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer() {
        List<String> mappingFiles = new ArrayList();
        mappingFiles.add("dozerJdk8Converters.xml");

        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        dozerBeanMapper.setMappingFiles(mappingFiles);
        return dozerBeanMapper;
    }
}
