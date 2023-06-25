package helper;

public class AusgabeKonsole implements IAusgabe {
	 private String text;

	
	public void Print(String output) {
		text = output;
		System.out.print(output);
	}
	
	@Override
    public String ausgabeHolen() {
        return text;
    }
}
