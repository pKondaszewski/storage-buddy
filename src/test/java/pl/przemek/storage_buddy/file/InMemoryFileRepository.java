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

class InMemoryFileRepository implements FileRepository {

    private final HashMap<UUID, File> db = new HashMap<>();

    @Override
    public void flush() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends File> S saveAndFlush(S entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends File> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllInBatch(Iterable<File> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<UUID> uuids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllInBatch() {
        throw new UnsupportedOperationException();
    }

    @Override
    public File getOne(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public File getById(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public File getReferenceById(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends File> Optional<S> findOne(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends File> List<S> findAll(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends File> List<S> findAll(Example<S> example, Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends File> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends File> long count(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends File> boolean exists(Example<S> example) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends File, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends File> S save(S entity) {
        UUID id = UUID.randomUUID();
        db.put(id, entity);
        entity.setId(id);
        return entity;
    }

    @Override
    public <S extends File> List<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<File> findById(UUID uuid) {
        return Optional.of(db.get(uuid));
    }

    @Override
    public boolean existsById(UUID uuid) {
        return db.containsKey(uuid);
    }

    @Override
    public List<File> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<File> findAllById(Iterable<UUID> uuids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(UUID uuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(File entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends File> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<File> findAll(Sort sort) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<File> findAll(Pageable pageable) {
        throw new UnsupportedOperationException();
    }
}
