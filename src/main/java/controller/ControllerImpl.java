package controller;

import view.View;

public abstract class ControllerImpl implements Controller {
	
	private View view;
	
	@Override
	public void attachView(View view) {
		this.view = view;
	}

	@Override
	public View getView() {
		return this.view;
	}

	@Override
	public abstract void init();
}
