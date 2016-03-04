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

package kiea.z01.book.design.patterns.AbstractFactory.t01;

import kiea.z01.book.design.patterns.AbstractFactory.t01.factory.Factory;
import kiea.z01.book.design.patterns.AbstractFactory.t01.factory.Link;
import kiea.z01.book.design.patterns.AbstractFactory.t01.factory.Page;
import kiea.z01.book.design.patterns.AbstractFactory.t01.factory.Tray;

/**
 * @author KangSeok
 * @date 2014. 9. 12.
 * @file_name Main.java
 *
 */
public class Main
{
	public static void main(String[] args)
	{
		String className;
		className = "kiea.z01.book.design.patterns.AbstractFactory.t01.listfactory.ListFactory";
		className = "kiea.z01.book.design.patterns.AbstractFactory.t01.tablefactory.TableFactory";
		
		Factory factory = Factory.getFactory(className);
		
		Link joins = factory.createLink("중앙일보", "http://www.joins.com/");
		Link hani = factory.createLink("한겨레 신문", "http://www.hani.co.kr/");
		
		Link us_yahoo = factory.createLink("Yahoo!", "http://www.yahoo.com/");
		Link kr_yahoo = factory.createLink("Yahoo! Korea", "http://www.yahoo.co.kr/");
		
		Link excite = factory.createLink("Excite", "http://www.excite.com/");
		Link google = factory.createLink("Google", "http://www.google.com/");
		
		Tray traynews = factory.createTray("신문");
		traynews.add(joins);
		traynews.add(hani);
		
		Tray trayyahoo = factory.createTray("Yahoo!");
		trayyahoo.add(us_yahoo);
		trayyahoo.add(kr_yahoo);

		Tray traysearch = factory.createTray("엔진");
		traysearch.add(trayyahoo);
		traysearch.add(excite);
		traysearch.add(google);
		
		Page page = factory.createPage("LinkPage", "홍길동");
		page.add(traynews);
		page.add(traysearch);
		page.output();
	}
}
