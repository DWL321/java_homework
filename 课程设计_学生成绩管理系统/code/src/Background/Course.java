package Background;

import java.util.*;

public class Course {
	private int classId;//初始化的时候由server分配，1开始，不可修改。
	private String name;
	private int teacherId;
	private List<Integer> studentsId = new ArrayList<Integer>();
	private List<Integer> grades = new ArrayList<Integer>();//和学生顺序一一对应，初始化为-1

	public Course(String s,int cid,int mode) {
		if(mode==1) {//加课，不带成绩的操作,cid有server类分配
			//s的样例："Math;10204456;19308024,19208022,19892344"
			String[] tmp = s.split(";");	
			classId = cid;
			name = tmp[0];
			teacherId = Integer.parseInt(tmp[1]);
			String[] stmp = tmp[2].split(",");
			for(int i=0;i<stmp.length;i++) {
				studentsId.add(Integer.parseInt(stmp[i]));
				grades.add(-1);
			}
		}
		else if(mode==0) {//从文件系统中初始化，带成绩的操作
			//s的样例："4;Math;10204456;19308024=99,19208022=46,19892344=87"
			String[] tmp = s.split(";");
			classId = Integer.parseInt(tmp[0]);
			name = tmp[1];
			teacherId = Integer.parseInt(tmp[2]);
			String[] stmp = tmp[3].split(",");
			for(int i=0;i<stmp.length;i++) {
				String[] sstmp = stmp[i].split("=");
				studentsId.add(Integer.parseInt(sstmp[0]));
				grades.add(Integer.parseInt(sstmp[1]));
			}
		}
	}
	
	public void ModifyCourseMessage(String s) {//新的string，不带成绩的操作
		//s的样例："Math;10204456;19308024,19208022,19892344"
		String[] tmp = s.split(";");	
		name = tmp[0];
		teacherId = Integer.parseInt(tmp[1]);
		String[] stmp = tmp[2].split(",");
		studentsId.clear();
		grades.clear();
		for(int i=0;i<stmp.length;i++) {
			studentsId.add(Integer.parseInt(stmp[i]));
			grades.add(-1);
		}
	}
	
	public void ModifyGrade(String s) {//修改这门课成绩的操作
		//s的样例："19308024,96;19578223,87;19807878,45"
		String[] tmp = s.split(";");
		for(int i=0;i<tmp.length;i++) {
			String[] stmp = tmp[i].split(",");
			int tid = Integer.parseInt(stmp[0]);
			int tgrade = Integer.parseInt(stmp[1]);
			for(int j=0;j<studentsId.size();j++) {
				if(studentsId.get(j)==tid) {
					grades.set(j, tgrade);
				}
			}
		}
		
	}
	public int getCourseId() {//只读
		return this.classId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public List<Integer> getStudentsId() {
		return studentsId;
	}

	public void setStudentsId(List<Integer> studentsId) {
		this.studentsId = studentsId;
	}

	public List<Integer> getGrades() {
		return grades;
	}

	public void setGrades(List<Integer> grades) {
		this.grades = grades;
	}
}
