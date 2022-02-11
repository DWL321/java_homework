package Background;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
public class Server {
	//创建集合对象，存储每一个连接进来的客户端
	public static List<Channel> list=new ArrayList<Channel>();
	public static List<Course> all_courses=new ArrayList<Course>();
	public static List<Teacher> all_teachers=new ArrayList<Teacher>();
	public static List<Student> all_students=new ArrayList<Student>();
	public static int cid=1;
	
	public static void main(String args[]) throws IOException{
		System.out.println("初始化课程： 读取course.txt开始");
		File file = new File("course.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				Course courseTemp = new Course(tempString,0,0);
				System.out.println("初始化课程： 添加课程, 课程id:"+courseTemp.getClassId()+", 课程名称:"+courseTemp.getName());
				all_courses.add(courseTemp);
			}
			reader.close();
			System.out.println("初始化课程： 读取course.txt完毕");
			System.out.println("初始化课程： 课程数量: "+all_courses.size());
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(Server.all_courses.size()>0)cid=Server.all_courses.get(Server.all_courses.size()-1).getClassId()+1;
		
		System.out.println("初始化教师： 读取teacher.txt开始");
		file=new File("teacher.txt");
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				Teacher teacherTemp = new Teacher(tempString,all_courses);
				all_teachers.add(teacherTemp);
				System.out.println("初始化教师： 添加教师, 教师id:"+teacherTemp.getId()+", 教师名:"+teacherTemp.getName()+", 教师密码:"+teacherTemp.getPassword());
			}
			reader.close();
			System.out.println("初始化教师： 读取teacher.txt完毕");
			System.out.println("初始化教师： 教师数量: "+all_teachers.size());
			System.out.println();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("初始化学生： 读取student.txt开始");
		file=new File("student.txt");
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				Student studentTemp = new Student(tempString,all_courses);
				all_students.add(studentTemp);
				System.out.println("初始化学生： 添加学生, 学生id:"+studentTemp.getId()+", 学生名:"+studentTemp.getName()+", 学生密码:"+studentTemp.getPassword());
			}
			reader.close();
			System.out.println("初始化学生： 读取student.txt完毕");
			System.out.println("初始化学生： 学生数量: "+all_students.size());
			System.out.println();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		//创建ServerSocket对象
		ServerSocket server=new ServerSocket(2333);
		do{
			//监听客户端是否有客户端链接
			Socket socket = server.accept();
			//创建线程类的对象
			Channel channel=new Channel(socket);
			list.add(channel);
			System.out.println("当前连接数： "+ list.size());
			System.out.println();
			new Thread(channel).start();
		}while(list.size()!=0);
		server.close();
		System.out.println("服务器退出");
	}
}
