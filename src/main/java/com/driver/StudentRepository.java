package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;



    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        String name=student.getName();
        studentMap.put(name,student);
    }

    public void saveTeacher(Teacher teacher){
        String name=teacher.getName();
        teacherMap.put(name,teacher);
    }



    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            // your code goes here
            if(teacherStudentMapping.containsKey(teacher))
            {
                List<String> al=teacherStudentMapping.get(teacher);
                al.add(student);
                teacherStudentMapping.put(teacher,al);
            }
            else {
                List<String> al=new ArrayList<>();
                al.add(student);
                teacherStudentMapping.put(teacher,al);
            }
        }
    }



    public Student findStudent(String student){
       return studentMap.get(student);
    }


    public Teacher findTeacher(String teacher){
        // your code goes here
        return teacherMap.get(teacher);
    }

    public List<String> findStudentsFromTeacher(String teacher){
        // your code goes here
        // find student list corresponding to a teacher
        List<String> al=teacherStudentMapping.get(teacher);
        return al;
    }


    public List<String> findAllStudents(){
        List<String> al=new ArrayList<>();
        for(String st:studentMap.keySet())
        {
            al.add(st);
        }
        return al;
    }


    public void deleteTeacher(String teacher){
        // your code goes here
        teacherStudentMapping.remove(teacher);
    }


    public void deleteAllTeachers(){
        // your code goes here
        teacherStudentMapping.clear();
    }
}

