package tr.com.yakanyazilim.types;

public class KategoriContract {
	private int id;
	private String adi;
	private int parentId;// sonsuz bir kategori eklemek icin

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return adi;
	}

}
