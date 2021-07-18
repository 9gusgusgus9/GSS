package view;

import java.io.IOException;
import java.sql.SQLException;

import entity.Event;
import entity.Society;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import utilities.Pair;
import utilities.Utilities;

public class SingleEventView extends ViewImpl{

	@FXML
	Label nameLabel;
	
	@FXML
	Button newEvento;
	
	@FXML
	ImageView logo;
	
	@FXML
	Rectangle color1;
	
	@FXML
	Rectangle color2;
	
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
		this.setSociety();
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
	
	private void setSociety() {
		Pair<Image, Society> society = null;
		try {
			society = Utilities.getSociety();
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.logo.setImage(society.getX());
		this.nameLabel.setText(society.getY().getNome());
		this.color1.setFill(Color.valueOf(society.getY().getColor1()));
		this.color2.setFill(Color.valueOf(society.getY().getColor2()));
	}

}
