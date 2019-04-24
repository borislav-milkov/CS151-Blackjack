package edu.sjsu.cs.cs151.blackjack.View;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

// NOTE
// Should probably implement this using Javax.swing instead of eclipse.swt


public class TablePrototype {

	protected Shell shell;
	private Text text;
	private Display display;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TablePrototype window = new TablePrototype();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		Image img = new Image(display, this.getClass().getResourceAsStream("/cards_Icon.png"));
		shell.setImage(img);
		shell.setText("BlackJack");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(0, 55, 87, 33);
		btnNewButton.setText("HIT");
		
		Button button = new Button(shell, SWT.NONE);
		button.setText("STAY");
		button.setBounds(0, 87, 87, 33);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("BET");
		button_1.setBounds(0, 119, 87, 33);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(86, 124, 48, 19);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(186, 184, 56, 71);
		lblNewLabel.setText("CARD");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("CARD");
		label.setBounds(248, 184, 56, 71);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("CARD");
		label_1.setBounds(124, 184, 56, 71);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("CARD");
		label_2.setBounds(186, 10, 56, 71);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("CARD");
		label_3.setBounds(248, 10, 56, 71);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setText("CARD");
		label_4.setBounds(124, 10, 56, 71);

	}

}
