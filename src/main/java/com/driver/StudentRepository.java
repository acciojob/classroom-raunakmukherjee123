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
                Teacher t=teacherMap.get(teacher);
                t.setNumberOfStudents(t.getNumberOfStudents()+1);
                teacherMap.put(teacher,t);
                teacherStudentMapping.put(teacher,al);
            }
            else {
                List<String> al=new ArrayList<>();
                al.add(student);
                Teacher t=teacherMap.get(teacher);
                t.setNumberOfStudents(t.getNumberOfStudents()+1);
                teacherMap.put(teacher,t);
                teacherStudentMapping.put(teacher,al);
            }
//            Teacher t=teacherMap.get(teacher);
//            t.setNumberOfStudents(t.getNumberOfStudents()+1);
//            teacherMap.put(teacher,t);
        }
    }



    public Student findStudent(String student) {
        if (studentMap.containsKey(student)) {
            return studentMap.get(student);
        }
        return null;
    }

    public Teacher findTeacher(String teacher) {
        // your code goes here
        if (teacherMap.containsKey(teacher)) {
            return teacherMap.get(teacher);
        }
        return null;
    }
    public List<String> findStudentsFromTeacher(String teacher) {
        // your code goes here
        // find student list corresponding to a teacher
        if (teacherStudentMapping.containsKey(teacher)) {
            List<String> al = teacherStudentMapping.get(teacher);
            return al;
        }
        return null;
    }

    public List<String> findAllStudents(){
        List<String> al=new ArrayList<>();
        for(String st:studentMap.keySet())
        {
            al.add(st);
        }
        return al;
    }


    public void deleteTeacher(String teacher) {
        if (teacherMap.containsKey(teacher)) {
            if (teacherStudentMapping.containsKey(teacher)) {
                Teacher t = teacherMap.get(teacher);
                t.setNumberOfStudents(0);
                teacherMap.put(teacher, t);
                teacherStudentMapping.remove(teacher);
            }
        }
    }

    public void deleteAllTeachers(){
        // your code goes here
        teacherStudentMapping.clear();
        studentMap.clear();
        teacherMap.clear();
    }
}

