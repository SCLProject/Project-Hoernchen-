package jp.ac.oit.sclab.hoernchen.main;
import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;

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
public class CalumnPanel extends javax.swing.JPanel {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JButton jButton1;
	private JTextPane jTextPane2;
	private JTextPane jTextPane1;

	/**
	* Auto-generated main method to display this
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new CalumnPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public CalumnPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(254, 86));

				jTextPane2 = new JTextPane();
				this.add(jTextPane2, new AnchorConstraint(122, 474, 331, 49, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextPane2.setText("jTextPane2");
				jTextPane2.setPreferredSize(new java.awt.Dimension(108, 18));


				jTextPane1 = new JTextPane();
				this.add(jTextPane1, new AnchorConstraint(447, 651, 738, 49, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jTextPane1.setText("jTextPane1");
			jTextPane1.setPreferredSize(new java.awt.Dimension(153, 25));


				jButton1 = new JButton();
				this.add(jButton1, new AnchorConstraint(377, 958, 877, 777, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				jButton1.setText("jButton1");
				jButton1.setPreferredSize(new java.awt.Dimension(46, 43));

				EtchedBorder etched = new EtchedBorder(EtchedBorder.LOWERED,Color.cyan,Color.red);
				this.setBorder(etched);
				this.setBounds(new Rectangle(4, 10, 180, 25));



		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setChearId(String text){
		jTextPane1.setText(text);
	}








}
