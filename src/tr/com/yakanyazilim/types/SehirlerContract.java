package tr.com.yakanyazilim.types;

public class SehirlerContract {

	private String sehirAdi;
	private int sehirId;

	public String getSehirAdi() {
		return sehirAdi;
	}

	public void setSehirAdi(String sehirAdi) {
		this.sehirAdi = sehirAdi;
	}

	public int getSehirId() {
		return sehirId;
	}

	public void setSehirId(int sehirId) {
		this.sehirId = sehirId;
	}

	@Override
	public String toString() {
		return sehirAdi;
	}
}
