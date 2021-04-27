package cz.muni.fi.pa165.esports.service;

import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
/**
 @author Elena Álvarez
 */
public interface BeenMappingService {
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
