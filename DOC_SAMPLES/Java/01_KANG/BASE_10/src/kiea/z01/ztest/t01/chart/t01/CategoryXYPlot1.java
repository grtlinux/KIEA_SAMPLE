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

package kiea.z01.ztest.t01.chart.t01;

import java.awt.Color;
import java.awt.Dimension;

import kiea.kr.co.tain.base.Flag;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * @author KangSeok
 * @date 2014. 8. 25.
 * @file_name CategoryXYPlot1.java
 *
 */
public class CategoryXYPlot1 extends ApplicationFrame
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public CategoryXYPlot1(String str)
	{
		super(str);
		JFreeChart jfreechart = createCombinedChart();
		jfreechart.setBackgroundPaint(Color.white);
		ChartPanel chartpanel = new ChartPanel(jfreechart, true, true, true, false, true);
		chartpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(chartpanel);
	}
	
	private static JFreeChart createCombinedChart()
	{
		LineAndShapeRenderer render = new LineAndShapeRenderer();
		CategoryAxis xAxis = new CategoryAxis("");
		ValueAxis yAxis = new NumberAxis("");
		CategoryPlot plot = new CategoryPlot(getDataset(), xAxis, yAxis, render);
		
		return new JFreeChart(plot);
	}
	
	private static DefaultCategoryDataset getDataset()
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		String[] row = { "대상제품건수", "인증제품수", "인증업체수" };
		
		for (int j=0; j < 10; j++) {
			dataset.addValue(new Double(j * 33), row[0], String.valueOf(j));
		}

		for (int j=0; j < 10; j++) {
			dataset.addValue(new Double(j * 13), row[1], String.valueOf(j));
		}
		
		for (int j=0; j < 10; j++) {
			dataset.addValue(new Double(j *  3), row[2], String.valueOf(j));
		}
		
		return dataset;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			CategoryXYPlot1 combinedxyplot = new CategoryXYPlot1("");
			combinedxyplot.pack();
			RefineryUtilities.centerFrameOnScreen(combinedxyplot);;
			combinedxyplot.setVisible(true);
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
