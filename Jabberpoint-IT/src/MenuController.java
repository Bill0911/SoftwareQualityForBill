import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/** <p>The controller for the menu</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar {
	
	private Frame parent; // the frame, only used as parent for the Dialogs
	private Presentation presentation; // Commands are given to the presentation
	
	private static final long serialVersionUID = 227L;

	protected static final String ADDSLIDE = "Add Slide";

	protected static final String ABOUT = "About";
	protected static final String FILE = "File";
	protected static final String EXIT = "Exit";
	protected static final String GOTO = "Go to";
	protected static final String HELP = "Help";
	protected static final String NEW = "New";
	protected static final String NEXT = "Next";
	protected static final String OPEN = "Open";
	protected static final String PAGENR = "Page number?";
	protected static final String PREV = "Prev";
	protected static final String SAVE = "Save";
	protected static final String VIEW = "View";
	
	protected static final String TESTFILE = "test.xml";
	protected static final String SAVEFILE = "dump.xml";
	
	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Load Error";
	protected static final String SAVEERR = "Save Error";

	public MenuController(Frame frame, Presentation pres) {
		parent = frame;
		presentation = pres;
		MenuItem menuItem;
		Menu fileMenu = new Menu(FILE);
		fileMenu.add(menuItem = mkMenuItem(OPEN));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("Open (Ctrl + O)");
				presentation.clear();
				Accessor xmlAccessor = new XMLAccessor();
				try {
					xmlAccessor.loadFile(presentation, TESTFILE);
					presentation.setToSlideNumber(0);
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(parent, IOEX + exc, 
         			LOADERR, JOptionPane.ERROR_MESSAGE);
				}
				parent.repaint();
			}
		} );
		fileMenu.add(menuItem = mkMenuItem(NEW));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("New (Ctrl + N)");
				presentation.clear();
				parent.repaint();
			}
		});

		fileMenu.add(menuItem = mkMenuItem(ADDSLIDE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// Step 1: Get the title and text input from the user
				String title = JOptionPane.showInputDialog(parent, "Enter slide title:");
				String content = JOptionPane.showInputDialog(parent, "Enter slide content:");

				// Step 2: Create a new slide and append text items
				Slide newSlide = new Slide();
				newSlide.setTitle(title);
				newSlide.append(1, content);  // Add text content with a level (e.g., 1)

				// Step 3: Ask the user if they want to add an image
				int imageOption = JOptionPane.showConfirmDialog(parent, "Would you like to add an image?", "Add Image", JOptionPane.YES_NO_OPTION);

				if (imageOption == JOptionPane.YES_OPTION) {
					// Step 4: Open a file chooser for the user to select an image
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "png", "gif"));
					int result = fileChooser.showOpenDialog(parent);

					if (result == JFileChooser.APPROVE_OPTION) {
						// Get the selected image file path
						File selectedFile = fileChooser.getSelectedFile();
						String imagePath = selectedFile.getPath();

						// Step 5: Create a BitmapItem and add it to the slide
						newSlide.append(new BitmapItem(1, imagePath));  // Using level 1 for image
					}
				}

				// Step 6: Add the slide to the presentation
				presentation.append(newSlide);

				// Step 7: Optionally, jump to the new slide (if you want to show it right away)
				presentation.setToSlideNumber(presentation.getSize() - 1);
				parent.repaint();
			}
		});

		fileMenu.add(menuItem = mkMenuItem(SAVE));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Save (Ctrl + S)");
				Accessor xmlAccessor = new XMLAccessor();
				try {
					xmlAccessor.saveFile(presentation, SAVEFILE);
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(parent, IOEX + exc, 
							SAVEERR, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		fileMenu.addSeparator();
		fileMenu.add(menuItem = mkMenuItem(EXIT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.exit(0);
			}
		});
		add(fileMenu);
		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(menuItem = mkMenuItem(NEXT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.nextSlide();
			}
		});
		viewMenu.add(menuItem = mkMenuItem(PREV));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.prevSlide();
			}
		});
		viewMenu.add(menuItem = mkMenuItem(GOTO));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String pageNumberStr = JOptionPane.showInputDialog((Object)PAGENR);
				int pageNumber = Integer.parseInt(pageNumberStr);
				presentation.setToSlideNumber(pageNumber - 1);
			}
		});
		add(viewMenu);
		Menu helpMenu = new Menu(HELP);
		helpMenu.add(menuItem = mkMenuItem(ABOUT));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AboutBox.show(parent);
			}
		});
		setHelpMenu(helpMenu);		// needed for portability (Motif, etc.).
	}

// create a menu item
	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
}
