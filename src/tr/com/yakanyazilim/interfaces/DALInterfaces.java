package tr.com.yakanyazilim.interfaces;

import java.util.List;

public interface DALInterfaces<T> {// bu interfaces generictir

	void Insert(T entity);// veri tabanina kayit atmak icin

	List<T> GetAll();// verileri listeleyebilmek icin

	T Delete(T entity);// contract turunde bir adet delete

	void Update(T entity);// guncelleme yapabilmek icin

	List<T> GetById(int id);// parametreli listeleme yapabilmek icin

}
