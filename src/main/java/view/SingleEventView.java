package view;

import entity.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utilities.Utilities;

public class SingleEventView extends ViewImpl{
	
	@FXML
	Label nomeEvento;
	
	@FXML
	Label dataInizio;
	
	@FXML
	Label dataFine;
	
	@FXML
	Label risultato;
	
	@FXML 
	HBox infoBox;
	
	@FXML
	Label setRisultato;
	
	@FXML
	Button showButton;
	
	private Event e;

	@Override
	public void init() {
		e = Utilities.getEvent();
		dataInizio.setText(Utilities.getEvent().getInizio().getDate());
		dataFine.setText(Utilities.getEvent().getFine().getDate());
		nomeEvento.setText(Utilities.getEvent().getEvent());
		if (e.getTipoEvento() == "Partita") {
			setRisultato.setText(e.getRisultato());
		}else if (e.getTipoEvento() == "Allenamento") {
			setRisultato.setVisible(false);
			risultato.setVisible(false);
		}else {
			infoBox.setVisible(false);
		}
	}
	
	@FXML
	public void showConvocati() {
		Utilities.setEvent(e);
		ViewSwitcher.getInstance().switchView(new Stage(), ViewType.EVENTCONVENE);
	}
	

}
