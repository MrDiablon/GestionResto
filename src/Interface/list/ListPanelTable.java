package Interface.list;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import Interface.PassFrame;
import LienBD.Salles;
import LienBD.Table;

public class ListPanelTable extends JPanel{

	private PassFrame parent;
	private JButton nouveau, supprimer, modifier;
	private JList<Table> TableList;
	private MyListModel<Table> modelList = new MyListModel<Table>();

	public ListPanelTable(PassFrame parent,int numSalle) {
		this.parent = parent;
		this.setLayout(new BorderLayout());

		this.nouveau = new JButton("Nouveau", new ImageIcon(getClass()
				.getResource("/img/new.png")));
		this.modifier = new JButton("Modifier", new ImageIcon(getClass()
				.getResource("/img/edit.png")));
		this.supprimer = new JButton("Supprimer", new ImageIcon(getClass()
				.getResource("/img/delete.png")));
		JToolBar barreOutils = new JToolBar();
		barreOutils.add(this.nouveau);
		barreOutils.add(this.modifier);
		barreOutils.add(this.supprimer);
		this.add(barreOutils);
		
		try {
			Table[] tables = Table.getAll(numSalle);
			this.TableList = new JList<Table>(tables);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(parent, "Probléme lors de la communication avec la base de donnée");
		}
		this.TableList.setModel(modelList);
		this.TableList.setCellRenderer(new RendererTable());
		
		this.TableList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// a Faire
				}
			}
		});
		
		
		this.add(this.TableList);
		
	}
}
