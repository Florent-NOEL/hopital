package dao;

import java.util.List;

public interface DaoGeneric<E, K> {
	void create(E obj);

	void update(E obj);

	void delete(E obj);

	void deleteByKey(K key);

	E findByKey(K key);

	List<E> findAll();
}
