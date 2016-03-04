package kiea.proj.sdc.anal.base.mura.detector.v03;

import java.util.Date;
import java.util.Random;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;

public class SampleParam implements ParamImpl
{
	private String[][][] arrParams = {
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
	
	public ParamInfo get()
	{
		ParamInfo info = new ParamInfo();
		
		if (Flag.TRUE) {
			Random random = new Random(new Date().getTime());
			int idx = random.nextInt(arrParams.length);
			
			info.setJobId(String.format("AR010%6sA30%02d", DateTime.getDate(6), idx));
			
			for (String[] param : arrParams[idx]) {
				info.addParam(param);
			}
		}
		
		return info;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			Random random = new Random(new Date().getTime());
			for (int i=0; i < 10; i++) {
				System.out.println(">" + random.nextInt(100));
			}
		}
		
		if (Flag.TRUE) {
			System.out.println(String.format("AR010%6sA30%02d", DateTime.getDate(6), 3));
		}
		
		if (Flag.TRUE) {
			ParamInfo info = new SampleParam().get();
			System.out.println(info.getStrVectorParams());
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
