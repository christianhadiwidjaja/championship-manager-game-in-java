package main;

public class Fraction {
	
	private int pembilang;
	private int penyebut;
	
	public Fraction (double decimal){
		String s = String.valueOf(decimal);
		int digitsDec = s.length() - 1 - s.indexOf('.');
		int denom = 1;
		for (int i = 0; i<digitsDec ; i++){
			decimal*=10;
			denom*=10;
		}
		
		int num = (int) Math.round(decimal);
		this.pembilang = num;
		this.penyebut = denom;
	}
	
	public int getPembilang(){
		return pembilang;
	}
	
	public int getPenyebut(){
		return penyebut;
	}
	
}
