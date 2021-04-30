package cz.muni.fi.pa165.esports.service;

import java.util.Collection;
import java.util.List;

import org.dozer.Mapper;
/**
 * taken as is from sample project
 */
public interface BeanMappingService {
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
    public  <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
