 package ch08.register;

 public class RegisterBean{
 	
 	private String id;
 	private String passwd;
 	private String name;
 	private String email;
 	private String tel;
 
 	
 	public void setId(String id) {
 		this.id = id; 
 	}
 
 	public void setPasswd(String passwd) {
 		this.passwd = passwd; 
 	}
 
 	public void setName(String name) {
 		this.name = name; 
 	}
 
 	public void setEmail(String email) {
 		this.email = email; 
 	}
 
 	public void setTel(String tel) {
 		this.tel = tel; 
 	}
 	
 	public String getId() {
 		return id; 
 	}
 
 	public String getPasswd() {
 		return passwd; 
 	}
 
 	public String getName() {
 		return name; 
 	}
  
 	public String getEmail() {
 		return email; 
 	}
 
 	public String getTel() {
 		return tel; 
 	}
 	
 }
