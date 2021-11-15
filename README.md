# CS180-Project-4
Group 087's Project 4 for CS18000
<br>
Option chosen - Option 2: Quizzes
<br>
<br>
Louis: Files (+help with randomizations in Quizzes)
<br>
Aditya: Accounts
<br>
Nathan R: Menu
<br>
Nathan S: Quizzes
<br>
Jay: Submissions, File handling (CourseList, StudentList, TeacherList), Courses
<br>
<br>
information on the structure of the program and division of roles found in image file which Nathan R will upload
<br>
<br>
Classes
<br>
********
<br>
User (implements Serializable):
<br>
-------------------------------
<br>
String username - Stores username of Student/Teacher
<br>
String password - Stores password of Student/Teacher
<br>
boolean isTeacher - True if User is Teacher, False if User is Student
<br>
public User(String username, String password, boolean isTeacher) - Constructor initializing fields of User
<br>
public String getUsername() - Returns username
<br>
public String getPassword() - Returns password
<br>
public boolean isTeacher() - Returns isTeacher
<br>
public boolean equals(Object o) - Returns true or false comparing Object o to this instance of User
<br>
public static void testEquals() - Method used to test the equals(Object o) method
<br>
<br>
Student (extends User):
<br>
-----------------------
<br>
private ArrayList<Course> courses - Stores the courses the Student chooses to enroll in
<br>
private ArrayList<Submission> submissions - Stores the quiz submissions of the Student
<br>
public Student(String username, String password) - Sends username, password, false to super class
<br>
public ArrayList<Submission> getSubmissions() - Returns submissions
<br>
public void addToCourse(Course c) - Adds Course c to courses
<br>
public ArrayList<Course> getCourses() - Returns courses
<br> 
public Course getCourse(int i) - Returns Course object with courseNumber i; null otherwise
<br>
public void addSubmission(Submission s) - Adds Submission s to submissions
<br>
public String toString() - Returns String representation of Student
<br>
<br>
Teacher (extends User):
<br>
-----------------------
<br>
private ArrayList<Course> courses - Stores courses created by the Teacher
<br>
public Teacher(String username, String password) - Sends username, password, true to super class
<br>
public void addCourse(Course c) - Adds Course c to courses
<br>
public ArrayList<Course> getCourses() - Returns courses
<br>
public Course getCourse(int i) - Returns Course object with courseNumber i; null otherwise
<br>
public String toString() - Returns String representation of Teacher
<br>
<br>
Menu:
<br>
-----------------------
<br>
public static void main() - controls the highest level of menu flow
<br>
public static int getIntegerFromScanner(Scanner scanner, String message, int minValue, int maxValue, 
boolean includeNewline) - returns an int between the minValue and maxValue after prompting the user, reprompts if 
the value is invalid or outside the given range
<br>
public static String getStringFromScanner(Scanner scanner, String message, boolean includeNewline) - 
returns a String that is not blank after prompting the user, reprompts if the String is blank
<br>
private static User login(Scanner scanner, TeacherList teacherList, StudentList studentList) - 
prompts user to log in or create account, and retrieves and returns that account
<br>
private static void teacherMenu(Scanner scanner, Teacher teacher, CourseList courseList) - 
controls the upper menu for the teacher, allowing selection of a course, then directs the teacher to
teacherCourseMenu()
<br>
private static Course createCourseMenu(Scanner scanner, Teacher teacher, CourseList courseList) - 
returns a course after creating it and adding it to all relevant directories
<br>
private static void teacherCourseMenu(Scanner scanner, Teacher teacher, Course course, CourseList courseList) -
controls the lower menu for the teacher after course selection, allowing the teacher to add, edit, delete, or view
quizzes
<br>
private static void studentMenu(Scanner scanner, Student student, CourseList courseList) -
controls the upper menu for the student, allowing selection of a course, then directs the student to
studentCourseMenu()
<br>
private static Course addCourseMenu(Scanner scanner, Student student, CourseList courseList) -
returns a course after adding it to a student's list of courses
<br>
private static void studentCourseMenu(Scanner scanner, Student student, Course course, CourseList courseList) - 
controls the lower menu for the student after course selection, allowing the student to take quizzes or view previous
submissions
<br>
<br>
File Imports:
<br>
-----------------------
<br>
public static String prompt() - 
return the user's input path for quiz files
<br>
public static ArrayList<String> readFile(String filePath) - 
return an ArrayList of the contents of the quiz file for further processing.
<br>
<br>
Course implements Serializable:
<br>
-----------------------
<br>
private String courseName - Stores name of Course
<br>
private Teacher teacher - Stores the teacher who created the course
<br>
private int courseNumber - Stores the unique number of the course
<br>
private ArrayList<Student> students - Stores the students taking the course
<br>
private ArrayList<Quiz> quizzes - Stores the quizzes that are present in the course
<br>
public Course(String courseName, Teacher teacher, int courseNumber) - Constructor that creates a course object
<br>
public String getCourseName() - returns the course name
<br>
public void setCourseName(String courseName) - sets the course name to String passed
<br>
public Teacher getTeacher() - returns the teacher who made the course
<br>
public void setTeacher(Teacher teacher) - sets the teacher who made the course
<br>
public int getCourseNumber - returns the course number 
<br>
public void setCourseNumber(int courseNumber) - sets the course number
<br>
public ArrayList<Student> getStudents() - returns the list of students taking the course
<br>
public void setStudents(ArrayList<Student> students - sets the list of students taking the course
<br>
public ArrayList<Quiz> getQuizzes() - returns the quizzes present in the course
<br>
public void setQuizzes(ArrayList<Quiz> quizzes) - sets the quizzes present in the course
<br>
public boolean addQuiz(Quiz quiz) - adds a quiz to the list of quizzes present in the course
<br>
public boolean removeQuiz(Quiz quiz) - removes a quiz from the list of quizzes in the course
<br>
public boolean addStudent(Student student) - adds a student to the course
<br>
public boolean removeStudent(Student student) - removes a student from the course
<br>
public boolean equals(Object o) - checks if the object passed is the same as the course object being referenced at that point
<br>
<br>
CourseList implements Serializable: 
<br>
-----------------------
<br>
public static final String FILENAME - stores the binary file that has the CourseList object
<br>
private ArrayList<Course> courses - stores the list of courses that have been created by teachers
<br>
public static CourseList readFromFile() - reads from the binary file to return the CourseList object
<br>
public ArrayList<Course> getCourses() - returns the list of courses created by teachers up till that point
<br>
public Course getCourse(int i) - returns the course in the course list at the index passed
<br>
public boolean exists(Course course) - checks whether the specific course passed is contained in the course list
<br>
public boolean add(Course course) - Adds the specific course passed to the course list, and returns whether it was added or already existed
<br> 
public boolean update(Course course) - stores an updated course object in the place of the same  course
<br>
public void saveToFile() - saves the CourseList object to the file
<br>
<br>
StudentList implements Serializable
<br>
-----------------------
<br>
public static final String FILENAME - stores the binary file that has the StudentList object
<br>
private ArrayList<Student> students - stores the list of students that have created an account
<br>
public static StudentList readFromFile() - reads from the binary file to return the StudentList object
<br>
public boolean exists(Course course) - checks whether the specific student passed is contained in the student list
<br>
public boolean add(Student student) - adds a student to the student list, checks whether student has been added or already existed
<br>
public boolean removes(Student student) - removes a student to the student list, checks whether student has been removed or never existed
<br>
public void saveToFile() - saves the StudentList object to the file
<br>
public Student findStudent(String username, String password) - returns a student from the student list whose username and password is given
<br>
<br>

