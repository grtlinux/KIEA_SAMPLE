package test.logon;
import java.sql.Timestamp;

public class LogonDataBean{

	private String id;
	private String passwd;
	private String name;
	private String studentnumber;
	private String cellphone1;
	private String cellphone2;
	private String cellphone3;
	private String email;	
	private Timestamp reg_date;

	public void setId (String id){
		this.id = id;
	}
   public void setPasswd (String passwd){
		this.passwd = passwd;
	}
	public void setName (String name){
		this.name = name;
	}
	public void setStudentnumber (String studentnumber){
		this.studentnumber = studentnumber;
	}
	public void setCellphone1 (String cellphone1){
		this.cellphone1 = cellphone1;
	}
	public void setCellphone2 (String cellphone2){
		this.cellphone2 = cellphone2;
	}
	public void setCellphone3 (String cellphone3){
		this.cellphone3 = cellphone3;
	}	
	public void setEmail (String email){
		this.email = email;
	}
	public void setReg_date (Timestamp reg_date){
		this.reg_date = reg_date;
	}

	public String getId(){
 		return id; 
 	}
	public String getPasswd(){
 		return passwd; 
 	}
	public String getName(){
 		return name; 
 	}
 	public String getStudentnumber(){
 		return studentnumber; 
 	}
	public String getCellphone1(){
 		return cellphone1; 
 	}
 	public String getCellphone2(){
 		return cellphone2; 
 	}
 	public String getCellphone3(){
 		return cellphone3; 
 	}
	public String getEmail(){
 		return email; 
 	}
	public Timestamp getReg_date(){
 		return reg_date; 
 	}
}