public class MemoJFrame extends AbstractJFrame {
        private static final long serialVersionUID=122342166345L;//JAVA5
        private MemoJPanel  mp=null;
        public MemoJFrame(String title) {
          super(title);
        }
        public void addListeners() {//AwtAbstractJFrame 에서호출
          //-------귀달기-------//
        }
        public void inits() {//AwtAbstractJFrame 에서 호출
          mp=new MemoJPanel();
          setMainJpanel(mp);
          //----필수----//
          this.setSize(400,300);
        }
}
