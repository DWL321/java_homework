package Background;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Channel implements Runnable{
	private DataInputStream dis;
	private DataOutputStream dos;
	private Boolean live=true;
	private String address;
	private int port;
	
	public Channel(Socket client) {
		try {
			//获取输入流接收数据
			dis=new DataInputStream(client.getInputStream());
			dos=new DataOutputStream(client.getOutputStream());
			address = client.getInetAddress().getHostAddress();
			port = client.getPort();
			System.out.println("已和客户端"+address+":"+port+"建立连接");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.live=false;
			Server.list.remove(this);
		}
	}
	//接收数据的方法
	private synchronized String receive() {
		try {
			String input=dis.readUTF();
			System.out.println("来自"+address+":"+port+": "+input);
			if(input.equals("quit")) {
				this.quit();
				this.live = false;
				Server.list.remove(this);
				System.out.println(address+":"+port+"客户端登出");
				System.out.println("当前连接数： "+ Server.list.size());
				return "no";
			}
			String op1=input.substring(0,2);
			String op2=input.substring(0,3);
			String output = "no";
			if(op1.contentEquals("1:"))output=checkStudent(input.substring(2));
			else if(op1.contentEquals("2:"))output=checkTeacher(input.substring(2));
			else if(op1.contentEquals("3:"))output=RegistStudent(input.substring(2));
			else if(op1.contentEquals("4:"))output=RegistTeacher(input.substring(2));
			else if(op1.contentEquals("5:"))output=ModifyStudent(input.substring(2));
			else if(op1.contentEquals("6:"))output=QGStudent(input.substring(2));
			else if(op1.contentEquals("7:"))output=QALLGStudent(input.substring(2));
			else if(op1.contentEquals("8:"))output=ModifyTeacher(input.substring(2));
			else if(op1.contentEquals("9:"))output=QALLCTeacher(input.substring(2));
			else if(op2.contentEquals("10:"))output=QCTeacher(input.substring(3));
			else if(op2.contentEquals("11:"))output=ACTeacher(input.substring(3));
			else if(op2.contentEquals("12:"))output=RCTeacher(input.substring(3));
			else if(op2.contentEquals("13:"))output=MCTeacher(input.substring(3));
			else if(op2.contentEquals("14:"))output=QCSTeacher(input.substring(3));
			else if(op2.contentEquals("15:"))output=QGTeacher(input.substring(3));
			else if(op2.contentEquals("16:"))output=MGTeacher(input.substring(3));		
			return output;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.live=false;
			Server.list.remove(this);
		}
		return "no";
	}
	//发送数据的方法
	private synchronized void send(String str) {
		if(str.equals("no"))return ;
		try {
			dos.writeUTF(str);
			System.out.println("发向"+address+":"+port+": "+str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			this.live=false;
			Server.list.remove(this);
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(this.live) {
			this.send(this.receive());
			System.out.println();
		}
	}
	
	// 查询/登录学生账号
	private String checkStudent(String str) {
		String[] tmp = str.split(";");
		int id = Integer.parseInt(tmp[0]);
		String password=tmp[1];
		System.out.println(address+":"+port+": 查询/登录学生账号, 学生id："+id+", 学生密码: "+password);
		int i;
		for(i=0;i<Server.all_students.size();i++) {
			if(Server.all_students.get(i).getId()==id) {
				System.out.println(address+":"+port+": 学生id匹配");
				if(Server.all_students.get(i).getPassword().equals(password)) {
					System.out.println(address+":"+port+": 学生密码正确");
					str=Server.all_students.get(i).getName();
				}
				else {
					System.out.println(address+":"+port+": 学生密码错误");
					str="密码错误\n";
				}
				break;
			}
		}
		if(i==Server.all_students.size()) {
			System.out.println(address+":"+port+": 无学生id匹配");
			str="此用户不存在\n";
		}
		return str;
	}
	
	//查询/登录教师账号
	private String checkTeacher(String str) {
		String[] tmp = str.split(";");
		int id = Integer.parseInt(tmp[0]);
		String password=tmp[1];
		System.out.println(address+":"+port+": 查询/登录教师账号, 教师id："+id+", 教师密码: "+password);
		int i;
		for(i=0;i<Server.all_teachers.size();i++) {
			if(Server.all_teachers.get(i).getId()==id) {
				System.out.println(address+":"+port+": 教师id匹配");
				if(Server.all_teachers.get(i).getPassword().equals(password)) {
					System.out.println(address+":"+port+": 教师密码正确");
					str=Server.all_teachers.get(i).getName();
				}
				else {
					System.out.println(address+":"+port+": 教师密码错误");
					str="密码错误\n";
				}
				break;
			}
		}
		if(i==Server.all_teachers.size()) {
			System.out.println(address+":"+port+": 无教师id匹配");
			str="此用户不存在\n";
		}
		return str;
	}
	
	//注册学生账号
	private String RegistStudent(String str) {
		String[] tmp = str.split(";");
		int id = Integer.parseInt(tmp[0]);
		String name=tmp[1];
		String password=tmp[2];
		System.out.println(address+":"+port+": 注册学生账号, 学生id："+id+", 学生密码: "+password);
		int i;
		for(i=0;i<Server.all_students.size();i++) {
			if(Server.all_students.get(i).getId()==id) {
				System.out.println(address+":"+port+": 学生id已存在，注册失败");
				str="此用户已存在\n";
				break;
			}
		}
		if(i==Server.all_students.size()) {
			Server.all_students.add(new Student(id+";"+name+";"+password+";0",Server.all_courses));
			System.out.println(address+":"+port+": 学生账号注册成功");
			str="ok";
		}
		return str;
	}
	
	//注册教师账号
	private String RegistTeacher(String str) {
		String[] tmp = str.split(";");
		int id = Integer.parseInt(tmp[0]);
		String name=tmp[1];
		String password=tmp[2];
		System.out.println(address+":"+port+": 注册教师账号, 教师id："+id+", 教师密码: "+password);
		int i;
		for(i=0;i<Server.all_teachers.size();i++) {
			if(Server.all_teachers.get(i).getId()==id) {
				System.out.println(address+":"+port+": 教师id已存在，注册失败");
				str="此用户已存在\n";
				break;
			}
		}
		if(i==Server.all_teachers.size()) {
			Server.all_teachers.add(new Teacher(id+";"+name+";"+password+";0",Server.all_courses));
			System.out.println(address+":"+port+": 教师账号注册成功");
			str="ok";
		}
		return str;
	}
	
	//修改学生账号信息
	private String ModifyStudent(String str) {
		String[] tmp = str.split(";");
		int id = Integer.parseInt(tmp[0]);
		String name=tmp[1];
		String password=tmp[2];
		System.out.println(address+":"+port+": 修改学生账号信息, 学生id："+id+", 学生密码: "+password);
		int i;
		for(i=0;i<Server.all_students.size();i++) {
			if(Server.all_students.get(i).getId()==id) {
				Server.all_students.get(i).ModifyPersonalMessage(name+";"+password);
				break;
			}
		}
		str="no";
		return str;
	}
	
	//根据课程id查询该学生账号课程成绩
	private String QGStudent(String str) {
		String[] tmp = str.split(";");
		int Sid = Integer.parseInt(tmp[0]);
		int Cid = Integer.parseInt(tmp[1]);
		int i;
		str="-2";
		for(i=0;i<Server.all_students.size();i++) {
			if(Server.all_students.get(i).getId()==Sid) {
				str=Server.all_students.get(i).QueryGradesById(Cid);
				System.out.println(address+":"+port+": 根据课程id查询该学生账号课程成绩, 学生id："+Sid+", 课程: "+Cid);
				break;
			}
		}
		return str;
	}
	
	//列出该学生账号所有课程成绩
	private String QALLGStudent(String str) {
		int id = Integer.parseInt(str);
		int i;
		str="-2";
		for(i=0;i<Server.all_students.size();i++) {
			if(Server.all_students.get(i).getId()==id) {
				str=Server.all_students.get(i).ListAllGrades();
				System.out.println(address+":"+port+": 列出该学生账号所有课程成绩, 学生id："+id);
				break;
			}
		}
		return str;
	}
	
	//修改教师账号信息
	private String ModifyTeacher(String str) {
		String[] tmp = str.split(";");
		int id = Integer.parseInt(tmp[0]);
		String name=tmp[1];
		String password=tmp[2];
		System.out.println(address+":"+port+": 修改教师账号信息, 教师id："+id+", 教师密码: "+password);
		int i;
		for(i=0;i<Server.all_teachers.size();i++) {
			if(Server.all_teachers.get(i).getId()==id) {
				Server.all_teachers.get(i).ModifyPersonalMessage(name+";"+password);
				break;
			}
		}
		str="no";
		return str;
	}
	
	//列出该教师账号所有课程
	private String QALLCTeacher(String str) {
		int id = Integer.parseInt(str);
		int i;
		str="-2";
		for(i=0;i<Server.all_teachers.size();i++) {
			if(Server.all_teachers.get(i).getId()==id) {
				str=Server.all_teachers.get(i).ListAllCourse();
				System.out.println(address+":"+port+": 列出该教师账号所有课程, 教师id："+id);
				break;
			}
		}
		return str;
	}
	
	//根据课程id查询该教师账号课程信息
	private String QCTeacher(String str) {
		String[] tmp = str.split(";");
		int Sid = Integer.parseInt(tmp[0]);
		int Cid = Integer.parseInt(tmp[1]);
		int i;
		str="-2";
		for(i=0;i<Server.all_teachers.size();i++) {
			if(Server.all_teachers.get(i).getId()==Sid) {
				str=Server.all_teachers.get(i).QueryCoursesById(Cid, Server.all_students);
				System.out.println(address+":"+port+": 根据课程id查询该教师账号课程信息, 教师id："+Sid+", 课程id: "+Cid);
				break;
			}
		}
		return str;
	}
	
	//教师增加新课程
	private String ACTeacher(String str) {
		String[] tmp = str.split(";");	
		String name = tmp[1];
		str="ok";
		int id = Integer.parseInt(tmp[0]);
		System.out.println(address+":"+port+": 教师增加新课程, 课程名："+name+", 教师id: "+id+", 课程id: " + Server.cid);
		int judge=0;
		int index = 0;
		if(tmp[2]!="0") {
			String[] stmp = tmp[2].split(",");
			for(int i=0;i<stmp.length;i++) {
				judge=0;
				int sid = Integer.parseInt(stmp[i]);
				for(int j=0;j<Server.all_students.size();j++) {
					if(Server.all_students.get(j).getId()==sid) {
						judge=1;
						break;
					}
				}
				if(judge==0)break;
			}
		}
		if(judge==0) {
			System.out.println(address+":"+port+": 学生录入错误，增加课程失败");
			str="学生录入错误\n";
			return str;
		}
		else {
			judge=1;
			for(int i=0;i<Server.all_teachers.size();i++) {
				if(Server.all_teachers.get(i).getId()==id) {
					index=i;
					for(int j=0;j<Server.all_teachers.get(i).getCourses().size();j++) {
						if(Server.all_teachers.get(i).getCourses().get(j).getName().equals(name)) {
							judge=0;
							break;
						}
					}
					break;
				}
			}
		}
		if(judge==0) {
			System.out.println(address+":"+port+": 课程已存在，增加课程失败");
			str="课程已存在\n";
		}
		else {
			Server.all_teachers.get(index).AddCourse(name+";"+id+";"+tmp[2], Server.cid++, Server.all_students);
			System.out.println(address+":"+port+": 增加课程成功");
		}
		return str;
	}

	//教师通过课程id删除课程
	private String RCTeacher(String str) {
		String[] tmp = str.split(";");	
		int cid = Integer.parseInt(tmp[1]);
		str="ok";
		int id = Integer.parseInt(tmp[0]);
		System.out.println(address+":"+port+": 教师通过课程id删除课程, 教师id: "+id+", 课程id: " + cid);
		int judge=0;
		int index = 0;
		for(int i=0;i<Server.all_teachers.size();i++) {
			if(Server.all_teachers.get(i).getId()==id) {
				index=i;
				for(int j=0;j<Server.all_teachers.get(i).getCourses().size();j++) {
					if(Server.all_teachers.get(i).getCourses().get(j).getClassId()==cid) {
						judge=1;
						break;
					}
				}
				break;
			}
		}
		if(judge==0) {
			str="课程不存在\n";
			System.out.println(address+":"+port+": 课程不存在，删除课程失败");
		}
		else {
			Server.all_teachers.get(index).RemoveCourse(cid, Server.all_students);
			for(int i=0;i<Server.all_courses.size();i++) {
				if(Server.all_courses.get(i).getClassId()==cid) {
					Server.all_courses.remove(i);
					System.out.println(address+":"+port+": 删除课程成功");
					break;
				}
			}
		}
		return str;
	}
	
	//教师通过课程id修改课程信息
	private String MCTeacher(String str) {
		String[] tmp = str.split(";");	
		int cid = Integer.parseInt(tmp[1]);
		str="ok";
		int id = Integer.parseInt(tmp[0]);
		String name=tmp[2];
		System.out.println(address+":"+port+": 教师通过课程id修改课程信息, 教师id: "+id+", 课程id: " + cid + ", 课程名: " + name);
		int judge=0;
		int index = 0;
		if(tmp[2]!="0") {
			String[] stmp = tmp[3].split(",");
			for(int i=0;i<stmp.length;i++) {
				judge=0;
				int sid = Integer.parseInt(stmp[i]);
				for(int j=0;j<Server.all_students.size();j++) {
					if(Server.all_students.get(j).getId()==sid) {
						judge=1;
						break;
					}
				}
				if(judge==0)break;
			}
		}
		if(judge==0) {
			str="学生录入错误\n";
			return str;
		}
		else {
			judge=1;
			for(int i=0;i<Server.all_teachers.size();i++) {
				if(Server.all_teachers.get(i).getId()==id) {
					index=i;
					for(int j=0;j<Server.all_teachers.get(i).getCourses().size();j++) {
						if(Server.all_teachers.get(i).getCourses().get(j).getName().equals(name)&&Server.all_teachers.get(i).getCourses().get(j).getClassId()!=cid) {
							judge=0;
							break;
						}
					}
					break;
				}
			}
		}
		if(judge==0)str="课程已存在\n";
		else Server.all_teachers.get(index).ModifyCourseMessage(cid, name+";"+id+";"+tmp[3]);
		return str;
	}
	
	//教师通过课程id查询课程信息
	private String QCSTeacher(String str) {
		String[] tmp = str.split(";");	
		int cid = Integer.parseInt(tmp[1]);
		int id = Integer.parseInt(tmp[0]);
		System.out.println(address+":"+port+": 教师通过课程id查询课程信息, 教师id: "+id+", 课程id: " + cid);
		str="-2";
		for(int i=0;i<Server.all_teachers.size();i++) {
			if(Server.all_teachers.get(i).getId()==id) {
				int j;
				for(j=0;j<Server.all_teachers.get(i).getCourses().size();j++) {
					if(Server.all_teachers.get(i).getCourses().get(j).getClassId()==cid) {
						str=cid+";"+Server.all_teachers.get(i).getCourses().get(j).getName()+";";
						for(int k=0;k<Server.all_teachers.get(i).getCourses().get(j).getStudentsId().size();k++) {
							if(k>0)str+=",";
							str+=Server.all_teachers.get(i).getCourses().get(j).getStudentsId().get(k);
						}
						break;
					}
				}
				break;
			}
		}
		return str;
	}
	
	//教师通过课程id查询课程成绩
	private String QGTeacher(String str) {
		String[] tmp = str.split(";");	
		int id = Integer.parseInt(tmp[0]);
		int cid = Integer.parseInt(tmp[1]);
		System.out.println(address+":"+port+": 教师通过课程id查询课程成绩, 教师id: "+id+", 课程id: " + cid);
		for(int i=0;i<Server.all_teachers.size();i++) {
			if(Server.all_teachers.get(i).getId()==id) {
				str=Server.all_teachers.get(i).QueryGradesById(cid);
				break;
			}
		}
		return str;
	}
	
	//教师通过课程id登记/修改课程成绩
	private String MGTeacher(String str) {
		String[] tmp = str.split(":");	
		int id = Integer.parseInt(tmp[0]);
		int cid = Integer.parseInt(tmp[1]);
		System.out.println(address+":"+port+": 教师通过课程id登记/修改课程成绩, 教师id: "+id+", 课程id: " + cid);
		for(int i=0;i<Server.all_teachers.size();i++) {
			if(Server.all_teachers.get(i).getId()==id) {
				Server.all_teachers.get(i).ModifyGrade(cid,tmp[2]);
				break;
			}
		}
		str="ok";
		return str;
	}
	
	//服务器线程退出
	private synchronized String quit() {
        String content = "";
        FileOutputStream fileOutputStream = null;
		File file = new File("course.txt");
		try {
			fileOutputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<Server.all_courses.size();i++){
			content+=Server.all_courses.get(i).getClassId()+";"+Server.all_courses.get(i).getName()+";"+Server.all_courses.get(i).getTeacherId()+";";
			for(int j=0;j<Server.all_courses.get(i).getStudentsId().size();j++) {
				if(j>0)content+=',';
				content+=Server.all_courses.get(i).getStudentsId().get(j)+"="+Server.all_courses.get(i).getGrades().get(j);
			}
			content+='\n';
			try {
				fileOutputStream.write(content.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			content = "";
		}
        try {
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("已将all_courses数据持久化储存到course.txt");
        
        file = new File("teacher.txt");
		try {
			fileOutputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<Server.all_teachers.size();i++){
			content+=Server.all_teachers.get(i).getId()+";"+Server.all_teachers.get(i).getName()+";"+Server.all_teachers.get(i).getPassword()+";";
			for(int j=0;j<Server.all_teachers.get(i).getCourses().size();j++) {
				if(j>0)content+=',';
				content+=Server.all_teachers.get(i).getCourses().get(j).getClassId();
			}
			if(Server.all_teachers.get(i).getCourses().size()==0) {
				content+="0";
			}
			content+='\n';
			try {
				fileOutputStream.write(content.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			content = "";
		}
        try {
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("已将all_teachers数据持久化储存到teacher.txt");
        
        file = new File("student.txt");
        try {
			fileOutputStream = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<Server.all_students.size();i++){
			content+=Server.all_students.get(i).getId()+";"+Server.all_students.get(i).getName()+";"+Server.all_students.get(i).getPassword()+";";
			for(int j=0;j<Server.all_students.get(i).getCourses().size();j++) {
				if(j>0)content+=',';
				content+=Server.all_students.get(i).getCourses().get(j).getClassId();
			}
			if(Server.all_students.get(i).getCourses().size()==0) {
				content+="0";
			}
			content+='\n';
			try {
				fileOutputStream.write(content.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			content = "";
		}
        try {
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("已将all_students数据持久化储存到student.txt");
        return "no";
	}
}
