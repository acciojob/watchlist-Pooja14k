package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {
    private HashMap<String, Student> studentList;
    private HashMap<String, Teacher> teacherList;
    private HashMap<String, List<String>> teacherStudentList;

    public StudentRepository(HashMap<String, Student> studentList, HashMap<String, Teacher> teacherList, HashMap<String, List<String>> teacherStudentList) {
        this.studentList = studentList;
        this.teacherList = teacherList;
        this.teacherStudentList = teacherStudentList;
    }

    public void addStudent(Student student) {
        studentList.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherList.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if(studentList.containsKey(student) && teacherList.containsKey(teacher)){
            List<String> studentTeacherList = new ArrayList<>();
            if(teacherStudentList.containsKey(teacher)){
                studentTeacherList = teacherStudentList.get(teacher);
            }
            studentTeacherList.add(student);
            teacherStudentList.put(teacher, studentTeacherList);
        }
    }

    public Student getStudentByName(String name) {
        return studentList.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherList.get(name);
    }

    public List<String> getStudentByTeachersName(String teacher) {
        List<String> student = new ArrayList<>();
        if(teacherStudentList.containsKey(teacher)){
            student = teacherStudentList.get(teacher);
        }
        return student;
    }

    public List<String> getAllStudents() {
        List<String> allStudents = new ArrayList<>(studentList.keySet());
        return allStudents;
    }

    public void deleteTeacherByName(String teacher) {
        List<String> students = new ArrayList<String>();
        if(teacherStudentList.containsKey(teacher)){
            students = teacherStudentList.get(teacher);
            for(String student: students){
                if(studentList.containsKey(student)){
                    studentList.remove(student);
                }
            }

            teacherStudentList.remove(teacher);
        }

        if(teacherList.containsKey(teacher)){
            teacherList.remove(teacher);
        }
    }

    public void deleteAllTeachers() {
        HashSet<String> studentMap = new HashSet<String>();
        for(String director: teacherStudentList.keySet()){
            for(String student: teacherStudentList.get(director)){
                studentMap.add(student);
            }
        }

        for(String student: studentMap){
            if(studentList.containsKey(student)){
                studentList.remove(student);
            }
        }
    }
}
