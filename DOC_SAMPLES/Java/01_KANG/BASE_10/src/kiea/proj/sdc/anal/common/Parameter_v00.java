package kiea.proj.sdc.anal.common;

import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;
import oracle.jdbc.OracleConnection;
import au.com.bytecode.opencsv.CSVReader;

public class Parameter_v00
{
	private static Parameter_v00 instance = null;
	
	private List<String[]> listParams = new ArrayList<String[]>();
	
	private String strJobId           = null;
	
	private String strAnalMethod      = null;
	private String strAreaCode        = null;
	private String strSubAreaCode     = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;

	private String strDecisionCode    = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	private String strProductType     = null;
	
	private String strUsl             = null;

	private String strStepId          = null;
	private String strProcessId       = null;
	private String strProductId       = null;

	/**
	 * constructor
	 */
	private Parameter_v00(int noSw)
	{
		if (Flag.TRUE) {
			switch (noSw) {
				case 1:  // 천안 LC
					this.strFromDateTime    = "20140805090000";
					this.strToDateTime      = "20140806085959";
					this.strLine            = "L6FAB";
					this.strAreaCode        = "LC";
					this.strUserGroupCode   = "L6156H11";
					this.strDefectGroupCode = "POR";
					break;
				case 2:  // 천안 MOD
					this.strFromDateTime    = "20140701110000";
					this.strToDateTime      = "20140702105959";
					this.strLine            = "L6FAB";
					this.strAreaCode        = "MOD";
					this.strUserGroupCode   = "LTL097QL01-A01";
					this.strDefectGroupCode = "32PR";
					break;
				case 3:  // 아산 LC
					this.strFromDateTime    = "20140702110000";
					this.strToDateTime      = "20140703105959";
					this.strLine            = "L8AFAB";
					this.strAreaCode        = "LC";
					this.strUserGroupCode   = "L8480F71";
					this.strDefectGroupCode = "32PR";
					break;
				case 4:  // 아산 MOD
					this.strFromDateTime    = "20140702070000";
					this.strToDateTime      = "20140703065959";
					this.strLine            = "L8AFAB";
					this.strAreaCode        = "MOD";
					this.strUserGroupCode   = "LSC480HN02-G01";
					this.strDefectGroupCode = "32BOR";
					break;
				case 5:   // L5FAB
					this.strAnalMethod      = "FILTER";
					this.strAreaCode        = "MOD";
					this.strSubAreaCode     = "BK1_FT";
					this.strFromDateTime    = "20140824210000";
					this.strToDateTime      = "20140825205959";
					this.strLine            = "L5FAB";
					this.strDecisionCode    = "X";
					this.strUserGroupCode   = "59.8-L5598A1";
					this.strDefectGroupCode = "GPTC";
					this.strProductType     = "PP";
					this.strUsl             = "2.08";
					break;
			    case 6:   // L6FAB
					this.strAnalMethod      = "FILTER";
					this.strAreaCode        = "MOD";
					this.strSubAreaCode     = "RD1_FT";
					this.strFromDateTime    = "20140825050000";
					this.strToDateTime      = "20140826045959";
					this.strLine            = "L6FAB";
					this.strDecisionCode    = "X";
					this.strUserGroupCode   = "9.7-L6097Q3";
					this.strDefectGroupCode = "GPTC";
					this.strProductType     = "PP";
					this.strUsl             = "1.21";
					break;
			    case 7:   // L7AFAB
					this.strAnalMethod      = "FILTER";
					this.strAreaCode        = "MOD";
					this.strSubAreaCode     = "MMT";
					this.strFromDateTime    = "20140825050000";
					this.strToDateTime      = "20140826045959";
					this.strLine            = "L7AFAB";
					this.strDecisionCode    = "RJ";
					this.strUserGroupCode   = "40-FHD-60P-MB4-PVA3-A6P-VNB5";
					this.strDefectGroupCode = "UWSP";
					this.strProductType     = "PP";
					this.strUsl             = "0.8";
					break;
			    case 8:   // L8AFAB
					this.strAnalMethod      = "FILTER";
					this.strAreaCode        = "MOD";
					this.strSubAreaCode     = "MMT";
					this.strFromDateTime    = "20140825050000";
					this.strToDateTime      = "20140826045959";
					this.strLine            = "L8AFAB";
					this.strDecisionCode    = "RJ";
					this.strUserGroupCode   = "46-FHD-MB3-SPR7a-A7PP-NNB-8A";
					this.strDefectGroupCode = "UVT1";
					this.strProductType     = "PP";
					this.strUsl             = "0.85";
					break;
				default:  // Oracle 연결 인자.
					this.strAnalMethod      = "FILTER";
					this.strAreaCode        = "MOD";
					this.strSubAreaCode     = "MMT";
					this.strFromDateTime    = "20140825050000";
					this.strToDateTime      = "20140826045959";
					this.strLine            = "L7AFAB";
					this.strDecisionCode    = "RJ";
					this.strUserGroupCode   = "40-FHD-60P-MB4-PVA3-A6P-VNB5";
					this.strDefectGroupCode = "UWSP";
					this.strProductType     = "PP";
					this.strUsl             = "0.8";
					break;
			}
		}
	}
	
	/**
	 * 
	 * getInstance
	 *
	 * @return
	 */
	public static synchronized Parameter_v00 getInstance(int noSw)
	{
		if (instance == null) {
			instance = new Parameter_v00(noSw);
		}
		
		return instance;
	}
	
	public static Parameter_v00 getInstance()
	{
		return getInstance(-1);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private String getStrFromVector(Vector<String> vector)
	{
		StringBuffer sb = new StringBuffer();
		
		for (int idx=0; idx < vector.size(); idx++) {
			if (idx > 0)
				sb.append(", ");
			
			sb.append("'");
			sb.append(vector.elementAt(idx));
			sb.append("'");
		}
		
		return sb.toString();
	}

	private Parameter_v00(String jobId, boolean flagDB)
	{
		if (Flag.TRUE) {
			
			String lineCode = "";
			String analMethod = "";
			String areaCode = "";
			String subAreaCode = "";
			String usl = "";
			
			Vector<String> vecInspectDt = new Vector<String>();
			Vector<String> vecStepId = new Vector<String>();
			Vector<String> vecProcessId = new Vector<String>();
			Vector<String> vecProductId = new Vector<String>();
			Vector<String> vecDecisionCode = new Vector<String>();
			Vector<String> vecUserGroupCode = new Vector<String>();
			Vector<String> vecDefectGroupCode = new Vector<String>();
			Vector<String> vecProductType = new Vector<String>();
			
			if (flagDB) {
				// Oracle Parameter
				try {
					
					String query = String.format("SELECT * FROM ANAL_PARAM WHERE JOB_ID = %s ORDER BY SEQ", StrUtil.quote(jobId));
					
					OracleConnection conn = Connection.getOracleConnection();

					if (Flag.TRUE) {
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(query);
						for (int i=0; i < 100 && rs.next(); i++) {

							if ("LINE_CODE".equals(rs.getString("PARAM_NM"))) {
								lineCode = rs.getString("PARAM_VAL");
							} else if ("ANAL_METHOD".equals(rs.getString("PARAM_NM"))) {
								analMethod = rs.getString("PARAM_VAL");
							} else if ("AREA_CODE".equals(rs.getString("PARAM_NM"))) {
								areaCode = rs.getString("PARAM_VAL");
							} else if ("SUB_AREA_CODE".equals(rs.getString("PARAM_NM"))) {
								subAreaCode = rs.getString("PARAM_VAL");
							} else if ("USL".equals(rs.getString("PARAM_NM"))) {
								usl = rs.getString("PARAM_VAL");
							} else if ("INSPECT_DT".equals(rs.getString("PARAM_NM"))) {
								vecInspectDt.add(rs.getString("PARAM_VAL"));
							} else if ("STEP_ID".equals(rs.getString("PARAM_NM"))) {
								vecStepId.add(rs.getString("PARAM_VAL"));
							} else if ("PROCESS_ID".equals(rs.getString("PARAM_NM"))) {
								vecProcessId.add(rs.getString("PARAM_VAL"));
							} else if ("PRODUCT_ID".equals(rs.getString("PARAM_NM"))) {
								vecProductId.add(rs.getString("PARAM_VAL"));
							} else if ("DECISION_CODE".equals(rs.getString("PARAM_NM"))) {
								vecDecisionCode.add(rs.getString("PARAM_VAL"));
							} else if ("USER_GROUP_CODE".equals(rs.getString("PARAM_NM"))) {
								vecUserGroupCode.add(rs.getString("PARAM_VAL"));
							} else if ("DEFECT_GROUP_CODE".equals(rs.getString("PARAM_NM"))) {
								vecDefectGroupCode.add(rs.getString("PARAM_VAL"));
							} else if ("PRODUCT_TYPE".equals(rs.getString("PARAM_NM"))) {
								vecProductType.add(rs.getString("PARAM_VAL"));
							}
						}
					}
					
					conn.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			} else {
				// CSV Parameter
				String date = "20" + jobId.substring(5, 11);
				String strFile = BasePath.getInstance().getDataPath() + "/" + date + "/" + jobId + "/A01_JOBID_PARAM.csv";
				Logger.info("PARAM CSV FILE : " + strFile);
				
				try {
					CSVReader reader = new CSVReader(new FileReader(strFile));
					String[] line;
					
					while ((line = reader.readNext()) != null) {
						if ("LINE_CODE".equals(line[2])) {
							lineCode = line[3];
						} else if ("ANAL_METHOD".equals(line[2])) {
							analMethod = line[3];
						} else if ("AREA_CODE".equals(line[2])) {
							areaCode = line[3];
						} else if ("SUB_AREA_CODE".equals(line[2])) {
							subAreaCode = line[3];
						} else if ("USL".equals(line[2])) {
							usl = line[3];
						} else if ("INSPECT_DT".equals(line[2])) {
							vecInspectDt.add(line[3]);
						} else if ("STEP_ID".equals(line[2])) {
							vecStepId.add(line[3]);
						} else if ("PROCESS_ID".equals(line[2])) {
							vecProcessId.add(line[3]);
						} else if ("PRODUCT_ID".equals(line[2])) {
							vecProductId.add(line[3]);
						} else if ("DECISION_CODE".equals(line[2])) {
							vecDecisionCode.add(line[3]);
						} else if ("USER_GROUP_CODE".equals(line[2])) {
							vecUserGroupCode.add(line[3]);
						} else if ("DEFECT_GROUP_CODE".equals(line[2])) {
							vecDefectGroupCode.add(line[3]);
						} else if ("PRODUCT_TYPE".equals(line[2])) {
							vecProductType.add(line[3]);
						}
						
						/*
						 * TODO : 2014.11.05 : Macro에서 넘기는 인자 생성
						 */
						listParams.add(line);
					}
					
					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			this.strJobId = jobId;
			
			this.strLine = lineCode;
			this.strAnalMethod = analMethod;
			this.strAreaCode = areaCode;
			this.strSubAreaCode = subAreaCode;
			this.strUsl = usl;
			
			if (Flag.TRUE) {
				Collections.sort(vecInspectDt);
				
				if (vecInspectDt.size() == 1) {
					this.strFromDateTime = vecInspectDt.elementAt(0);
					this.strToDateTime = vecInspectDt.elementAt(0);
				} else if (vecInspectDt.size() >= 2) {
					this.strFromDateTime = vecInspectDt.elementAt(0);
					this.strToDateTime = vecInspectDt.elementAt(1);
				} else {
					this.strFromDateTime = "";
					this.strToDateTime = "";
				}
			}
			
			this.strStepId = getStrFromVector(vecStepId);
			this.strProcessId = getStrFromVector(vecProcessId);
			this.strProductId = getStrFromVector(vecProductId);
			this.strDecisionCode = getStrFromVector(vecDecisionCode);
			this.strUserGroupCode = getStrFromVector(vecUserGroupCode);
			this.strDefectGroupCode = getStrFromVector(vecDefectGroupCode);
			this.strProductType = getStrFromVector(vecProductType);
			
			if (Flag.TRUE) {
				StringBuffer sb = new StringBuffer();
				sb.append("### PARAMETERS ###\n");
				sb.append(String.format("jobId                   = [%s]\n", jobId                   ));
				sb.append(String.format("this.strLine            = [%s]\n", this.strLine           ));
				sb.append(String.format("this.strAnalMethod      = [%s]\n", this.strAnalMethod     ));
				sb.append(String.format("this.strAreaCode        = [%s]\n", this.strAreaCode       ));
				sb.append(String.format("this.strSubAreaCode     = [%s]\n", this.strSubAreaCode    ));
				sb.append(String.format("this.strUsl             = [%s]\n", this.strUsl            ));
				sb.append(String.format("this.strFromDateTime    = [%s]\n", this.strFromDateTime   ));
				sb.append(String.format("this.strToDateTime      = [%s]\n", this.strToDateTime     ));
				sb.append(String.format("this.strStepId          = [%s]\n", this.strStepId         ));
				sb.append(String.format("this.strProcessId       = [%s]\n", this.strProcessId      ));
				sb.append(String.format("this.strProductId       = [%s]\n", this.strProductId      ));
				sb.append(String.format("this.strDecisionCode    = [%s]\n", this.strDecisionCode   ));
				sb.append(String.format("this.strUserGroupCode   = [%s]\n", this.strUserGroupCode  ));
				sb.append(String.format("this.strDefectGroupCode = [%s]\n", this.strDefectGroupCode));
				sb.append(String.format("this.strProductType     = [%s]\n", this.strProductType    ));
				
				Logger.info(sb.toString());
			}
		}
	}
	
	public static synchronized Parameter_v00 getInstance(String jobId, boolean flagDB)
	{
		if (instance == null) {
			instance = new Parameter_v00(jobId, flagDB);
		}
		
		return instance;
	}
	
	public static Parameter_v00 getInstance(String jobId)
	{
		//return getInstance(jobId, true);
		return getInstance(jobId, false);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * @return the strJobId
	 */
	public String getStrJobId()
	{
		return strJobId;
	}

	/**
	 * @param strJobId the strJobId to set
	 */
	public void setStrJobId(String strJobId)
	{
		this.strJobId = strJobId;
	}

	/**
	 * @return the strAnalMethod
	 */
	public String getStrAnalMethod()
	{
		return strAnalMethod;
	}

	/**
	 * @return the strAreaCode
	 */
	public String getStrAreaCode()
	{
		return strAreaCode;
	}

	/**
	 * @return the strSubAreaCode
	 */
	public String getStrSubAreaCode()
	{
		return strSubAreaCode;
	}

	/**
	 * @return the strFromDateTime
	 */
	public String getStrFromDateTime()
	{
		return strFromDateTime;
	}

	/**
	 * @return the strToDateTime
	 */
	public String getStrToDateTime()
	{
		return strToDateTime;
	}

	/**
	 * @return the strLine
	 */
	public String getStrLine()
	{
		return strLine;
	}

	/**
	 * @return the strDecisionCode
	 */
	public String getStrDecisionCode()
	{
		return strDecisionCode;
	}

	/**
	 * @return the strUserGroupCode
	 */
	public String getStrUserGroupCode()
	{
		return strUserGroupCode;
	}

	/**
	 * @return the strDefectGroupCode
	 */
	public String getStrDefectGroupCode()
	{
		return strDefectGroupCode;
	}

	/**
	 * @return the strProductType
	 */
	public String getStrProductType()
	{
		return strProductType;
	}

	/**
	 * @param strAnalMethod the strAnalMethod to set
	 */
	public void setStrAnalMethod(String strAnalMethod)
	{
		this.strAnalMethod = strAnalMethod;
	}

	/**
	 * @param strAreaCode the strAreaCode to set
	 */
	public void setStrAreaCode(String strAreaCode)
	{
		this.strAreaCode = strAreaCode;
	}

	/**
	 * @param strSubAreaCode the strSubAreaCode to set
	 */
	public void setStrSubAreaCode(String strSubAreaCode)
	{
		this.strSubAreaCode = strSubAreaCode;
	}

	/**
	 * @param strFromDateTime the strFromDateTime to set
	 */
	public void setStrFromDateTime(String strFromDateTime)
	{
		this.strFromDateTime = strFromDateTime;
	}

	/**
	 * @param strToDateTime the strToDateTime to set
	 */
	public void setStrToDateTime(String strToDateTime)
	{
		this.strToDateTime = strToDateTime;
	}

	/**
	 * @param strLine the strLine to set
	 */
	public void setStrLine(String strLine)
	{
		this.strLine = strLine;
	}

	/**
	 * @param strDecisionCode the strDecisionCode to set
	 */
	public void setStrDecisionCode(String strDecisionCode)
	{
		this.strDecisionCode = strDecisionCode;
	}

	/**
	 * @param strUserGroupCode the strUserGroupCode to set
	 */
	public void setStrUserGroupCode(String strUserGroupCode)
	{
		this.strUserGroupCode = strUserGroupCode;
	}

	/**
	 * @param strDefectGroupCode the strDefectGroupCode to set
	 */
	public void setStrDefectGroupCode(String strDefectGroupCode)
	{
		this.strDefectGroupCode = strDefectGroupCode;
	}

	/**
	 * @param strProductType the strProductType to set
	 */
	public void setStrProductType(String strProductType)
	{
		this.strProductType = strProductType;
	}

	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * @return the strUsl
	 */
	public String getStrUsl()
	{
		return strUsl;
	}

	/**
	 * @param strUsl the strUsl to set
	 */
	public void setStrUsl(String strUsl)
	{
		this.strUsl = strUsl;
	}

	/**
	 * @return the strStepId
	 */
	public String getStrStepId()
	{
		return strStepId;
	}

	/**
	 * @return the strProcessId
	 */
	public String getStrProcessId()
	{
		return strProcessId;
	}

	/**
	 * @return the strProductId
	 */
	public String getStrProductId()
	{
		return strProductId;
	}

	/**
	 * @param strStepId the strStepId to set
	 */
	public void setStrStepId(String strStepId)
	{
		this.strStepId = strStepId;
	}

	/**
	 * @param strProcessId the strProcessId to set
	 */
	public void setStrProcessId(String strProcessId)
	{
		this.strProcessId = strProcessId;
	}

	/**
	 * @param strProductId the strProductId to set
	 */
	public void setStrProductId(String strProductId)
	{
		this.strProductId = strProductId;
	}

	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			Parameter_v00.getInstance(5);
			
			Print.println("strAnalMethod      = [%s]", Parameter_v00.getInstance().getStrAnalMethod     ());
			Print.println("strAreaCode        = [%s]", Parameter_v00.getInstance().getStrAreaCode       ());
			Print.println("strSubAreaCode     = [%s]", Parameter_v00.getInstance().getStrSubAreaCode    ());
			Print.println("strFromDateTime    = [%s]", Parameter_v00.getInstance().getStrFromDateTime   ());
			Print.println("strToDateTime      = [%s]", Parameter_v00.getInstance().getStrToDateTime     ());
			Print.println("strLine            = [%s]", Parameter_v00.getInstance().getStrLine           ());
			Print.println("strDecisionCode    = [%s]", Parameter_v00.getInstance().getStrDecisionCode   ());
			Print.println("strUserGroupCode   = [%s]", Parameter_v00.getInstance().getStrUserGroupCode  ());
			Print.println("strDefectGroupCode = [%s]", Parameter_v00.getInstance().getStrDefectGroupCode());
			Print.println("strProductType     = [%s]", Parameter_v00.getInstance().getStrProductType    ());
			Print.println("strUsl             = [%s]", Parameter_v00.getInstance().getStrUsl            ());
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			String strJobId = "";
			
			Parameter_v00.getInstance(strJobId);
			
			Print.println("strAnalMethod      = [%s]", Parameter_v00.getInstance().getStrAnalMethod     ());
			Print.println("strAreaCode        = [%s]", Parameter_v00.getInstance().getStrAreaCode       ());
			Print.println("strSubAreaCode     = [%s]", Parameter_v00.getInstance().getStrSubAreaCode    ());
			Print.println("strFromDateTime    = [%s]", Parameter_v00.getInstance().getStrFromDateTime   ());
			Print.println("strToDateTime      = [%s]", Parameter_v00.getInstance().getStrToDateTime     ());
			Print.println("strLine            = [%s]", Parameter_v00.getInstance().getStrLine           ());
			Print.println("strDecisionCode    = [%s]", Parameter_v00.getInstance().getStrDecisionCode   ());
			Print.println("strUserGroupCode   = [%s]", Parameter_v00.getInstance().getStrUserGroupCode  ());
			Print.println("strDefectGroupCode = [%s]", Parameter_v00.getInstance().getStrDefectGroupCode());
			Print.println("strProductType     = [%s]", Parameter_v00.getInstance().getStrProductType    ());
			Print.println("strUsl             = [%s]", Parameter_v00.getInstance().getStrUsl            ());
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
