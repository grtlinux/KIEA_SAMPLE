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

package kiea.z01.ztest.t01.Enum.t01;

/**
 * @author KangSeok
 * @date 2014. 8. 11.
 * @file_name EnumTest01Main.java
 *
 */
public class EnumTest01Main
{
	private String name;
	private int size;
	private Type type;
	
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @return the size
	 */
	public int getSize()
	{
		return size;
	}
	/**
	 * @return the type
	 */
	public Type getType()
	{
		return type;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size)
	{
		this.size = size;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Type type)
	{
		this.type = type;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		EnumTest01Main main = new EnumTest01Main();
		
		main.setName("나이키");
		main.setSize(230);
		main.setType(Type.RUNNING);
		
		System.out.println("신발이름   : " + main.getName());
		System.out.println("신발사이즈 : " + main.getSize());
		System.out.println("신발종류   : " + main.getType());
		
		System.out.println();
		for (Type type : Type.values()) {
			System.out.println(type + ":" + type.ordinal());
		}
		
		Type tp1 = Type.WALKING;
		Type tp2 = Type.valueOf("WALKING");
		
		System.out.println(tp1 + ", " + tp2);
	}
}
