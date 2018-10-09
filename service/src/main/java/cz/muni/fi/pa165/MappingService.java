package cz.muni.fi.pa165;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author Radovan Lapar
 */
public interface MappingService {

    /**
     * Maps a collection of objects to objects of given type
     * @param objects to be mapped
     * @param mapToClass class to be mapped to
     * @param <T>
     * @return list of objects of type mapToClass
     */
    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    /**
     * Maps given object to an object of given type
     * @param o object to be mapped
     * @param mapToClass class to be mapped to
     * @param <T>
     * @return object of type mapToClass
     */
    <T> T mapTo(Object o, Class<T> mapToClass);

    /**
     * Get used mapper
     * @return used mapper
     */
    Mapper getMapper();
}
