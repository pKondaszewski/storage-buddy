package pl.przemek.storage_buddy.file;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

class InMemoryFileInfoRepository implements FileInfoRepository {

    private final HashMap<UUID, FileInfo> db = new HashMap<>();

    @Override
    public void flush() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends FileInfo> S saveAndFlush(S entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends FileInfo> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllInBatch(Iterable<FileInfo> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllInBatch() {
        db.clear();
    }

    @Override
    public FileInfo getOne(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FileInfo getById(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FileInfo getReferenceById(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends FileInfo> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends FileInfo> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends FileInfo> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends FileInfo> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends FileInfo> long count(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends FileInfo> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends FileInfo, R> R findBy(
            Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends FileInfo> S save(S entity) {
        UUID id = UUID.randomUUID();
        db.put(id, entity);
        entity.setId(id);
        return entity;
    }

    @Override
    public <S extends FileInfo> List<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<FileInfo> findById(UUID uuid) {
        return Optional.of(db.get(uuid));
    }

    @Override
    public boolean existsById(UUID uuid) {
        return db.containsKey(uuid);
    }

    @Override
    public List<FileInfo> findAll() {
        return db.values().stream().toList();
    }

    @Override
    public List<FileInfo> findAllById(Iterable<UUID> uuids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        return db.size();
    }

    @Override
    public void deleteById(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(FileInfo entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends FileInfo> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<FileInfo> findAll(Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<FileInfo> findAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsByName(String name) {
        return db.values().stream().map(FileInfo::getName).anyMatch(name::equals);
    }
}
