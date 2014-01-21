package com.mlazovla.woodendart;

public class TemporaryScore extends WoodenDart {
	private int[] tosses;
	private int tossCounter, tossCounterDistinct;
	
	public TemporaryScore() {
		tosses = new int[9];
		reset();
	}
	
	public String getLine() {
		String res = null;
		for (int i=0; i<tossCounter; i++) {
			res += tosses[i] + " ";
		}
		return res;
	}
	
	public boolean addToss(int _score) {
		if (tossCounter >= 9) return false;
		if (tossCounterDistinct == 3 && _score != tosses[tossCounter - 1]) return false;
		if (tossCounter == 0)
			tossCounterDistinct = 1;
		else if (_score != tosses[tossCounter - 1]) tossCounterDistinct++;
		
		tosses[tossCounter] = _score;
		tossCounter++;

		return true;
	}
	
	public void reset() {
		for (int i=0; i<9; i++) {
			tosses[i] = 0;
		}
		tossCounter = 0;
		tossCounterDistinct = 0;
	}
	
	public void undo() {
		if (tossCounter > 0) {
			tossCounter--;
			tosses[tossCounter] = 0;
		}
		recomputeDistinctCounter();
	}
	
	public int getSum() {
		int res = 0;
		for (int i=0; i<tossCounter; i++) {
			res += tosses[i];
		}
		return res;
	}
	
	private void recomputeDistinctCounter() {
		tossCounterDistinct = 1;
		for (int i=1; i<tossCounter; i++) {
			if(tosses[i-1] != tosses[i]) tossCounterDistinct++;
		}
	}
}
