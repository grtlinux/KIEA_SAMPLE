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

package sdc.anal.mura.macro.A90.OUTPUT_HISTORY.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name ANAL_INDEX_WriteBean.java
 *
 */
public class OUTPUT_HISTORY_WriteBean extends AbstractBean
{
	/*
	"ANAL_OUTPUT_NAME_CSV:AnalOutputNameCsv",
	"REQUESTED           :Requested        ",
	"SUCCESSFUL          :Successful       ",
	"GENERATED           :Generated        ",
	"ROWS                :Rows             ",
	*/

	private String analOutputNameCsv;
	private String requested        ;
	private String successful       ;
	private String generated        ;
	private String rows             ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s]"
			, analOutputNameCsv
			, requested        
			, successful       
			, generated        
			, rows             
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("ANAL_OUTPUT_NAME_CSV");
		list.add("REQUESTED"           );
		list.add("SUCCESSFUL"          );
		list.add("GENERATED"           );
		list.add("ROWS"                );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(analOutputNameCsv);
		list.add(requested        );
		list.add(successful       );
		list.add(generated        );
		list.add(rows             );

		return list.toArray(new String[list.size()]);
	}

	public String getAnalOutputNameCsv()
	{
		return analOutputNameCsv;
	}

	public void setAnalOutputNameCsv(String analOutputNameCsv)
	{
		this.analOutputNameCsv = analOutputNameCsv;
	}

	public String getRequested()
	{
		return requested;
	}

	public void setRequested(String requested)
	{
		this.requested = requested;
	}

	public String getSuccessful()
	{
		return successful;
	}

	public void setSuccessful(String successful)
	{
		this.successful = successful;
	}

	public String getGenerated()
	{
		return generated;
	}

	public void setGenerated(String generated)
	{
		this.generated = generated;
	}

	public String getRows()
	{
		return rows;
	}

	public void setRows(String rows)
	{
		this.rows = rows;
	}
}
