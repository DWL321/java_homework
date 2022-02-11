package Background;

import java.util.List;

public class Student extends User {
	
	public Student(String s,List<Course> allcourses) {
		super(s,allcourses);
	}
	
	public String ListAllGrades() {
		//s样例："1,Math,98;3,English,85;4,History,66",对空返回要做处理,对-1的成绩要做处理
		String s;
		String[] tmp = new String[getCourses().size()];
		for(int i=0;i<getCourses().size();i++) {
			int n = getCourses().get(i).getStudentsId().size();
			for(int j=0;j<n;j++) {
				if(getCourses().get(i).getStudentsId().get(j)==this.getId()) {
					tmp[i] = getCourses().get(i).getCourseId() + "," + getCourses().get(i).getName() +"," +getCourses().get(i).getGrades().get(j);
				}
			}
		}
		s = String.join(";", tmp);
		return s;
	}
	
	public String QueryGradesById(int tid) {
		//返回值样例："3;English;85"或者"5;PE;-1"
		String s="";//对-2返回要做处理,对-1的成绩要做处理
		int x = -2;//未找到这门课（学校没有这门课或者自己没有这门课），返回-2；找到了但未上成绩，成绩计为-1。
		for(int i=0;i<getCourses().size();i++) {
			if(getCourses().get(i).getCourseId()==tid) {
				int n = getCourses().get(i).getStudentsId().size();
				for(int j=0;j<n;j++) {
					if(getCourses().get(i).getStudentsId().get(j)==this.getId()) {
						x = getCourses().get(i).getGrades().get(j);
						s = getCourses().get(i).getCourseId() + ";" + getCourses().get(i).getName() +";" + x;
						break;
					}
				}
			}
		}
		if(x==-2) {
			return "-2";
		}
		return s;
	}
}
