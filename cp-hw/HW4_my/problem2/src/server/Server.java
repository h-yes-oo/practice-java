package server;

import course.*;
import utils.Config;
import utils.ErrorCode;
import utils.Pair;

import java.io.*;
import java.nio.file.Files; //added
import java.util.*;

public class Server {
    public List<Course> courseList;

    public Server(){
        courseList = new LinkedList<Course>();
        File collegeList = new File("data/Courses/2020_Spring");
        for(File college : collegeList.listFiles()){
            for(File id : college.listFiles()){
                try{
                    String info = Files.readString(id.toPath());
                    String collegeName = college.getName();
                    int courseId = Integer.parseInt(id.getName().split("\\.")[0]);
                    String[] infoList = info.split("\\|");
                    Course course = new Course(courseId,collegeName,infoList[0],infoList[1],Integer.parseInt(infoList[2]),infoList[3],Integer.parseInt(infoList[4]),infoList[5],infoList[6],Integer.parseInt(infoList[7]));
                    courseList.add(course);
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Course> search(Map<String,Object> searchConditions, String sortCriteria){
        List<Course> list = new ArrayList<>();
        for(Course course : this.courseList){
            list.add(course);
        }
        //if searchConditions containsKey("dept")
        if(searchConditions.containsKey("dept")) {
            String dept = (String) searchConditions.get("dept");
            Iterator<Course> iterator = list.iterator();
            while(iterator.hasNext()){
                Course c = iterator.next();
                if (!c.department.equals(dept)){
                    iterator.remove();
                }
            }
        }
        //if searchConditions containsKey("ay")
        if(searchConditions.containsKey("ay")) {
            int ay = (int) searchConditions.get("ay");
            Iterator<Course> iterator = list.iterator();
            while(iterator.hasNext()) {
                Course c = iterator.next();
                if (c.academicYear != ay) {
                    iterator.remove();
                }
            }
        }
        //if searchConditions containsKey("name")
        if(searchConditions.containsKey("name")) {
            String[] keywords = ((String)searchConditions.get("name")).split(" ");
            Iterator<Course> iterator = list.iterator();
            while(iterator.hasNext()){
                Course c = iterator.next();
                Set<String> courseNames = new HashSet<>();
                for(String name: c.courseName.split(" ")){
                    courseNames.add(name);
                }
                for(String keyword : keywords){
                    if(!courseNames.contains(keyword)) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
        //sort list before returning
        //default: sort by courseId
        Comparator<Course> idComparator = new Comparator<Course>() {
            public int compare(Course c1, Course c2) {
                return c1.courseId - c2.courseId;
            }
        };
        Collections.sort(list, idComparator);
        if(sortCriteria!=null) {
            //if sortCriteria.equals("name")
            if (sortCriteria.equals("name")) {
                Comparator<Course> nameComparator = new Comparator<Course>() {
                    public int compare(Course c1, Course c2) {
                        return c1.courseName.compareTo(c2.courseName);
                    }
                };
                Collections.sort(list, nameComparator);
            }
            //if sortCriteria.equals("dept")
            if (sortCriteria.equals("dept")) {
                Comparator<Course> deptComparator = new Comparator<Course>() {
                    public int compare(Course c1, Course c2) {
                        return c1.department.compareTo(c2.department);
                    }
                };
                Collections.sort(list, deptComparator);
            }
            //if sortCriteria.equals("ay")
            if (sortCriteria.equals("ay")) {
                Comparator<Course> ayComparator = new Comparator<Course>() {
                    public int compare(Course c1, Course c2) {
                        return c1.academicYear - c2.academicYear;
                    }
                };
                Collections.sort(list, ayComparator);
            }
        }
        return list;
    }

    public int bid(int courseId, int mileage, String userId){
        Pair<Integer,List<Bidding>> currBid = retrieveBids(userId);

        if(currBid.key == ErrorCode.USERID_NOT_FOUND) return currBid.key;

        int idFlag = 0;
        for(Course course: this.courseList){
            if(course.courseId == courseId){
                idFlag = 1;
                break;
            }
        }
        if(idFlag == 0) return ErrorCode.NO_COURSE_ID;

        List<Bidding> bids = currBid.value;
        if(mileage<0) return ErrorCode.NEGATIVE_MILEAGE;

        if(mileage > Config.MAX_MILEAGE_PER_COURSE) return ErrorCode.OVER_MAX_COURSE_MILEAGE;

        Iterator<Bidding> iterator = bids.iterator();
        while(iterator.hasNext()) {
            Bidding b = iterator.next();
            if (b.courseId == courseId) {
                iterator.remove();
                break;
            }
        }

        int total=0;
        for(Bidding bidding : bids){
            total += bidding.mileage;
        }
        if((total+mileage) > Config.MAX_MILEAGE) return ErrorCode.OVER_MAX_MILEAGE;


        if(mileage != 0){
            bids.add(new Bidding(courseId,mileage));
        }
        try{
            FileWriter fw = new FileWriter("data/Users/"+userId+"/bid.txt",false);
            for(Bidding bidding : bids){
                fw.write(Integer.toString(bidding.courseId)+"|"+Integer.toString(bidding.mileage)+"\n");
            }
            fw.close();
        } catch(IOException e){
            e.printStackTrace();
            return ErrorCode.IO_ERROR;
        }
        return ErrorCode.SUCCESS;
    }

    public Pair<Integer,List<Bidding>> retrieveBids(String userId){
        int code = ErrorCode.SUCCESS;
        List<Bidding> bidList = new ArrayList<Bidding>();
        //find file with data/Users/userId
        File user = new File("data/Users/"+userId);
        //if not found, code = ErrorCode.USERID_NOT_FOUND;
        if(!user.exists() || userId.length() != 10) {
            code = ErrorCode.USERID_NOT_FOUND;
        } else {
            //find bid.txt file inside data/Users/userId
            File bidFile = new File(user,"bid.txt");
            //if not found, code = ErrorCode.IO_ERROR
            if(!bidFile.exists()) {
                code = ErrorCode.IO_ERROR;
            } else {
                //read bid.txt linebyline
                try{
                    FileInputStream fis = new FileInputStream(bidFile);
                    Scanner sc = new Scanner(fis);
                    while(sc.hasNextLine()){
                        String[] bidInfo = sc.nextLine().split("\\|");
                        bidList.add(new Bidding(Integer.parseInt(bidInfo[0]),Integer.parseInt(bidInfo[1])));
                    }
                    sc.close();
                } catch(IOException e){
                    code = ErrorCode.IO_ERROR;
                }
            }
        }
        return new Pair<>(code,bidList);
    }

    public boolean confirmBids() {
        File users = new File("data/Users");
        for (Course course : courseList) {
            List<String> studentList = new ArrayList<>();
            int quota = course.quota;
            Map<String, Pair<Integer,Integer>> allStudents = new TreeMap<>();
            for (File user : users.listFiles()) {
                Pair<Integer, List<Bidding>> bids = retrieveBids(user.getName());
                if (bids.key == ErrorCode.SUCCESS) {
                    List<Bidding> bidList = bids.value;
                    int total = 0;
                    int bidToThisCourese = -1;
                    for (Bidding bidding : bidList) {
                        total += bidding.mileage;
                    }
                    for (Bidding bidding : bidList) {
                        if (bidding.courseId == course.courseId) {
                            bidToThisCourese = bidding.mileage;
                        }
                    }
                    if(bidToThisCourese != -1) {
                        allStudents.put(user.getName(), new Pair(bidToThisCourese,total));
                    }
                }
            }
            List<Map.Entry<String, Pair<Integer,Integer>>> list = new LinkedList<>(allStudents.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Pair<Integer,Integer>>>() {
                @Override
                public int compare(Map.Entry<String, Pair<Integer,Integer>> o1, Map.Entry<String, Pair<Integer,Integer>> o2) {
                    int comparison = (o1.getValue().key - o2.getValue().key) * -1;
                    if(comparison!=0){
                        return comparison;
                    } else {
                        int comp = o1.getValue().value - o2.getValue().value;
                        return comp==0 ? o1.getKey().compareTo(o2.getKey()) : comp;
                    }
                }
            });

            for (Iterator<Map.Entry<String, Pair<Integer,Integer>>> iter = list.iterator(); iter.hasNext(); ) {
                if (quota > 0) {
                    Map.Entry<String, Pair<Integer,Integer>> entry = iter.next();
                    quota--;
                    try {
                        FileWriter fw = new FileWriter("data/Users/" + entry.getKey() + "/course.txt", true);
                        fw.write(Integer.toString(course.courseId)+"\n");
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        for (File user : users.listFiles()) {
                            File bidFile = new File(user,"bid.txt");
                            if(bidFile.exists()){
                                try{
                                    FileWriter fwuser = new FileWriter(bidFile,false);
                                    fwuser.write("");
                                    fwuser.close();
                                } catch(IOException ee){
                                    ee.printStackTrace();
                                }
                            }
                        }
                        return false;
                    }
                } else {
                    break;
                }
            }
        }
        for (File user : users.listFiles()) {
            File bidFile = new File(user,"bid.txt");
            if(bidFile.exists()){
                try{
                    FileWriter fwuser = new FileWriter(bidFile,false);
                    fwuser.write("");
                    fwuser.close();
                } catch(IOException ee){
                    ee.printStackTrace();
                }
            }
        }
        return true;
    }

    public Pair<Integer,List<Course>> retrieveRegisteredCourse(String userId){
        int code = ErrorCode.SUCCESS;
        List<Course> myCourses = new ArrayList<>();
        File me = new File("data/Users/"+userId);
        if(!me.exists()){
            code = ErrorCode.USERID_NOT_FOUND;
        }else {
            File myCourse = new File(me,"/course.txt");
            if (myCourse.exists()) {
                try {
                    FileInputStream fis = new FileInputStream(myCourse);
                    Scanner sc = new Scanner(fis);
                    while (sc.hasNextLine()) {
                        int id = Integer.parseInt(sc.nextLine());
                        for (Course course : this.courseList) {
                            if (course.courseId == id) {
                                myCourses.add(course);
                                break;
                            }
                        }
                    }
                    sc.close();
                } catch (IOException e) {
                    code = ErrorCode.IO_ERROR;
                }
            } else{

            }
        }
        return new Pair<>(code,myCourses);
    }
}