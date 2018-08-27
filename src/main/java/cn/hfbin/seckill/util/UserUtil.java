package cn.hfbin.seckill.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hfbin.seckill.entity.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class UserUtil {
	
	private static void createUser(int count) throws Exception{
		List<User> users = new ArrayList<User>(count);
		//生成用户
		for(int i=0;i<count;i++) {
			User user = new User();
			//user.setId((int)10000000000L+i);
			user.setLoginCount(1);
			user.setUserName("user"+i);
			user.setRegisterDate(new Date());
			user.setPhone((18077200000L+i)+"");
			user.setLastLoginDate(new Date());
			user.setSalt("9d5b364d");
			user.setHead("");
			user.setPassword(MD5Util.inputPassToDbPass("123456", user.getSalt()));
			users.add(user);
		}
//		System.out.println("create user");
//		//插入数据库
//		Connection conn = DBUtil.getConn();
//		String sql = "INSERT INTO `seckill`.`user` (`user_name`, `phone`, `password`, `salt`, `head`, `login_count`," +
//				" `register_date`, `last_login_date`)values(?,?,?,?,?,?,?,?)";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		for(int i=0;i<users.size();i++) {
//			User user = users.get(i);
//			//pstmt.setLong(1, user.getId());
//			pstmt.setString(1, user.getUserName());
//			pstmt.setString(2, user.getPhone());
//			pstmt.setString(3, user.getPassword());
//			pstmt.setString(4, user.getSalt());
//			pstmt.setString(5, user.getHead());
//			pstmt.setInt(6, user.getLoginCount());
//			pstmt.setTimestamp(7, new Timestamp(user.getRegisterDate().getTime()));
//			pstmt.setTimestamp(8, new Timestamp(user.getRegisterDate().getTime()));
//			pstmt.addBatch();
//		}
//		pstmt.executeBatch();
//		pstmt.close();
//		conn.close();
//		System.out.println("insert to db");
		//登录，生成token
		String urlString = "http://localhost:8080/page/login";
		File file = new File("D:/tokens.txt");
		if(file.exists()) {
			file.delete();
		}
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		file.createNewFile();
		raf.seek(0);
		for(int i=0;i<users.size();i++) {
			User user = users.get(i);
			URL url = new URL(urlString);
			HttpURLConnection co = (HttpURLConnection)url.openConnection();
			co.setRequestMethod("POST");
			co.setDoOutput(true);
			OutputStream out = co.getOutputStream();
			String params = "mobile="+user.getPhone()+"&password="+MD5Util.inputPassToFormPass("123456");
			out.write(params.getBytes());
			out.flush();
			InputStream inputStream = co.getInputStream();
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte buff[] = new byte[1024];
			int len = 0;
			while((len = inputStream.read(buff)) >= 0) {
				bout.write(buff, 0 ,len);
			}
			inputStream.close();
			bout.close();
			String response = new String(bout.toByteArray());
			JSONObject jo = JSON.parseObject(response);
			String token = jo.getString("data");
			System.out.println("create token : " + user.getId());

			String row = user.getId()+","+token;
			raf.seek(raf.length());
			raf.write(row.getBytes());
			raf.write("\r\n".getBytes());
			System.out.println("write to file : " + user.getId());
		}
		raf.close();
		
		System.out.println("over");
	}
	
	public static void main(String[] args)throws Exception {
		createUser(5000);
	}
}
