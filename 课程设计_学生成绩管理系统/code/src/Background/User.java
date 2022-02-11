package Background;

import java.util.*;

public class User {
	private int id;
	private String name;
	private String password;
	private List<Course> courses = new ArrayList<Course>();
	
	public User(String s,List<Course> allcourses) {//个人信息、课程信息
		//s样例："19308024;cuizx;abc11111;1,3,4"
		String[] tmp = s.split(";");	
		id = Integer.parseInt(tmp[0]);
		name = tmp[1];
		password = tmp[2];
		if(tmp[3]!="0") {
			String[] stmp = tmp[3].split(",");
			for(int i=0;i<stmp.length;i++) {
				int ind = Integer.parseInt(stmp[i]);
				for(int j=0;j<allcourses.size();j++) {
					if(allcourses.get(j).getCourseId() == ind) {
						courses.add(allcourses.get(j));
					}
				}
			}
		}
		
	}
	
	public void ModifyPersonalMessage(String s) {//修改个人信息，学号不可修改。(学生没有权限修改课程信息，但老师有)
		//s样例："cuizx23333;abd22222"
		String[] tmp = s.split(";");	
		name = tmp[0];
		password = tmp[1];
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
