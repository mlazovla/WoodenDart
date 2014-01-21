package com.mlazovla.woodendart;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WoodenDart extends Activity {
	private int[] tosses;
	private int tossCounter, tossCounterDistinct;
	private int playersScore[];
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wooden_dart);
        
    	playersScore = new int[2];
		tosses = new int[9];
		resetAll();
        
        final Button[] buttons = new Button[22];
        final TextView[] players = new TextView[2];
        buttons[0] = (Button) findViewById(R.id.button21); // score 25
        buttons[1] = (Button) findViewById(R.id.button1);
        buttons[2] = (Button) findViewById(R.id.button2);
        buttons[3] = (Button) findViewById(R.id.button3);
        buttons[4] = (Button) findViewById(R.id.button4);
        buttons[5] = (Button) findViewById(R.id.button5);
        buttons[6] = (Button) findViewById(R.id.button6);
        buttons[7] = (Button) findViewById(R.id.button7);
        buttons[8] = (Button) findViewById(R.id.button8);
        buttons[9] = (Button) findViewById(R.id.button9);
        buttons[10] = (Button) findViewById(R.id.button10);
        buttons[11] = (Button) findViewById(R.id.button11);
        buttons[12] = (Button) findViewById(R.id.button12);
        buttons[13] = (Button) findViewById(R.id.button13);
        buttons[14] = (Button) findViewById(R.id.button14);
        buttons[15] = (Button) findViewById(R.id.button15);
        buttons[16] = (Button) findViewById(R.id.button16);
        buttons[17] = (Button) findViewById(R.id.button17);
        buttons[18] = (Button) findViewById(R.id.button18);
        buttons[19] = (Button) findViewById(R.id.button19);
        buttons[20] = (Button) findViewById(R.id.button20);
        
        // undo/reset button
        buttons[21] = (Button) findViewById(R.id.undo);
        
        // players
        players[0] = (TextView) findViewById(R.id.textView1);
        players[1] = (TextView) findViewById(R.id.textView2);               
        
        // clicking
        buttons[0].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(25);} } );
        buttons[1].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(1); } });
        buttons[2].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(2); } });
        buttons[3].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(3); } });
        buttons[4].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(4); } });
        buttons[5].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(5); } });
        buttons[6].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(6); } });
        buttons[7].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(7); } });
        buttons[8].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(8); } });
        buttons[9].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(9); } });
        buttons[10].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(10); } });
        buttons[11].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(11); } });
        buttons[12].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(12); } });
        buttons[13].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(13); } });
        buttons[14].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(14); } });
        buttons[15].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(15); } });
        buttons[16].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(16); } });
        buttons[17].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(17); } });
        buttons[18].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(18); } });
        buttons[19].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(19); } });
        buttons[20].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {addToss(20); } });

        buttons[21].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {undo(); } });
        buttons[21].setOnLongClickListener(new OnLongClickListener() {	public boolean onLongClick(View v) {resetAll(); return true;} });

        players[0].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {playersScore[0] += getSum(); reset();} } );
        players[1].setOnClickListener(new View.OnClickListener() {	public void onClick(View v) {playersScore[1] += getSum(); reset();} });        

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wooden_dart, menu);
        return true;
    }

    
	public String getLine() {
		String res = "";
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
		
		update();
		
		return true;
	}
	
	public void reset() {
		for (int i=0; i<9; i++) {
			tosses[i] = 0;
		}
		tossCounter = 0;
		tossCounterDistinct = 0;
		
		update();
	}
	
	public void resetAll() {
    	for (int i=0; i<2; i++) {
    		playersScore[i] = 0;
    	}
    	reset();
	}
	
	public void undo() {
		if (tossCounter > 0) {
			tossCounter--;
			tosses[tossCounter] = 0;
		}
		recomputeDistinctCounter();
		update();
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
	
	public void update() {
		TextView textView = (TextView) findViewById(R.id.tmpScoreLine);
		textView.setText( getLine() );
		textView = (TextView) findViewById(R.id.tmpScoreSum);
		textView.setText( String.valueOf(getSum()) );
		textView = (TextView) findViewById(R.id.p1_score);
		textView.setText( String.valueOf( playersScore[0] ) );
		textView = (TextView) findViewById(R.id.p2_score);
		textView.setText( String.valueOf( playersScore[1] ) );
		
	}
    
}
