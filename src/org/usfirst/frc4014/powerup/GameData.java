package org.usfirst.frc4014.powerup;

public final class GameData {

	private final String fieldData;

	/**
	 * @param fieldData From Field, e.g., "RLR"
	 */
	GameData (String fieldData) {
		this.fieldData = fieldData.toUpperCase();
	}

	public boolean isAllySwitchOnLeft() {
		return fieldData.charAt(0) == 'L';
	}

	public boolean isAllyScaleOnLeft() {
		return fieldData.charAt(1) == 'L';
	}

}
