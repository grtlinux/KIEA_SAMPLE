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

package kiea.proj.sdc.anal.util.log.v01;

import java.util.Vector;

import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name LogQueue.java
 *
 */
public class LogQueue
{
	/**
	 * Class Variables
	 */
	private final Vector<Object>   queue;
	private int                    count;

	/**
	 * Constructor
	 */
	public LogQueue()
	{
		if (Flag.TRUE) {
			this.queue = new Vector<Object> (5, 5);
			this.count = 0;
		}
	}
	
	/**
	 * put to the queue.
	 * @param object
	 * @return
	 */
	public synchronized int put(Object object)
	{
		if (Flag.TRUE) {
			/*
			 * vector 에 객체(String object)를 넣고 읽으려고 대기하는 thread 에게 알린다. notify
			 */
			try {
				if (object != null) {
					
					// object 객체가 null 이 아니면 queue 에 저장한다.
					this.queue.addElement(object);
					this.count ++;
					
					notifyAll();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return this.count;
	}
	
	/*
	 * get from the queue. blocking
	 */
	public synchronized Object get()
	{
		Object object = null;

		if (Flag.TRUE) {
			/*
			 * 무한 기다린다.
			 * InterruptedException 이 발생하면 return
			 */
			try {
				for (int i=0; i < 1 && this.count <= 0; i++) {
					wait();
				}
				
				if (this.count <= 0) {
					return object;   // return null
				}
				
				object = this.queue.elementAt(0);
				this.queue.remove(0);
				this.count --;
			
			} catch (InterruptedException e) {
				// throw new InterruptedException("[HANDLE] interrupted Exception by Kang Seok");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return object;
	}
	
	/**
	 * get from the queue. blocking
	 * @param timeout
	 * @return
	 */
	public synchronized Object get(long timeout)
	{
		Object object = null;
		
		if (Flag.TRUE) {
			/*
			 * timeout(milliseconds) 만큼 기다린다.
			 */
			try {
				for (int i=0; i < 1 && this.count <= 0; i++) {
					wait(timeout);
				}
				
				if (this.count <= 0) {
					return object;
				}
				
				object = this.queue.elementAt(0);
				this.queue.remove(0);
				this.count --;

			} catch (InterruptedException e) {
				// throw new InterruptedException("[HANDLE] interrupted Exception by Kang Seok");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return object;
	}
	
	/**
	 * clear the queue
	 */
	public synchronized void clear()
	{
		if (Flag.TRUE) {
			this.queue.removeAllElements();
			this.count = 0;
		}
	}
	
	/**
	 * 채워져 있는 queue 의 사이즈를 구한다.
	 * @return
	 */
	public int getSize() {
		
		return this.count;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * test-01
	 */
	private static void test01()
	{
		long time1, time2;
		time1 = System.nanoTime();

		if (Flag.TRUE) {
			LogQueue queue = new LogQueue();
			
			String objectIn = "This is a testing message....";
			System.out.println(">" + objectIn);
			
			// put into the queue
			queue.put(objectIn);
			
			// get out from the queue
			String objectOut = (String) queue.get();
			System.out.println(">" + objectOut);
		}
		
		if (Flag.TRUE) {
			LogQueue queue = new LogQueue();
			
			queue.get(10 * 1000);
		}

		time2 = System.nanoTime();
		if (Flag.TRUE) System.out.println(String.format("### took : %d ms", (time2 - time1)/1000/1000));
	}
	
	/**
	 * main entry
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
