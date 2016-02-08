package com.calcul.diabetif.commun.dao;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.ObjectCache;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.stmt.GenericRowMapper;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.PreparedUpdate;
import com.j256.ormlite.support.DatabaseResults;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseCommonDAO<T, ID> extends Dao<T, ID> {
    void initializeOrFail();

    T queryForIdOrFail(ID id);

    T queryForFirstOrFail(PreparedQuery<T> preparedQuery);

    List<T> queryForAllOrFail();

    List<T> queryForEqOrFail(String fieldName, Object value);

    List<T> queryOrFail(PreparedQuery<T> preparedQuery);

    List<T> queryForMatchingOrFail(T matchObj);

    List<T> queryForMatchingArgsOrFail(T matchObj);

    List<T> queryForFieldValuesOrFail(Map<String, Object> fieldValues);

    List<T> queryForFieldValuesArgsOrFail(Map<String, Object> fieldValues);

    T queryForSameIdOrFail(T data);

    int createOrFail(T data);

    T createIfNotExistsOrFail(T data);

    CreateOrUpdateStatus createOrUpdateOrFail(T data);

    int updateOrFail(T data);

    int updateIdOrFail(T data, ID newId);

    int updateOrFail(PreparedUpdate<T> preparedUpdate);

    int refreshOrFail(T data);

    int deleteOrFail(T data);

    int deleteByIdOrFail(ID id);

    int deleteOrFail(Collection<T> datas);

    int deleteIdsOrFail(Collection<ID> ids);

    int deleteOrFail(PreparedDelete<T> preparedDelete);

    void closeLastIteratorOrFail();

    CloseableIterator<T> iteratorOrFail(PreparedQuery<T> preparedQuery);

    CloseableIterator<T> iteratorOrFail(PreparedQuery<T> preparedQuery, int resultFlags);


    GenericRawResults<String[]> queryRawOrFail(String query, String... arguments);

    <GR> GenericRawResults<GR> queryRawOrFail(String query, RawRowMapper<GR> mapper,
                                              String... arguments);

    GenericRawResults<Object[]> queryRawOrFail(String query, DataType[] columnTypes,
                                               String... arguments);

    int executeRawOrFail(String statement, String... arguments);

    int updateRawOrFail(String statement, String... arguments);

    boolean objectsEqualOrFail(T data1, T data2);

    ID extractIdOrFail(T data);

    boolean isTableExistsOrFail();

    long countOfOrFail();

    long countOfOrFail(PreparedQuery<T> preparedQuery);

    <FT> ForeignCollection<FT> getEmptyForeignCollectionOrFail(String fieldName);

    void setObjectCacheOrFail(boolean enabled);

    void setObjectCacheOrFail(ObjectCache objectCache);

    T mapSelectStarRowOrFail(DatabaseResults results);

    GenericRowMapper<T> getSelectStarRowMapperOrFail();

}
