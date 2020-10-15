public class OOP {
    public static void main (String[] args){
        System.out.println(Student.totalNumStudent);
        Student st1 = new Student();
        System.out.println(st1.totalNumStudent);
        Student st2 = new Student();
        System.out.println(st2.totalNumStudent);
        Student.increaseStudentNum();
        System.out.println(Student.totalNumStudent);
    }
}
