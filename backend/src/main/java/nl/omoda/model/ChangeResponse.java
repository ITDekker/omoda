package nl.omoda.model;

import java.util.Map;

public class ChangeResponse {
    private double totalChange;
    private Map<String, Integer> denominations;
    private int status;
    private String text;

    public ChangeResponse(double totalChange, Map<String, Integer> denominations, int status, String text) {
        this.totalChange = totalChange;
        this.denominations = denominations;
        this.status = status;
        this.text = text;
    }

    // Getters and setters
    public double getTotalChange() {
        return totalChange;
    }

    public Map<String, Integer> getDenominations() {
        return denominations;
    }

	public int getStatus() {
		return status;
	}

	public String getText() {
		return text;
	}
}