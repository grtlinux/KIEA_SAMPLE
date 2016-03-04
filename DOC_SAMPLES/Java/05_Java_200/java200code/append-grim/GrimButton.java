//package sameimage;

import javax.swing.*;
import java.awt.Dimension;

public class GrimButton extends JButton {
  private static final long serialVersionUID=122487214237L;//JAVA5
  public static final int WIDTH=GrimUtil.WIDTH;
  private int width=WIDTH;
  private int height=WIDTH;
  private int  clickNum=0;

  private int imageNnum=0;              //앞모습
  private static final int initNum=12;  //뒷모습-초기화 되었을때 기본
  ImageIcon icon=null;                  //저장할 아이콘

  private boolean isBack=true;          //뒷모습
  private boolean first=true;           //



  public GrimButton(int imageNnum) {
    this.imageNnum=imageNnum;
    init();
  }
  public void shake(){
    //this("GrimButton");
    icon=null;

    String imageNums="image/s"+imageNnum+".gif";
    icon = new ImageIcon(imageNums);
    //System.out.println(imageNums);
    this.setPreferredSize(new Dimension(width,height));
    this.setIcon(icon);
  }

  public void init(){
    //this("GrimButton");
    icon=null;
    //this.imageNnum=imageNnum;
    String imageNums="image/s"+initNum+".gif";
    icon = new ImageIcon(imageNums);
    //System.out.println(imageNums);
    this.setPreferredSize(new Dimension(width,height));
    this.setIcon(icon);

  }
  public boolean getFirst() {
    return first;
  }
  public void setFirst() {
    this.first = first;
  }
  public void setBack() {
    this.isBack = true;   //뒷쪽
  }
  public void setFront() {
    this.isBack = false;  //앞쪽
  }
  public boolean backOrFront() {
    return isBack;
  }
  public int getImageNnum() {
    return imageNnum;
  }
  public int getClickNum() {
    return clickNum;
  }
  public void setClickNum() {
    clickNum++;
  }
  public void setClickNumZero() {
    clickNum=0;
  }


/*
  public GrimButton(String text) {
    super(text);
  }
*/


}