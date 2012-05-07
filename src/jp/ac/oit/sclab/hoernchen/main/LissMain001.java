package jp.ac.oit.sclab.hoernchen.main;

import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class LissMain001 extends JFrame implements MouseListener {



	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	JDialog jDialog;
	JFrame mJFrame;
	JPanel mJPanel;
	JButton	mJButton;
	CalumnPanel cPanel[] = new CalumnPanel[10];
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LissMain001 mLSMain = new LissMain001();


	}

	 public LissMain001(){
		 GridLayout thisLayout = new GridLayout(1, 1);
		 thisLayout.setColumns(1);
		 thisLayout.setHgap(5);
		 thisLayout.setVgap(5);
		 getContentPane().setLayout(thisLayout);
	 	{
		 	this.setSize(504, 436);
	 	}
		 mJFrame = new JFrame("タイトル");
		 mJFrame.setTitle("test");
		 mJButton = new JButton();
		 mJButton.setText("閉じる");
		 mJPanel = new JPanel();
		 mJButton.setActionCommand("close");
		 mJButton.addMouseListener(this);

		 for(int i = 0 ; i<cPanel.length;i++){
		 cPanel[i] = new CalumnPanel();
		 cPanel[i].setChearId("C-"+i);
		 mJPanel.add(cPanel[i], i);
		 }



		 mJPanel.add(mJButton);
		 mJFrame.add(mJPanel);

		 toggleFullScreenWindow();



	 }

	private void toggleFullScreenWindow(){
		GraphicsEnvironment gEn = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = gEn.getDefaultScreenDevice();
		if(gd.getFullScreenWindow()==null){
		mJFrame.dispose();
		mJFrame.setUndecorated(true);
		gd.setFullScreenWindow(mJFrame);
		}else{
			gd.setFullScreenWindow(null);
			mJFrame.dispose();
			mJFrame.setUndecorated(false);
			mJFrame.setVisible(true);
			mJFrame.repaint();






		}


	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Component src = (Component)e.getSource();
		if(src == mJButton){
			mJFrame.dispose();
			return;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}




}
