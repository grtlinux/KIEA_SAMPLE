package kiea.proj.sdc.anal.base.lcmg.detector.v03;

import java.util.Vector;

import kiea.kr.co.tain.base.Flag;

public class ParamInfo
{
	private String jobId;
	
	public void setJobId(String jobId)
	{
		this.jobId = jobId;
	}
	
	public String getJobId()
	{
		return this.jobId;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private Vector<String[]> vectorParams = new Vector<String[]>();
	
	public void addParam(String[] arrStr)
	{
		vectorParams.add(arrStr);
	}
	
	public String get(String keyParam)
	{
		StringBuffer sb = new StringBuffer();
		
		int cntParams = 0;
		for (String[] param : vectorParams) {
			if (keyParam.equals(param[0])) {
				if (cntParams > 0) {
					sb.append(",");
				}
				sb.append("'").append(param[1]).append("'");
				cntParams ++;
			}
		}
		
		if (cntParams == 0)
			return null;
		else
			return sb.toString();
	}
	
	public Vector<String[]> getVectorParams()
	{
		return this.vectorParams;
	}
	
	///////////////////////////////////////////////////////////////////////////

	public String getStrVectorParams()
	{
		StringBuffer sb = new StringBuffer();
		
		for (String[] param : vectorParams) {
			sb.append(String.format("[%s] => [%s]\n", param[0], param[1]));
		}
		
		return sb.toString();
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private static void test01()
	{
		if (Flag.TRUE) {
			
			ParamInfo info = new ParamInfo();
			
			info.setJobId("AR010140915A0001");
			info.addParam(new String[] {"LINE_CODE"        , "L7AFAB"                      });
			info.addParam(new String[] {"ANAL_METHOD"      , "FILTER"                      });
			info.addParam(new String[] {"AREA_CODE"        , "MOD"                         });
			info.addParam(new String[] {"SUB_AREA_CODE"    , "MMT"                         });
			info.addParam(new String[] {"USL"              , "0.8"                         });
			info.addParam(new String[] {"INSPECT_DT"       , "20140825050000"              });
			info.addParam(new String[] {"INSPECT_DT"       , "20140826045959"              });
			info.addParam(new String[] {"STEP_ID"          , "STEP"                        });
			info.addParam(new String[] {"PROCESS_ID"       , "PROCESS"                     });
			info.addParam(new String[] {"PRODUCT_ID"       , "PRODUCT"                     });
			info.addParam(new String[] {"DECISION_CODE"    , "RJ"                          });
			info.addParam(new String[] {"DECISION_CODE"    , "JR"                          });
			info.addParam(new String[] {"DECISION_CODE"    , "JJ"                          });
			info.addParam(new String[] {"USER_GROUP_CODE"  , "40-FHD-60P-MB4-PVA3-A6P-VNB5"});
			info.addParam(new String[] {"DEFECT_GROUP_CODE", "UWSP"                        });
			info.addParam(new String[] {"PRODUCT_TYPE"     , "PP"                          });
			info.addParam(new String[] {"PRODUCT_TYPE"     , "TT"                          });
			info.addParam(new String[] {"PRODUCT_TYPE"     , "SS"                          });
			
			if (Flag.TRUE) System.out.println(info.getStrVectorParams());
			if (Flag.TRUE) {
				System.out.println("[" + info.get("LINE_CODE"        ) + "]");
				System.out.println("[" + info.get("ANAL_METHOD"      ) + "]");
				System.out.println("[" + info.get("AREA_CODE"        ) + "]");
				System.out.println("[" + info.get("SUB_AREA_CODE"    ) + "]");
				System.out.println("[" + info.get("USL"              ) + "]");
				System.out.println("[" + info.get("INSPECT_DT"       ) + "]");
				System.out.println("[" + info.get("STEP_ID"          ) + "]");
				System.out.println("[" + info.get("PROCESS_ID"       ) + "]");
				System.out.println("[" + info.get("PRODUCT_ID"       ) + "]");
				System.out.println("[" + info.get("DECISION_CODE"    ) + "]");
				System.out.println("[" + info.get("USER_GROUP_CODE"  ) + "]");
				System.out.println("[" + info.get("DEFECT_GROUP_CODE") + "]");
				System.out.println("[" + info.get("PRODUCT_TYPE"     ) + "]");
			}
		}

		if (Flag.TRUE) {
			
			String[][][] arrParam = {
					{
						{"LINE_CODE"        , "L7AFAB"                      },
						{"ANAL_METHOD"      , "FILTER"                      },
						{"AREA_CODE"        , "MOD"                         },
						{"SUB_AREA_CODE"    , "MMT"                         },
						{"USL"              , "0.8"                         },
						{"INSPECT_DT"       , "20140825050000"              },
						{"INSPECT_DT"       , "20140826045959"              },
						{"STEP_ID"          , "STEP"                        },
						{"PROCESS_ID"       , "PROCESS"                     },
						{"PRODUCT_ID"       , "PRODUCT"                     },
						{"DECISION_CODE"    , "RJ"                          },
						{"DECISION_CODE"    , "JR"                          },
						{"DECISION_CODE"    , "JJ"                          },
						{"USER_GROUP_CODE"  , "40-FHD-60P-MB4-PVA3-A6P-VNB5"},
						{"DEFECT_GROUP_CODE", "UWSP"                        },
						{"PRODUCT_TYPE"     , "PP"                          },
						{"PRODUCT_TYPE"     , "TT"                          },
						{"PRODUCT_TYPE"     , "SS"                          },
					},
					{
						{"LINE_CODE"        , "L7AFAB"                      },
						{"ANAL_METHOD"      , "FILTER"                      },
						{"AREA_CODE"        , "MOD"                         },
						{"SUB_AREA_CODE"    , "MMT"                         },
						{"USL"              , "0.8"                         },
						{"INSPECT_DT"       , "20140825050000"              },
						{"INSPECT_DT"       , "20140826045959"              },
						{"DECISION_CODE"    , "RJ"                          },
						{"USER_GROUP_CODE"  , "40-FHD-60P-MB4-PVA3-A6P-VNB5"},
						{"DEFECT_GROUP_CODE", "UWSP"                        },
						{"PRODUCT_TYPE"     , "PP"                          },
					},
			};
			
			ParamInfo info = new ParamInfo();
			
			info.setJobId("AR010140915A0001");
			for (String[] param : arrParam[0]) {
				info.addParam(param);
			}
			
			if (Flag.TRUE) System.out.println(info.getStrVectorParams());
			if (Flag.TRUE) {
				System.out.println("[" + info.get("LINE_CODE"        ) + "]");
				System.out.println("[" + info.get("ANAL_METHOD"      ) + "]");
				System.out.println("[" + info.get("AREA_CODE"        ) + "]");
				System.out.println("[" + info.get("SUB_AREA_CODE"    ) + "]");
				System.out.println("[" + info.get("USL"              ) + "]");
				System.out.println("[" + info.get("INSPECT_DT"       ) + "]");
				System.out.println("[" + info.get("STEP_ID"          ) + "]");
				System.out.println("[" + info.get("PROCESS_ID"       ) + "]");
				System.out.println("[" + info.get("PRODUCT_ID"       ) + "]");
				System.out.println("[" + info.get("DECISION_CODE"    ) + "]");
				System.out.println("[" + info.get("USER_GROUP_CODE"  ) + "]");
				System.out.println("[" + info.get("DEFECT_GROUP_CODE") + "]");
				System.out.println("[" + info.get("PRODUCT_TYPE"     ) + "]");
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
