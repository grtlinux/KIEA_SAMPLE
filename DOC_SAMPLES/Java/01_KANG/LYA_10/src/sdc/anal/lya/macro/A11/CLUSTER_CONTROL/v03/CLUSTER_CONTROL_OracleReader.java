/* Copyright (c) 2008-2014, KangSeok
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package sdc.anal.lya.macro.A11.CLUSTER_CONTROL.v03;

import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_CLUSTER_CONTROL_OracleReader.java
 *
 */
public class CLUSTER_CONTROL_OracleReader extends AbstractOracleReader
{
	private List<CLUSTER_CONTROL_ReadBean> list = new ArrayList<CLUSTER_CONTROL_ReadBean>();
	
	private String[][] lines = {
			{ "LCD","L7AFAB","Clustering_by_Default","fClust_DBSCAN_100_a4am","*","*","1","","","","Y","fClust_DBSCAN_100_a4am","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7AFAB","Clustering_by_Default","fClust_LinearityCheck_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7AFAB","Clustering_by_Default","fClust_MinExclusion_100_a4am","*","*","3","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7AFAB","Clustering_by_Default","fClust_CellSignificance_100_a4am","*","*","4","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7AFAB","Clustering_by_Default","fCluster_CellRegroup_100_a4am","*","*","5","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7BFAB","Clustering_by_Default","fClust_DBSCAN_100_a4am","*","*","1","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7BFAB","Clustering_by_Default","fClust_LinearityCheck_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7BFAB","Clustering_by_Default","fClust_MinExclusion_100_a4am","*","*","3","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7BFAB","Clustering_by_Default","fClust_CellSignificance_100_a4am","*","*","4","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7BFAB","Clustering_by_Default","fCluster_CellRegroup_100_a4am","*","*","5","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8AFAB","Clustering_by_Default","fClust_DBSCAN_100_a4am","*","*","1","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8AFAB","Clustering_by_Default","fClust_LinearityCheck_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8AFAB","Clustering_by_Default","fClust_MinExclusion_100_a4am","*","*","3","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8AFAB","Clustering_by_Default","fClust_CellSignificance_100_a4am","*","*","4","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8AFAB","Clustering_by_Default","fCluster_CellRegroup_100_a4am","*","*","5","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8ZFAB","Clustering_by_Default","fClust_DBSCAN_100_a4am","*","*","1","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8ZFAB","Clustering_by_Default","fClust_LinearityCheck_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8ZFAB","Clustering_by_Default","fClust_MinExclusion_100_a4am","*","*","3","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8ZFAB","Clustering_by_Default","fClust_CellSignificance_100_a4am","*","*","4","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8ZFAB","Clustering_by_Default","fCluster_CellRegroup_100_a4am","*","*","5","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7AFAB","Clustering_by_Inspect_Based","fClust_CellSignificance_100_a4am","*","*","1","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7AFAB","Clustering_by_Inspect_Based","fCluster_CellRegroup_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7BFAB","Clustering_by_Inspect_Based","fClust_CellSignificance_100_a4am","*","*","1","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7BFAB","Clustering_by_Inspect_Based","fCluster_CellRegroup_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8AFAB","Clustering_by_Inspect_Based","fClust_CellSignificance_100_a4am","*","*","1","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8AFAB","Clustering_by_Inspect_Based","fCluster_CellRegroup_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8ZFAB","Clustering_by_Inspect_Based","fClust_CellSignificance_100_a4am","*","*","1","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8ZFAB","Clustering_by_Inspect_Based","fCluster_CellRegroup_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7AFAB","CELL_SIG","fClust_DBSCAN_100_a4am","*","*","1","","","","Y","fClust_DBSCAN_100_a4am","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L7BFAB","CELL_SIG","fClust_LinearityCheck_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8AFAB","CELL_SIG","fClust_MinExclusion_100_a4am","*","*","3","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L8ZFAB","CELL_SIG","fClust_CellSignificance_100_a4am","*","*","4","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L5FAB","Clustering_by_Default","fClust_DBSCAN_100_a4am","*","*","1","","","","Y","fClust_DBSCAN_100_a4am","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L5FAB","Clustering_by_Default","fClust_LinearityCheck_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L5FAB","Clustering_by_Default","fClust_MinExclusion_100_a4am","*","*","3","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L5FAB","Clustering_by_Default","fClust_CellSignificance_100_a4am","*","*","4","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L5FAB","Clustering_by_Default","fCluster_CellRegroup_100_a4am","*","*","5","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L5FAB","Clustering_by_Inspect_Based","fClust_CellSignificance_100_a4am","*","*","1","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L5FAB","Clustering_by_Inspect_Based","fCluster_CellRegroup_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L5FAB","CELL_SIG","fClust_DBSCAN_100_a4am","*","*","1","","","","Y","fClust_DBSCAN_100_a4am","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L6FAB","Clustering_by_Default","fClust_DBSCAN_100_a4am","*","*","1","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L6FAB","Clustering_by_Default","fClust_LinearityCheck_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L6FAB","Clustering_by_Default","fClust_MinExclusion_100_a4am","*","*","3","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L6FAB","Clustering_by_Default","fClust_CellSignificance_100_a4am","*","*","4","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L6FAB","Clustering_by_Default","fCluster_CellRegroup_100_a4am","*","*","5","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L6FAB","Clustering_by_Inspect_Based","fClust_CellSignificance_100_a4am","*","*","1","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L6FAB","Clustering_by_Inspect_Based","fCluster_CellRegroup_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
			{ "LCD","L6FAB","CELL_SIG","fClust_LinearityCheck_100_a4am","*","*","2","","","","Y","","","QAF","2011-12-28 17:54:25.0", },
	};
	
	/**
	 * Sample02OracleReader
	 */
	public CLUSTER_CONTROL_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * getList
	 */
	@Override
	public List<CLUSTER_CONTROL_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<CLUSTER_CONTROL_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				return list;
			}
			
			for (String[] items : lines) {
				
				if (Flag.TRUE) {
					CLUSTER_CONTROL_ReadBean bean = new CLUSTER_CONTROL_ReadBean();

					bean.setQafJobBu             (items[ 0]);
					bean.setQafLineCode          (items[ 1]);
					bean.setQafGroupId           (items[ 2]);
					bean.setQafLogicId           (items[ 3]);
					bean.setQafJudgeStep         (items[ 4]);
					bean.setQafDefectGroupCode   (items[ 5]);
					bean.setQafLogicSeq          (items[ 6]);
					bean.setQafJudgeCode         (items[ 7]);
					bean.setQafExtractDays       (items[ 8]);
					bean.setQafWorkflowName      (items[ 9]);
					bean.setQafLogicStatus       (items[10]);
					bean.setQafLogicName         (items[11]);
					bean.setQafLogicSourceCode   (items[12]);
					bean.setQafLastUpdateUser    (items[13]);
					bean.setQafLastUpdateDatetime(items[14]);
					
					list.add(bean);
				}
			}
		}
		
		return list;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
			BasePath.getInstance();
			try {
				Logger.getInstance(CLUSTER_CONTROL_Main.jobId, CLUSTER_CONTROL_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(CLUSTER_CONTROL_Main.jobId);
			CLUSTER_CONTROL_OracleReader reader = new CLUSTER_CONTROL_OracleReader();
			
			for (CLUSTER_CONTROL_ReadBean bean : reader.getList(true)) {
				System.out.println(bean);
			}
			
			System.out.println("the read ok !!!");
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
