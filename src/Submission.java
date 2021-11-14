import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;

public class Submission implements Serializable{
    Student student;
    Quiz quizz;
    Timestamp timestamp;
    int totalScore;
    String filename;

    public Submission(Student student, Quiz quizz){
        this.student=student;
        this.quizz=quizz;
        Date date =new Date();
        Timestamp ts = new Timestamp(date.getTime());
        this.timestamp=ts;
    }

    public Student getStudent(){
        return student;
    }

    public String getFilename(){
        return this.filename;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Course getCourse(){
        return this.quizz.getCourse();
    }

    public Quiz getQuiz(){
        return quizz;
    }
    public boolean takeQuiz(Scanner scanner) {
        Object[][] needToBeSubmitted = new Object[quizz.getQuiz().size()][2];
        String studentAnswer;
        for (int i = 0; i < quizz.getQuiz().size(); i++) {
            Question temp = quizz.getQuiz().get(i);
            if (quizz.getQuiz().get(i).getClass() == FillInTheBlank.class) {
                FillInTheBlank temp2 = (FillInTheBlank) temp;
                temp2.displayQuestion();
                System.out.println("1. Do you want to attach a file for the answer?\n2. Do you want to type the answer?");
                int option;
                do {
                    option = scanner.nextInt();
                    scanner.nextLine();
                    if (option > 2 || option < 1) {
                        System.out.println("Invalid input, try again");
                        continue;
                    }
                    break;
                } while (true);
                if (option == 1) {
                    studentAnswer = readingTheAnswerFromFile(scanner);
                    if (studentAnswer == null) {
                        System.out.println("You submitted an invalid file for the answer");
                        return false;
                    }
                } else {
                    System.out.println("Your answer:");
                    studentAnswer = scanner.nextLine();
                }
                Object[] relevantInfo = {temp2, studentAnswer};
                needToBeSubmitted[i] = relevantInfo;
            } else if (quizz.getQuiz().get(i).getClass() == TrueFalse.class) {
                TrueFalse temp2 = (TrueFalse) temp;
                temp2.displayQuestion();
                System.out.println("1. Do you want to attach a file for the answer?\n2. Do you want to type the answer?");
                int option;
                do {
                    option = scanner.nextInt();
                    scanner.nextLine();
                    if (option > 2 || option < 1) {
                        System.out.println("Invalid input, try again");
                        continue;
                    }
                    break;
                } while (true);
                if (option == 1) {
                    studentAnswer = readingTheAnswerFromFile(scanner);
                    if (studentAnswer == null) {
                        System.out.println("You submitted an invalid file for the answer");
                        return false;
                    }
                } else {
                    System.out.println("Your answer:");
                    studentAnswer = scanner.nextLine();
                }
                Object[] relevantInfo = {temp2, studentAnswer};
                needToBeSubmitted[i] = relevantInfo;
            } else if (quizz.getQuiz().get(i).getClass() == MultipleChoice.class) {
                MultipleChoice temp2=(MultipleChoice) temp;
                temp2.displayQuestion();
                System.out.println("1. Do you want to attach a file for the answer?\n2. Do you want to type the answer?");
                int option;
                do {
                    option = scanner.nextInt();
                    scanner.nextLine();
                    if (option > 2 || option < 1) {
                        System.out.println("Invalid input, try again");
                        continue;
                    }
                    break;
                } while (true);
                if (option == 1) {
                    studentAnswer = readingTheAnswerFromFile(scanner);
                    if (studentAnswer == null) {
                        System.out.println("You submitted an invalid file for the answer");
                    }
                } else {
                    System.out.println("Your answer:");
                    studentAnswer = scanner.nextLine();
                }
                Object[] relevantInfo = {temp2, studentAnswer};
                needToBeSubmitted[i] = relevantInfo;
            }
        }
        submissionReport(needToBeSubmitted);
        return true;
    }

    public void submissionReport(Object[][] needToBeSubmitted){

        String filepath = createsNewFile();
        try(PrintWriter pw =new PrintWriter(new FileWriter(filepath))){
            pw.println(student.getUsername()+": "+ quizz.getName());
            for(int i=0;i<needToBeSubmitted.length;i++){
                if(needToBeSubmitted[i][0].getClass()==FillInTheBlank.class){
                    FillInTheBlank temp = (FillInTheBlank) needToBeSubmitted[i][0];
                    pw.print("Question: "+i+". ");
                    pw.println(temp.getQuestion());
                    pw.print("Answer: "+i+". ");
                    pw.println(temp.getAnswer());
                    pw.println("Your answer: "+ ((String)needToBeSubmitted[i][1]));
                    if(temp.getAnswer().equals(needToBeSubmitted[i][1])) {
                        pw.println("Points got: " + temp.getPointValue());
                        totalScore+=temp.getPointValue();
                    } else {
                        pw.println("Points got: "+0);
                    }
                }
                else if(needToBeSubmitted[i][0].getClass()==TrueFalse.class){
                    TrueFalse temp=(TrueFalse) needToBeSubmitted[i][0];
                    pw.print("Question: "+i+". ");
                    pw.println(temp.getQuestion());
                    pw.print("Answer: "+i+". ");
                    pw.println(temp.getAnswer());
                    pw.println("Your answer: "+ ((String)needToBeSubmitted[i][1]));
                    if(temp.getAnswer().equals(needToBeSubmitted[i][1])) {
                        pw.println("Points got: " + temp.getPointValue());
                        totalScore+=temp.getPointValue();
                    } else {
                        pw.println("Points got: "+0);
                    }
                }
                else if(needToBeSubmitted[i][0].getClass()==MultipleChoice.class){
                    MultipleChoice temp=(MultipleChoice) needToBeSubmitted[i][0];
                    pw.print("Question: "+i+". ");
                    pw.println(temp.getQuestion());
                    pw.print("Answer: "+i+". ");
                    pw.println(temp.correctAnswerIndex);
                    pw.println("Your answer: "+ ((String)needToBeSubmitted[i][1]));
                    if(temp.correctAnswerIndex==Integer.parseInt((String)needToBeSubmitted[i][1])){
                        pw.println("Points got: " + temp.getPointValue());
                        totalScore+=temp.getPointValue();
                    } else {
                        pw.println("Points got: "+0);
                    }
                }
            }
        } catch(IOException e){
            e.printStackTrace();
            return;
        }
        student.addSubmission(this);
        quizz.addSubmission(this);
    }

    public String createsNewFile(){
        Random a=new Random();
        String filepath="Submission"+student.getUsername()+a.nextInt(10000);
        this.filename=filepath;
        return filepath;
    }

    public String readingTheAnswerFromFile(Scanner scanner) {
        String studentAnswer = "";
        try {
            System.out.println("Enter the file path:");
            String filepath = scanner.nextLine();
            File f = new File(filepath);
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = bfr.readLine();
            while (line != null) {
                studentAnswer = studentAnswer+line;
                line = bfr.readLine();
            }
        } catch (IOException e) {
            System.out.println("There was an error accessing your file.");
            return null;
        }
        return studentAnswer;
    }

    public void view(Scanner scanner, int submissionNumber){
        ArrayList<Submission> allThePreviousSubmissions= student.getSubmissions();
//        ArrayList<String> namesOfTheSubmissions=new ArrayList<String>();
//        for(int j=0;j< allThePreviousSubmissions.size();j++){
//            namesOfTheSubmissions.add(allThePreviousSubmissions.get(j).getFilename());
//        }
//        do {
//            System.out.println("Which submission do you want to see");
//            String submission=scanner.nextLine();
//            for(int j=0;j< namesOfTheSubmissions.size();j++){
//                if(namesOfTheSubmissions.get(j).equals(submission)){
//                    try(BufferedReader bfr=new BufferedReader(new FileReader(allThePreviousSubmissions.get(j).getFilename()))){
//                        String line=bfr.readLine();
//                        while(line!=null){
//                            System.out.println(line);
//                            line=bfr.readLine();
//                        }
//                        break;
//                    } catch(IOException e){
//                        System.out.println("There was an error reading from the file.");
//                    }
//                }
//                System.out.println("The name you entered for the submission was wrong. Enter again");
//            }
//        } while(true);
        for(int i=0;i< allThePreviousSubmissions.size();i++){
            if(i==submissionNumber){
                try(BufferedReader bfr=new BufferedReader(new FileReader(allThePreviousSubmissions.get(i).getFilename()))){
                        String line=bfr.readLine();
                        while(line!=null){
                            System.out.println(line);
                            line=bfr.readLine();
                        }
                        break;
                    } catch(IOException e){
                        System.out.println("There was an error reading from the file.");
                        throw new RuntimeException();
                    }
            }
        }
    }

//    public static void main(String[] args) {
//        Scanner scanner=new Scanner(System.in);
//        Teacher teacher =new Teacher("j","m");
//        Student student=new Student("jay","mehta");
//        Course a=new Course("cs",teacher,3);
//        Quiz q=new Quiz(scanner,a);
//        Submission s=new Submission(student, q);
//        s.takeQuiz(scanner);
//
//    }

}
