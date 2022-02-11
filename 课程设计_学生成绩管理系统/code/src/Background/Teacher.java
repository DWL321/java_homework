package Background;

import java.util.List;

public class Teacher extends User{
	
	public Teacher(String s,List<Course> allcourses) {
		super(s,allcourses);
	}
	
	public String ListAllCourse() {
		//s样例："1,Math;2,English"
		String[] tmp = new String[getCourses().size()];
		for(int i=0;i<getCourses().size();i++) {
			tmp[i] = getCourses().get(i).getCourseId()+","+getCourses().get(i).getName();
		}
		String n = String.join(";", tmp);
		return n;
	}
	
	public String QueryCoursesById(int tid,List<Student> students) {
		//s样例："3;English;19308024=cuizx,19308025=zhangsan,19445556=lisi"
		String s="";//对-2返回要做处理,对-1的成绩要做处理
		int x = -2;//未找到这门课（学校没有这门课或者自己没有这门课），返回-2；
		for(int i=0;i<getCourses().size();i++) {
			if(getCourses().get(i).getCourseId()==tid) {
				x=0;
				s = tid + ";" +getCourses().get(i).getName() + ";";
				int n = getCourses().get(i).getStudentsId().size();
				String[] tmp = new String[n];
				for(int j=0;j<n;j++) {
					for(int k=0;k<students.size();k++) {
						if(students.get(k).getId()==getCourses().get(i).getStudentsId().get(j)) {
							tmp[j] = getCourses().get(i).getStudentsId().get(j) + "=" + students.get(k).getName();
						}
					}
				}
				s += String.join(",", tmp);
			}
		}
		if(x==-2) {
			return "-2";
		}
		return s;
	}
	
	public String QueryGradesById(int tid) {
		//s样例："3;English;19308024=85,19308025=99,19445556=89"
		String s="";//对-2返回要做处理,对-1的成绩要做处理
		int x = -2;//未找到这门课（学校没有这门课或者自己没有这门课），返回-2；
		for(int i=0;i<getCourses().size();i++) {
			if(getCourses().get(i).getCourseId()==tid) {
				x=0;
				s = tid + ";" +getCourses().get(i).getName() + ";";
				int n = getCourses().get(i).getStudentsId().size();
				String[] tmp = new String[n];
				for(int j=0;j<n;j++) {
					tmp[j] = getCourses().get(i).getStudentsId().get(j) + "=" + getCourses().get(i).getGrades().get(j);
				}
				s += String.join(",", tmp);
			}
		}
		if(x==-2) {
			return "-2";
		}
		return s;
	}
	
	public int AddCourse(String s,int cid,List<Student> students) {//要在server类中将那个引用链表传过来，cid也由server决定。
		//s的样例："Math;10204456;19308024,19208022,19892344"
		int flag=0;
		Course cs = new Course(s,cid,1);//课程端
		Server.all_courses.add(cs);
		this.getCourses().add(cs);//老师端
		String[] tmp = s.split(";");//学生端	
		String[] stmp = tmp[2].split(",");
		for(int i=0;i<stmp.length;i++) {
			for(int j=0;j<students.size();j++) {
				if(students.get(j).getId()==Integer.parseInt(stmp[i])) {
					flag=1;
					students.get(j).getCourses().add(cs);
					break;
				}
			}
		}
		return flag;//处理返回0的情况，说明添加了不存在的学生。
	}
	
	public int RemoveCourse(int cid,List<Student> students) {
		int flag=0;
		for(int i=0;i<getCourses().size();i++) {//老师端
			if(cid==getCourses().get(i).getCourseId()) {
				flag=1;
				for(int j=0;j<getCourses().get(i).getStudentsId().size();j++) {//学生端
					for(int k=0;k<students.size();k++) {
						if(students.get(k).getId()==getCourses().get(i).getStudentsId().get(j)) {
							students.get(k).getCourses().remove(this.getCourses().get(i));
							break;
						}
					}
				}
				this.getCourses().remove(i);
			}
		}
		
		return flag;//处理0，说明删除了不存在的课程
	}
	
	public void ModifyCourseMessage(int cid,String s) {
		for(int i=0;i<getCourses().size();i++) {
			if(cid==getCourses().get(i).getCourseId()) {
				for(int j=0;j<getCourses().get(i).getStudentsId().size();j++) {//学生端
					for(int k=0;k<Server.all_students.size();k++) {
						if(Server.all_students.get(k).getId()==getCourses().get(i).getStudentsId().get(j)) {
							Server.all_students.get(k).getCourses().remove(this.getCourses().get(i));
							break;
						}
					}
				}
				getCourses().get(i).ModifyCourseMessage(s);
				for(int j=0;j<getCourses().get(i).getStudentsId().size();j++) {//学生端
					for(int k=0;k<Server.all_students.size();k++) {
						if(Server.all_students.get(k).getId()==getCourses().get(i).getStudentsId().get(j)) {
							Server.all_students.get(k).getCourses().add(this.getCourses().get(i));
							break;
						}
					}
				}
			}
		}
	}
	public void ModifyGrade(int cid,String s) {
		for(int i=0;i<getCourses().size();i++) {
			if(cid==getCourses().get(i).getCourseId()) {
				getCourses().get(i).ModifyGrade(s);
			}
		}
	}
}
