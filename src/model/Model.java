package model;

import view.View;

public class Model implements ModelAPI {

	private View view;
	
	public Model(View view) {
		this.view = view;
	}

}
