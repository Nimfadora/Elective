package service.impl;

import dao.CourseDao;
import dao.Impl.CourseDaoImpl;
import model.Course;
import model.SortFilterParams;

import java.util.List;

public class SortFilterService {
    private static CourseDao dao = CourseDaoImpl.getInstance();
    private static SortFilterService service;
    private SortFilterService(){}
    public static synchronized SortFilterService getInstance(){
        if(service == null){
            service = new SortFilterService();
        }
        return service;
    }
    public List<Course> getAll(SortFilterParams params){
        return dao.getFilteredSortedCourses(params);
    }
}
