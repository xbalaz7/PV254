package cz.muni.fi.pv254;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MappingServiceImpl implements MappingService {

    @Autowired
    private Mapper dozer;

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object o : objects) {
            if(o == null) continue;
            mappedCollection.add(this.mapTo(o, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public <T> T mapTo(Object o, Class<T> mapToClass) {
        if(o == null) return null;
        return dozer.map(o, mapToClass);
    }

    @Override
    public Mapper getMapper() {
        return dozer;
    }
}
