package cpta;

import cpta.environment.Compiler;
import cpta.environment.Executer;
import cpta.exam.ExamSpec;
import cpta.exam.Problem; // added
import cpta.exam.Student;
import cpta.exam.TestCase;
import cpta.exceptions.CompileErrorException;
import cpta.exceptions.FileSystemRelatedException;
import cpta.exceptions.InvalidFileTypeException;

import java.nio.file.StandardCopyOption;
import java.util.*;
//added
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.io.File;

public class Grader {
    Compiler compiler;
    Executer executer;

    public Grader(Compiler compiler, Executer executer) {
        this.compiler = compiler;
        this.executer = executer;
    }

    public Map<String,Map<String, List<Double>>> gradeSimple(ExamSpec examSpec, String submissionDirPath) {
        //make new Map<String, Map<String,List<Double>>>
        Map<String,Map<String, List<Double>>> grade = new HashMap<String,Map<String, List<Double>>>();

        //for each student,
        for(Student student : examSpec.students) {
            Map<String,List<Double>> myGrade = new HashMap<String,List<Double>>();
            for(Problem problem : examSpec.problems) {
                //for each problem,
                // make new List<Double> named scoreList
                List<Double> scoreList = new LinkedList<Double>();
                //.sugo_file_name = submissionDirPath + student.id + / + problem.id + / + problem.targetFileName
                String sugoFileName = submissionDirPath + student.id + "/" + problem.id + "/" + problem.targetFileName;
                // compiler.compile(.sugo_file_name) to make .yo file in the same directory & catch each error
                try {
                    compiler.compile(sugoFileName);
                } catch (Exception e){
                    e.printStackTrace();
                }
                //for each testcase,
                for(TestCase testcase : problem.testCases) {
                    //.yo_file_name -> change problem.targetFileName from .sugo to .yo
                    String yoFileName = problem.targetFileName.replace("sugo","yo");
                    String yoFilePath = submissionDirPath + student.id + "/" + problem.id + "/" + yoFileName;
                    String inputFilePath = problem.testCasesDirPath + testcase.inputFileName;
                    String outputFilePath = submissionDirPath + student.id + "/" + problem.id + "/" + testcase.outputFileName;
                    //executer.excute(.yo_file_path, testcase_input, filepath_to_save) -> make out_file for each test cases & catch error
                    try{
                        executer.execute(yoFilePath,inputFilePath,outputFilePath);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                    //compare testcase_answer with testcase_output
                    //testcase_answer = problem.testCasesDirPath + testcase.outputFileName
                    String answerFilePath = problem.testCasesDirPath + testcase.outputFileName;
                    Path outputPath = Paths.get(outputFilePath);
                    Path answerPath = Paths.get(answerFilePath);
                    try{
                        String output = Files.readString(outputPath);
                        String answer = Files.readString(answerPath);
                        if(output.equals(answer)){
                            scoreList.add(testcase.score);
                        } else {
                            scoreList.add(0.0);
                        }
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    //using String content = Files.readString(path, StandardCharsets.US_ASCII);
                    //if two String are the same, give score = testcase.score , otherwise 0.0
                    //add score to scoreList
                }
                myGrade.put(problem.id, scoreList);
            }
            grade.put(student.id,myGrade);
        }
        return grade;
    }

    public Map<String,Map<String, List<Double>>> gradeRobust(ExamSpec examSpec, String submissionDirPath) {
        //make new map to return named grade
        Map<String,Map<String, List<Double>>> grade = new HashMap<String,Map<String, List<Double>>>();
        //for each students,
        for(Student student : examSpec.students) {
            //make Map<String,List<Double>> named myGrade
            Map<String,List<Double>> myGrade = new HashMap<String, List<Double>>();
            int noSubmissionFlag = 0;
            //find folder name starts with student.id
            File submissionDir = new File(submissionDirPath);
            File[] studentList = submissionDir.listFiles();
            File thisStudent = null;
            for(File st : studentList){
                if(st.getName().substring(0,10).equals(student.id)){
                    thisStudent = st;
                    break;
                }
            }
            if (thisStudent == null) noSubmissionFlag=1;
            //for each problems,
            for(Problem problem : examSpec.problems) {
                //make List<Double> named scoreList
                List<Double> scoreList = new LinkedList<Double>();
                //if noSubmissionFlag == 1 , add 0s by the number of testcases to scoreList and map problem.id with scoreList and continue to next problem loop
                if(noSubmissionFlag == 1){
                    for(int i = 0; i < problem.testCases.size(); i++){
                        scoreList.add(0.0);
                    }
                    myGrade.put(problem.id,scoreList);
                    continue;
                }
                File thisProblem = new File(thisStudent,problem.id);
                //if no dir named problem.id, add 0s by the number of testcases to scoreList and map problem.id with scoreList and continue to next problem loop
                if(!thisProblem.exists()) {
                    for(int i = 0; i < problem.testCases.size(); i++){
                        scoreList.add(0.0);
                    }
                    myGrade.put(problem.id,scoreList);
                    continue;
                }

                int halfFlag = 0;
                //find if there's a directory in this submission dir
                File[] fileList = thisProblem.listFiles();
                for(File file : fileList){
                      if(file.isDirectory()){
                //          move all the files in this directory to root directory and delete this folder
                          for(File subfile : file.listFiles()){
                              subfile.renameTo(new File(thisProblem, subfile.getName()));
                          }
                      }
                }
                // if wrapperDirPath is not null,
                if(problem.wrappersDirPath != null) {
                    //copy all .sugo files to the submission directory
                    File wrappersDir = new File(problem.wrappersDirPath);
                    String[] fileNames = wrappersDir.list();
                    //for each filename in fileNames
                    for(String filename : fileNames) {
                        Path sourcePath = Paths.get(problem.wrappersDirPath+filename);
                        File target = new File(thisProblem,filename);
                        Path targetPath = target.toPath();
                        try {
                            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }

                //find targeted Sugo file
                File targetFile = new File(thisProblem,problem.targetFileName);
                //if not exists, find for problem.id.yo file
                if(!targetFile.exists()) {
                    String targetYoName = problem.targetFileName.replace("sugo", "yo");
                    File targetYoFile = new File(thisProblem, targetYoName);
                    //if .yo file exists, cut the score into half by halfFlag=1;
                    if (targetYoFile.exists()) {
                        halfFlag = 1;
                    } else {
                        //else, considered as no submission for the problem
                        //add 0s by the number of testcases to scoreList and map problem.id with scoreList and continue to next problem loop
                        for (int i = 0; i < problem.testCases.size(); i++) {
                            scoreList.add(0.0);
                        }
                        myGrade.put(problem.id, scoreList);
                        continue;
                    }
                }

                for(File file : thisProblem.listFiles()){
                    String[] fileNames = file.getName().split("\\.");
                    if(fileNames.length > 1) {
                        if (fileNames[1].equals("yo")) {
                            File sugoFile = new File(thisProblem,fileNames[0] + ".sugo");
                            if (!sugoFile.exists()) {
                                halfFlag = 1;
                            }
                        }
                    }
                }

                //compile all the sugo files into yo files -> existing yo files would be overwritten
                int complieErrorFlag = 0;
                for(File file : thisProblem.listFiles()) {
                    try {
                        compiler.compile(file.getPath());
                    } catch(CompileErrorException e){
                        complieErrorFlag = 1;
                        break;
                    } catch(InvalidFileTypeException e){

                    } catch(FileSystemRelatedException e){

                    }
                }
                //if compileErrorException, add 0s by the number of testcases to scoreList and map problem.id with scoreList and continue to next problem loop
                if(complieErrorFlag == 1){
                    for(int i = 0; i < problem.testCases.size(); i++){
                        scoreList.add(0.0);
                    }
                    myGrade.put(problem.id,scoreList);
                    continue;
                }
                //for each testcases
                for(TestCase testcase : problem.testCases) {
                    int runTimeExceptionFlag = 0;
                    String targetYoName = problem.targetFileName.replace("sugo", "yo");
                    File targetYoFile = new File(thisProblem, targetYoName);
                    String inputFilePath = problem.testCasesDirPath + testcase.inputFileName;
                    File outputFile = new File(thisProblem,testcase.outputFileName);
                    //excute
                    try{
                        executer.execute(targetYoFile.getPath(),inputFilePath,outputFile.getPath());
                    } catch(RuntimeException e){
                        //if RunTimeErrorException, add 0 to scoreList and continue to next testcase loop
                        scoreList.add(0.0);
                        runTimeExceptionFlag = 1;
                    } catch(InvalidFileTypeException e){
                    } catch(FileSystemRelatedException e){
                    } catch(Exception e){
                    }

                    if(runTimeExceptionFlag == 1){
                        continue;
                    }
                    //processing answer and output with problem.judgingTypes
                    String answerFilePath = problem.testCasesDirPath + testcase.outputFileName;
                    Path outputPath = outputFile.toPath();
                    Path answerPath = Paths.get(answerFilePath);
                    try{
                        String output = Files.readString(outputPath);
                        String answer = Files.readString(answerPath);
                        //Trailing_whitespaces
                        if(problem.judgingTypes!= null) {
                            if (problem.judgingTypes.contains(Problem.TRAILING_WHITESPACES)) {
                                int len = output.length();
                                for (; len > 0; len--) {
                                    if (output.charAt(len - 1)!= ' ' && output.charAt(len - 1)!= '\n' && output.charAt(len - 1)!= '\t') break;
                                }
                                output = output.substring(0, len);
                                int ansLen = answer.length();
                                for (; ansLen > 0; ansLen--) {
                                    if (answer.charAt(ansLen - 1)!= ' ' && answer.charAt(ansLen - 1)!= '\n' && answer.charAt(ansLen - 1)!= '\t') break;
                                }
                                answer = answer.substring(0, ansLen);
                            }
                            //ignore_whitespaces : string.trim() or use replaceAll
                            if (problem.judgingTypes.contains(Problem.IGNORE_WHITESPACES)) {
                                answer = answer.replaceAll(" " ,"");
                                answer = answer.replaceAll("\n" ,"");
                                answer = answer.replaceAll("\t" ,"");
                                output = output.replaceAll(" ","");
                                output = output.replaceAll("\n","");
                                output = output.replaceAll("\t","");
                            }
                            //CASE_INSENSITIVE : use toLowerCase()
                            if (problem.judgingTypes.contains(Problem.CASE_INSENSITIVE)) {
                                answer = answer.toLowerCase();
                                output = output.toLowerCase();
                            }
                        }
                        //compare answer and output and give score with halfFlag
                        if(output.equals(answer)){
                            if(halfFlag == 0) {
                                scoreList.add(testcase.score);
                            } else {
                                scoreList.add(testcase.score/2);
                            }
                        } else {
                            scoreList.add(0.0);
                        }
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
                //map problem.id with scoreList into myGrade
                myGrade.put(problem.id,scoreList);
            }
            //map student.id with myGrade into grade
            grade.put(student.id,myGrade);
        }
        return grade;
    }
}

