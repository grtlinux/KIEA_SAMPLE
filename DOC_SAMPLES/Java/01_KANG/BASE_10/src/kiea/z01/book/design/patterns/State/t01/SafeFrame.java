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

package kiea.z01.book.design.patterns.State.t01;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author KangSeok
 * @date 2014. 8. 6.
 * @file_name SafeFrame.java
 *
 */
public class SafeFrame extends Frame implements ActionListener, Context
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private TextField textClock = new TextField(60);
	private TextArea textScreen = new TextArea(10, 60);
	private Button buttonUse = new Button("Use");
	private Button buttonAlarm = new Button("Alarm");
	private Button buttonPhone = new Button("Phone");
	private Button buttonExit = new Button("Exit");
	
	private State state = DayState.getInstance();
	
	@SuppressWarnings("deprecation")
	public SafeFrame(String title)
	{
		super(title);
		
		setBackground(Color.lightGray);
		setLayout(new BorderLayout());
		
		add(textClock, BorderLayout.NORTH);
		add(textScreen, BorderLayout.CENTER);
		textClock.setEditable(false);
		textScreen.setEditable(false);
		
		Panel panel = new Panel();
		panel.add(buttonUse);
		panel.add(buttonAlarm);
		panel.add(buttonPhone);
		panel.add(buttonExit);
		add(panel, BorderLayout.SOUTH);
		
		pack();
		show();
		
		buttonUse.addActionListener(this);
		buttonAlarm.addActionListener(this);
		buttonPhone.addActionListener(this);
		buttonExit.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("" + e);
		
		if (e.getSource() == buttonUse) {
			state.doUse(this);
		} else if (e.getSource() == buttonAlarm) {
			state.doAlarm(this);
		} else if (e.getSource() == buttonPhone) {
			state.doPhone(this);
		} else if (e.getSource() == buttonExit) {
			System.exit(0);
		} else {
			System.out.println("?");
		}
	}
	
	public void setClock(int hour)
	{
		String clockString = String.format("현재 시각은 %02d:00", hour);
		System.out.println(clockString);
		textClock.setText(clockString);
		state.doClock(this, hour);;
	}
	
	public void changeState(State state)
	{
		System.out.println(String.format("%s에서 %s로 상태가 변화되었습니다.", this.state, state));
		this.state = state;
	}
	
	public void callSecurityCenter(String msg)
	{
		textScreen.append("call : " + msg + "\n");
	}
	
	public void recordLog(String msg)
	{
		textScreen.append("record : " + msg + "\n");
	}
}
