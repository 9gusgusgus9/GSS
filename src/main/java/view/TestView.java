package view;

public class TestView extends ViewImpl {

	
	
	@Override
	public void init() {
		
	}
	
	public void switchToStart() {
		ViewSwitcher.getInstance().switchView(this.getStage(), ViewType.START);
	}

}
