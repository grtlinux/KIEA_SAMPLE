import java.awt.*;
public class Builder {
  public static void build(java.awt.Container conainer,
                                 javax.swing.JComponent compo,
                                 java.awt.GridBagConstraints constrain){
	conainer.add(compo,constrain);
  }//
  private static java.awt.Insets makeInsets(int top, int left,
                                            int bottom, int right) {
    return new Insets(top, left, bottom, right) ;
  }//
  public static java.awt.GridBagConstraints set(
    int gridx, int gridy, int gridwidth, int gridheight,
    double weightx, double weighty, String anchor, String fill,
    int top, int left,int bottom, int right, int ipadx, int ipady){
    GridBagConstraints cons=new GridBagConstraints(
      gridx,gridy,gridwidth,gridheight,
      weightx, weighty, anchor(anchor), fill(fill),
      makeInsets(top,left,bottom,right), ipadx, ipady);
    return cons;
  }//
  public static java.awt.GridBagConstraints set(
    int gridx, int gridy, int gridwidth, int gridheight,
    double weightx, double weighty, String anchor, String fill){
    return set(gridx,gridy,gridwidth,gridheight,
      weightx, weighty, anchor, fill,0,0,0,0,0,0);
  }//
  public static java.awt.GridBagConstraints set(
    int gridx, int gridy, int gridwidth, int gridheight,
    double weightx, double weighty, String anchor, String fill,
    int top, int left,int bottom, int right){
    return set(gridx,gridy,gridwidth,gridheight,
      weightx, weighty, anchor, fill,top,left,bottom,right,0,0);
  }//
  private static int fill(String str){
    int temp=GridBagConstraints.NONE;
    if(str.equalsIgnoreCase("B")){
      temp=GridBagConstraints.BOTH;
    }else if(str.equalsIgnoreCase("H")){
      temp=GridBagConstraints.HORIZONTAL;
    }else if(str.equalsIgnoreCase("V")){
      temp=GridBagConstraints.VERTICAL;
    }else if(str.equals("") || str==null || str.equalsIgnoreCase("N")){
      temp=GridBagConstraints.NONE;
    }
    return temp;
  }//
  private static int anchor(String str){
    int temp=GridBagConstraints.CENTER;
    if(str.equalsIgnoreCase("N")){
      temp=GridBagConstraints.NORTH;
    }else if(str.equalsIgnoreCase("S")){
      temp=GridBagConstraints.SOUTH;
    }else if(str.equalsIgnoreCase("E")){
      temp=GridBagConstraints.EAST;
    }else if(str.equalsIgnoreCase("W")){
      temp=GridBagConstraints.WEST;
    }else if(str.equalsIgnoreCase("NE")){
      temp=GridBagConstraints.NORTHEAST;
    }else if(str.equalsIgnoreCase("NW")){
      temp=GridBagConstraints.NORTHWEST;
    }else if(str.equalsIgnoreCase("SE")){
      temp=GridBagConstraints.SOUTHEAST;
    }else if(str.equalsIgnoreCase("SW")){
      temp=GridBagConstraints.SOUTHWEST;
    }else if(str.equals("") || str==null || str.equalsIgnoreCase("C")){
      temp=GridBagConstraints.CENTER;
    }
    return temp;
  }//
}
