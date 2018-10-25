import java.util.ArrayList;

public class Course {
	private ArrayList<String> pre;
	private ArrayList<String> sub ;
	private String courseName;
	
	public Course(String courseName,ArrayList<String> pre,ArrayList<String> sub) {
		this.courseName = courseName;
		this.pre = pre;
		this.sub = sub;
	}
	
	public ArrayList<String> getPre(){
		return this.pre;
	}
	

	public ArrayList<String> getSub(){
		return this.sub;
	}
	
	public String getName() {
		return this.courseName;
	}

	
}
