package comportamiento.command.swing.undo_redo;

import java.awt.event.*;
import java.awt.*;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.undo.UndoableEditSupport;

/**
 * The undo applet defines the undo / redo concept in swing. The applet allow
 * you to add / remove object to/from a list. It also support undoing/ redoing
 * those operations
 * 
 * @author Tomer Meshorer
 */

public class TestUndoRedo extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JList lista;
	private DefaultListModel modeloLista;
	private JScrollPane panelScroll;
	private JButton bntAddElemento;
	private JButton brnBorrarElemento;
	private JButton btnDeshacer;
	private JButton btnRepetir;
	
	private int id_UltimoElemento;

	/**
	 * Objetos del sistema undo
	 */
	private UndoManager historial;
	private UndoableEditSupport gestorEventosUndo;

	public static void main(String args[]) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					TestUndoRedo instancia = new TestUndoRedo();
					instancia.setTitle("Soporte de Swing para operaciones undo/redo");
					instancia.setSize(500, 400);
					instancia.setLocationRelativeTo(null);					
					instancia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});				
	}
	
	public TestUndoRedo() {
		modeloLista = new DefaultListModel();
		lista = new JList(modeloLista);
		panelScroll = new JScrollPane(lista);
		
		/*
		 * Añadimos a la lista un manejador para controlar la
		 * desactivación del botón Borrar si no hay seleccionado
		 * ningún elemento en la lista
		 */
		lista.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent evt) {
				if (evt.getLastIndex() >= evt.getFirstIndex()) {
					brnBorrarElemento.setEnabled(true);
				}
			}
		});

		// Acciones
		Action crearAction = new CrearElementoAction();
		Action borrarAction = new BorrarElementoAction();
		Action deshacerAction = new DeshacerAction();
		Action repetirAction = new RepetirAction();

		// Crear los botones y registrar las acciones manejadoras de cada botón
		bntAddElemento = new JButton("Nueva");
		bntAddElemento.addActionListener(crearAction);
		
		brnBorrarElemento = new JButton("Borrar");
		brnBorrarElemento.addActionListener(borrarAction);		
		brnBorrarElemento.setEnabled(false);
		
		btnDeshacer = new JButton("Deshacer");
		btnDeshacer.addActionListener(deshacerAction);
		
		btnRepetir = new JButton("Repetir");
		btnRepetir.addActionListener(repetirAction);

		crearGUI();

		// Inicializar el sistema deshacer/repetir
		historial = new UndoManager();
		gestorEventosUndo = new UndoableEditSupport();
		gestorEventosUndo.addUndoableEditListener(new UndoAdapter());
		refreshUndoRedo();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void crearGUI() {
		// Crear el panel deshacer/repetir
		JPanel panelDeshacer = new JPanel();
		panelDeshacer.setLayout(new GridLayout(1, 2));
		panelDeshacer.setBorder(BorderFactory.createTitledBorder(
				LineBorder.createBlackLineBorder(), "Deshacer / Repetir",
				TitledBorder.LEFT, TitledBorder.TOP));
		panelDeshacer.add(btnDeshacer);
		panelDeshacer.add(btnRepetir);

		// Crear el panel de acciones
		JPanel panelAcciones = new JPanel();
		panelAcciones.setLayout(new GridLayout(1, 2));
		panelAcciones.setBorder(BorderFactory.createTitledBorder(
				LineBorder.createBlackLineBorder(), "Acciones",
				TitledBorder.LEFT, TitledBorder.TOP));
		panelAcciones.add(bntAddElemento);
		panelAcciones.add(brnBorrarElemento);

		// Crear el panel de tareas
		JPanel panelTareas = new JPanel();
		panelTareas.setLayout(new GridLayout(1, 2));
		panelTareas.add(panelAcciones);
		panelTareas.add(panelDeshacer);
		panelTareas.setSize(100, 100);

		// Añadir el panel de tareas al panel principal
		setLayout(new BorderLayout());
		add(BorderLayout.SOUTH, panelTareas);
		add(BorderLayout.CENTER, panelScroll);		
	}

	/**
	 * This method is called after each undoable operation in order to refresh
	 * the presentation state of the undo/redo GUI
	 */

	public void refreshUndoRedo() {

		// refresh undo
		btnDeshacer.setText(historial.getUndoPresentationName());
		btnDeshacer.setEnabled(historial.canUndo());

		// refresh redo
		btnRepetir.setText(historial.getRedoPresentationName());
		btnRepetir.setEnabled(historial.canRedo());
	}

	/**
	 * Add new element Action.
	 */
	private class CrearElementoAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent evt) {
			// always add to the end of the JList
			int NumOfElements = modeloLista.getSize();
			// however, give the the element is ID number
			Object element = new String("Tarea " + id_UltimoElemento);

			// record the effect
			UndoableEdit edit = new AddEdit(modeloLista, element,
					NumOfElements);
			// perform the operation
			modeloLista.addElement(element);

			// notify the listeners
			gestorEventosUndo.postEdit(edit);

			// increment the ID
			id_UltimoElemento++;

		}
	}

	/**
	 * The remove action
	 */
	private class BorrarElementoAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent evt) {
			int selectIdx = lista.getSelectedIndex();
			
			if (selectIdx > -1) {
				Object selectedElement = modeloLista.getElementAt(selectIdx);
	
				// create the edit
				UndoableEdit edit_ = new RemoveEdit(modeloLista, selectedElement,
						selectIdx);
	
				// perfrom the remove
				modeloLista.removeElementAt(selectIdx);
	
				// notify the listeners
				gestorEventosUndo.postEdit(edit_);
	
				// disable the remove button
				brnBorrarElemento.setEnabled(false);
			}
		}
	}

	/**
	 * undo action
	 */

	private class DeshacerAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent evt) {
			historial.undo();
			refreshUndoRedo();
		}
	}

	/**
	 * inner class that defines the redo action
	 * 
	 */

	private class RepetirAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent evt) {
			historial.redo();
			refreshUndoRedo();
		}
	}

	/**
	 * An undo/redo adapter. The adapter is notified when an undo edit
	 * occur(e.g. add or remove from the list) The adaptor extract the edit from
	 * the event, add it to the UndoManager, and refresh the GUI
	 */

	private class UndoAdapter implements UndoableEditListener {
		public void undoableEditHappened(UndoableEditEvent evt) {
			UndoableEdit edit = evt.getEdit();
			historial.addEdit(edit);
			refreshUndoRedo();
		}
	}

	

}
