package view;

import entity.Evento;
import entity.Society;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	TextField setRisultato;
	
	@FXML
	Button showButton;
	
	@FXML
	Button deleteEvent;
	
	@FXML
	Button insertRisultato;
	
	@FXML
	Button saveRisultato;
	
	private Evento e;

	@Override
	public void init() {
		this.setSociety();
		e = Utilities.getEvent();
		dataInizio.setText(Utilities.getEvent().getInizio().getDate());
		dataFine.setText(Utilities.getEvent().getFine().getDate());
		nomeEvento.setText(Utilities.getEvent().getEvent());
		setRisultato.setText(e.getRisultato());
		deleteEvent.setStyle("-fx-background-color: Red");
		if (e.getTipoEvento() == "Partita") {
			saveRisultato.setVisible(false);
			setRisultato.setEditable(false);
			insertRisultato.setOnMouseClicked(f->{
				saveRisultato.setVisible(true);
				setRisultato.setEditable(true);
			});
			saveRisultato.setOnMouseClicked(f->{
				e.setRisultato(setRisultato.getText());
				saveRisultato.setVisible(false);
				setRisultato.setEditable(false);
			});
		}else if (e.getTipoEvento() == "Allenamento") {
			infoBox.setVisible(false);
			insertRisultato.setVisible(false);
		}else {
			infoBox.setVisible(false);
			insertRisultato.setVisible(false);
		}
	}
	
	@FXML
	public void showConvocati() {
		Utilities.setEvent(e);
		ViewSwitcher.getInstance().switchView(new Stage(), ViewType.EVENTCONVENE);
	}
	
	private void setSociety() {
		Pair<Image, Society> society = null;
		society = Utilities.getSociety();
		this.logo.setImage(society.getX());
		this.nameLabel.setText(society.getY().getNome());
		this.color1.setFill(Color.valueOf(society.getY().getColor1()));
		this.color2.setFill(Color.valueOf(society.getY().getColor2()));
	}
	
	@FXML
	private void deleteEvent() {
		Utilities.deleteConvocazioni((int)e.getPrimaryKey());
		e.delete();
		getStage().close();
	}
	
}
