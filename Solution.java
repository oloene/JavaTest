package test1;
import java.util.*;
import java.io.*;
/**
 * 
 * Java test. here I'm doing one iteration around the table for each 
 * student n, meaning I do n^2 iterations, which might not be optimal.
 * 
 * @author Olof Enekvist
 * @version 1, 05 Okt 2018
 * 
 */
public class Solution {
	/**
	 * Main method
	 * <br/>
	 * Reads two inputs from STDIN and creates an array of students.
	 * <br/>
	 * Also print the optimal student ID
	 * 
	 * @param args read console input
	 */
	public static void main(String[] args) {
		ArrayList<Student> students = new ArrayList<Student>(); //list of students
		Scanner inp; //read from STDIN
		Student student; //a student
		int n; //number of students
		int T; //extra time for each student

		//get number of students from STDIN
		inp = new Scanner(System.in);
		System.out.print("Enter number of students:");
		n = inp.nextInt();
		//create n new students
		for(int i = 1; i <= n; i++) {
			System.out.print("Extra time for student " + i + ":");
			T = inp.nextInt();
			student = new Student(i,T); //create a student with id i, extra time T
			students.add(student);
		}
		student = getOptimalStudent(students); //look for optimal student to start from
		System.out.println("OPTIMAL STUDENT ID:"+student.ID);
	}
	
	
	/**
	 * Iterate over students ArrayList and return the student
	 * where maximum number of students finishes
	 * 
	 * @param students ArrayList of students
	 * @return opStudent optimal student to start from
	 */
	static Student getOptimalStudent(ArrayList<Student> students) {
		int lastIteration=0; //number of students finished last iteration
		int maxIteration=0; //max over all iterations
		Student opStudent = students.get(0); //initial student
		
		for(int j = 0; j < students.size(); j++) {
			lastIteration = finishedPaintings(students);
			if(lastIteration > maxIteration) {
				maxIteration = lastIteration;
				opStudent = students.get(0);
			}
			//"rotate" around the table
			students.add(students.get(0));
			students.remove(0);	
		}
		System.out.println(maxIteration + " students finished");
		return opStudent;
	}
	
	
	/**
	 * @param students ArrayList of students 
	 * @return number of students finished their paintings in time
	 */
	static int finishedPaintings(ArrayList<Student> students) {
		int meerasTime=0; //teacher named Meera
		int studentsFinished=0;
		for(int i=0; i<students.size();i++) {
			if(students.get(i).EXTRATIME - meerasTime > 0) {
				meerasTime++;
			}
			else {
				studentsFinished++;
				meerasTime++;
			}
		}
		return studentsFinished;
	}
	
}

/**
 * Student <br/>
 * Simple student class <br/>
 * Constructor takes student ID and Extra time needed to finish
 * their painting
 */
class Student{
	int ID; //student ID
	int EXTRATIME; //extra time needed
	public Student(int ID, int EXTRATIME) {
		this.ID = ID;
		this.EXTRATIME = EXTRATIME;
	}
	
	int getID() {return this.ID;}
	int getExtraTime() {return this.EXTRATIME;}
}

